package com.jpmc.midascore.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class UserRecord {
    @Id
    private Long id;

    private String name;

    @Column(nullable = false)
    private BigDecimal balance;

    public UserRecord() {}

    public UserRecord(Long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }


    public Long getId() { return id; }

    public String getName() { return name; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
