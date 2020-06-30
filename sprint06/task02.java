import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;

/*
 * Create the static field cons of type Consumer and assign it the lambda expression that takes an array
 * of doubles as a parameter and changes it using the next rule: all values that are greater
 * than 2 should be multiplied by 0.8, and other values should be multiplied by 0.9.
 * Also implement a static method getChanged(...) that takes an array of doubles as a first parameter
 * and Consumer implementation as a second. The method should return an array changed by the second parameter.
 * The getChanged(...) method should not change initial array.
 */

/**
 * @author Bogdan Kurchak
 */
class App {
    private static final DoubleUnaryOperator greaterOrLessThanTwo = i ->
            (i > 2) ? (i * 0.8) : (i * 0.9);

    public static Consumer<double[]> cons = arrays -> {
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = greaterOrLessThanTwo.applyAsDouble(arrays[i]);
        }
    };

    public static double[] getChanged(double[] initialArray, Consumer<double[]> consumer) {
        double[] copy = Arrays.copyOf(initialArray, initialArray.length);
        consumer.accept(copy);
        return copy;
    }
}
