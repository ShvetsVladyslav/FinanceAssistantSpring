package com.server.financeassistantspring.MenuController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import com.server.financeassistantspring.Entity.Main.User;
import com.server.financeassistantspring.Interfases.ILoginMenu;
import com.server.financeassistantspring.Repository.PersonalSettingsRepository;
import com.server.financeassistantspring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin
@RestController("/loginMenu")
@RequestMapping("/loginMenu")
public class LoginController implements ILoginMenu {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonalSettingsRepository personalSettingsRepository;

    @PostMapping("/login")
    @Override
    public User Login(@RequestParam(value = "token") String token) throws IOException {
        User client = new User();
        client.setPersonalToken(token);
        try{
        JsonNode data = new ObjectMapper().readTree(client.apiCall());
        client = client.mapper.readValue(data.toString(), User.class);
        client.setPersonalToken(token);
        if (personalSettingsRepository.findByClientId(client.getId()) == null){
            personalSettingsRepository.save(new PersonalSettings(client.getId(), new ArrayList<>()));
        }
        return client;
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage());
        }
    }

    @GetMapping("/monourl")
    @Override
    public String getMonoLink() {
        return "https://www.monobank.ua/";
    }

    @GetMapping("/apiurl")
    @Override
    public String getTokenLink() {
        return "https://api.monobank.ua/";
    }
}
