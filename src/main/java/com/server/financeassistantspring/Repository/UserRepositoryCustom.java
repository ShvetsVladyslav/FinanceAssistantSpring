package com.server.financeassistantspring.Repository;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Main.User;

public interface UserRepositoryCustom {
    void update(String clientId, User client);
}
