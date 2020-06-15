import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * Please create class Shape with abstract method to calculate area of figure and field name.
 * Replace code in method getArea() according to principles of polymorphism i.e.
 * Circle and Rectangle classes extends Shape class and override double getArea() method.
 * Develop maxAreas() method of the MyUtils class to return a List with instances of maximum area.
 * The original list must be unchanged.
 * For example, for a given list
 * [Circle [radius=2.00],
 * Rectangle [height=2.00, width=3.00],
 * Circle [radius=1.00],
 * Rectangle [height=3.00, width=2.00],
 * Circle [radius=0.50],
 * Rectangle [height=1.00, width=2.00]]
 * you should get
 * [Circle [radius=2.00],
 * Rectangle [height=2.00, width=3.00],
 * Rectangle [height=3.00, width=2.00]]
 */

/**
 * @author Bogdan Kurchak
 */
abstract class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getArea();
}

class Circle extends Shape {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Circle [radius=" + radius
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.getRadius(), getRadius()) == 0;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = (int) (31 * result + getRadius());
        result = (int) (31 * result + getArea());
        return result;
    }
}

class Rectangle extends Shape  {
    private double height;
    private double width;

    public Rectangle(String name, double height, double width) {
        super(name);
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public String toString() {
        return "Rectangle [height=" + height
                + ", width=" + width
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.getHeight(), getHeight()) == 0 &&
                Double.compare(rectangle.getWidth(), getWidth()) == 0;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = (int) (31 * result + getHeight());
        result = (int) (31 * result + getWidth());
        result = (int) (31 * result + getArea());
        return result;
    }
}

class MyUtils {
    public List<Shape> maxAreas(List<Shape> shapes) {
        if (shapes == null || shapes.isEmpty())
            return new ArrayList<>();
        
        double maxCircleArea = shapes.stream()
                .filter(Objects::nonNull)
                .filter(Circle.class::isInstance)
                .mapToDouble(Shape::getArea)
                .max()
                .orElse(0.0);

        double maxRectangleArea = shapes.stream()
                .filter(Objects::nonNull)
                .filter(Rectangle.class::isInstance)
                .mapToDouble(Shape::getArea)
                .max()
                .orElse(0.0);

        Predicate<Shape> maxValue = shape -> shape instanceof Circle && shape.getArea() == maxCircleArea ||
                shape instanceof Rectangle && shape.getArea() == maxRectangleArea;

        return shapes.stream()
                .distinct()
                .filter(maxValue)
                .collect(Collectors.toList());
    }
}
