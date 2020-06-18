/*
 * Create public static inner class named PizzaBuilder inside Pizza class that correspond the class diagram.
 * Inside the cook method create and return an instance of Pizza class with your at least three favorite ingredients.
 */

/**
 * @author Bogdan Kurchak
 */
class Pizza {
    private String cheese;
    private String meat;
    private String seafood;
    private String vegetable;
    private String mushroom;

    private Pizza() { }

    public String getCheese() {
        return cheese;
    }
    
    public String getMeat() {
        return meat;
    }
    
    public String getSeafood() {
        return seafood;
    }
    
    public String getVegetable() {
        return vegetable;
    }
    
    public String getMushroom() {
        return mushroom;
    }

    public static PizzaBuilder base() {
        return new PizzaBuilder();
    }

    public static class PizzaBuilder {
        private Pizza pizza;

        private PizzaBuilder() {
            this.pizza = new Pizza();
        }

        public PizzaBuilder addCheese(String cheese) {
            this.pizza.cheese = cheese;
            return this;
        }

        public PizzaBuilder addMeat(String meat) {
            this.pizza.meat = meat;
            return this;
        }

        public PizzaBuilder addSeafood(String seafood) {
            this.pizza.seafood = seafood;
            return this;
        }

        public PizzaBuilder addVegetable(String vegetable) {
            this.pizza.vegetable = vegetable;
            return this;
        }

        public PizzaBuilder addMushroom(String mushroom) {
            this.pizza.mushroom = mushroom;
            return this;
        }

        public Pizza build() {
            return pizza;
        }
    }
}

class Oven {
    public static Pizza cook() {
        return Pizza.base()
                .addCheese("Parmesan")
                .addMeat("Pork")
                .addSeafood("Shrinks")
                .addVegetable("Tomato")
                .addMushroom("Truffle")
                .build();
    }
}
