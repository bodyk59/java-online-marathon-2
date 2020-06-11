/*
 * Create a new version of the Circle class where the draw method will be overloaded four times:
 * The version without parameters.
 * Using the one parameter color of String type.
 * Using the one parameter scale of float type.
 * Using two parameters color and scale of String and float type.
 */

/**
 * @author Bogdan Kurchak
 */
class Circle {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    public void draw() {}
    
    public void draw(String color) {}
    
    public void draw(float scale) {}
    
    public void draw(String color, float scale) {}
}
