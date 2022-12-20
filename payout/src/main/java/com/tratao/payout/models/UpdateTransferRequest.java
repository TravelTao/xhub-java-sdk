package com.tratao.payout.models;

import com.tratao.payout.emuns.FundsSource;
import com.tratao.payout.emuns.Relationship;

public class UpdateTransferRequest {
    private String orderNo;
    private Relationship relationship;
    private String purpose;
    private FundsSource fundsSource;

    private Beneficiary beneficiary;
    private Payer payer;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
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
