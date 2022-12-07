package com.tratao.xcore;

public class Config {
    private String appKey;
    private String privateKey;
    private String secretKey;

    public Config(String appKey) {
        this.appKey = appKey;
    }

    public Config(String appKey, String secretKey, String privateKey) {
        this.appKey = appKey;
        this.secretKey = secretKey;
        this.privateKey = privateKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
