package com.tratao.payout.emuns;

public enum AccountType {
    BANK("bank"),ALIPAY("alipay"),WECHAT("wechat");
    public final String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
