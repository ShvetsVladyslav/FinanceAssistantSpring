package com.server.financeassistantspring;

import com.server.financeassistantspring.Entity.Additional.UnixTimeParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnixTimeParserTest {
    String timestamp1;
    long timestamp2;
    @Before
    public void setUp(){
        timestamp1 = "10|05|2022 00:00:00";
        timestamp2 = 1649970000;
    }
    @Test
    public void testParse() {
        long result = 1647295200;
        Assert.assertEquals("Parse process failed!", result, UnixTimeParser.timeParse(timestamp1));
    }
    @Test
    public void testUnparse(){
        String result = "15/04/2022 00:00:00";
        Assert.assertEquals("Unparse process failed!", result, UnixTimeParser.timeUnparse(timestamp2));
    }
}
