package com.nhnacademy.user;

import com.nhnacademy.Time;
import com.nhnacademy.car.Car;
import com.nhnacademy.exceptions.LackMoneyException;
import java.time.LocalDateTime;

public class User {
    private final Car car;
    private final Time outTime;
    private long amount;

    public User(long amount, Car car, LocalDateTime outTime) {
        this.amount = amount;
        this.car = car;
        this.outTime = new Time(outTime);
    }

    public void payParkingLotFee(long fee) {
        if (this.amount < fee) {
            throw new LackMoneyException(this.getCarNumber() + "차량의 잔액이 부족합니다.");
        }
        amount -= car.getCarType().discount(fee);
    }

    public Car getCar() {
        return this.car;
    }

    public String getCarNumber() {
        return car.getCarNumber();
    }

    public long getAmount() {
        return amount;
    }

    public Time getOutTime() {
        return outTime;
    }

}
