package com.vendingmachine;

import com.vendingmachine.exception.ChangeGenerationException;
import com.vendingmachine.exception.InsufficientAvailableChangeException;
import com.vendingmachine.exception.InsufficientFundsException;
import com.vendingmachine.model.Coin;
import com.vendingmachine.model.Product;
import com.vendingmachine.model.Purchase;

import java.util.*;

public class Bank {

    private List<Coin> insertedMoney;
    public BankInventory inventory;

    public Bank(HashMap<Coin, Integer> inventory) {
        this.insertedMoney = new ArrayList<Coin>();
        this.inventory = new BankInventory(inventory);
    }

    public List<Coin> getInsertedMoney() {
        return insertedMoney;
    }

    public int getInsertedMoneyTotal() {
        int total = 0;
        for (Coin value : insertedMoney) {
            total += value.getValue();
        }
        return total;
    }

    public void addInsertedMoney(Coin coin) {
        insertedMoney.add(coin);
    }

    public void clearInsertedMoney() {
        insertedMoney.clear();
    }

    public Purchase processPurchase(Product product) {

        if (product.getPrice() > getInsertedMoneyTotal()) {
            throw new InsufficientFundsException();
        }

        List<Coin> change = generateChange(getInsertedMoneyTotal() - product.getPrice());
        inventory.addToInventory(getInsertedMoney());
        clearInsertedMoney();
        return new Purchase(product, change);
    }

    protected List<Coin> generateChange(int change) {

        if (inventory.getBankInventoryTotal() < change) {
            throw new InsufficientAvailableChangeException();
        }

        List<Coin> coins = new ArrayList<Coin>();
        HashMap<Coin, Integer> inventoryCopy = new HashMap<Coin, Integer>(inventory.getInventory());
        Iterator<Map.Entry<Coin, Integer>> iterator = inventoryCopy.entrySet().iterator();

        try {

            Map.Entry<Coin, Integer> coin = iterator.next();
            while (change > 0) {
                boolean coinEligible = coin.getKey().getValue() <= change && coin.getValue() > 0;
                if (coinEligible) {
                    coins.add(coin.getKey());
                    change -= coin.getKey().getValue();
                    inventoryCopy.put(coin.getKey(), coin.getValue() - 1);
                } else {
                    if (!iterator.hasNext()) {
                        throw new InsufficientAvailableChangeException();
                    }
                    coin = iterator.next();
                }
            }

            inventory.removeFromInventory(coins);
            return coins;

        } catch (InsufficientAvailableChangeException e) {
            throw new InsufficientAvailableChangeException();
        } catch (Exception e) {
            throw new ChangeGenerationException();
        }
    }
}
