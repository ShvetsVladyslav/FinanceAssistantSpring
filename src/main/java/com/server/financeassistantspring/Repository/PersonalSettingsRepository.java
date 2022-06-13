package com.server.financeassistantspring.Repository;

import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonalSettingsRepository extends MongoRepository<PersonalSettings,String> {
    PersonalSettings findByClientId(String clientId);
}
