package com.nhnacademy.parkingzone;

import com.nhnacademy.parkingzone.parkingspace.ParkingSpace;
import java.util.ArrayList;
import java.util.List;

public class ParkingZone {
    private List<ParkingSpace> spaces = new ArrayList<>();

    public void parkCar(ParkingSpace space) {
        this.spaces.add(space);
    }

    public int parkedCount() {
        return spaces.size();
    }
}
