package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void add() {
        Assertions.assertEquals(4, Main.add(2, 2), "Main.add(2, 2) test failed");
        long a = 10l;
        long b = 20l;
        Assertions.assertEquals(30l, Main.add(a,b));
    }
}