package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

@SpringBootTest
public class TaskFiveTests {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Test
    public void task_five_verifier() {
        kafkaTemplate.send("midas.transactions", new Transaction(5L, 6L, BigDecimal.valueOf(33.33)));
        kafkaTemplate.send("midas.transactions", new Transaction(6L, 7L, BigDecimal.valueOf(10.00)));
        kafkaTemplate.send("midas.transactions", new Transaction(7L, 1L, BigDecimal.valueOf(11.00)));
        System.out.println("✅ Task 5 transactions sent.");
    }
}
