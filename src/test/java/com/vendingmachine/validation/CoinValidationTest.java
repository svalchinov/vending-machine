package com.vendingmachine.validation;

import com.vendingmachine.exception.UnrecognisedCoinException;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class CoinValidationTest {

    @Test
    public void coinValidation_acceptsRecognisedCoinTypes() {

        // given
        int value = 20;

        // when
        boolean result = CoinValidation.validateCoinType(value);

        // then
        assertTrue(result);
    }


    @Test(expected = UnrecognisedCoinException.class)
    public void coinValidation_shouldRejectUnrecognisedCoinTypes() {

        // given
        int value = 30;

        // when
        CoinValidation.validateCoinType(value);
    }
}
