package org.arakitski.deliveroo.parser;

import java.util.HashSet;
import java.util.Set;

/**
 * Parse cron job interval parameter * /number. For example * /15 will return for minutes will return 0,15,30,45.
 * <p>
 * If parameter not in range for example * /75 will return only 0.
 */
class IntervalCronParse implements CronParser {

    @Override
    public Set<Integer> parse(String input, int min, int max) {
        int interval = parseIntFromString(input);
        if(interval<min && interval>=max){
            throw new IllegalArgumentException();
        }
        Set<Integer> result = new HashSet<>();
        for(int i=min;i<max;i+=interval){
            result.add(i);
        }
        return result;
    }

    private static int parseIntFromString(String arg) {
        return Integer.parseInt(arg.replaceAll("\\D+",""));
    }
}
