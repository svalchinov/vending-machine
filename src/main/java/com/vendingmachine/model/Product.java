package com.vendingmachine.model;

public enum Product {

    CRISPS(60), JUICE(100), SANDWICH(170);

    private int price;

    private Product(int price) {
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
