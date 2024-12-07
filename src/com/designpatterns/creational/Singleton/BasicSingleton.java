package com.designpatterns.creational.singleton;

public class BasicSingleton {
    private static BasicSingleton instance;

    // Private constructor prevents instantiation
    private BasicSingleton() {}

    // Public method to provide access to the instance
    public static BasicSingleton getInstance() {
        if (instance == null) {
            instance = new BasicSingleton();
        }
        return instance;
    }

    public static void main(String[] args){
        BasicSingleton basicSingleton = BasicSingleton.getInstance();
        BasicSingleton basicSingleton1 = BasicSingleton.getInstance();
        System.out.println(basicSingleton == basicSingleton1);
    }

}
