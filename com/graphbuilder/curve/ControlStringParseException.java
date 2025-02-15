/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.math.ExpressionParseException;

public class ControlStringParseException
extends RuntimeException {
    private String descrip = null;
    private int fromIndex = -1;
    private int toIndex = -1;
    private ExpressionParseException epe = null;

    public ControlStringParseException(String descrip) {
        this.descrip = descrip;
    }

    public ControlStringParseException(String descrip, int index) {
        this.descrip = descrip;
        this.fromIndex = index;
        this.toIndex = index;
    }

    public ControlStringParseException(String descrip, int fromIndex, int toIndex) {
        this.descrip = descrip;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    public ControlStringParseException(String descrip, int fromIndex, int toIndex, ExpressionParseException epe) {
        this.descrip = descrip;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.epe = epe;
    }

    public int getFromIndex() {
        return this.fromIndex;
    }

    public int getToIndex() {
        return this.toIndex;
    }

    public String getDescription() {
        return this.descrip;
    }

    public ExpressionParseException getExpressionParseException() {
        return this.epe;
    }

    public String toString() {
        String e = "";
        if (this.epe != null) {
            e = "\n" + this.epe.toString();
        }
        if (this.fromIndex == -1 && this.toIndex == -1) {
            return this.descrip + e;
        }
        if (this.fromIndex == this.toIndex) {
            return this.descrip + " : [" + this.toIndex + "]" + e;
        }
        return this.descrip + " : [" + this.fromIndex + ", " + this.toIndex + "]" + e;
    }
}

