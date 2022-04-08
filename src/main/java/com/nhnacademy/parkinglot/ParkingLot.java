package com.nhnacademy.parkinglot;

import com.nhnacademy.car.Car;
import com.nhnacademy.exceptions.CarNotFoundException;
import com.nhnacademy.parkinglot.parkingspace.ParkingSpace;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLot {
    private final List<ParkingSpace> spaces = new ArrayList<>();

    public ParkingSpace enter(Car car, String lotCode) {
        ParkingSpace space = new ParkingSpace(car, lotCode);
        spaces.add(space);
        return space;
    }

    public int getParkedSpaceCount() {
        return spaces.size();
    }

    public ParkingSpace findByCarNumber(String carNumber) {
        Optional<ParkingSpace> space = spaces.stream()
            .filter(parkingSpace -> parkingSpace.getCar().getCarNumber().equals(carNumber))
            .findFirst();

        if (space.isPresent()) {
            return space.get();
        }

        throw new CarNotFoundException(carNumber + " 번호판을 가진 자동차가 없습니다.");
    }
}
