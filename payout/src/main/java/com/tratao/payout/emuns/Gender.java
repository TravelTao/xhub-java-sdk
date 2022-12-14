package com.tratao.payout.emuns;

public enum Gender {
    MALE("male"),FEMALE("female");
    public final String name;

    Gender(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
