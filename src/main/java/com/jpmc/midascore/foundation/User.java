package com.jpmc.midascore.foundation;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

    public User() {}

    public User(Long id, String name, Balance balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Balance getBalance() { return balance; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBalance(Balance balance) { this.balance = balance; }
}
