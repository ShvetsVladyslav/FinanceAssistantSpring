package com.server.financeassistantspring.RepositoryImpl;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import com.server.financeassistantspring.Repository.PersonalSettingsRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalSettingsRepositoryCustomImpl implements PersonalSettingsRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PersonalSettings addClientSettings(PersonalSettings client, MCC mcc) {
        Query query = new Query().addCriteria(Criteria.where("clientId").is(client.getClientId()));
        client.getPersonalSettings().add(mcc);
        Update update = new Update().set("personalSettings", client.getPersonalSettings());
        mongoTemplate.findAndModify(query, update, PersonalSettings.class);
        return client;
    }

    @Override
    public PersonalSettings deleteClientSettings(PersonalSettings client, MCC mcc) {
        Query query = new Query().addCriteria(Criteria.where("clientId").is(client.getClientId()));
        client.getPersonalSettings().remove(mcc);
        Update update = new Update().set("personalSettins", client.getPersonalSettings());
        mongoTemplate.findAndModify(query, update, PersonalSettings.class);
        return client;
    }

    @Override
    public PersonalSettings updateClientSettings(PersonalSettings client, MCC mcc) {
        Query query = new Query().addCriteria(Criteria.where("clientId").is(client.getClientId()));
        for (int i = 0; i < client.getPersonalSettings().size(); i++) {
            if (client.getPersonalSettings().get(i).getMcc() == mcc.getMcc()){
                client.getPersonalSettings().set(i, mcc);
            }
        }
        Update update = new Update().set("personalSettins", client.getPersonalSettings());
        mongoTemplate.findAndModify(query, update, PersonalSettings.class);
        return client;
    }
}
