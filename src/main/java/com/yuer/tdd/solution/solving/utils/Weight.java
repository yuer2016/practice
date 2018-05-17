package com.yuer.tdd.solution.solving.utils;

public enum Weight {
    MustMatch(Integer.MAX_VALUE),
    VeryImportant(5000),
    Important(1000),
    WouldPrefer(100),
    DontCare(0);

    private int value;

    public int getValue() {
        return value;
    }

    Weight(int value) {
        this.value = value;
    }
}
