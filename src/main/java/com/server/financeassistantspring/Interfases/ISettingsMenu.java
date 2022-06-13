package com.server.financeassistantspring.Interfases;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import com.server.financeassistantspring.Entity.Main.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ISettingsMenu {
    PersonalSettings addSettings(@RequestParam(value = "clientId") String clientId, @RequestBody MCC newMcc);
    PersonalSettings deleteSettings(@RequestParam(value = "clientId") String clientId,@RequestBody MCC mcc);
    PersonalSettings updateSettings(@RequestParam(value = "clientId") String clientId,@RequestBody MCC mcc);
}
