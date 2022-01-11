package org.arakitski.deliveroo.parser;

import java.util.HashSet;
import java.util.Set;

/**
 * Parse cron job interval parameter * /number. For example * /15 will return for minutes will return 0,15,30,45.
 * <p>
 * If parameter not in range for example * /75 will return only 0.
 */
class StarCronParser implements CronParser{
    @Override
    public Set<Integer> parse(String input, int min, int max) {
        Set<Integer> result = new HashSet<>();
        for (int i = min; i < max; i++) {
            result.add(i);
        }
        return result;
    }
}
