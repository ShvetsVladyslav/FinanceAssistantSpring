package com.server.financeassistantspring.MenuController;

import com.server.financeassistantspring.Entity.Additional.Timestamp;
import com.server.financeassistantspring.Entity.Additional.UnixTimeParser;
import com.server.financeassistantspring.Entity.Main.Analyser;
import com.server.financeassistantspring.Entity.Main.Transaction;
import com.server.financeassistantspring.Entity.Main.User;
import com.server.financeassistantspring.Interfases.IAnalyserMenu;
import com.server.financeassistantspring.Repository.UserRepository;
import com.server.financeassistantspring.Repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/analyser")
public class AnalyserController implements IAnalyserMenu {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRepositoryCustom userRepositoryCustom;
    Analyser analyser = new Analyser();
    @Override
    @PostMapping("/writeoff")
    public double showWriteOff(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        return analyser.calculateWriteOff(extract);
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
    @GetMapping("/compare/writeoff")
    public boolean compareToBiggerWriteOff(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract1 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        Transaction[] extract2 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp3()), UnixTimeParser.timeParse(timestamp.getTimestamp4()), client.getPersonalToken()), Transaction[].class);
        return analyser.isWriteoffBigger(extract1, extract2);
    }
    @Override
    @GetMapping("/compare/refills")
    public boolean compareToBiggerRefills(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract1 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        Transaction[] extract2 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp3()), UnixTimeParser.timeParse(timestamp.getTimestamp4()), client.getPersonalToken()), Transaction[].class);
        return analyser.isRefillsBigger(extract1, extract2);
    }
    @Override
    @GetMapping("/equal/writeoff")
    public boolean compareToEqualWriteOff(String clientId, String account, Timestamp timestamp) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract1 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        Transaction[] extract2 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp3()), UnixTimeParser.timeParse(timestamp.getTimestamp4()), client.getPersonalToken()), Transaction[].class);
        return analyser.isWriteoffEquals(extract1, extract2);
    }
    @Override
    @GetMapping("/equal/refills")
    public boolean compareToEqualRefills(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestBody Timestamp timestamp) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract1 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        Transaction[] extract2 = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp3()), UnixTimeParser.timeParse(timestamp.getTimestamp4()), client.getPersonalToken()), Transaction[].class);
        return analyser.isRefillsEquals(extract1, extract2);
    }
    @Override
    @GetMapping("/prognosis")
    public double prognosis(@RequestParam(value = "clientId")String clientId) throws IOException, InterruptedException {
        User client = userRepository.findByClientId(clientId);
        return 0;
    }
    @Override
    @GetMapping("/bymcc")
    public List<Transaction> mccAnalyse(@RequestParam(value = "clientId")String clientId, @RequestParam(value = "group")String group) {
        User client = userRepository.findByClientId(clientId);
        return null;
    }
    @Override
    @GetMapping("/extract")
    public List<Transaction> showExtract(@RequestParam(value = "clientId")String clientId,@RequestParam(value = "account")String account, @RequestParam Timestamp timestamp) throws IOException {
        User client = userRepository.findByClientId(clientId);
        Transaction caller = new Transaction();
        Transaction[] extract = Transaction.mapper.readValue(caller.apiCall(account, UnixTimeParser.timeParse(timestamp.getTimestamp1()), UnixTimeParser.timeParse(timestamp.getTimestamp2()), client.getPersonalToken()), Transaction[].class);
        return Arrays.asList(extract);
    }
}