package com.nhnacademy.parkinglot.parkingspace;

import com.nhnacademy.car.Car;

public class ParkingSpace {
    private final Car car;
    private final String lotCode;

    public ParkingSpace(Car car, String lotCode) {
        this.car = car;
        this.lotCode = lotCode;
    }

    public Car getCar() {
        return car;
    }

    public String getLotCode() {
        return lotCode;
    }
}
