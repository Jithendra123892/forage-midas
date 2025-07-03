package com.jpmc.midascore.foundation;

import java.math.BigDecimal;

public class Transaction {
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;

    public Transaction() {}

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", amount=" + amount +
                '}';
    }
}
