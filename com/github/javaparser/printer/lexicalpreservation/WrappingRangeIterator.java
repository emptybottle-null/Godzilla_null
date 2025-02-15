/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.printer.lexicalpreservation;

import java.util.Iterator;

public class WrappingRangeIterator
implements Iterator<Integer> {
    private final int limit;
    private int currentValue = 0;

    public WrappingRangeIterator(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int valueToReturn = this.currentValue++;
        if (this.currentValue == this.limit) {
            this.currentValue = 0;
        }
        return valueToReturn;
    }
}

