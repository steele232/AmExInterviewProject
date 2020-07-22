package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // TODO optimize this with a string builder pattern.
        System.out.println("Welcome to AmEx Groceries.");
        System.out.println("Apples and Oranges are for sales.");
        System.out.println("Here are today's Prices:");
        System.out.println("- Apples: 60 cents");
        System.out.println("- Oranges: 25 cents");
        System.out.println("You may now order your items. ");
        System.out.println("Please enter a comma-delimited list of items or an empty line if you don't want to purchase anything. ");
        System.out.println("Here is an example: \"apple, apple, orange, orange\"");
        System.out.println("Now please enter your order: ");
        List<String> order = fetchOrderFromCLI();

    }

    private static List<String> fetchOrderFromCLI() {
        // fetch string
        String userInput = ""; // TODO write this

        List<String> order = new ArrayList<>();
        order = parseOrderString(userInput);
        return order;
    }

    // TODO
    private static List<String> parseOrderString(String userInput) {
        // split on commas

        // trim and toLowerCase (Stream opportunity)

        return null;
    }

    public static long add(long a, long b) {
        return a + b;
    }
}
