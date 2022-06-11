package com.server.financeassistantspring.MenuController;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Main.User;
import com.server.financeassistantspring.Interfases.ISettingsMenu;
import com.server.financeassistantspring.Repository.UserRepository;
import com.server.financeassistantspring.Repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/settings")
public class SettingsController implements ISettingsMenu {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRepositoryCustom userRepositoryCustom;
    @Override
    @PostMapping("/add")
    public User addSettings(@RequestParam(value = "clientId") String clientId,@RequestBody MCC newMcc) {
        User client = userRepository.findByClientId(clientId);
        return userRepositoryCustom.addClientSettings(client, newMcc);
    }
    @Override
    @DeleteMapping("/delete")
    public User deleteSettings(@RequestParam(value = "clientId") String clientId,@RequestBody MCC mcc) {
        User client = userRepository.findByClientId(clientId);
        return userRepositoryCustom.deleteClientSettings(client, mcc);
    }
    @Override
    @PatchMapping("/edit")
    public User updateSettings(@RequestParam(value = "clientId") String clientId,@RequestBody MCC mcc) {
        User client = userRepository.findByClientId(clientId);
        return userRepositoryCustom.updateClientSettings(client, mcc);
    }
}
