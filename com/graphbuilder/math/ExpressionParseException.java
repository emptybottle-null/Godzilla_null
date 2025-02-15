/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

public class ExpressionParseException
extends RuntimeException {
    private String descrip = null;
    private int index = 0;

    public ExpressionParseException(String descrip, int index) {
        this.descrip = descrip;
        this.index = index;
    }

    public String getDescription() {
        return this.descrip;
    }

    public int getIndex() {
        return this.index;
    }

    public String toString() {
        return "(" + this.index + ") " + this.descrip;
    }
}

