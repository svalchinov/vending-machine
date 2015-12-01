package com.vendingmachine.exception;


public class MachineUnavailableException extends RuntimeException {
    public MachineUnavailableException() {
        super("The machine has been turned off");
    }
}
