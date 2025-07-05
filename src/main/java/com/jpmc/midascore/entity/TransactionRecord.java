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
    @Column(nullable = false)
    private BigDecimal incentive;

    public TransactionRecord(BigDecimal amount, BigDecimal incentive, UserRecord sender, UserRecord receiver) {
        this.amount = amount;
        this.incentive = incentive;
        this.sender = sender;
        this.receiver = receiver;
    }

    public BigDecimal getAmount() { return amount; }

    public UserRecord getSender() { return sender; }

    public UserRecord getReceiver() { return receiver; }
}
