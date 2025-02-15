/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.mutable.Mutable;

public class MutableInt
extends Number
implements Comparable<MutableInt>,
Mutable<Number> {
    private static final long serialVersionUID = 512176391864L;
    private int value;

    public MutableInt() {
    }

    public MutableInt(int value) {
        this.value = value;
    }

    public MutableInt(Number value) {
        this.value = value.intValue();
    }

    public MutableInt(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void setValue(Number value) {
        this.value = value.intValue();
    }

    public void increment() {
        ++this.value;
    }

    public int getAndIncrement() {
        int last = this.value++;
        return last;
    }

    public int incrementAndGet() {
        ++this.value;
        return this.value;
    }

    public void decrement() {
        --this.value;
    }

    public int getAndDecrement() {
        int last = this.value--;
        return last;
    }

    public int decrementAndGet() {
        --this.value;
        return this.value;
    }

    public void add(int operand) {
        this.value += operand;
    }

    public void add(Number operand) {
        this.value += operand.intValue();
    }

    public void subtract(int operand) {
        this.value -= operand;
    }

    public void subtract(Number operand) {
        this.value -= operand.intValue();
    }

    public int addAndGet(int operand) {
        this.value += operand;
        return this.value;
    }

    public int addAndGet(Number operand) {
        this.value += operand.intValue();
        return this.value;
    }

    public int getAndAdd(int operand) {
        int last = this.value;
        this.value += operand;
        return last;
    }

    public int getAndAdd(Number operand) {
        int last = this.value;
        this.value += operand.intValue();
        return last;
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }

    @Override
    public float floatValue() {
        return this.value;
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    public Integer toInteger() {
        return this.intValue();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MutableInt) {
            return this.value == ((MutableInt)obj).intValue();
        }
        return false;
    }

    public int hashCode() {
        return this.value;
    }

    @Override
    public int compareTo(MutableInt other) {
        return NumberUtils.compare(this.value, other.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}

