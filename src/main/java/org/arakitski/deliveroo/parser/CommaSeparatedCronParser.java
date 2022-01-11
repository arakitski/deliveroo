package org.arakitski.deliveroo.parser;

import java.util.HashSet;
import java.util.Set;

import static org.arakitski.deliveroo.parser.CronParser.getAndValidate;

/**
 * Parse cron job parameter separated by comma of single parameter. For example 2,3,4 or 5.
 * <p>
 * If parameter not in range [min,max) throws IllegalArgumentException.
 */
class CommaSeparatedCronParser implements CronParser {

    @Override
    public Set<Integer> parse(String input, int min, int max) {
        Set<Integer> result = new HashSet<>();
        String[] split = input.split(",");
        for (String str : split) {
            result.add(getAndValidate(str, min, max));
        }
        return result;
    }
}
