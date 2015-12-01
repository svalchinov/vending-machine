package com.vendingmachine;

import com.vendingmachine.model.Coin;
import com.vendingmachine.model.Product;

import java.util.List;

public class Machine {

    private static boolean isOn = false;

    public static boolean isOn() {
        return isOn;
    }

    public static void turnOn() {
        Machine.isOn = true;
    }

    public static void turnOff() {
        Machine.isOn = false;
    }

    public static List<Coin> returnChange(List<Coin> change) {
        return change;
    }

    public static Product dispenseProduct(Product product) {
        return product;
    }

}
