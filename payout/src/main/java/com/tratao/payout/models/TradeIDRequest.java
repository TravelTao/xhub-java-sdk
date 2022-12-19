package com.tratao.payout.models;

public class TradeIDRequest {
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
