package com.tratao.payout.models;

import java.io.Serializable;

public class GetTokenRequest implements Serializable {
    private String appKey;
    private String secretKey;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
