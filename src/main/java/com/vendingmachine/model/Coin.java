package com.vendingmachine.model;

public enum Coin {

    TEN(10), TWENTY(20), FIFTY(50), POUND(100);

    private int value;

    private Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
