package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to AmEx Groceries.\n");
        sb.append("Apples and Oranges are for sales.\n");
        sb.append("Here are today's Prices:\n");
        sb.append("- Apples: 60 cents\n");
        sb.append("- Oranges: 25 cents\n");
        sb.append("You may now order your items. \n");
        sb.append("Please enter a space-delimited list of items or an empty line if you don't want to purchase anything. \n");
        sb.append("Here is an example: \"apple apple orange orange\"\n");
        sb.append("Now please enter your order: ");
        System.out.println(sb.toString());

        List<String> order = fetchOrderFromCLI();

        // failure state: empty string as input
        if (order.size() == 1 && order.get(0).equals("")) {
            System.out.println("Empty input. Come back again later!");
            return;
        }

        // failure state: product name not found
        List<Long> prices = new ArrayList<>();
        try {
            for (String item : order) {
                long priceInCents = getPriceInCents(item);
                prices.add(priceInCents);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getLocalizedMessage());
            return;
        }

        System.out.println("Here is your order: ");
        for (String item : order) {
            String cost = null;
            try {
                cost = getFormattedPriceStringFromCents(getPriceInCents(item));
            } catch (Exception e) {
                // this should happen because getPriceInCents has already been called on all inputs
                e.printStackTrace();
                return;
            }
//            String cost
            System.out.println("An " + item + " costs " + cost);
        }

        // Get discounts...
        // it will be easier to calculate discounts after the fact instead of trying to count it up as we go.
        long discountTotal = getDiscountTotal(order);

        // print out the total in the right format
        Long total = prices.stream().mapToLong(Long::longValue).sum() - discountTotal;
        String totalString = getFormattedPriceStringFromCents(total);
        System.out.println("Here is your total price: " + totalString);

        System.out.println("Thanks for shopping at AmEx Groceries. Have a nice day!");
    }

    private static List<String> fetchOrderFromCLI() {
        // fetch string
        Scanner cliScanner = new Scanner(System.in);
        String userInput = cliScanner.nextLine();

        List<String> order = new ArrayList<>();
        order = parseOrderString(userInput);
        return order;
    }

    public static List<String> parseOrderString(String userInput) {
        // split on commas
        String[] items = userInput.split(" ");

        // trim and toLowerCase
        return Stream.of(items)
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public static long getPriceInCents(String productName) throws Exception {
        switch (productName) {
            case "apple":
                return 60l;
            case "orange":
                return 25l;
            default:
                throw new Exception("Product name not found: " + productName);
        }
    }

    public static long getDiscountTotal(List<String> order) {

        // let's do a frequency count on apples and oranges.
        // and calculate discounts that way.

        Map<String, Integer> frequencies = new HashMap<>();
        for (String item : order) {
            if (frequencies.containsKey(item)) {
                frequencies.put(
                        item,
                        frequencies.get(item) + 1
                );
            } else {
                frequencies.put(
                        item,
                        1
                );
            }
        }

        long discountTotal = 0l;

        // buy one get one free on apples
        if (frequencies.containsKey("apple")) {
            int numApples = frequencies.get("apple");
            int numAppleDiscounts = numApples / 2;
            discountTotal += numAppleDiscounts * 60;
        }

        // 3 for the price of 2 on oranges
        if (frequencies.containsKey("orange")) {
            int numOranges = frequencies.get("orange");
            int numOrangeDiscounts = numOranges / 3;
            discountTotal += numOrangeDiscounts * 25;
        }

        return discountTotal;
    }

    public static String getFormattedPriceStringFromCents(long price) {
        long dollars = price / 100;
        long cents = price % 100;
        StringBuilder sb = new StringBuilder();
        sb.append("$");
        sb.append(dollars);
        sb.append(".");
        if (cents >= 10) {
            sb.append(cents);
        } else if (cents >= 1) {
            sb.append("0");
            sb.append(cents);
        } else {
            sb.append("00");
        }
        return sb.toString();
    }
}
