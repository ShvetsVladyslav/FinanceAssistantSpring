package com.server.financeassistantspring.Repository;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Main.User;

public interface UserRepositoryCustom {
    User addClientSettings(User client, MCC mcc);
    User deleteClientSettings(User client, MCC mcc);
    User updateClientSettings(User client, MCC mcc);
}
