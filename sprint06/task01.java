import java.util.Arrays;
import java.util.function.Predicate;

/*
 * Implement a static method getCount(...) that takes an array of integers as the first parameter.
 * The second parameter is a functional interface that works with integers and defines a some condition.
 * The method should return the count of elements in the array that satisfy the condition defined by the second argument.
 */

/**
 * @author Bogdan Kurchak
 */
public class MyUtils {
    public static int getCount(int[] integers, Predicate<Integer> condition) {
        return (int) Arrays.stream(integers)
                .boxed()
                .filter(condition)
                .count();
    }
}
