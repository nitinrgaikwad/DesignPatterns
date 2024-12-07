package com.designpatterns.creational.singleton;

// Bill Pugh Singleton implementation
public class Logger {

    // Private constructor to prevent direct instantiation
    private Logger() {
        // Normally initialization code would go here (e.g., setting up resources)
        System.out.println("Logger instance created.");
    }

    // Static inner class for Bill Pugh Singleton implementation
    private static class LoggerHelper {
        // The instance of Logger is created only when this class is loaded
        private static final Logger INSTANCE = new Logger();
    }

    // Public method to access the singleton instance
    public static Logger getInstance() {
        // The first call to getInstance() will create the instance, subsequent calls will return the same instance
        return LoggerHelper.INSTANCE;
    }

    // Logging method to output log messages
    public void log(String message) {
        System.out.println("LOG: " + message);
    }

    public static void main(String[] args) {
        // Thread 1: Attempt to access the Logger instance
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1: Attempting to get the logger instance...");
            Logger logger = Logger.getInstance(); // This will create the Logger instance
            logger.log("Thread 1 logging..."); // Log message from Thread 1
        });

        // Thread 2: Attempt to access the Logger instance
        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2: Attempting to get the logger instance...");
            Logger logger = Logger.getInstance(); // This will use the already created Logger instance
            logger.log("Thread 2 logging..."); // Log message from Thread 2
        });

        // Start both threads
        t1.start();
        t2.start();
    }
}