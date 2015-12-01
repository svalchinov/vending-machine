package com.vendingmachine;

import com.vendingmachine.exception.OutOfStockException;
import com.vendingmachine.model.Coin;
import com.vendingmachine.model.Product;
import com.vendingmachine.model.Products;
import com.vendingmachine.model.Purchase;
import com.vendingmachine.validation.CoinValidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VendingMachine extends Machine {
    private Products products;
    private Bank bank;

    public VendingMachine(HashMap<Product, Integer> products, HashMap<Coin, Integer> inventory) {
        this.products = new Products(products);
        this.bank = new Bank(inventory);
        Machine.turnOn();
    }

    public void insertMoney(Coin coin) {
        CoinValidation.validateCoinType(coin.getValue());
        bank.addInsertedMoney(coin);
    }

    public List<Coin> getInsertedMoney() {
        return bank.getInsertedMoney();
    }

    public List<Coin> returnMoney() {
        List<Coin> moneyToReturn = new ArrayList<Coin>(bank.getInsertedMoney());
        bank.clearInsertedMoney();
        return moneyToReturn;
    }

    public Purchase dispense(Product product) throws Exception {

        if (products.findProductCount(product) < 1) {
            throw new OutOfStockException(product.name());
        }

        Purchase purchase = bank.processPurchase(product);
        if (!purchase.getAvailableChange().isEmpty()) {
            Machine.returnChange(purchase.getAvailableChange());
        }
        Machine.dispenseProduct(purchase.getProduct());
        return purchase;
    }
}
