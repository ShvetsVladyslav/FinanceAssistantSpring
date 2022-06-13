package com.server.financeassistantspring.Repository;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import com.server.financeassistantspring.Entity.Main.User;

public interface PersonalSettingsRepositoryCustom {
    PersonalSettings addClientSettings(PersonalSettings client, MCC mcc);
    PersonalSettings deleteClientSettings(PersonalSettings client, MCC mcc);
    PersonalSettings updateClientSettings(PersonalSettings client, MCC mcc);
}
