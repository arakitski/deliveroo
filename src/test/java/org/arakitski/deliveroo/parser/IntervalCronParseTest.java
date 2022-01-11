package org.arakitski.deliveroo.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

class IntervalCronParseTest {

    private IntervalCronParse parser;

    @BeforeEach
    void setUp() {
        parser = new IntervalCronParse();
    }

    @Test
    void parseInterval() {
        int[] actual = parser.parse("*/15", 0, 60).stream()
                .mapToInt(i -> i)
                .toArray();

        Arrays.sort(actual);
        assertArrayEquals(new int[]{0, 15, 30, 45}, actual);
    }

    @Test
    void parseInterval_higherThenMax_returnZero() {
        int[] actual = parser.parse("*/75", 0, 60).stream()
                .mapToInt(i -> i)
                .toArray();

        Arrays.sort(actual);
        assertArrayEquals(new int[]{0}, actual);
    }
}