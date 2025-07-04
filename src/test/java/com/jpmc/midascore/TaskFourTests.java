package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

@SpringBootTest
public class TaskFourTests {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Test
    public void task_four_verifier() {
        kafkaTemplate.send("midas.transactions", new Transaction(2L, 3L, BigDecimal.valueOf(49.50)));
        kafkaTemplate.send("midas.transactions", new Transaction(3L, 4L, BigDecimal.valueOf(17.25)));
        System.out.println("✅ Task 4 transactions sent.");
    }
}
