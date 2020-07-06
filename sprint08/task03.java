import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/*
 * You need to implement the methods of the Interactor class so that output will look like this:
 * Serving thread running
 * Serving thread initializes the key
 * key = -7
 * Consuming thread received the key. key = -7
 * Consuming thread changed the key. key = 33
 * Serving thread resumed
 *
 * serve method should initialize x field with applied its first parameter to the second one.
 * And the method should print the messages only about its own actions.
 *
 * counsume method should wait until serve initializes x field and then change x by assigning
 * it the result of applying the method's first parameter to the second and the third ones.
 * This method also prints the messages only about its own actions.
 * Assume that consume() should be able to execute without serve() after waiting for 3 seconds.
 *
 * Use synchronized blocks (or methods), wait() and notify() methods for the implementation.
 */

/**
 * @author Bogdan Kurchak
 */
class Interactor {
    int x;

    public synchronized void serve(UnaryOperator<Integer> uo, int initializer) throws InterruptedException{
       this.x = uo.apply(initializer);
       System.out.println("Serving thread running");
       System.out.println("Serving thread initializes the key");
       System.out.println("key = " + this.x);
       notify();
       wait();
       System.out.println("Serving thread resumed");
    }

    public synchronized void consume(BinaryOperator<Integer> bo, int operand1) throws InterruptedException{
        wait(3000);
        System.out.println("Consuming thread received the key. key = " + this.x);
        this.x = bo.apply(this.x, operand1);
        System.out.println("Consuming thread changed the key. key = " + this.x);
        notify();
    }
}

class ThreadExample {
    public static void threadRun() {
        Interactor interactor = new Interactor();

        Thread t1 = new Thread(() -> {
            try {
                interactor.serve(x -> -x + 4, 11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                interactor.consume((a, b) -> a + 2 * b, 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            t2.start();
            t1.start();
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
