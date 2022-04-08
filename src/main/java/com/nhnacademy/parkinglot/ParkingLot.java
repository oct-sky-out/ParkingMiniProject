package com.nhnacademy.parkinglot;

import com.nhnacademy.car.Car;
import com.nhnacademy.parkinglot.parkingspace.ParkingSpace;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<ParkingSpace> spaces = new ArrayList<>();

    public int enter(Car car) {
        spaces.add(new ParkingSpace(car));
        return spaces.size();
    }
}
