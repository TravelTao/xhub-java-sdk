package com.tratao.payout.models;

import java.io.Serializable;

public class GetRateRequest implements Serializable {
    private String sourceCurrency;
    private String targetCurrency;

    public GetRateRequest() {

    }

    public GetRateRequest(String sourceCurrency, String targetCurrency) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
