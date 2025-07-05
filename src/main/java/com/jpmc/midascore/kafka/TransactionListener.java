package com.jpmc.midascore.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionListener {

    private final UserRepository userRepo;
    private final TransactionRecordRepository txnRepo;
    private final RestTemplate restTemplate;

    public TransactionListener(UserRepository userRepo, TransactionRecordRepository txnRepo, RestTemplate restTemplate) {
        this.userRepo = userRepo;
        this.txnRepo = txnRepo;
        this.restTemplate = restTemplate;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Incentive {
        private BigDecimal amount;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }
    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-group")
    public void listen(Transaction txn) {
        Optional<UserRecord> senderOpt = userRepo.findById(txn.getSenderId());
        Optional<UserRecord> receiverOpt = userRepo.findById(txn.getReceiverId());

        if (senderOpt.isEmpty() || receiverOpt.isEmpty()) return;

        UserRecord sender = senderOpt.get();
        UserRecord receiver = receiverOpt.get();

        if (sender.getBalance().compareTo(txn.getAmount()) < 0) return;

        // Fetch incentive
        Incentive incentive = restTemplate.postForObject(
                "http://localhost:8080/incentive",
                txn,
                Incentive.class
        );

        BigDecimal incentiveAmount = (incentive != null) ? incentive.getAmount() : BigDecimal.ZERO;

        // Update balances
        sender.setBalance(sender.getBalance().subtract(txn.getAmount()));
        receiver.setBalance(receiver.getBalance().add(txn.getAmount()).add(incentiveAmount));

        // Save sender and receiver
        userRepo.save(sender);
        userRepo.save(receiver);

        // Save transaction
        TransactionRecord record = new TransactionRecord(txn.getAmount(), incentiveAmount, sender, receiver);
        txnRepo.save(record);

        // ✅ Print Wilbur’s balance if exists
        Optional<UserRecord> wilburOpt = userRepo.findAll().stream().filter(user -> "wilbur".equalsIgnoreCase(user.getName())).findFirst();
        System.out.println("📦 Receiver Name: " + receiver.getName());
        System.out.println("📥 Received transaction: " + txn);
        wilburOpt.ifPresent(wilbur -> System.out.println("💰 Wilbur's final balance: " + wilbur.getBalance().toBigInteger()));
        System.out.flush(); // ✅ forces output to appear in console during tests



    }



}
