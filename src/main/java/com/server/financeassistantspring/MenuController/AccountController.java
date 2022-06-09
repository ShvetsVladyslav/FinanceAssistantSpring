package com.server.financeassistantspring.MenuController;

import com.server.financeassistantspring.Entity.Main.User;
import com.server.financeassistantspring.Interfases.IAccountMenu;
import com.server.financeassistantspring.Repository.UserRepository;
import com.server.financeassistantspring.Repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController implements IAccountMenu {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryCustom userRepositoryCustom;
    @Override
    @GetMapping("/balance")
    public double getBalance(@RequestParam(value = "account")String account, @RequestParam(value = "clientId")String clientId) {
        User client = userRepository.findByClientId(clientId);
        double result = 0;
        for (int i = 0; i < client.getAccount().size(); i++){
            if (Objects.equals(client.getAccount().get(i).getId(), account)){
                result = client.getAccount().get(i).getBalance();
            }
        }
        return result;
    }
    @Override
    public String getIBan(@RequestParam(value = "account")String account, @RequestParam(value = "clientId")String clientId) {
        User client = userRepository.findByClientId(clientId);
        String result = null;
        for (int i = 0; i < client.getAccount().size(); i++){
            if (Objects.equals(client.getAccount().get(i).getId(), account)){
                result = client.getAccount().get(i).getIban();
            }
        }
        return result;
    }
}
