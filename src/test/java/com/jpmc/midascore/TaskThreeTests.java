package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

@SpringBootTest
public class TaskThreeTests {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Test
    public void task_three_verifier() throws InterruptedException {
        kafkaTemplate.send("midas.transactions", new Transaction(7L, 4L, BigDecimal.valueOf(161.79)));
        kafkaTemplate.send("midas.transactions", new Transaction(8L, 7L, BigDecimal.valueOf(22.22)));
        kafkaTemplate.send("midas.transactions", new Transaction(2L, 4L, BigDecimal.valueOf(160.79)));
        kafkaTemplate.send("midas.transactions", new Transaction(1L, 2L, BigDecimal.valueOf(90.60)));

        System.out.println("✅ Transactions sent. Check debugger.");
        Thread.sleep(30000); // Let listener process before test ends
    }
}
