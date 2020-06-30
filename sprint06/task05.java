import java.util.function.Predicate;
import java.util.Set;

/*
 * Implement a static method getPredicateFromSet(...) in MyUtils class.
 * The getPredicateFromSet method should take a Set of predicates working with integers as a parameter
 * and return a predicate with the functionality of all predicates in the set invoked and connected by logical AND.
 */

/**
 * @author Bogdan Kurchak
 */
class MyUtils{
    public static Predicate<Integer> getPredicateFromSet(Set<Predicate<Integer>> set) {
        return set.stream()
                .reduce(Predicate::and)
                .get();
    }
}
