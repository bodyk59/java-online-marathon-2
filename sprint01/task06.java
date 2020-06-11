/* 
 * Create a Product class with fields name of type String and price of type double.
 * The Product class must meet the following principles:
 * All class fields must be private.
 * Get and set methods must be used to access the class fields.
 * The class must have a constructors with and without parameters.
 * In the Product class write count() static method that returns count of created objects of Product type.
 */

/**
 * @author Bogdan Kurchak
 */
class Product {
    private String name;
    private double price;
    private static int counter;
    
    public Product() {
        counter++;
    }
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        counter++;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
    
    public static int count() {
        return counter;
    }
}
