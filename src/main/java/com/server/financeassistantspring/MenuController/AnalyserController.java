package com.server.financeassistantspring.MenuController;

import com.server.financeassistantspring.Entity.Additional.MCC.MCC;
import com.server.financeassistantspring.Entity.Additional.Timestamp;
import com.server.financeassistantspring.Entity.Additional.UnixTimeParser;
import com.server.financeassistantspring.Entity.Main.Analyser;
import com.server.financeassistantspring.Entity.Main.Transaction;
import com.server.financeassistantspring.Entity.Main.User;
import com.server.financeassistantspring.Interfases.IAnalyserMenu;
import com.server.financeassistantspring.Repository.UserRepository;
import com.server.financeassistantspring.Repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/analyser")
public class AnalyserController implements IAnalyserMenu {
    @Autowired
    UserRepository userRepository;
    Analyser analyser = new Analyser();
    @Override
    @PostMapping("/writeoff")
    public double showWriteOff(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        return analyser.calculateWriteOff(extract)*(-1);
    }
    @Override
    @PostMapping("/refills")
    public double showRefills(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        return analyser.calculateRefills(extract);
    }
    @Override
    @PostMapping("/moneycircle")
    public double showMoneyCircle(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        return analyser.calculateRefills(extract) - analyser.calculateWriteOff(extract);
    }
    @Override
    @PostMapping("/compare/writeoff")
    public boolean compareToBiggerWriteOff(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract1 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        Transaction[] extract2 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp3()), UnixTimeParser.timeParse(timestamp.getTimestamp4()), client.getPersonalToken()), Transaction[].class);
        return analyser.isWriteoffBigger(extract1, extract2);
    }
    @Override
    @PostMapping("/compare/refills")
    public boolean compareToBiggerRefills(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract1 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        Transaction[] extract2 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp3()), UnixTimeParser.timeParse(timestamp.getTimestamp4()), client.getPersonalToken()), Transaction[].class);
        return analyser.isRefillsBigger(extract1, extract2);
    }
    @Override
    @PostMapping("/equal/writeoff")
    public boolean compareToEqualWriteOff(String clientId, String account, Timestamp timestamp) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract1 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        Transaction[] extract2 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp3()), UnixTimeParser.timeParse(timestamp.getTimestamp4()), client.getPersonalToken()), Transaction[].class);
        return analyser.isWriteoffEquals(extract1, extract2);
    }
    @Override
    @PostMapping("/equal/refills")
    public boolean compareToEqualRefills(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract1 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        Transaction[] extract2 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp3()), UnixTimeParser.timeParse(timestamp.getTimestamp4()), client.getPersonalToken()), Transaction[].class);
        return analyser.isRefillsEquals(extract1, extract2);
    }
    @Override
    @PostMapping("/prognosis")
    public double prognosis(@RequestParam(value = "clientId")String clientId) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        return 0;
    }
    @Override
    @PostMapping("/bymcc")
    public List<Transaction> mccAnalyse(@RequestParam(value = "clientId")String clientId, @RequestParam(value = "group")String group,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException {
        List<MCC> mccList = new ArrayList<>();
        User client = userRepository.findByClientId(clientId);
        for (int i = 0; i<client.getPersonalSettings().getPersonalSettings().size(); i++){
            if (Objects.equals(client.getPersonalSettings().getPersonalSettings().get(i).getGroup().getType(), group)){
                mccList.add(client.getPersonalSettings().getPersonalSettings().get(i));
            }
        }
        for (int i = 0; i < client.getMccListByGroup().length;i++){
            if (Objects.equals(client.getMccListByGroup()[i].getGroup().getType(), group)){
                mccList.add(client.getMccListByGroup()[i]);
            }
        }
        if (mccList.size() == 0){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"group not found");
        }
        else {
            Transaction caller = new Transaction();
            Transaction[] extract = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
            return analyser.sortByMcc(Arrays.asList(extract), mccList);
        }
    }
    @Override
    @PostMapping("/extract")
    public List<Transaction> showExtract(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        return Arrays.asList(extract);
    }
}