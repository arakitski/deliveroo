package org.arakitski.deliveroo;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
public class CronExpression {
    private final Set<Integer> minutes;
    private final Set<Integer> hours;
    private final Set<Integer> daysOfMonth;
    private final Set<Integer> months;
    private final Set<Integer> daysOfWeeks;
    private final String command;
}
