package com.server.financeassistantspring.Entity.Main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.financeassistantspring.Interfases.IAPIUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.server.financeassistantspring.Interfases.IAPIUser.BASE_URL;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Transaction implements IAPIUser {
    private String id;
    private int time;
    private String description;
    private int mcc;
    private boolean hold;
    private int amount;
    private int operationAmount;
    private int currencyCode;
    private int commissionRate;
    private int cashbackAmount;
    private int balance;
    private String comment;
    private String receiptId;
    private String counterEdrpou;
    private String counterIban;

    //======================================

    @Override
    public String apiCall() throws IOException {
        System.out.println("Неверный набор параметров запуска");
        return null;
    }

    public String apiCall(String account, long from, String token) throws IOException {
        String JsonStr = null;
        final URL url = new URL(BASE_URL + "personal/statement/"+account+"/"+from);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("X-Token", token);
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
        catch (final Exception ex) {
            ex.printStackTrace();
            System.out.println("ex");
        }
        return JsonStr;
    }

    public String apiCall(String account, long from, long to, String token) throws IOException {
        String JsonStr = null;
        final URL url = new URL(BASE_URL + "personal/statement/" + account + "/" + from + "/" + to);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("X-Token", token);
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
        catch (final Exception ex) {
            ex.printStackTrace();
            System.out.println("ex");
        }
        return JsonStr;
    }

    //=======================================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public boolean isHold() {
        return hold;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }

    public double getAmount() {
        double result = 0;
        StringBuffer temp = new StringBuffer(String.valueOf(this.amount));
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(int operationAmount) {
        this.operationAmount = operationAmount;
    }

    public int getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(int currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(int commissionRate) {
        this.commissionRate = commissionRate;
    }

    public int getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(int cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getCounterEdrpou() {
        return counterEdrpou;
    }

    public void setCounterEdrpou(String counterEdrpou) {
        this.counterEdrpou = counterEdrpou;
    }

    public String getCounterIban() {
        return counterIban;
    }

    public void setCounterIban(String counterIban) {
        this.counterIban = counterIban;
    }
}
