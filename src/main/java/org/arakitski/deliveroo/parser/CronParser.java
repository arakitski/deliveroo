package org.arakitski.deliveroo.parser;

import java.util.Set;

/**
 *
 */
interface CronParser {

    /**
     * Parse arguments for cron job to set of numbers. For example parse 2-7 string to set of exact numbers 2,3,4,5,6,7
     *
     * @param input string to parse
     * @param min   value, for example for weeks it 1
     * @param max   value, not included for example for weeks it is 8
     * @return set of numbers for string
     */
    Set<Integer> parse(String input, int min, int max);

    static int getAndValidate(String value, int min, int max) {
        int intValue = Integer.parseInt(value);
        if (intValue < 0) {
            throw new IllegalArgumentException("Value '" + intValue + "' should be positive");
        }
        if (intValue < min || intValue >= max) {
            throw new IllegalArgumentException("Value '" + intValue + "' should be int interval [" + min + "," + (max - 1) + "]");
        }
        return intValue;
    }
}
