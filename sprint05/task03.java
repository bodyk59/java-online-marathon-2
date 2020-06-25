/*
 * Suppose, we have class Plant from the task2, which includes private fields String name, Color color and Type type,
 * and constructor with three String parameters where these fields are initialized. Color and Type are Enum.
 * Color contains White, Red, Blue entries.
 * Type contains Rare and Ordinary entries.
 * And we have classes ColorException and TypeException as derived from Exception.
 * The constructor of Plant throws a corresponding exception whenever an inappropriate parameter is passed:
 * String type, String color, String name.
 * Write a static tryCreatePlant method that takes 3 string parameters - type, color and name and returns an instance
 * of Plant, created based on passed params. (Don't create any classes, write as if you are already inside a class.)
 * The tryCreatePlant method should catch exceptions that can be thrown by Plant constructor. If inappropriate type
 * passed as a parameter, a Plant object should be returned anyway, with Ordinary type. If an inappropriate color
 * is passed, set Red color for created instance.
 */

/**
 * @author Bogdan Kurchak
 */
class MyUtils {
    public static Plant tryCreatePlant(String type, String color, String name) throws TypeException, ColorException{
        try {
            return new Plant(type.toUpperCase(), color.toUpperCase(), name);
        } catch (TypeException typeException) {
            try {
               return new Plant(Type.ORDINARY.name(), color.toUpperCase(), name);
            } catch (ColorException colorException) {
                return new Plant(Type.ORDINARY.name(), Color.RED.name(), name);
            }
        } catch (ColorException colorException) {
            try {
                return new Plant(type.toUpperCase(), Color.RED.name(), name);
            } catch (TypeException typeException) {
                return new Plant(Type.ORDINARY.name(), Color.RED.name(), name);
            }
        }
    }
}

enum Color {
    WHITE, RED, BLUE
}

enum Type {
    RARE, ORDINARY
}

class Plant {
    private String name;
    private Color color;
    private Type type;

    public Plant(String type, String color, String name) throws ColorException, TypeException {
        try {
            this.type = Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TypeException(String.format("Invalid value %s for field type", type));
        }

        try {
            this.color = Color.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ColorException(String.format("Invalid value %s for field color", color));
        }

        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("{type: %s, color: %s, name: %s}", type, color, name);
    }
}

class ColorException extends Exception {
    public ColorException(String message) {
        super(message);
    }
}

class TypeException extends Exception {
    public TypeException(String message) {
        super(message);
    }
}
