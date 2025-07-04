package com.jpmc.midascore.foundation;

import jakarta.persistence.*;

@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    public Balance() {}

    public Balance(double amount) {
        this.amount = amount;
    }

    public Long getId() { return id; }
    public double getAmount() { return amount; }

    public void setId(Long id) { this.id = id; }
    public void setAmount(double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return String.format("Balance{id=%d, amount=%.2f}", id, amount);
    }
}
