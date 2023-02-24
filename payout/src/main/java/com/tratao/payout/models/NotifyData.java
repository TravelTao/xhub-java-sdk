package com.tratao.payout.models;

import com.google.common.base.Strings;

import java.io.Serializable;

public class NotifyData implements Serializable {
    private String event;
    private String id;
    private String status;
    private double amount;
    private double targetAmount;
    private double sourceAmount;
    // error message
    private String message;

    public NotifyData() {

    }

    /**
     * check the payment is transferred success.
     * @return true or false
     */
    public boolean isCompleted() {
        if (Strings.isNullOrEmpty(this.status)) {
            return false;
        }
        return this.status.equals("completed");
    }

    /**
     * check the payment can confirm transfer or not
     * true mean that can call confirm transfer API
     * false mean that can not call confirm transfer API
     * @return true or false
     */
    public boolean canConfirmTransfer() {
        if (Strings.isNullOrEmpty(this.status)) {
            return false;
        }
        return this.status.equals("awaiting_transfer");
    }

    /**
     * check the payment status is transferred failed
     * if true, can get the message to know what's happened by the payment transfer.
     * @return true or false
     */
    public boolean isTransferFailed() {
        if (Strings.isNullOrEmpty(this.status)) {
            return false;
        }
        return this.status.equals("failed");
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
