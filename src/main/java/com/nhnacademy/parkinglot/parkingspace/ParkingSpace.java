package com.nhnacademy.parkinglot.parkingspace;

import com.nhnacademy.Time;
import com.nhnacademy.car.Car;
import java.time.LocalDateTime;

public class ParkingSpace {
    private final Car car;
    private final String lotCode;
    private final Time enterTime;

    public ParkingSpace(Car car, String lotCode) {
        this.car = car;
        this.lotCode = lotCode;
        LocalDateTime now = LocalDateTime.now();
        this.enterTime = new Time(now);
    }

    public Car getCar() {
        return car;
    }

    public String getLotCode() {
        return lotCode;
    }

    public Time getEnterTime() {
        return this.enterTime;
    }
}
