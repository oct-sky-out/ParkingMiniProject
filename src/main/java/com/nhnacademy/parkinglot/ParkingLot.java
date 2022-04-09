package com.nhnacademy.parkinglot;

import com.nhnacademy.car.Car;
import com.nhnacademy.exceptions.CarNotFoundException;
import com.nhnacademy.parkinglot.parkingspace.ParkingSpace;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private final Map<String, ParkingSpace> spaces = new HashMap<>();

    public ParkingSpace enter(Car car, String lotCode) {
        ParkingSpace space = new ParkingSpace(car, lotCode);
        spaces.put(car.getCarNumber(), space);
        return space;
    }

    public int getParkedSpaceCount() {
        return spaces.size();
    }

    public ParkingSpace findByCarNumber(String carNumber) {
        ParkingSpace space = spaces.get(carNumber);
        if (Objects.isNull(space)) {
            throw new CarNotFoundException(carNumber + " 번호판을 가진 자동차가 없습니다.");
        }
        return space;
    }
}
