package com.mlio.mylifeinorder.mylifeinorder1.model;

import java.math.BigDecimal;

public class Subscription extends History {

    private BigDecimal amount;
    private int per; // perMonth, perWeek, etc

    public Subscription(BigDecimal amount, int per) {
        this.amount = amount;
        this.per = per;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getPer() {
        return per;
    }

    public void setPer(int per) {
        this.per = per;
    }
}
