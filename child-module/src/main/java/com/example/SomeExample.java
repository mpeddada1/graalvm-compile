package com.example;

public class SomeExample {

    public void printHello(){
        System.out.println("Hi I'm SomeExample");
        SomeExampleInnerClass.printInnerHello();
    }

    private static class SomeExampleInnerClass {
        public static void printInnerHello(){
            System.out.println("Hi I'm SomeExampleInnerClass");
        }
    }
}
