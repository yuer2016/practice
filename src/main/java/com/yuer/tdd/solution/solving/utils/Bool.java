package com.yuer.tdd.solution.solving.utils;

public enum Bool {
    True(1),
    False(0);

    private int value;
    public static final int FALSE = 0;
    public static final int TRUE = 1;

    Bool(int value) {
        this.value = value;
    }

    public int getVaule() {
        return value;
    }
}
