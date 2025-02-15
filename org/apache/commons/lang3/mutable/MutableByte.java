/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.mutable.Mutable;

public class MutableByte
extends Number
implements Comparable<MutableByte>,
Mutable<Number> {
    private static final long serialVersionUID = -1585823265L;
    private byte value;

    public MutableByte() {
    }

    public MutableByte(byte value) {
        this.value = value;
    }

    public MutableByte(Number value) {
        this.value = value.byteValue();
    }

    public MutableByte(String value) {
        this.value = Byte.parseByte(value);
    }

    @Override
    public Byte getValue() {
        return this.value;
    }

    @Override
    public void setValue(byte value) {
        this.value = value;
    }

    @Override
    public void setValue(Number value) {
        this.value = value.byteValue();
    }

    public void increment() {
        this.value = (byte)(this.value + 1);
    }

    public byte getAndIncrement() {
        byte last = this.value;
        this.value = (byte)(this.value + 1);
        return last;
    }

    public byte incrementAndGet() {
        this.value = (byte)(this.value + 1);
        return this.value;
    }

    public void decrement() {
        this.value = (byte)(this.value - 1);
    }

    public byte getAndDecrement() {
        byte last = this.value;
        this.value = (byte)(this.value - 1);
        return last;
    }

    public byte decrementAndGet() {
        this.value = (byte)(this.value - 1);
        return this.value;
    }

    public void add(byte operand) {
        this.value = (byte)(this.value + operand);
    }

    public void add(Number operand) {
        this.value = (byte)(this.value + operand.byteValue());
    }

    public void subtract(byte operand) {
        this.value = (byte)(this.value - operand);
    }

    public void subtract(Number operand) {
        this.value = (byte)(this.value - operand.byteValue());
    }

    public byte addAndGet(byte operand) {
        this.value = (byte)(this.value + operand);
        return this.value;
    }

    public byte addAndGet(Number operand) {
        this.value = (byte)(this.value + operand.byteValue());
        return this.value;
    }

    public byte getAndAdd(byte operand) {
        byte last = this.value;
        this.value = (byte)(this.value + operand);
        return last;
    }

    public byte getAndAdd(Number operand) {
        byte last = this.value;
        this.value = (byte)(this.value + operand.byteValue());
        return last;
    }

    @Override
    public byte byteValue() {
        return this.value;
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

    public Byte toByte() {
        return this.byteValue();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MutableByte) {
            return this.value == ((MutableByte)obj).byteValue();
        }
        return false;
    }

    public int hashCode() {
        return this.value;
    }

    @Override
    public int compareTo(MutableByte other) {
        return NumberUtils.compare(this.value, other.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}

