/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.mutable.Mutable;

public class MutableFloat
extends Number
implements Comparable<MutableFloat>,
Mutable<Number> {
    private static final long serialVersionUID = 5787169186L;
    private float value;

    public MutableFloat() {
    }

    public MutableFloat(float value) {
        this.value = value;
    }

    public MutableFloat(Number value) {
        this.value = value.floatValue();
    }

    public MutableFloat(String value) {
        this.value = Float.parseFloat(value);
    }

    @Override
    public Float getValue() {
        return Float.valueOf(this.value);
    }

    @Override
    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public void setValue(Number value) {
        this.value = value.floatValue();
    }

    public boolean isNaN() {
        return Float.isNaN(this.value);
    }

    public boolean isInfinite() {
        return Float.isInfinite(this.value);
    }

    public void increment() {
        this.value += 1.0f;
    }

    public float getAndIncrement() {
        float last = this.value;
        this.value += 1.0f;
        return last;
    }

    public float incrementAndGet() {
        this.value += 1.0f;
        return this.value;
    }

    public void decrement() {
        this.value -= 1.0f;
    }

    public float getAndDecrement() {
        float last = this.value;
        this.value -= 1.0f;
        return last;
    }

    public float decrementAndGet() {
        this.value -= 1.0f;
        return this.value;
    }

    public void add(float operand) {
        this.value += operand;
    }

    public void add(Number operand) {
        this.value += operand.floatValue();
    }

    public void subtract(float operand) {
        this.value -= operand;
    }

    public void subtract(Number operand) {
        this.value -= operand.floatValue();
    }

    public float addAndGet(float operand) {
        this.value += operand;
        return this.value;
    }

    public float addAndGet(Number operand) {
        this.value += operand.floatValue();
        return this.value;
    }

    public float getAndAdd(float operand) {
        float last = this.value;
        this.value += operand;
        return last;
    }

    public float getAndAdd(Number operand) {
        float last = this.value;
        this.value += operand.floatValue();
        return last;
    }

    @Override
    public int intValue() {
        return (int)this.value;
    }

    @Override
    public long longValue() {
        return (long)this.value;
    }

    @Override
    public float floatValue() {
        return this.value;
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    public Float toFloat() {
        return Float.valueOf(this.floatValue());
    }

    public boolean equals(Object obj) {
        return obj instanceof MutableFloat && Float.floatToIntBits(((MutableFloat)obj).value) == Float.floatToIntBits(this.value);
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value);
    }

    @Override
    public int compareTo(MutableFloat other) {
        return Float.compare(this.value, other.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}

