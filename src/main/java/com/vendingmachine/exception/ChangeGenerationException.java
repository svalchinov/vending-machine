package com.vendingmachine.exception;

public class ChangeGenerationException extends RuntimeException {
    public ChangeGenerationException() {
        super("Failed to generate change");
    }
}
