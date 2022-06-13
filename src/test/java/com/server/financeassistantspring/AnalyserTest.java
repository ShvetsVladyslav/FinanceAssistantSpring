package com.server.financeassistantspring;


import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Main.Analyser;
import com.server.financeassistantspring.Entity.Main.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyserTest {
    Transaction[] testExtract1;
    Transaction[] testExtract2;
    List<MCC> mccTest;
    List<MCC> personalMccTest;
    Analyser analyserTest;

    @Before
    public void setUp() throws IOException {
        analyserTest = new Analyser();
        testExtract1 = new Transaction[2];
        testExtract2 = new Transaction[2];
        mccTest = Arrays.asList(MCC.mccFill());
    }

    @Test
    public void testCalculateWriteoff(){
        testExtract1[0] = new Transaction();
        testExtract1[1] = new Transaction();
        testExtract1[0].setAmount(-10000);
        testExtract1[1].setAmount(-10000);
        double result = -200.00;
        Assert.assertEquals(result, analyserTest.calculateWriteOff(testExtract1), 0);
    }

    @Test
    public void testCalculateRefills(){
        testExtract1[0] = new Transaction();
        testExtract1[1] = new Transaction();
        testExtract1[0].setAmount(10000);
        testExtract1[1].setAmount(10000);
        double result = 200.00;
        Assert.assertEquals(result, analyserTest.calculateRefills(testExtract1), 0);
    }

    @Test
    public void testIsWriteoffBigger(){
        testExtract1[0] = new Transaction();
        testExtract1[1] = new Transaction();
        testExtract2[0] = new Transaction();
        testExtract2[1] = new Transaction();
        testExtract1[0].setAmount(-20000);
        testExtract1[1].setAmount(-10000);
        testExtract2[0].setAmount(-10000);
        testExtract2[1].setAmount(-10000);
        Assert.assertTrue("Write-off in Extract 1 not bigger than Extract 2", analyserTest.isWriteoffBigger(testExtract1, testExtract2));
    }

    @Test
    public void testIsWriteoffEquals(){
        testExtract1[0] = new Transaction();
        testExtract1[1] = new Transaction();
        testExtract2[0] = new Transaction();
        testExtract2[1] = new Transaction();
        testExtract1[0].setAmount(-20000);
        testExtract1[1].setAmount(-10000);
        testExtract2[0].setAmount(-20000);
        testExtract2[1].setAmount(-10000);
        Assert.assertTrue("Write-off in Extract 1 not bigger than Extract 2", analyserTest.isWriteoffEquals(testExtract1, testExtract2));
    }

    @Test
    public void testIsRefillsBigger(){
        testExtract1[0] = new Transaction();
        testExtract1[1] = new Transaction();
        testExtract2[0] = new Transaction();
        testExtract2[1] = new Transaction();
        testExtract1[0].setAmount(20000);
        testExtract1[1].setAmount(10000);
        testExtract2[0].setAmount(10000);
        testExtract2[1].setAmount(10000);
        Assert.assertTrue("Refills in Extract 1 not bigger than Extract 2", analyserTest.isRefillsBigger(testExtract1, testExtract2));
    }

    @Test
    public void testIsRefillsEquals(){
        testExtract1[0] = new Transaction();
        testExtract1[1] = new Transaction();
        testExtract2[0] = new Transaction();
        testExtract2[1] = new Transaction();
        testExtract1[0].setAmount(20000);
        testExtract1[1].setAmount(10000);
        testExtract2[0].setAmount(20000);
        testExtract2[1].setAmount(10000);
        Assert.assertTrue("Refills in Extract 1 not bigger than Extract 2", analyserTest.isRefillsEquals(testExtract1, testExtract2));
    }

    @Test
    public void testSortByMCC() throws IOException {
        testExtract1[0] = new Transaction();
        testExtract1[1] = new Transaction();
        testExtract1[0].setMcc(3001);
        testExtract1[1].setMcc(3000);
        testExtract2[0] = new Transaction();
        testExtract2[1] = new Transaction();
        Assert.assertNotNull(analyserTest.sortByMCC(testExtract1, mccTest,"AL"));
        Assert.assertEquals(2, analyserTest.sortByMCC(testExtract1, mccTest,"AL").size());
    }

    @Test
    public void testSortByPersonalMCC(){
        testExtract1[0] = new Transaction();
        testExtract1[1] = new Transaction();
        testExtract1[0].setMcc(3001);
        testExtract1[1].setMcc(3000);
        personalMccTest = new ArrayList<>();
        personalMccTest.add(MCC.createPersonalGroup("3000", "test", "test", "test", "test"));
        Assert.assertNotNull(analyserTest.sortByMCC(testExtract1,personalMccTest, "test"));
        Assert.assertEquals(1, analyserTest.sortByMCC(testExtract1,personalMccTest, "test").size());
    }

    @Test
    public void testAmountForPeriod(){
        testExtract1[0] = new Transaction();
        testExtract1[1] = new Transaction();
        testExtract1[0].setTime(1000);
        testExtract1[1].setTime(2000);
        Assert.assertNotNull(analyserTest.amountForPeriod(testExtract1, 500, 3000));
        Assert.assertEquals(2 , analyserTest.amountForPeriod(testExtract1, 500, 3000).length);
    }

    @Test
    public void testPrognosis(){

    }

    @After
    public void cleanUp(){
        analyserTest = null;
        testExtract1 = null;
        testExtract2 = null;
        mccTest = null;
    }
}
