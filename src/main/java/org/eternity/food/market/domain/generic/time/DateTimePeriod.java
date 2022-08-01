package org.eternity.food.market.domain.generic.time;

import java.time.LocalDateTime;

public class DateTimePeriod {
    private LocalDateTime from;
    private LocalDateTime to;

    public static DateTimePeriod between(LocalDateTime from, LocalDateTime to) {
        return new DateTimePeriod(from, to);
    }

    public DateTimePeriod(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    DateTimePeriod() {}

    public boolean contains(LocalDateTime dateTime) {
        return (dateTime.isAfter(from) || dateTime.equals(from) &&
                dateTime.isBefore(to) || dateTime.equals(to));
    }
}
