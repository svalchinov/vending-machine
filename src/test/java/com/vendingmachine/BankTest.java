package com.vendingmachine;

import com.vendingmachine.exception.InsufficientAvailableChangeException;
import com.vendingmachine.exception.InsufficientFundsException;
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

public class BankTest {
    private Bank bank;
    private HashMap<Coin, Integer> inventory;

    @Before
    public void setup() {
        inventory = new HashMap<Coin, Integer>();
        inventory.put(Coin.TEN, 0);
        inventory.put(Coin.TWENTY, 2);
        inventory.put(Coin.FIFTY, 0);
        inventory.put(Coin.POUND, 1);

        this.bank = new Bank(inventory);
    }


    @Test
    public void getInsertedMoney_returnsInsertedMoney() {

        // when
        bank.addInsertedMoney(Coin.FIFTY);

        // then
        assertTrue(bank.getInsertedMoney().contains(Coin.FIFTY));
    }


    @Test
    public void getInsertedMoneyTotal_returnsTheSumOfInsertedMoney() {

        // when
        bank.addInsertedMoney(Coin.FIFTY);
        bank.addInsertedMoney(Coin.POUND);

        // then
        assertEquals(bank.getInsertedMoneyTotal(), 150);
    }


    @Test
    public void clearInsertedMoney_shouldClearInsertedMoney_andInsertedMoneyTotal() {

        //given
        bank.addInsertedMoney(Coin.POUND);
        bank.addInsertedMoney(Coin.POUND);
        bank.addInsertedMoney(Coin.TWENTY);

        // when
        bank.clearInsertedMoney();

        // then
        assertEquals(bank.getInsertedMoneyTotal(), 0);
        assertTrue(bank.getInsertedMoney().isEmpty());
    }


    @Test
    public void processPurchase_shouldCreateNewPurchase_andAddMoneyToInventory() throws Exception {

        List<Coin> change = new ArrayList<Coin>();
        change.add(Coin.TWENTY);
        change.add(Coin.TWENTY);

        Purchase expectedPurchase = new Purchase(Product.CRISPS, change);

        // given
        bank.addInsertedMoney(Coin.POUND);
        Product product = Product.CRISPS;

        // when
        Purchase actual = bank.processPurchase(product);

        // then
        assertEquals(expectedPurchase.getAvailableChange(), actual.getAvailableChange());
        assertEquals(expectedPurchase.getProduct(), actual.getProduct());
        assertEquals(bank.getInsertedMoneyTotal(), 0);
        assertTrue(bank.getInsertedMoney().isEmpty());
    }


    @Test(expected = InsufficientFundsException.class)
    public void processPurchase_shouldThrowException_whenMoneyIsNotEnough() throws Exception {

        // given
        Product product = Product.CRISPS;
        bank.addInsertedMoney(Coin.TEN);

        // when
        bank.processPurchase(product);
    }


    @Test(expected = InsufficientAvailableChangeException.class)
    public void generateChange_shouldThrowException_whenChangeValueIsBiggerThanAvailableMoney() throws Exception {

        // when
        bank.generateChange(250);
    }


    @Test(expected = InsufficientAvailableChangeException.class)
    public void generateChange_shouldThrowException_whenMoneyIsNotEnoughToGenerateChange() throws Exception {

        HashMap<Coin, Integer> expectedInventory = bank.inventory.getInventory();

        // when
        bank.generateChange(80);

        assertEquals(expectedInventory, bank.inventory.getInventory());
    }


    @Test
    public void generateChange_shouldGenerateListOfCoinsToReturnAsChange() throws Exception {

        List<Coin> expectedChange = new ArrayList<Coin>();
        expectedChange.add(Coin.POUND);
        expectedChange.add(Coin.TWENTY);
        expectedChange.add(Coin.TWENTY);

        // when
        List<Coin> change = bank.generateChange(140);

        // then
        assertEquals(change.size(), 3);
        assertTrue(expectedChange.contains(expectedChange.get(0)));
        assertTrue(expectedChange.contains(expectedChange.get(1)));
        assertTrue(expectedChange.contains(expectedChange.get(2)));
    }


//    @Test(expected = ChangeGenerationException.class)
//    public void processPurchase_shouldThrowException_whenUnableToGenerateChange(){
//
//        // when
//        bank.generateChange(315);
//    }

}
