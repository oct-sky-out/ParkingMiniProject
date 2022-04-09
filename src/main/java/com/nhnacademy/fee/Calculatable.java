package com.nhnacademy.fee;

import com.nhnacademy.time.Time;

public interface Calculatable {
    long calculate(Time enterTime, Time outTime);
}
