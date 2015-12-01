package com.vendingmachine.exception;

public class InsufficientAvailableChangeException extends RuntimeException {

    public InsufficientAvailableChangeException(){
        super("Insufficient available change");
    }

}
