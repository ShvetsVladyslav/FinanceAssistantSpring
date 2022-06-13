package com.server.financeassistantspring.Entity.Additional.MCC;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Description {
    static ObjectMapper mapper = new ObjectMapper();
    @JsonProperty("uk")
    private String uk;
    @JsonProperty("en")
    private String en;
    @JsonProperty("ru")
    private String ru;

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }
}
