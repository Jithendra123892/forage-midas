package com.jpmc.midascore.foundation;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal amount;

    public Balance(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Balance{" + "amount=" + amount + '}';
    }
}
