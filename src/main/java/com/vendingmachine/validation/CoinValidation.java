package com.vendingmachine.validation;

import com.vendingmachine.exception.UnrecognisedCoinException;
import com.vendingmachine.model.Coin;

public class CoinValidation {

    public static boolean validateCoinType(int value) {

        for (Coin coin : Coin.values()) {
            if (coin.getValue() == value) {
                return true;
            }
        }
        throw new UnrecognisedCoinException();
    }
}
