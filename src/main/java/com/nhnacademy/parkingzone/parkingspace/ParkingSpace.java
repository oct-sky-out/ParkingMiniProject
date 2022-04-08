package com.nhnacademy.parkingzone.parkingspace;

import com.nhnacademy.car.Car;

public class ParkingSpace {
    private final Car car;

    public static ParkingSpace parking(Car car) {
        return new ParkingSpace(car);
    }


    private ParkingSpace(Car car) {
        this.car = car;
    }

}
