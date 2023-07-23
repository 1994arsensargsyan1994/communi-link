package com.arsensargsyan.communi.link.common;

import java.time.Duration;
import java.time.LocalDateTime;

public record DataRange(LocalDateTime start, LocalDateTime end) {

    public static final LocalDateTime MAX_DATE = LocalDateTime.parse("2099-12-31T23:59:59");

    public static boolean duration(LocalDateTime start, LocalDateTime end, int days) {
        return !Duration.between(start, end).minusDays(days).isNegative();
    }
}
