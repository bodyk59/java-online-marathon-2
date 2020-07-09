/*
 * Write a method to get the date n-years m-months and k-days after today using new DateTime API.
 * Return the obtained date in the format ISO_LOCAL_DATE.
 */
 
/**
 * @author Bohdan Kurchak
 */
public static String getDateAfterToday(int years, int months, int days) {
    return LocalDate.now()
                .plusYears(years)
                .plusMonths(months)
                .plusDays(days)
                .toString();
}
