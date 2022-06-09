package com.server.financeassistantspring.Interfases;

import com.server.financeassistantspring.Entity.Main.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

public interface ILoginMenu {
    @GetMapping("/login")
    User Login(@RequestParam(value = "token") String token) throws IOException;

    String getMonoLink();
    String getTokenLink();
}
