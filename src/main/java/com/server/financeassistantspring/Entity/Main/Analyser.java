package com.server.financeassistantspring.Entity.Main;


import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.UnixTimeParser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Analyser{

    public double calculateWriteOff(Transaction[] extract){
        double result = 0;
        try{
            for (Transaction transction: extract) {
                if (transction.getAmount() < 0){
                    result += transction.getAmount();
                }
            }
            return result;
        }
        catch (ArrayIndexOutOfBoundsException exception){
            return result;
        }
    }

    public double calculateRefills(Transaction[] extract){
        double result = 0;
        try {
            for (Transaction transction: extract) {
                if (transction.getAmount() > 0){
                    result += transction.getAmount();
                }
            }
            return result;
        }
        catch (ArrayIndexOutOfBoundsException exception){
            return result;
        }
    }

    public Transaction[] amountForPeriod(Transaction[] extract, long from, long to){
        if (extract[0] != null) {
            ArrayList<Transaction> result = new ArrayList<>();
            for (Transaction transaction: extract) {
                if(transaction.getTime() <= to & transaction.getTime() >= from){
                    result.add(transaction);
                }
            }
            return result.toArray(new Transaction[0]);
        }
        else return null;
    }

    public List<Transaction> sortByMCC(Transaction[] extract, List<MCC> mccList, String group){
        ArrayList<Transaction> result = new ArrayList<>();
        if (extract[0] != null){
            for (Transaction transaction: extract) {
                for (MCC mcc: mccList) {
                    if (Objects.equals(mcc.getGroup().getType(), group) & Objects.equals(mcc.getMcc(), String.valueOf(transaction.getMcc()))){
                        result.add(transaction);
                    }
                }

            }
            return result;
        }
        else return null;
    }

    public boolean isWriteoffBigger(Transaction[] extract1, Transaction[] extract2){
        if (extract1 != null & extract2 != null){
            if (calculateWriteOff(extract1) < calculateWriteOff(extract2)){
                return true;
            }
            return false;
        }
        else return false;
    }

    public boolean isWriteoffEquals(Transaction[] extract1, Transaction[] extract2){
        if (extract1 != null & extract2 != null){
            if (calculateWriteOff(extract1) == calculateWriteOff(extract2)){
                return true;
            }
            else return false;
        }
        else return false;
    }

    public boolean isRefillsBigger(Transaction[] extract1, Transaction[] extract2){
        if (extract1 != null & extract2 != null){
            if (calculateRefills(extract1) > calculateRefills(extract2)){
                return true;
            }
            return false;
        }
        else return false;
    }
    public boolean isRefillsEquals(Transaction[] extract1, Transaction[] extract2){
        if (extract1 != null & extract2 != null){
            if (calculateRefills(extract1) == calculateRefills(extract2)){
                return true;
            }
            else return false;
        }
        else return false;
    }

    public double prognosis(User client) throws IOException, InterruptedException {
        double result = 0;
        double ratio = 0;
        double writeoffRatio;
        double reffilsRatio;
        Transaction[] temp1;
        Transaction[] temp2;
        Transaction[] temp3;
        Date date = new Date ();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Transaction caller = new Transaction();
        for (int i = 0; i < client.getAccount().size(); i++){
            temp1 = Transaction.mapper.readValue(caller.apiCall(client.getAccount().get(i).getId(), (UnixTimeParser.timeParse(sdf.format(date.getTime())) - (4*UnixTimeParser.oneWeek)), UnixTimeParser.timeParse(sdf.format(date.getTime())),client.getPersonalToken()), Transaction[].class);
            TimeUnit.SECONDS.sleep(70);
            temp2 = Transaction.mapper.readValue(caller.apiCall(client.getAccount().get(i).getId(), (UnixTimeParser.timeParse(sdf.format(date.getTime())) - (8*UnixTimeParser.oneWeek)), (UnixTimeParser.timeParse(sdf.format(date.getTime())) - (4*UnixTimeParser.oneWeek)),client.getPersonalToken()), Transaction[].class);
            TimeUnit.SECONDS.sleep(70);
            temp3 = Transaction.mapper.readValue(caller.apiCall(client.getAccount().get(i).getId(), (UnixTimeParser.timeParse(sdf.format(date.getTime())) - (12*UnixTimeParser.oneWeek)), (UnixTimeParser.timeParse(sdf.format(date.getTime())) - (8*UnixTimeParser.oneWeek)),client.getPersonalToken()), Transaction[].class);
            writeoffRatio = ((calculateWriteOff(temp2)/calculateWriteOff(temp1)) + (calculateWriteOff(temp3)/calculateWriteOff(temp2))/2);
            reffilsRatio = ((calculateRefills(temp2)/calculateRefills(temp1)) + (calculateRefills(temp3)/calculateRefills(temp2))/2);
            ratio += (reffilsRatio - writeoffRatio);
        }
        if (ratio > 0){
            for (int i = 0; i < client.getAccount().size(); i++){
                TimeUnit.SECONDS.sleep(70);
                temp1 = Transaction.mapper.readValue(caller.apiCall(client.getAccount().get(i).getId(), (UnixTimeParser.timeParse(sdf.format(date.getTime())) - (4*UnixTimeParser.oneWeek)), UnixTimeParser.timeParse(sdf.format(date.getTime())),client.getPersonalToken()), Transaction[].class);
                result += (calculateRefills(temp1)*ratio - calculateWriteOff(temp1));
            }
            return result;
        }
        else if(ratio < 0){
            for (int i = 0; i < client.getAccount().size(); i++){
                TimeUnit.SECONDS.sleep(70);
                temp1 = Transaction.mapper.readValue(caller.apiCall(client.getAccount().get(i).getId(), (UnixTimeParser.timeParse(sdf.format(date.getTime())) - (4*UnixTimeParser.oneWeek)), UnixTimeParser.timeParse(sdf.format(date.getTime())),client.getPersonalToken()), Transaction[].class);
                result += (calculateRefills(temp1) - (-1*(calculateWriteOff(temp1)*ratio)));
            }
            return result;
        }
        else return result;
    }
}