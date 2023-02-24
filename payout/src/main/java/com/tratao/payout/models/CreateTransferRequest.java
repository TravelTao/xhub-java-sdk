package com.tratao.payout.models;

import com.alibaba.fastjson2.annotation.JSONField;
import com.tratao.payout.emuns.FundsPurpose;
import com.tratao.payout.emuns.FundsSource;
import com.tratao.payout.emuns.Relationship;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

public class CreateTransferRequest implements Serializable {
    @NotEmpty(message = "can not be null")
    private String orderNo;
    @NotEmpty(message = "can not be null")
    private String targetCurrency;
    @Positive(message = "should be larger than 0")
    private double targetAmount;
    @NotEmpty(message = "can not be null")
    private String sourceCurrency;
    @Positive(message = "should be larger than 0")
    private double sourceAmount;
    @NotNull(message = "can not be null")
    private Relationship relationship;
    @NotNull(message = "can not be null")
    private FundsPurpose purpose;
    @NotNull(message = "can not be null")
    private FundsSource fundsSource;

    @Valid
    @NotNull(message = "can not be null")
    private Beneficiary beneficiary;
    @Valid
    @NotNull(message = "can not be null")
    private Payer payer;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public FundsPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(FundsPurpose purpose) {
        this.purpose = purpose;
    }

    public FundsSource getFundsSource() {
        return fundsSource;
    }

    public void setFundsSource(FundsSource fundsSource) {
        this.fundsSource = fundsSource;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }
}
