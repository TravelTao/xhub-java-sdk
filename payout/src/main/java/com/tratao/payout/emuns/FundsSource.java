package com.tratao.payout.emuns;

public enum FundsSource {
    BUSINESS("Business income"),
    EMPLOYMENT("Employment income"),
    PART_TIME("Part-time income"),
    SAVING_DEPOSITS("Saving deposits");
    public final String name;

    FundsSource(String name) {
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
