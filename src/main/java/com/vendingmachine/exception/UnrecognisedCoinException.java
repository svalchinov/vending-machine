package com.vendingmachine.exception;


public class UnrecognisedCoinException extends RuntimeException {

    public UnrecognisedCoinException(){
        super("Unrecognised coin type");
    }
}
