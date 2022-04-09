package com.nhnacademy.parkinglot.exit;

import com.nhnacademy.Time;
import com.nhnacademy.fee.Fee;

public class Exit {
    public static long pay(Time enter, Time out) {
        return new Fee().calculate(enter, out);
    }
}
