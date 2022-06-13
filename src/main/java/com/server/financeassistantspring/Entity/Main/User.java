package com.server.financeassistantspring.Entity.Main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.financeassistantspring.Entity.Additional.Currency;
import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.PersonalSettings;
import com.server.financeassistantspring.Interfases.IAPIUser;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "User")
@JsonIgnoreProperties( ignoreUnknown = true )
public class User implements IAPIUser {
    private String personalToken;

    @Field("clientId")
    @JsonProperty("clientId")
    private String clientId;

    @Field("name")
    @JsonProperty("name")
    private String name;

    @JsonProperty("webHookUrl")
    private String webHookUrl;

    @JsonProperty("accounts")
    private List<AccountParams> account;

    @JsonProperty("permissions")
    private String permission;

    private MCC[] mccListByGroup;

    @Field("personalMcc")
    private PersonalSettings personalSettings;
    //TODO: Create saving process for personal MCC
    private Currency[] currList;

    //OTHER METHOD
    @Override
    public String apiCall() throws IOException {
        String JsonStr = null;

        final URL url = new URL(BASE_URL + "personal/client-info");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("X-Token", this.personalToken);
        con.setConnectTimeout(50000);
        con.setReadTimeout(7000000);

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            JsonStr = content.toString();
        }
        return JsonStr;
    }

    public double getTotalBalance(List<Currency> currencies){
        double balance = 0;
        for (int i = 0; i < this.account.size(); i++) {
            if (this.account.get(i).getCurrencyCode() == 980) {
                balance += this.account.get(i).getBalance();
            } else {
                for (Currency currency : currencies) {
                    if (currency.getCurrencyCodeA() == this.account.get(i).getCurrencyCode()) {
                        balance += this.account.get(i).getBalance() / currency.getRateSell();
                    }
                }
            }
        }
        return balance;
    }
    // GETTER AND SETTER

    public List<AccountParams> getAccount() {
        return account;
    }

    public void setAccount(List<AccountParams> account) {
        this.account = account;
    }

    public String getWebHookUrl() {
        return webHookUrl;
    }

    public void setWebHookUrl(String webHookUrl) {
        this.webHookUrl = webHookUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return clientId;
    }

    public void setId(String id) {
        this.clientId = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPersonalToken() {
        return personalToken;
    }

    public void setPersonalToken(String personalToken) {
        this.personalToken = personalToken;
    }

    public MCC[] getMccListByGroup() {
        return mccListByGroup;
    }

    public void setMccListByGroup(MCC[] mccListByGroup) throws IOException {
        this.mccListByGroup = MCC.mccFill();
    }
    public Currency[] getCurrList() {
        return currList;
    }

    public void setCurrList(Currency[] currList) {
        this.currList = currList;
    }

    public PersonalSettings getPersonalSettings() {
        return personalSettings;
    }

    public void setPersonalSettings(PersonalSettings personalSettings) {
        this.personalSettings = personalSettings;
    }
}