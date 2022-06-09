package com.server.financeassistantspring.Entity.Additional.MCC;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MCC {

    static ObjectMapper mapper = new ObjectMapper();
    @JsonProperty("mcc")
    private int mcc;
    @JsonProperty("group")
    private MCCGroup group;

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public MCCGroup getGroup() {
        return group;
    }

    public void setGroup(MCCGroup group) {
        this.group = group;
    }

    static public MCC[] mccFill() throws IOException {
        String JsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/MCC.json")));
        JsonNode data = new ObjectMapper().readTree(JsonStr);
        MCC[] mccList = mapper.readValue(data.toString(),MCC[].class);
        return mccList;
    }

    static public MCC createPersonalGroup(int thisMcc, String thisType, String thisDescription, String thisFullDescription, String thisShortDescription){
        MCC personalGroup = new MCC();
        personalGroup.setGroup(new MCCGroup());
        personalGroup.getGroup().setDescription(new Description());
        personalGroup.getGroup().setFullDescription(new Description());
        personalGroup.getGroup().setShortDescription(new Description());
        personalGroup.setMcc(thisMcc);
        personalGroup.getGroup().setType(thisType);
        personalGroup.getGroup().getDescription().setUk(thisDescription);
        personalGroup.getGroup().getFullDescription().setUk(thisFullDescription);
        personalGroup.getGroup().getShortDescription().setUk(thisShortDescription);
        return personalGroup;
    }
}
