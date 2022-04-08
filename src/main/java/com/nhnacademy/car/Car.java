package com.nhnacademy.car;

import com.nhnacademy.exceptions.NotMatchCarNumberException;
import java.util.Scanner;

public class Car {
    private final String carNumber;
    
    public Car() {
        Scanner scanner = new Scanner(System.in);
        String newCarNumber = scanner.next();
        if (!newCarNumber.matches("^(\\d){2,3}([A-Z])(\\d{4})$")) {
            throw new NotMatchCarNumberException("올바르지 않은 번호판 형식입니다.");
        }
        this.carNumber = newCarNumber;
    }
}
