package com.server.financeassistantspring.RepositoryImpl;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import com.server.financeassistantspring.Entity.Main.User;
import com.server.financeassistantspring.Repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public void update(String clientId, User client) {
        Query query = new Query().addCriteria(Criteria.where("clientId").is(clientId));
        Update update = new Update().set("accounts", client.getAccount());
        mongoTemplate.findAndModify(query, update, User.class);
    }
}
