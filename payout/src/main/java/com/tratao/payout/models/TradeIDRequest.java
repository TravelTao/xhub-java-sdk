package com.tratao.payout.models;

import java.io.Serializable;

public class TradeIDRequest implements Serializable {
    private String tradeId;

    public TradeIDRequest(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
}
