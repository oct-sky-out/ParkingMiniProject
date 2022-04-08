package com.nhnacademy.parkingzone;

import com.nhnacademy.parkingzone.parkingspace.ParkingSpace;

public class ParkingZoneService {
    private final ParkingZoneRepository parkingZoneRepo;

    public ParkingZoneService(ParkingZoneRepository parkingZoneRepo) {
        this.parkingZoneRepo = parkingZoneRepo;
    }

    public void enterCar(ParkingSpace parkedSpace) {
        parkingZoneRepo.addParkedSpace(parkedSpace);
    }

    public int parkedCarCount() {
        return parkingZoneRepo.countParkedCar();
    }
}
