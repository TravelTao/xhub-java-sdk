package com.tratao.payout.emuns;

public enum Relationship {
    CHILDREN("Children"),
    PARENT("Parent"),
    RELATIVE("Relative"),
    SELF("Self"),
    SIBLING("Sibling"),
    SPOUSE("Spouse"),
    GRANDPARENT("Grandparent"),
    GRANDCHILDREN("Grandchildren"),
    MATERNALGRANDPARENT("MaternalGrandparent"),
    MATERNALGRANDCHILDREN("MaternalGrandchildren");;
    public final String name;

    Relationship(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
