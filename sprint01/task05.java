/*
 * A Point class, which models a 2D point with x and y coordinates should contains:
 * Two private instance variables x and y  of int type.
 * A constructor that constructs a point with the given x and y coordinates.
 * A method getXYPair() which returns the x and y in a 2-element int array.
 * A method called distance(int x, int y) that returns the distance from this point to another point at the given (x, y) coordinates.
 * An overloaded distance(Point point) method that returns the distance from this point to the given Point instance.
 * Another overloaded distance() method that returns the distance from this point to the origin (0, 0)
 */

/**
 * @author Bogdan Kurchak
 */
class Point {
    private int x;
    private int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int[] getXYPair() {
        return new int[] {x, y};
    }
    
    public double distance(int x, int y) {
        return Math.hypot((x - this.x), (y - this.y));
    }
    
    public double distance(Point point) {
        int[] pair = point.getXYPair();
        return Math.hypot((pair[0] - x), (pair[1] - y));
    }
    
    public double distance() {
        return Math.hypot((-x), (-y));
    }
}
