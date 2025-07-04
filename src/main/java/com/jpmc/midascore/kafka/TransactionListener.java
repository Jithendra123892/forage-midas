package com.jpmc.midascore.kafka;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionListener {

    private final UserRepository userRepo;
    private final TransactionRecordRepository txnRepo;

    public TransactionListener(UserRepository userRepo, TransactionRecordRepository txnRepo) {
        this.userRepo = userRepo;
        this.txnRepo = txnRepo;
    }

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-group")
    public void listen(Transaction txn) {
        Optional<UserRecord> senderOpt = userRepo.findById(txn.getSenderId());
        Optional<UserRecord> receiverOpt = userRepo.findById(txn.getReceiverId());

        if (senderOpt.isEmpty() || receiverOpt.isEmpty()) return;

        UserRecord sender = senderOpt.get();
        UserRecord receiver = receiverOpt.get();

        if (sender.getBalance().compareTo(txn.getAmount()) < 0) return;

        sender.setBalance(sender.getBalance().subtract(txn.getAmount()));
        receiver.setBalance(receiver.getBalance().add(txn.getAmount()));

        userRepo.save(sender);
        userRepo.save(receiver);

        TransactionRecord record = new TransactionRecord(txn.getAmount(), sender, receiver);
        txnRepo.save(record);
    }
}
