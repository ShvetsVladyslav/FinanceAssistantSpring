package com.server.financeassistantspring.Entity.Additional.MCC;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MCCGroup {

    static ObjectMapper mapper = new ObjectMapper();
    @JsonProperty("type")
    private String type;
    @JsonProperty("description")
    private Description description;
    @JsonProperty("fullDescription")
    private Description fullDescription;
    @JsonProperty("shortDescription")
    private Description shortDescription;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Description getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(Description fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Description getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(Description shortDescription) {
        this.shortDescription = shortDescription;
    }
}