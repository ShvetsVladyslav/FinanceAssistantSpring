package com.server.financeassistantspring.Entity.Additional;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "PersonalSettings")
public class PersonalSettings {
    @Field(value = "clientId")
    private String clientId;
    private List<MCC> personalSettings;

    public PersonalSettings(String clientId, List<MCC> personalSettings) {
        this.clientId = clientId;
        this.personalSettings = personalSettings;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<MCC> getPersonalSettings() {
        return personalSettings;
    }

    public void setPersonalSettings(List<MCC> personalSettings) {
        this.personalSettings = personalSettings;
    }
}
