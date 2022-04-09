package com.nhnacademy.exceptions;

public class LackMoneyException extends RuntimeException {
    public LackMoneyException(String message) {
        super(message);
    }
}
