package com.tratao.payout.emuns;

public enum IDCategory {
    IDCARD("idcard"),PASSPORT("passport"),DRIVER("driver"),RESIDENCE("residence"),OTHER("other");

    public final String name;

    IDCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
