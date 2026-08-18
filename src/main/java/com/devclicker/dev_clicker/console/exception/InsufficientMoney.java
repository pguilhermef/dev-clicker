package com.devclicker.dev_clicker.console.exception;

public class InsufficientMoney extends RuntimeException {
    public InsufficientMoney(String message) {
        super(message);
    }
}
