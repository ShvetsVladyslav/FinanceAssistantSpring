package com.server.financeassistantspring.Interfases;

import com.server.financeassistantspring.Entity.Main.User;
import org.springframework.web.bind.annotation.RequestParam;

public interface IAccountMenu {
    double getBalance(@RequestParam(value = "account")String account, @RequestParam(value = "clientId")String clientId);
    String getIBan(@RequestParam(value = "account")String account, @RequestParam(value = "clientId")String clientId);
}
