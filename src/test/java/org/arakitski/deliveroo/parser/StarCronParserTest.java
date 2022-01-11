package org.arakitski.deliveroo.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

class StarCronParserTest {

    private StarCronParser parser;

    @BeforeEach
    void setUp() {
        parser = new StarCronParser();
    }

    @Test
    void parseFromTo() {
        int[] actual = parser.parse("*", 0, 8).stream()
                .mapToInt(i -> i)
                .toArray();

        Arrays.sort(actual);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 6}, actual);
    }


}