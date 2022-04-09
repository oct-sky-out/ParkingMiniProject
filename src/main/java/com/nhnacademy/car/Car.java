package com.nhnacademy.car;

import com.nhnacademy.car.cartype.CarType;

public class Car {
    private final String carNumber;
    private final CarType carType;

    public Car(String carNumber, CarType carType) {
        this.carNumber = carNumber;
        this.carType = carType;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public CarType getCarType() {
        return carType;
    }
}
