package com.adam.model;

public enum Orderings {
    NONE(0,"NONE"), ASC(1,"ASC"), DESC(2, "DESC");

    int orderBy;
    String value;

    Orderings(int orderBy, String value) {
        this.orderBy = orderBy;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getOrderBy() {
        return orderBy;
    }
}
