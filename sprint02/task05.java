import java.util.List;
import java.util.Objects;

/*
 * Create classes Square and Rectang with method (double getPerimeter()) for calculating of perimeter.
 * Find solution for avoiding of duplicate code.
 * Create a double sumPerimeter(List<?> firures) method of the MyUtils class to return a sum perimeters of all figures.
 * For example, for a given list
 * [[Square [width=4.00], Square [width=5.00], Rectang [height=2.00, width=3.00]]
 * you should get 46
 */

/**
 * @author Bogdan Kurchak
 */
abstract class Shapes {
    private double width;

    public Shapes(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public abstract double getPerimeter();
}

class Rectang extends Shapes {
    private double height;

    public Rectang(double width, double height) {
        super(width);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (getWidth() + getHeight());
    }
}

class Square extends Shapes{
    public Square(double width) {
        super(width);
    }

    @Override
    public double getPerimeter() {
        return 4 * getWidth();
    }
}

class MyUtils {
    public double sumPerimeter(List<?> figures) {
        return figures.stream()
                .filter(Objects::nonNull)
                .filter(element -> element instanceof Shapes)
                .map(element -> (Shapes) element)
                .map(Shapes::getPerimeter)
                .reduce(0.0, Double::sum);
    }
}
