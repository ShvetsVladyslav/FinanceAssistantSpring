package com.server.financeassistantspring.Entity.Main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//TODO: Fix pan card fill from API
@JsonIgnoreProperties( ignoreUnknown = true )
public class AccountParams {
    //@JsonProperty("id")
    private String id;
    //@JsonProperty("balance")
    private int balance;
    //@JsonProperty("creditLimit")
    private int creditLimit;
    //@JsonProperty("type")
    private String type;
    //@JsonProperty("cashbackType")
    private String cashbackType;
    //@JsonProperty("currencyCode")
    private int currencyCode;
    //@JsonProperty("sendId")
    private String sendID;
    //@JsonProperty("maskedPan")
    private String panCard;
    //@JsonProperty("iban")
    private String iban;

    public int getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(int currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCashbackType() {
        return cashbackType;
    }

    public void setCashbackType(String cashbackType) {
        this.cashbackType = cashbackType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCreditLimit() {
        double result = 0;
        StringBuffer temp = new StringBuffer(String.valueOf(this.creditLimit));
        if (temp.length()>2) {
            temp.insert(temp.length()-2,".");
            result = Double.parseDouble(temp.toString());
        }
        else {
            if(temp.length() == 2 & temp.charAt(0) == '-'){
                temp.insert(1,"0.0");
                result = Double.parseDouble(temp.toString());
            }
            else if (temp.length() == 2 & temp.charAt(0) != '-'){
                temp.insert(0,"0.");
                result = Double.parseDouble(temp.toString());
            }

            else {
                temp.insert(0,"0.0");
                result = Double.parseDouble(temp.toString());
            }
        }
        return result;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        double result = 0;
        StringBuffer temp = new StringBuffer(String.valueOf(this.balance));
        if (temp.length()>2) {
            temp.insert(temp.length()-2,".");
            result = Double.parseDouble(temp.toString());
        }
        else {
            if(temp.length() == 2 & temp.charAt(0) == '-'){
                temp.insert(1,"0.0");
                result = Double.parseDouble(temp.toString());
            }
            else if (temp.length() == 2 & temp.charAt(0) != '-'){
                temp.insert(0,"0.");
                result = Double.parseDouble(temp.toString());
            }

            else {
                temp.insert(0,"0.0");
                result = Double.parseDouble(temp.toString());
            }
        }
        return result;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getSendID() {
        return sendID;
    }

    public void setSendID(String sendID) {
        this.sendID = sendID;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }
}
