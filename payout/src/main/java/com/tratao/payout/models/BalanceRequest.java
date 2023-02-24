package com.tratao.payout.models;

import java.io.Serializable;

public class BalanceRequest implements Serializable {
    private String currency;

    public BalanceRequest() {

    }

    public BalanceRequest(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
