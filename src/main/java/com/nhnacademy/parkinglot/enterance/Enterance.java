package com.nhnacademy.parkinglot.enterance;

import com.nhnacademy.car.Car;
import com.nhnacademy.exceptions.NotMatchCarNumberException;

public class Enterance {
    public static void scan(Car car) {
        matchCarNumberType(car.getCarNumber());
    }
    
    private static void matchCarNumberType(String carNumber) {
        if (!carNumber.matches("^(\\d){2,3}([A-Z])\\u0020(\\d){4}$")) {
            throw new NotMatchCarNumberException("번호판의 형식이 올바르지 않습니다.");
        }
    }

}
