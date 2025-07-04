package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

@SpringBootTest
public class TaskTwoTests {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Test
    public void task_two_verifier() {
        Transaction txn = new Transaction(6L, 7L, BigDecimal.valueOf(122.86));
        kafkaTemplate.send("midas.transactions", txn);
        System.out.println("✅ Sent: " + txn);
    }
}
