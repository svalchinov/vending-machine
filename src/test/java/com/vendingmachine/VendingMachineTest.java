package com.vendingmachine;

import com.vendingmachine.exception.OutOfStockException;
import com.vendingmachine.model.Coin;
import com.vendingmachine.model.Product;
import com.vendingmachine.model.Purchase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VendingMachineTest {

    private VendingMachine vendingMachine;
    private HashMap<Product, Integer> availableProducts;
    private HashMap<Coin, Integer> bankInventory;

    @Before
    public void setup() {
        availableProducts = new HashMap<Product, Integer>();
        availableProducts.put(Product.CRISPS, 5);
        availableProducts.put(Product.JUICE, 5);
        availableProducts.put(Product.SANDWICH, 5);

        bankInventory = new HashMap<Coin, Integer>();
        bankInventory.put(Coin.TEN, 2);
        bankInventory.put(Coin.TWENTY, 2);
        bankInventory.put(Coin.FIFTY, 2);
        bankInventory.put(Coin.POUND, 2);

        vendingMachine = new VendingMachine(availableProducts, bankInventory);
    }

    @Test
    public void insertMoney_shouldAddMoneyToInsertedMoney() {

        ArrayList<Coin> expected = new ArrayList<Coin>();
        expected.add(Coin.POUND);
        expected.add(Coin.TWENTY);
        expected.add(Coin.TEN);

        // given
        vendingMachine.insertMoney(expected.get(0));
        vendingMachine.insertMoney(expected.get(1));
        vendingMachine.insertMoney(expected.get(2));

        // when
        List<Coin> insertedMoneyTotal = vendingMachine.getInsertedMoney();

        // then
        assertEquals(expected, insertedMoneyTotal);
    }

//    @Test(expected = UnrecognisedCoinException.class)
//    public void insertMoney_shouldThrowException_whenUnsupportedCoinUsed() {
//
//        // when
//        vendingMachine.insertMoney(Coin.TWENTY);
//    }


    @Test
    public void returnMoney_shouldReturnInsertedMoneyBack() {
        List<Coin> expected = new ArrayList<Coin>();
        expected.add(Coin.TWENTY);
        expected.add(Coin.TEN);
        expected.add(Coin.FIFTY);

        // given
        vendingMachine.insertMoney(expected.get(0));
        vendingMachine.insertMoney(expected.get(1));
        vendingMachine.insertMoney(expected.get(2));

        // when
        List<Coin> moneyReturned = vendingMachine.returnMoney();

        // then
        assertTrue(vendingMachine.getInsertedMoney().isEmpty());
        assertEquals(expected, moneyReturned);
    }


    @Test
    public void returnMoney_shouldReturnInsertedMoneyBack1() {
        List<Coin> expected = new ArrayList<Coin>();
        expected.add(Coin.TWENTY);
        expected.add(Coin.TEN);
        expected.add(Coin.FIFTY);

        // given
        vendingMachine.insertMoney(expected.get(0));
        vendingMachine.insertMoney(expected.get(1));
        vendingMachine.insertMoney(expected.get(2));

        // when
        List<Coin> moneyReturned = vendingMachine.returnMoney();

        // then
        assertTrue(vendingMachine.getInsertedMoney().isEmpty());
        assertEquals(expected, moneyReturned);
    }


    @Test
    public void dispense_shouldCollectMoneyAndDispenseItem() throws Exception {

        Product expectedProduct = Product.JUICE;

        List<Coin> expectedChange = new ArrayList<Coin>();
        expectedChange.add(Coin.FIFTY);

        // given
        HashMap<Coin, Integer> inventory = new HashMap<Coin, Integer>(bankInventory);
        VendingMachine vendingMachine = new VendingMachine(availableProducts, inventory);
        inventory.put(Coin.TEN, 0);
        inventory.put(Coin.TWENTY, 0);
        inventory.put(Coin.POUND, 0);
        vendingMachine.insertMoney(Coin.POUND);
        vendingMachine.insertMoney(Coin.FIFTY);

        // when
        Purchase purchase = vendingMachine.dispense(expectedProduct);

        // then
        assertEquals(purchase.getProduct(), expectedProduct);
        assertEquals(purchase.getAvailableChange(), expectedChange);
    }


    @Test(expected = OutOfStockException.class)
    public void dispense_willThrowOutOfStockException_whenProductCountIsZero() throws Exception {
        Product expectedProduct = Product.CRISPS;
        HashMap<Product, Integer> productList = new HashMap<Product, Integer>();
        productList.put(expectedProduct, 0);

        VendingMachine vendingMachine = new VendingMachine(productList, bankInventory);

        // given
        vendingMachine.insertMoney(Coin.POUND);

        // when
        vendingMachine.dispense(expectedProduct);
    }
}
