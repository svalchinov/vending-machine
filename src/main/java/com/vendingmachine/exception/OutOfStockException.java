package com.vendingmachine.exception;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String itemName) {
        super(String.format("Out of stock of %s", itemName));
    }
}
