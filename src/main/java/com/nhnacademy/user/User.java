package com.nhnacademy.user;

import com.nhnacademy.car.Car;
import com.nhnacademy.exceptions.LackMoneyException;
import com.nhnacademy.paycoserver.PaycoMember;
import com.nhnacademy.time.Time;
import java.time.LocalDateTime;

public class User {
    private final Car car;
    private final Time outTime;
    private final PaycoMember paycoMember;
    private long amount;

    public static User paycoUser(long amount, Car car, LocalDateTime outTime) {
        return new User(amount, car, outTime, new PaycoMember());
    }

    public static User normalUser(long amount, Car car, LocalDateTime outTime) {
        return new User(amount, car, outTime, null);
    }

    private User(long amount, Car car, LocalDateTime outTime, PaycoMember paycoMember) {
        this.amount = amount;
        this.car = car;
        this.outTime = new Time(outTime);
        this.paycoMember = paycoMember;
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

    public PaycoMember getPaycoMember() {
        return paycoMember;
    }
}
