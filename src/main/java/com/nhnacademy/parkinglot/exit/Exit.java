package com.nhnacademy.parkinglot.exit;

import com.nhnacademy.fee.Fee;
import com.nhnacademy.time.Time;

public class Exit {
    public static long pay(Time enter, Time out) {
        return new Fee().calculate(enter, out);
    }
}
