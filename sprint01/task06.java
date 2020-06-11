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
