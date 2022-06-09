package com.server.financeassistantspring.Entity.Additional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.financeassistantspring.Interfases.IAPIUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Currency implements IAPIUser {
    @JsonProperty("currencyCodeA")
    private int currencyCodeA;

    @JsonProperty("currencyCodeB")
    private int currencyCodeB;

    @JsonProperty("date")
    private long date;

    @JsonProperty("rateSell")
    private double rateSell;

    @JsonProperty("rateBuy")
    private double rateBuy;

    @JsonProperty("rateCross")
    private float rateCross;

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public void setCurrencyCodeA(int currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public int getCurrencyCodeB() {
        return currencyCodeB;
    }

    public void setCurrencyCodeB(int currencyCodeB) {
        this.currencyCodeB = currencyCodeB;
    }

    public long getDTM() {
        return date;
    }

    public void setDTM(long DTM) {
        this.date = DTM;
    }

    public double getRateSell() {
        return rateSell;
    }

    public void setRateSell(double rateSell) {
        this.rateSell = rateSell;
    }

    public double getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(double rateBuy) {
        this.rateBuy = rateBuy;
    }

    public float getRateCross() {
        return rateCross;
    }

    public void setRateCross(float rateCross) {
        this.rateCross = rateCross;
    }

    @Override
    public String toString() {
        return getCurrencyCodeA()+","+getCurrencyCodeB()+","+getRateBuy()+","+getRateSell()+","+getDTM();
    }

    @Override
    public String apiCall() throws IOException {
        String JsonStr = null;
        final URL url = new URL(BASE_URL + "bank/currency");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
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
            //ex.printStackTrace();
            JsonStr = new String(Files.readAllBytes(Paths.get("src/main/resources/CURRENCY.json")));
        }
        return JsonStr;
    }
    static public List<Currency> currencyFill() throws IOException {
        Currency caller = new Currency();
        Currency[] currList = Currency.mapper.readValue(caller.apiCall(),Currency[].class);
        return Arrays.asList(currList);
    }
}
