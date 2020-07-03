import java.lang.annotation.*;
import java.time.LocalDate;
import java.util.Objects;

/*
 * Create annotation Review with two string elements: reviewer and date.
 * Element date has default string value today.
 * This annotation can be applied to class.
 * When we execute static method review(String className) of class Util, the result of this method will be printed:
 * "Class <ClassName> was reviewed <date in format yyyy-mm-dd> by <reviewer>." to standard output (console).
 * If the class <className> isn’t annotated we show message: "Class <ClassName> isn't marked as Reviewed".
 * If the class was not found we show message: "Class <ClassName> was not found".
 */

/**
 * @author Bogdan Kurchak
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Review {
    String reviewer();
    String date() default "";
}

class Util {
    public static void review(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println(String.format("Class %s was not found", className));
        }

        if (Objects.nonNull(clazz)) {
            if (clazz.isAnnotationPresent(Review.class)) {
                Review reviewAnnotation = clazz.getAnnotation(Review.class);
                String date = reviewAnnotation.date();
                System.out.println(String.format("Class %s was reviewed %s by %s.",
                        className,
                        date.equals("today") ? LocalDate.now() : date,
                        reviewAnnotation.reviewer()));
            } else {
                System.out.println(String.format("Class %s isn't marked as Reviewed", className));
            }
        }
    }
}
