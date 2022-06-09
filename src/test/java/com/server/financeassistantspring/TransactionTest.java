package com.server.financeassistantspring;

import com.server.financeassistantspring.Entity.Main.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransactionTest {

    Transaction testTransaction;
    @Before
    public void setUp() {

        testTransaction = new Transaction();
    }
    @Test
    public void getAmountTest(){
        double result = -100;
        testTransaction.setAmount(-10000);
        Assert.assertEquals(result, testTransaction.getAmount(), 0);
    }
    @After
    public void cleanUp(){

        testTransaction = null;
    }
}

