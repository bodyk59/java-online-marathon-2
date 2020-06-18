/*
 * Using anonymous classes concept, call the execute method 4 times with different strategy
 * (override method doOperation from Strategy interface):
 * Add a to b   \\   (a + b)
 * Subtract b from a   \\   (a - b)
 * Multiply a by b   \\   (a * b)
 * Divide a by b   \\   (a / b)
 */

/**
 * @author Bogdan Kurchak
 */
class MyUtils {
    interface Strategy {
        double doOperation(int a, int b);
    }

    public static void execute(int a, int b, Strategy strategy) {
        double result = strategy.doOperation(a, b);
        System.out.println(result);
    }

    public static void addAtoB(int a, int b) {
        execute(a, b, new Strategy() {
            @Override
            public double doOperation(int a, int b) {
                return (double) a + b;
            }
        });
    }

    public static void subtractBfromA(int a, int b) {
        execute(a, b, new Strategy() {
            @Override
            public double doOperation(int a, int b) {
                return (double) a - b;
            }
        });
    }

    public static void multiplyAbyB(int a, int b) {
        execute(a, b, new Strategy() {
            @Override
            public double doOperation(int a, int b) {
                return (double) a * b;
            }
        });
    }

    public static void divideAbyB(int a, int b) {
        execute(a, b, new Strategy() {
            @Override
            public double doOperation(int a, int b) {
                return (double) a / b;
            }
        });
    }
}
