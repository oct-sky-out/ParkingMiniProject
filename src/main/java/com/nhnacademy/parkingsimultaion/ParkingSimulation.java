package com.nhnacademy.parkingsimultaion;

import com.nhnacademy.parkinglot.parkingspace.ParkingSpace;
import com.nhnacademy.parkinglot.parkingsystem.ParkingSystem;
import com.nhnacademy.user.User;

public class ParkingSimulation implements Runnable {
    private final User user;
    private final ParkingSystem system;

    public ParkingSimulation(User user, ParkingSystem system) {
        this.user = user;
        this.system = system;
    }

    @Override
    public void run() {
        ParkingSpace space = system.enterParkingLot(user); // 입차
        System.out.println("유저의 소지금 :" + this.user.getAmount());
        System.out.println("유저의 차량번호는 :" + this.user.getCarNumber());
        System.out.println("유저의 차량 타입은 :" + this.user.getCar().getCarType().name());
        System.out.println("유저의 주차 코드는 :" + space.getLotCode());
        System.out.println("유저는 페이코 회원여부 :" + this.user.getPaycoMember());
        System.out.println("유저가 가진 주차 쿠폰 :" + this.user.getVoucher());

        system.exitUserCar(this.user);

        System.out.println(
            this.user.getCarNumber() + " 차량의 정산 시간은 :" + this.user.getOutTime().getDateTime());
        System.out.println(this.user.getCarNumber() + " 유저의 잔액은 :" + this.user.getAmount());
        System.out.println("___________________________________________________");
    }
}
