package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    public void send(Transaction txn) {
        kafkaTemplate.send("midas.transactions", txn);
    }
}
