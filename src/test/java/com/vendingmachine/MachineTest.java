package com.vendingmachine;

import com.vendingmachine.model.Coin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class MachineTest {


    @Test
    public void returnChange_shouldReturnChangeBack() {

        List<Coin> expectedChange = new ArrayList<Coin>();
        expectedChange.add(Coin.FIFTY);
        expectedChange.add(Coin.TWENTY);

        List<Coin> actual = Machine.returnChange(expectedChange);

        assertEquals(expectedChange, actual);
    }

    @Test
    public void dispenseProduct_shouldDispenseAProductBack() {

        List<Coin> expectedChange = new ArrayList<Coin>();
        expectedChange.add(Coin.FIFTY);
        expectedChange.add(Coin.TWENTY);

        List<Coin> actual = Machine.returnChange(expectedChange);

        assertEquals(expectedChange, actual);
    }
}
