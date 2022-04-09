package com.nhnacademy;

import com.nhnacademy.car.Car;
import com.nhnacademy.parkinglot.ParkingLot;
import com.nhnacademy.parkinglot.parkingsystem.ParkingSystem;
import com.nhnacademy.parkingsimultaion.ParkingSimulation;
import com.nhnacademy.user.User;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class NhnParkingLot {
    public static void main(String[] args) {
        ParkingSystem nhnParkingLotSys = new ParkingSystem(new ParkingLot());

        Set<Thread> userThreads = new HashSet<>();

        for (int i = 0; i < 20; i++) {
            SecureRandom random = new SecureRandom();
            long amount = random.nextInt(100_000 - 10_000) + 10_000L;
            String carNumber = "12A " + (1230 + i);
            Car car = new Car(carNumber);
            LocalDateTime dateTime = LocalDateTime.now()
                .plusHours(random.nextInt(100))
                .plusMinutes(random.nextInt(1000))
                .plusSeconds(random.nextInt(1000));
            User user = new User(amount, car, dateTime);

            userThreads.add(new Thread(new ParkingSimulation(user, nhnParkingLotSys)));
        }

        userThreads.forEach(Thread::start);
    }
}
