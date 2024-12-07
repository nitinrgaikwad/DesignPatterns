import com.designpatterns.creational.singleton.Logger;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1: Attempting to get the logger instance...");
            Logger logger = Logger.getInstance(); // Will block on the first call until initialization is complete
            logger.log("Thread 1 logging...");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2: Attempting to get the logger instance...");
            Logger logger = Logger.getInstance(); // Will block until the first thread finishes initializing the instance
            logger.log("Thread 2 logging...");
        });

        t1.start();
        t2.start();
    }
}