import java.util.*;
import java.util.stream.Collectors;

/*
 * Create interface DrinkReceipt with methods String getName()
 * and DrinkReceipt addComponent(String componentName, int componentCount).
 * Create interface DrinkPreparation with method Map<String, Integer> makeDrink() to return a drink components.
 * Create interface Rating with method int getRating().
 * Class Caffee contains fields String name, int rating, Map of ingredients and implements interfaces DrinkReceipt,
 * DrinkPreparation and Rating. Method makeDrink() prepare coffee with typically components: {Water=100, Arabica=20}.
 * Espresso and Cappuccino classes extends the Caffee Class and override method makeDrink().
 * Espresso caffee has 50 g. of Water. Cappuccino caffee has an additional of 50 g. of Milk.
 * Create a averageRating() method of the MyUtils class
 * to return a Map with coffee name as key and coffee average rating as value.
 * For example, for a given list
 * [Espresso [name=Espresso, rating=8],
 * Cappuccino [name=Cappuccino, rating=10],
 * Espresso [name=Espresso, rating=10],
 * Cappuccino [name=Cappuccino, rating=6],
 * Caffee [name=Caffee, rating=6]]
 * you should get
 * {Espresso=9.00, Cappuccino=8.00, Caffee=6.00}
 */

/**
 * @author Bogdan Kurchak
 */
interface DrinkReceipt {
    String getName();
    DrinkReceipt addComponent(String componentName, int componentCount);
}

interface DrinkPreparation {
    Map<String, Integer> makeDrink();
}

interface Rating {
    int getRating();
}

class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
    private String name;
    private int rating;
    private Map<String, Integer> ingredients = new HashMap<>();

    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRating() {
        return rating;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public DrinkReceipt addComponent(String componentName, int componentCount) {
        this.ingredients.put(componentName, componentCount);
        return this;
    }

    @Override
    public Map<String, Integer> makeDrink() {
        ingredients.put("Water", 100);
        ingredients.put("Arabica", 20);
        return ingredients;
    }

    @Override
    public String toString() {
        return "Caffee=" + rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Caffee)) return false;
        Caffee caffee = (Caffee) o;
        return getRating() == caffee.getRating() &&
                Objects.equals(getName(), caffee.getName()) &&
                Objects.equals(ingredients, caffee.ingredients);
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getRating();
        result = 31 * result + makeDrink().hashCode();
        return result;
    }
}

class Espresso extends Caffee {

    public Espresso(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        getIngredients().put("Water", 50);
        getIngredients().put("Arabica", 20);

        return getIngredients();
    }

    @Override
    public String toString() {
        return "Espresso=" + super.getRating();
    }
}

class Cappuccino extends Caffee {

    public Cappuccino(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        getIngredients().put("Water", 100);
        getIngredients().put("Arabica", 20);
        getIngredients().put("Milk", 50);

        return getIngredients();
    }

    @Override
    public String toString() {
        return "Cappuccino=" + super.getRating();
    }
}

class MyUtils {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        if (coffees == null || coffees.isEmpty())
            return new HashMap<String, Double>();
        
        return coffees.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Caffee::getName,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Caffee::getRating)));
    }
}
