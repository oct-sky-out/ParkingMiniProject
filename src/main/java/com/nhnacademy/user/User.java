package com.nhnacademy.user;

import com.nhnacademy.Time;
import java.time.LocalDateTime;

public class User {
    private final String carNumber;
    private final Time outTime;
    private long amount;

    public User(long amount, String carNumber, LocalDateTime outTime) {
        this.amount = amount;
        this.carNumber = carNumber;
        this.outTime = new Time(outTime);
    }

    public void payParkingLotFee(long fee) {
        amount -= fee;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public long getAmount() {
        return amount;
    }

    public Time getOutTime() {
        return outTime;
    }

}
