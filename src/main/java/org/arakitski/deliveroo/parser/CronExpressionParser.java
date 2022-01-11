package org.arakitski.deliveroo.parser;

import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;


public class CronExpressionParser {

    private static final Map<String, CronParser> patternToParserMap = Map.ofEntries(
            entry("^\\*$", new StarCronParser()),
            entry("^\\d+(,\\d+)*$", new CommaSeparatedCronParser()),
            entry("^\\d+-\\d+$", new FromToCronParser()),
            entry("^\\*/\\d+$", new IntervalCronParse())
    );

    public static Set<Integer> parseCron(String input, int min, int max) {
        for (Map.Entry<String, CronParser> entry : patternToParserMap.entrySet()) {
            if (input.matches(entry.getKey())) {
                return entry.getValue().parse(input, min, max);
            }
        }
        throw new IllegalArgumentException("Can't parse input : " + input);
    }


}
