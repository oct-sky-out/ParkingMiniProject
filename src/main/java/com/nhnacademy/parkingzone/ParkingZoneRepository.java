package com.nhnacademy.parkingzone;

import com.nhnacademy.parkingzone.parkingspace.ParkingSpace;

public class ParkingZoneRepository {
    private final ParkingZone parkingZone = new ParkingZone();

    public void addParkedSpace(ParkingSpace space) {
        this.parkingZone.parkCar(space);
    }

    public int countParkedCar() {
        return parkingZone.parkedCount();
    }
}
