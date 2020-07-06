/*
 * Please, fix the problem with the preloaded implementation of the class MyThread.
 * Test starts both threads with the code:
 * MyThreads.t1.start();
 * MyThreads.t2.start();
 * The goal of each thread is to update fields m and n and not switch between threads
 * while in the loop. You need to get an output like this:
 * Thread1 n = 0
 * Thread1 n = 1
 * Thread1 n = 2
 * Thread1 n = 3
 * Thread1 n = 4
 * Thread2 m = 0
 * Thread2 m = 1
 * Thread2 m = 2
 * Thread2 m = 3
 * Thread2 m = 4
 * Thread2 n = 5
 * Thread2 n = 6
 * Thread2 n = 7
 * Thread2 n = 8
 * Thread2 n = 9
 * Thread2 success!
 * Thread1 m = 5
 * Thread1 m = 6
 * Thread1 m = 7
 * Thread1 m = 8
 * Thread1 m = 9
 * Thread1 success!
 */

/**
 * @author Bogdan Kurchak
 */
class MyThreads {
    public final static Object den = new Object();
    public final static Object ada = new Object();

    public static int n;
    public static int m;

    public static Thread t1 = new Thread() {
        public void run() {
            synchronized (den) {
                for(int i = 0; i < 5; i++, n++)
                    System.out.println("Thread1 n = " + n);
            }
            synchronized (ada) {
                ada.notify();
                try {
                    ada.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i < 5; i++, m++)
                    System.out.println("Thread1 m = " + m);
                System.out.println( "Thread1 success!");
            }
        }
    };

    public static Thread t2 = new Thread() {
        public void run() {
            synchronized (ada) {
                try {
                    ada.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i < 5; i++, m++)
                    System.out.println("Thread2 m = " + m);

                synchronized (den) {
                    for(int i = 0; i < 5; i++, n++)
                        System.out.println("Thread2 n = " + n);
                    System.out.println("Thread2 success!");
                }
                ada.notify();
            }
        }
    };
}
