package com.nhnacademy.parkinglot.parkingsystem;

import com.nhnacademy.car.Car;
import com.nhnacademy.parkinglot.ParkingLot;
import com.nhnacademy.parkinglot.enterance.Enterance;
import com.nhnacademy.parkinglot.parkingspace.ParkingSpace;

public class ParkingSystem {
    private final ParkingLot parkingLot;

    public ParkingSystem(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void enterParkingLot(Car car) {
        Enterance.scan(car);
        this.parkingLot.enter(car);
    }

    public ParkingSpace findParkingLotByCarNumber(String carNumber) {
        return null;
    }
}
