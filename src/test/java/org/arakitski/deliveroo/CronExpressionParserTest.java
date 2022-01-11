package org.arakitski.deliveroo;

import org.arakitski.deliveroo.parser.CronExpressionParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

public class CronExpressionParserTest {
    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of("*", new int[]{1, 2, 3, 4, 5, 6, 7}),
                Arguments.of("*/2", new int[]{1, 3, 5, 7}),
                Arguments.of("1,2,4", new int[]{1, 2, 4}),
                Arguments.of("2-5", new int[]{2, 3, 4, 5}),
                Arguments.of("3", new int[]{3})
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, int[] expected) {
        int[] actual = CronExpressionParser.parseCron(input, 1, 8).stream()
                .mapToInt(i -> i)
                .toArray();

        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }
}
