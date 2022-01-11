package org.arakitski.deliveroo;

import java.util.Set;
import java.util.stream.Collectors;

import static org.arakitski.deliveroo.parser.CronExpressionParser.parseCron;

/**
 * Write a command line application or script which parses a cron string and expands each field
 * to show the times at which it will run.
 * <p>
 * The cron string will be passed to your application as a single argument.
 * <p>
 * For example, the following input argument:
 * * /15 0 1,15*1-5/usr/bin/find
 * <p>
 * * * * * * command to be executed
 * – – – – –
 * | | | | |
 * | | | | +—– day of week (0 – 6) (Sunday=0)
 * | | | +——- month (1 – 12)
 * | | +——— day of month (1 – 31)
 * | +———– hour (0 – 23)
 * +————- min (0 – 59)
 */
public class App {

    private static final String OUTPUT_FORMAT = "%-13s %s";

    public static void main(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException("Input parameters are not set, please set minutes, " +
                    "hours, days of month, months, days of weeks and command");
        }
        String[] splitArgs = args[0].split(" ");
        if (splitArgs.length != 6) {
            throw new IllegalArgumentException("Wrong number of parameters - should be 6: " +
                    "minutes, hours, days of month, months, days of weeks and command");
        }
        CronExpression cronExpression = CronExpression.builder()
                .minutes(parseCron(splitArgs[0], 0, 60))
                .hours(parseCron(splitArgs[1], 0, 24))
                .daysOfMonth(parseCron(splitArgs[2], 1, 32))
                .months(parseCron(splitArgs[3], 1, 13))
                .daysOfWeeks(parseCron(splitArgs[4], 1, 8))
                .command(splitArgs[5])
                .build();
        print(cronExpression);
    }

    private static void print(CronExpression cronExpression) {
        println("minute", cronExpression.getMinutes());
        println("hour", cronExpression.getHours());
        println("day of month", cronExpression.getDaysOfMonth());
        println("month", cronExpression.getMonths());
        println("day of week", cronExpression.getDaysOfWeeks());
        print("command", cronExpression.getCommand());
    }

    private static void println(String name, Set<Integer> set) {
        print(name, set.stream().map(Object::toString).collect(Collectors.joining(" ")));
        System.out.println();
    }

    private static void print(String name, String value) {
        System.out.format(OUTPUT_FORMAT, name, value);
    }
}
