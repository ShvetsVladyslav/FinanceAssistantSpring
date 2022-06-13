package com.server.financeassistantspring.Repository;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonalSettingsRepository extends MongoRepository<PersonalSettings,String> {
    PersonalSettings findByClientId(String clientId);
}
