package com.jpmc.midascore.foundation;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    private Long id;

    private String name;

    @Column(nullable = false)
    private BigDecimal balance;

    public User() {}

    public User(Long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getBalance() { return balance; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
