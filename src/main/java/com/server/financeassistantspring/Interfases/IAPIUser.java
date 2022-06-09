package com.server.financeassistantspring.Interfases;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public interface IAPIUser {

    ObjectMapper mapper = new ObjectMapper();
    String BASE_URL = "https://api.monobank.ua/";
    String apiCall() throws IOException;

}
