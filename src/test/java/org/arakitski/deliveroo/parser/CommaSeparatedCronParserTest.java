package org.arakitski.deliveroo.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertArrayEquals;

class CommaSeparatedCronParserTest {

    private CommaSeparatedCronParser parser;

    @BeforeEach
    void setUp() {
        parser = new CommaSeparatedCronParser();
    }

    @Test
    void parse_commaSeparated() {
        int[] actual = parser.parse("1,4,6", 0, 8).stream()
                .mapToInt(i -> i)
                .toArray();

        Arrays.sort(actual);
        assertArrayEquals(new int[]{1,4,6}, actual);
    }

    @Test
    void parse_onlyOneNumber() {
        int[] actual = parser.parse("1", 0, 8).stream()
                .mapToInt(i -> i)
                .toArray();

        Arrays.sort(actual);
        assertArrayEquals(new int[]{1}, actual);
    }

    @Test
    void parse_numberHigherThenMax_throwException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> parser.parse("2,100,5", 1, 8))
                .withMessage("Value '100' should be int interval [1,7]");
    }

    @Test
    void parse_numberLowerThenMin_throwException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> parser.parse("3,5,0,6", 1, 8))
                .withMessage("Value '0' should be int interval [1,7]");
    }
}