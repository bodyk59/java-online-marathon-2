import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/*
 * Create marker-annotation CamelCase which will check whether method named according to code conventions.
 * Create class CheckCamelCase for checking if all the annotated methods of some class satisfy naming pattern.
 * This class contains constant 'CAMELCASE_PATTERN' that introduces regex for checking method name.
 * Also this class contains method checkAndPrint(Class clazz) which returns true if all annotated methods
 * of class satisfy 'CAMELCASE_PATTERN' and prints to standard output information about all incorrect method names
 * by template: method <className>.<methodName> doesn't satisfy camelCase naming convention. For example
 * For class
 * public class Class1{
 * @CamelCase
 * public void correct(){}
 * @CamelCase
 * public void InCorrect(){}
 * public void JustMethod(){}
 * }
 * call CheckCamelCase.checkAndPrint(Class1.class)
 * prints to console
 * method Class1.InCorrect doesn't satisfy camelCase naming convention
 */

/**
 * @author Bogdan Kurchak
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CamelCase {}

class CheckCamelCase {
    public static final String CAMELCASE_PATTERN = "^[a-z]+([A-Z][a-z]+)*";

    public static boolean checkAndPrint(Class<?> clazz) {
        boolean result =  Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(CamelCase.class))
                .allMatch(method -> method.getName().matches(CAMELCASE_PATTERN));

        if (!result) {
            Arrays.stream(clazz.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(CamelCase.class))
                    .filter(method -> !method.getName().matches(CAMELCASE_PATTERN))
                    .forEach(method -> System.out.println(
                            String.format("method %s.%s doesn't satisfy camelCase naming convention",
                                    clazz.getSimpleName(),
                                    method.getName())));
        }

        return result;
    }
}

class ClassForAnnot {
    @CamelCase
    public static void example() {}
    @CamelCase
    public void Example() {}
    public static void _main(String[] args) {}
}

class Class1{
    @CamelCase
    public void correct(){}
    @CamelCase
    public void InCorrect(){}
    @CamelCase
    public void JustMethod(){}
}

class Class2{
    @CamelCase
    public void correct(){}
    @CamelCase
    public void oneMoreCorrect(){}
}
