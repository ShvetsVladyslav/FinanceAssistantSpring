package com.server.financeassistantspring.Interfases;

import com.server.financeassistantspring.Entity.Additional.Currency;
import com.server.financeassistantspring.Entity.Main.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface IAccountMenu {
    double getBalance(@RequestParam(value = "account") String account, @RequestParam(value = "clientId") String clientId);

    String getIBan(@RequestParam(value = "account") String account, @RequestParam(value = "clientId") String clientId);
    double getTotalBalance(@RequestParam(value = "clientId")String clientId, @RequestBody List<Currency> currencies);
    double getCreditlim(@RequestParam(value = "clientId")String clientId, @RequestBody List<Currency> currencies);
}
