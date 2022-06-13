package com.server.financeassistantspring.MenuController;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import com.server.financeassistantspring.Interfases.ISettingsMenu;
import com.server.financeassistantspring.Repository.PersonalSettingsRepository;
import com.server.financeassistantspring.Repository.PersonalSettingsRepositoryCustom;
import com.server.financeassistantspring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/settings")
public class SettingsController implements ISettingsMenu {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonalSettingsRepository personalSettingsRepository;
    @Autowired
    PersonalSettingsRepositoryCustom  personalSettingsRepositoryCustom;
    @Override
    @PostMapping("/add")
    public PersonalSettings addSettings(@RequestParam(value = "clientId") String clientId,@RequestBody MCC newMcc) {
        PersonalSettings client = personalSettingsRepository.findByClientId(clientId);
        return personalSettingsRepositoryCustom.addClientSettings(client, newMcc);
    }
    @Override
    @DeleteMapping("/delete")
    public PersonalSettings deleteSettings(@RequestParam(value = "clientId") String clientId,@RequestBody MCC mcc) {
        PersonalSettings client = personalSettingsRepository.findByClientId(clientId);
        return personalSettingsRepositoryCustom.deleteClientSettings(client, mcc);
    }
    @Override
    @PatchMapping("/edit")
    public PersonalSettings updateSettings(@RequestParam(value = "clientId") String clientId,@RequestBody MCC mcc) {
        PersonalSettings client = personalSettingsRepository.findByClientId(clientId);
        return personalSettingsRepositoryCustom.updateClientSettings(client, mcc);
    }
    @Override
    @GetMapping
    public PersonalSettings getSettings(@RequestParam(value = "clientId")String clientId){
        return personalSettingsRepository.findByClientId(clientId);
    }
}
