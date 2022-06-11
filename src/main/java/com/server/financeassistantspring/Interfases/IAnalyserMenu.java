package com.server.financeassistantspring.Interfases;

import com.server.financeassistantspring.Entity.Additional.Timestamp;
import com.server.financeassistantspring.Entity.Main.Transaction;
import com.server.financeassistantspring.Entity.Main.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface IAnalyserMenu {
    double showWriteOff(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException;
    double showRefills(@RequestParam(value = "clientId")String clientId, @RequestParam(value = "account")String account,@RequestBody Timestamp timestamp) throws IOException;
    double showMoneyCircle(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException;
    boolean compareToBiggerWriteOff(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException;
    boolean compareToBiggerRefills(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException;
    boolean compareToEqualWriteOff(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException;
    boolean compareToEqualRefills(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException;
    double prognosis(@RequestParam(value = "clientId")String clientId) throws IOException, InterruptedException;
    List<Transaction> mccAnalyse(@RequestParam(value = "clientId")String clientId);
    List<Transaction> showExtract(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestParam Timestamp timestamp) throws IOException;
}
