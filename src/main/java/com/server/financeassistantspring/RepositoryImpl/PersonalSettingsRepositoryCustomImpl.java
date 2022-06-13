package com.server.financeassistantspring.RepositoryImpl;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import com.server.financeassistantspring.Repository.PersonalSettingsRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalSettingsRepositoryCustomImpl implements PersonalSettingsRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PersonalSettings addClientSettings(PersonalSettings client, MCC mcc) {
        Query query = new Query().addCriteria(Criteria.where("clientId").is(client.getClientId()));
        for (int i = 0; i < client.getPersonalSettings().size(); i++) {
            if (client.getPersonalSettings().get(i).getMcc() == mcc.getMcc()){
                client.getPersonalSettings().remove(i);
            }
        }
        mongoTemplate.findAndReplace(query, client);
        return client;
    }

    @Override
    public PersonalSettings deleteClientSettings(PersonalSettings client, MCC mcc) {
        Query query = new Query().addCriteria(Criteria.where("clientId").is(client.getClientId()));
        client.getPersonalSettings().add(mcc);
        mongoTemplate.findAndReplace(query, client);
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
        mongoTemplate.findAndReplace(query, client);
        return client;
    }
}
