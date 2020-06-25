/*
 * Create a class Plant, which includes private fields String name, Color color and Type type,
 * and constructor with three String parameters (String type, String color, String name)
 * where these fields are initialized. Create getters for all fields.
 * Color and Type are Enum.
 * Color contains White, Red, Blue entries.
 * Type contains Rare and Ordinary entries.
 * Override the method toString( ) for Plant class which returns result formatted like following:
 * {type: Rare, color: Red, name: MyPlant}
 * Create classes ColorException and TypeException as derived from Exception.
 * Both classes should have a constructor with one String parameter and pass this parameter to the base class.
 * The constructor of Plant should throw a corresponding exception whenever an inappropriate parameter is passed.
 */

/**
 * @author Bogdan Kurchak
 */
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
