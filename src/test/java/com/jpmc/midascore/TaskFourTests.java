package com.jpmc.midascore;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

@SpringBootTest
public class TaskFourTests {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void task_four_verifier() throws InterruptedException {
       
        userRepository.deleteAll();
        userRepository.save(new UserRecord(2L, "alice", new BigDecimal("100.00")));
        userRepository.save(new UserRecord(3L, "bob", new BigDecimal("100.00")));
        userRepository.save(new UserRecord(4L, "wilbur", new BigDecimal("100.00")));


        kafkaTemplate.send("midas.transactions", new Transaction(2L, 3L, new BigDecimal("49.50")));
        kafkaTemplate.send("midas.transactions", new Transaction(3L, 4L, new BigDecimal("17.25")));


        Thread.sleep(5000);


        BigDecimal aliceExpected = new BigDecimal("50.50");  // 100 - 49.50
        BigDecimal bobExpected = new BigDecimal("132.25");   // 100 + 49.50 + 0.00 (no incentive)
        BigDecimal wilburExpected = new BigDecimal("117.25");// 100 + 17.25 + 0.00 (no incentive)

        assertEquals(aliceExpected.stripTrailingZeros(), userRepository.findById(2L).get().getBalance().stripTrailingZeros());
        assertEquals(bobExpected.stripTrailingZeros(), userRepository.findById(3L).get().getBalance().stripTrailingZeros());
        assertEquals(wilburExpected.stripTrailingZeros(), userRepository.findById(4L).get().getBalance().stripTrailingZeros());

        System.out.println("✅ Task 4 assertions passed and transactions sent.");
    }
}
