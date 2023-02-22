package com.tratao.payout.models;

public class BalanceRequest {
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
