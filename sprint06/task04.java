import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * Suppose, we have the Person class with fields name and goShopping.
 * The goShopping field defines if Person will do shopping based on product name and discount that come as parameters.
 * You should define the next default behavior for goShopping:
 * return true if product name = "product1"  and discount > 10, otherwise return false.
 * Define the type for goShopping field and name it DecisionMethod and define a method decide in it.
 * Also, we have the class Shop with method sale().
 * This method informs users about a discount product and a percentage of discount by using their goShopping values
 * (which are stored in clients field). The method should return the count of users that will go shopping.
 */

/**
 * @author Bogdan Kurchak
 */
@FunctionalInterface
interface DecisionMethod {
    boolean decide(String product, int percent);
}

class Person{
    String name;
    DecisionMethod goShopping = (product, percent) ->
            Objects.equals(product, "product1") && percent > 10;

    Person(String name){
        this.name = name;
    }
}

class Shop{
    public List<DecisionMethod> clients = new ArrayList<>();

    public int sale(String product, int percent) {
        return (int) clients.stream()
                .filter(decisionMethod -> decisionMethod.decide(product, percent))
                .count();
    }
}
