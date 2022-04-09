package com.nhnacademy.parkinglot.parkingsystem;

import com.nhnacademy.Time;
import com.nhnacademy.car.Car;
import com.nhnacademy.car.cartype.CarType;
import com.nhnacademy.exceptions.LackMoneyException;
import com.nhnacademy.exceptions.LargeCarDoseNotParkException;
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

    public synchronized ParkingSpace enterParkingLot(User user) {
        Car userCar = user.getCar();
        if (userCar.getCarType() == CarType.LARGE) {
            throw new LargeCarDoseNotParkException("대형차는 주차할 수 없습니다.");
        }
        Enterance.scan(userCar);
        return this.parkingLot.enter(userCar, generateParkingLotCode());
    }

    private String generateParkingLotCode() {
        String lotCode = "";
        int parkedCodeNumber = parkingLot.getParkedSpaceCount();

        if (parkedCodeNumber >= 10) { // 10대 보다 많이 주차하면
            int backNumber = (parkedCodeNumber - (parkedCodeNumber / 10) * 10) + 1;
            int frontCharCode = 'A' + (parkedCodeNumber / 10);

            if (frontCharCode >= 91) {
                throw new ParkingSpaceOverflowException("더 이상 주차 공간이 없습니다.");
            }
            lotCode = Character.toString(frontCharCode) + "-" + backNumber;
        }
        if (parkedCodeNumber < 10) { // 10대보다 적게 주차했으면
            lotCode = "A-" + (parkedCodeNumber + 1);
        }

        return lotCode;
    }

    public synchronized void exitUserCar(User user) {
        ParkingSpace space = findParkingLotByCarNumber(user.getCarNumber());
        Time enterTime = space.getEnterTime();
        Time outTime = user.getOutTime();
        payUserAmount(user, enterTime, outTime);
    }

    private void payUserAmount(User user, Time enterTime, Time outTime) {
        try {
            user.payParkingLotFee(Exit.pay(enterTime, outTime));
        } catch (LackMoneyException e) {
            System.out.println(e.getMessage());
        }
    }

    public ParkingSpace findParkingLotByCarNumber(String carNumber) {
        return parkingLot.findByCarNumber(carNumber);
    }
}
