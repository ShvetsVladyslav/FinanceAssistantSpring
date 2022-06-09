package com.server.financeassistantspring.Interfases;

import com.server.financeassistantspring.Entity.Additional.Currency;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

public interface ICurrencyMenu {
    List<Currency> getCurrency() throws IOException;
}
