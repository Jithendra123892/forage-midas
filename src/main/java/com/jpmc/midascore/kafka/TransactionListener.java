package com.jpmc.midascore.kafka;

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class TransactionListener {

    private static final Logger logger = LoggerFactory.getLogger(TransactionListener.class);

    @KafkaListener(
            topics = "${general.kafka-topic}",
            groupId = "midas-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(Transaction txn) {
        logger.info("✅ Received: {}", txn);
    }
}
