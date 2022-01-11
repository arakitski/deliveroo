package org.arakitski.deliveroo.parser;

import java.util.HashSet;
import java.util.Set;

import static org.arakitski.deliveroo.parser.CronParser.getAndValidate;

/**
 * Parse cron job parameter separated by -. For example 2-4 will return 2,3,4.
 * <p>
 * If parameter not in range [min,max) throws IllegalArgumentException.
 */
class FromToCronParser implements CronParser{
    @Override
    public Set<Integer> parse(String input, int min, int max) {
        String[] split = input.split("-");
        if(split.length!=2){
            throw new IllegalArgumentException("Incorrect interval argument :" + input);
        }
        Set<Integer> result = new HashSet<>();
        int from = getAndValidate(split[0], min, max);
        int to = getAndValidate(split[1], min, max);
        validateFromTo(from, to);
        for (int i = from; i <= to; i++) {
            result.add(i);
        }
        return result;
    }

    private static void validateFromTo(int from, int to) {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Values [" + from + "," + to + "] should be positive");
        }
        if (from > to) {
            throw new IllegalArgumentException("Input validation fails. Value " + from + " should be more then " + to);
        }
    }
}
