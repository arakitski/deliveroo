package org.arakitski.deliveroo.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertArrayEquals;

class FromToCronParserTest {

    private FromToCronParser parser;

    @BeforeEach
    void setUp() {
        parser = new FromToCronParser();
    }

    @Test
    void parseInterval() {
        int[] actual = parser.parse("2-5", 0, 8).stream()
                .mapToInt(i -> i)
                .toArray();

        Arrays.sort(actual);
        assertArrayEquals(new int[]{2, 3, 4, 5}, actual);
    }

    @Test
    void parse_fromHigherThenMax_throwException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> parser.parse("100-500", 1, 8))
                .withMessage("Value '100' should be int interval [1,7]");
    }

    @Test
    void parse_fromLowerThenMin_throwException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> parser.parse("0-5", 1, 8))
                .withMessage("Value '0' should be int interval [1,7]");
    }

    @Test
    void parse_toHigherThenMax_throwException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> parser.parse("1-100", 1, 8))
                .withMessage("Value '100' should be int interval [1,7]");
    }

    @Test
    void parse_fromHigherThenTo_throwException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> parser.parse("5-1", 1, 8))
                .withMessage("Input validation fails. Value 5 should be more then 1");
    }
}