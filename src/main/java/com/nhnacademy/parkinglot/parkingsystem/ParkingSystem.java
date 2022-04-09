package com.nhnacademy.parkinglot.parkingsystem;

import com.nhnacademy.Time;
import com.nhnacademy.car.Car;
import com.nhnacademy.exceptions.ParkingSpaceOverflowException;
import com.nhnacademy.parkinglot.ParkingLot;
import com.nhnacademy.parkinglot.enterance.Enterance;
import com.nhnacademy.parkinglot.exit.Exit;
import com.nhnacademy.parkinglot.parkingspace.ParkingSpace;
import com.nhnacademy.user.User;

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
            int frontCharCode = 'A' + (parkedCodeNumber / 10);

            if (frontCharCode >= 91) {
                throw new ParkingSpaceOverflowException("더 이상 주차 공간이 없습니다.");
            }
            lotCode = Character.toString(frontCharCode) + "-" + backNumber;
        }
        if (parkedCodeNumber < 10) { // 10대보다 적게 주차했으면
            lotCode = "A-" + parkedCodeNumber;
        }

        return lotCode;
    }

    public User exitUserCar(User user) {
        ParkingSpace space = findParkingLotByCarNumber(user.getCarNumber());
        Time enterTime = space.getEnterTime();
        Time outTime = user.getOutTime();
        user.payParkingLotFee(Exit.pay(enterTime, outTime));
        return user;
    }

    public ParkingSpace findParkingLotByCarNumber(String carNumber) {
        return parkingLot.findByCarNumber(carNumber);
    }
}
