package com.nhnacademy;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Time {
    private final LocalDateTime dateTime;
    private final long milliSecond;

    public Time(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.milliSecond = parseMilli(dateTime);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getMilliSeconds() {
        return milliSecond;
    }

    private long parseMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
