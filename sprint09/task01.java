import java.time.LocalDate;

/*
 * Write a method to check if a year is a leap year or not, using for this the LocalDate class.
 * If a year is leap then method should return true, otherwise - false.
 */

/**
 * @author Bogdan Kurchak
 */
public static boolean isLeapYear(int year) {
    return LocalDate.of(year, 1, 1).isLeapYear();
}
