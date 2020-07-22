package com.company;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    /**
     * This is mainly for my personal benefit.
     * Testing a stdlib method to verify its behavior
     */
    void splitString() {
        System.out.println("Empty String: ");
        String[] array = "".split(",");
        printArray(array);
        System.out.println("Size: " + array.length);

        System.out.println("Single Apple String: ");
        array = "apple".split(",");
        printArray(array);
        System.out.println("Size: " + array.length);

        System.out.println("Two Apples String: ");
        array = "apple, apple".split(",");
        printArray(array);
        System.out.println("Size: " + array.length);
    }

    void printArray(String[] strings) {
        for (String str : strings) {
            System.out.println(str);
        }
    }

    @Test
    void parseOrderString() {

        String input = "apple apple Apple";
        List<String> order = Main.parseOrderString(input);

        List<String> expected = new ArrayList<>();
        expected.add("apple");
        expected.add("apple");
        expected.add("apple");

        Assertions.assertEquals(
            expected,
            order
        );

        input = "apple Orange apple";
        order = Main.parseOrderString(input);
        expected.clear();
        expected.add("apple");
        expected.add("orange");
        expected.add("apple");

        Assertions.assertEquals(
                expected,
                order
        );

    }

    @Test
    void getFormattedPriceStringFromCents() {
        long price = 100l;
        Assertions.assertEquals(
                "$1.00",
                Main.getFormattedPriceStringFromCents(price)
        );

        price = 120l;
        Assertions.assertEquals(
                "$1.20",
                Main.getFormattedPriceStringFromCents(price)
        );

        price = 20l;
        Assertions.assertEquals(
                "$0.20",
                Main.getFormattedPriceStringFromCents(price)
        );

        price = 5l;
        Assertions.assertEquals(
                "$0.05",
                Main.getFormattedPriceStringFromCents(price)
        );

        price = 0l;
        Assertions.assertEquals(
                "$0.00",
                Main.getFormattedPriceStringFromCents(price)
        );

        price = 1020l;
        Assertions.assertEquals(
                "$10.20",
                Main.getFormattedPriceStringFromCents(price)
        );
    }
}