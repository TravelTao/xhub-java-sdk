package com.tratao.payout.models;

import com.tratao.payout.emuns.FundsSource;
import com.tratao.payout.emuns.Relationship;

public class Payment {
    private String orderNo;
    private String targetCurrency;
    private String targetAmount;
    private String sourceCurrency;
    private String sourceAmount;
    private Relationship relationship;
    private String purpose;
    private FundsSource fundsSource;

    private Beneficiary beneficiary;
    private Payer payer;
}
