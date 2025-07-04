package com.jpmc.midascore.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    private UserRecord sender;

    @ManyToOne
    private UserRecord receiver;

    public TransactionRecord() {}

    public TransactionRecord(BigDecimal amount, UserRecord sender, UserRecord receiver) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
    }

    public BigDecimal getAmount() { return amount; }

    public UserRecord getSender() { return sender; }

    public UserRecord getReceiver() { return receiver; }
}
