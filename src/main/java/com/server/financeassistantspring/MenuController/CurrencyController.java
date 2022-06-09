package com.server.financeassistantspring.MenuController;

import com.server.financeassistantspring.Entity.Additional.Currency;
import com.server.financeassistantspring.Interfases.ICurrencyMenu;
import com.server.financeassistantspring.Interfases.ILoginMenu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController implements ICurrencyMenu {
    @GetMapping
    public List<Currency> getCurrency() throws IOException {
        return Currency.currencyFill();
    }
}
