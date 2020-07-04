import java.lang.annotation.*;
import java.lang.reflect.*;

/*
 * Create single-value annotation TestSuite with default element that returns array of strings.
 * Create class TestSuitHandler with static method run(Class clazz).
 * This method invokes all public non-static zero args methods.
 * This method print to console information about all executed methods in form:
 * -- Method <class>.<methodname> started --
 * <result of  methodname invocation>
 * -- Method <class><methodname> finished --
 * (before -- should printed tab character)
 * If clazz doesn't contain the <methodName> defined in annotation information Method with name <methodName>
 * doesn't exists or not public in class clazz should be displayed.
 * If clazz is not marked with TestSuite annotation message Class clazz isn't annotated  should be displayed.
 * For example
 * We have
 * @TestSuite({"m1", "m2"})
 * class Class1{
 *     public void m2(){
 *         System.out.println("Hello");
 *     }
 * }
 * We run
 * TestSuiteHandler.run(Class1.class);
 * As a result we have
 * Method with name m1 doesn't exists or not public in class Class1
 * -- Method Class1.method2 started--
 * Hello
 * -- Method Class1.method2 finished--
 */

/**
 * @author Bogdan Kurchak
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TestSuite {
    String[] value();
}

class TestSuitHandler {
    public static void run(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(TestSuite.class)) {
            System.out.println(String.format("Class %s isn't annotated", clazz.getSimpleName()));
            return;
        }

        String[] values = clazz.getAnnotation(TestSuite.class).value();

        Object object = null;
        try {
            object = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException |
                NoSuchMethodException |
                InvocationTargetException |
                IllegalAccessException e) {
            e.printStackTrace();
        }

        for (String value : values) {
            Method method;
            try {
                method = clazz.getMethod(value);
            } catch (NoSuchMethodException e) {
                System.out.println(String.format("Method with name %s doesn't exists or not public in class %s",
                        value,
                        clazz.getSimpleName()));
                continue;
            }

            boolean isSuitable = Modifier.isPublic(method.getModifiers())
                    && !Modifier.isStatic(method.getModifiers())
                    && method.getParameters().length == 0;

            if (!isSuitable) {
                continue;
            }

            try {
                System.out.println(String.format("\t -- Method %s.%s started --", clazz.getSimpleName(), value));
                method.setAccessible(true);
                method.invoke(object);
                System.out.println(String.format("\t -- Method %s.%s finished -- ", clazz.getSimpleName(), value));
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.getMessage();
            }
        }
    }
}
