package com.server.financeassistantspring.RepositoryImpl;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import com.server.financeassistantspring.Entity.Main.User;
import com.server.financeassistantspring.Repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public User addClientSettings(User client, MCC mcc) {
        Query query = new Query().addCriteria(Criteria.where("clientId").is(client.getId()));
        for (int i = 0; i < client.getPersonalSettings().getPersonalSettings().size(); i++) {
            if (client.getPersonalSettings().getPersonalSettings().get(i).getMcc() == mcc.getMcc()){
                client.getPersonalSettings().getPersonalSettings().add(mcc);
            }
        }
        mongoTemplate.findAndReplace(query, client);
        return null;
    }

    @Override
    public User deleteClientSettings(User client, MCC mcc) {
        Query query = new Query().addCriteria(Criteria.where("clientId").is(client.getId()));
        //client.getPersonalSettings().add(mcc);
        mongoTemplate.findAndReplace(query, client);
        return client;
    }

    @Override
    public User updateClientSettings(User client, MCC mcc) {
        Query query = new Query().addCriteria(Criteria.where("clientId").is(client.getId()));
        for (int i = 0; i < client.getPersonalSettings().getPersonalSettings().size(); i++) {
            if (client.getPersonalSettings().getPersonalSettings().get(i).getMcc() == mcc.getMcc()){
                client.getPersonalSettings().getPersonalSettings().add(mcc);
            }
        }
        mongoTemplate.findAndReplace(query, client);
        return client;
    }
}
