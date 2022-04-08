package com.nhnacademy.exceptions;

public class ParkingSpaceOverflowException extends RuntimeException {
    public ParkingSpaceOverflowException(String message) {
        super(message);
    }
}
