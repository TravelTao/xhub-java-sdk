package com.tratao.payout.models;

import com.google.common.base.Strings;

import java.security.PublicKey;

public class PaymentStatus {
    /**
     * error message
     */
    private String message;
    /**
     * payment status
     * - pending
     * - awaiting_transfer
     * - transferring
     * - completed
     * - failed
     * - canceled
     */
    private String status;
    /**
     * tradeId, should store with payment
     */
    private String tradeId;

    public PaymentStatus() {

    }

    public PaymentStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 判断请求是否成功
     *
     * @return true or false
     */
    public boolean isSuccess() {
        if (Strings.isNullOrEmpty(this.status)) {
            return false;
        }
        return !this.status.equals("-1");
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

    @Override
    public String toString() {
        return String.format("{ \"status\": \"%s\", \"message\": \"%s\"}", status, message);
    }
}
