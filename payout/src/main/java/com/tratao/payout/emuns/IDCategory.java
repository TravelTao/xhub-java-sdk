package com.tratao.payout.emuns;

public enum IDCategory {
    IDCARD("idcard"),PASSPORT("passport"),DRIVER("driver"),RESIDENCE("residence"),WORKPERMIT("workpermit"),OTHER("other");

    public final String name;

    IDCategory(String name) {
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
