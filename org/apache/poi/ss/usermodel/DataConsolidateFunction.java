/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum DataConsolidateFunction {
    AVERAGE(1, "Average"),
    COUNT(2, "Count"),
    COUNT_NUMS(3, "Count"),
    MAX(4, "Max"),
    MIN(5, "Min"),
    PRODUCT(6, "Product"),
    STD_DEV(7, "StdDev"),
    STD_DEVP(8, "StdDevp"),
    SUM(9, "Sum"),
    VAR(10, "Var"),
    VARP(11, "Varp");

    private final int value;
    private final String name;

    private DataConsolidateFunction(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}

