package com.vendingmachine;

import com.vendingmachine.model.Coin;

import java.util.HashMap;
import java.util.List;

public class BankInventory {
    private HashMap<Coin, Integer> inventory;

    public BankInventory(HashMap<Coin, Integer> inventory) {
        this.inventory = inventory;
    }

    public HashMap<Coin, Integer> getInventory() {
        return inventory;
    }

    public void addToInventory(List<Coin> coins) {
        for (Coin coin : coins) {
            int updatedCoinCount = inventory.get(coin) + 1;
            inventory.put(coin, updatedCoinCount);
        }
    }

    public void removeFromInventory(List<Coin> coins) {
        for (Coin coin : coins) {
            int updatedCoinCount = inventory.get(coin) - 1;
            if (updatedCoinCount >= 0) {
                inventory.put(coin, updatedCoinCount);
            }
        }
    }

    public int getBankInventoryTotal() {
        int total = 0;
        for (Coin coin : getInventory().keySet()) {
            total += coin.getValue() * getInventory().get(coin);
        }
        return total;
    }
}
