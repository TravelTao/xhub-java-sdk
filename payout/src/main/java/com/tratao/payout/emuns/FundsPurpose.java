package com.tratao.payout.emuns;

public enum FundsPurpose {
    FAMILY_SUPPORT("Family support"),
    SALARY("Salary");
    public final String name;

    FundsPurpose(String name) {
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
