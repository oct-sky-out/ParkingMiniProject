package com.nhnacademy;

import com.nhnacademy.car.Car;
import com.nhnacademy.car.cartype.CarType;
import com.nhnacademy.parkinglot.ParkingLot;
import com.nhnacademy.parkinglot.parkingsystem.ParkingSystem;
import com.nhnacademy.parkingsimultaion.ParkingSimulation;
import com.nhnacademy.paycoserver.PaycoServer;
import com.nhnacademy.user.User;
import com.nhnacademy.voucher.Voucher;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class NhnParkingLot {
    public static void main(String[] args) {
        ParkingSystem nhnParkingLotSys = new ParkingSystem(new ParkingLot(), new PaycoServer());

        Set<Thread> userThreads = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            SecureRandom random = new SecureRandom();
            long amount = random.nextInt(100_000 - 10_000) + 10_000L;
            String carNumber = "12A " + (1230 + i);
            int carTypeRandom = random.nextInt(3);
            Car car = new Car(carNumber, CarType.values()[carTypeRandom]);
            LocalDateTime dateTime = LocalDateTime.now()
                .plusHours(random.nextInt(100))
                .plusMinutes(random.nextInt(1000))
                .plusSeconds(random.nextInt(1000));

            User user;
            if (i % 2 == 0) {
                user = User.normalUser(amount, car, dateTime);
            } else {
                user = User.paycoUser(amount, car, dateTime);
            }
            user.takeVoucher(Voucher.values()[random.nextInt(3)]);

            userThreads.add(new Thread(new ParkingSimulation(user, nhnParkingLotSys)));
        }

        userThreads.forEach(thread -> {
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
                System.out.println(e.getMessage());
            }
        });
    }
}
