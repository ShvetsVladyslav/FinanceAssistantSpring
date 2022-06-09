package com.server.financeassistantspring.Repository;

import com.server.financeassistantspring.Entity.Main.AccountParams;
import com.server.financeassistantspring.Entity.Main.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    User findByClientId(String clientId);
}
