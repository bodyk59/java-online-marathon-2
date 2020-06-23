/*
 * In the class ArrayUtil write a public static generic
 * method named "setAndReturn(...)" to modify
 * and return the element in an array from the given position.
 */

/**
 * @author Bogdan Kurchak
 */
class ArrayUtil {
    public static <T> T setAndReturn(T[] array, T element, int position) {
        return array[position] = element;
    }
}
