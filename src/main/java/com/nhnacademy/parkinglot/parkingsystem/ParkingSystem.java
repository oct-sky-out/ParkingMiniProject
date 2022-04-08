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

    public ParkingSpace enterParkingLot(Car car) {
        Enterance.scan(car);
        return this.parkingLot.enter(car, generateParkingLotCode());
    }

    private String generateParkingLotCode() {
        String lotCode = "";
        int parkedCodeNumber = parkingLot.getParkedSpaceCount() + 1;

        if (parkedCodeNumber >= 10) { // 10대 보다 많이 주차하면
            int backNumber = parkedCodeNumber % 10 == 0 ? 1 : parkedCodeNumber % 10;
            lotCode =
                Character.toString('A' + (parkedCodeNumber / 10)) + "-" + backNumber;

        }
        if (parkedCodeNumber < 10) { // 10대보다 적게 주차했으면
            lotCode = "A-" + parkedCodeNumber;
        }

        return lotCode;
    }

    public ParkingSpace findParkingLotByCarNumber(String carNumber) {
        return parkingLot.findByCarNumber(carNumber);
    }
}
