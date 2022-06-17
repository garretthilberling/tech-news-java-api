package com.technews.technewsjavaapi;

public class VariablesPractice {
    public static void main(String[] args) {
        int a = 10;
        double b = .99;
        String c = "doggo";
        // calculated sum is a double
        double sum = a + b;
        // calculated variable concat is a String
        String concat = a + c;

        System.out.println(sum); // 10.99
        System.out.println(concat); // 10doggo
    }
}
