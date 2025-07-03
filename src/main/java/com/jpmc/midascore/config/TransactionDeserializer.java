package com.jpmc.midascore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.midascore.foundation.Transaction;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TransactionDeserializer implements Deserializer<Transaction> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Transaction deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Transaction.class);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to deserialize Transaction", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
    @Override
    public void close() {}
}
