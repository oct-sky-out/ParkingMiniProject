package com.nhnacademy;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Time {
    private final LocalDateTime dateTime;
    private final long milliSecond;

    public Time(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.milliSecond = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getMilliSeconds() {
        return milliSecond;
    }
}
