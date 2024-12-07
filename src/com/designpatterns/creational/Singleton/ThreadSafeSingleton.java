package com.designpatterns.creational.singleton;

public class ThreadSafeSingleton {

    // The single instance of the class
    private static ThreadSafeSingleton instance;

    // Private constructor to prevent direct instantiation
    private ThreadSafeSingleton() {}

    // Method to return the singleton instance
    public static ThreadSafeSingleton getInstance() {
        // First check (without synchronization) for performance optimization
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) { // Lock only when needed
                // Second check to ensure the instance was not created while waiting for the lock
                if (instance == null) {
                    instance = new ThreadSafeSingleton(); // Lazy initialization
                }
            }
        }
        return instance;
    }

    // Example method to demonstrate the singleton functionality
    public void log(String message) {
        System.out.println("LOG: " + message);
    }

    // Main method to test the singleton behavior
    public static void main(String[] args) {
        // Thread 1: Attempt to access the singleton instance
        Thread t1 = new Thread(() -> {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
            singleton.log("Thread 1 logging..."); // Log message from Thread 1
        });

        // Thread 2: Attempt to access the singleton instance
        Thread t2 = new Thread(() -> {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
            singleton.log("Thread 2 logging..."); // Log message from Thread 2
        });

        // Start both threads
        t1.start();
        t2.start();

        // Ensure both threads complete
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test the singleton property
        ThreadSafeSingleton singleton1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton singleton2 = ThreadSafeSingleton.getInstance();

        // Output: true, because both references should point to the same instance
        System.out.println(singleton1 == singleton2);
    }
}
