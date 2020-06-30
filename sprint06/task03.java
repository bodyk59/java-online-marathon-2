import java.util.function.BinaryOperator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Add to App class static field greetingOperator of type BinaryOperator.
 * The greetingOperator should create a new string as a result by the rule:
 * "Hello " + parameter1 + " " + parameter2 + "!!!"
 * Create a static method createGreetings(...) that takes two parameters:
 * List<Person> and BinaryOperator and generates List<String> as a result.
 * We should be able to pass greetingOperator as a parameter here.
 * Please, use the second parameter in creating the result.
 */

/**
 * @author Bogdan Kurchak
 */
class App {
    public static BinaryOperator<String> greetingOperator = (parameter1, parameter2) ->
            String.format("Hello %s %s!!!", parameter1, parameter2);

    public static List<String> createGreetings(List<Person> people, BinaryOperator<String> binaryOperator){
        return people.stream()
                .map(person -> binaryOperator.apply(person.name, person.surname))
                .collect(Collectors.toList());
    }
}

class Person {
    String name;
    String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
