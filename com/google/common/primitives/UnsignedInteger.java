/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedInts;
import java.math.BigInteger;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class UnsignedInteger
extends Number
implements Comparable<UnsignedInteger> {
    public static final UnsignedInteger ZERO = UnsignedInteger.fromIntBits(0);
    public static final UnsignedInteger ONE = UnsignedInteger.fromIntBits(1);
    public static final UnsignedInteger MAX_VALUE = UnsignedInteger.fromIntBits(-1);
    private final int value;

    private UnsignedInteger(int value) {
        this.value = value & 0xFFFFFFFF;
    }

    public static UnsignedInteger fromIntBits(int bits) {
        return new UnsignedInteger(bits);
    }

    public static UnsignedInteger valueOf(long value) {
        Preconditions.checkArgument((value & 0xFFFFFFFFL) == value, "value (%s) is outside the range for an unsigned integer value", value);
        return UnsignedInteger.fromIntBits((int)value);
    }

    public static UnsignedInteger valueOf(BigInteger value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.signum() >= 0 && value.bitLength() <= 32, "value (%s) is outside the range for an unsigned integer value", (Object)value);
        return UnsignedInteger.fromIntBits(value.intValue());
    }

    public static UnsignedInteger valueOf(String string) {
        return UnsignedInteger.valueOf(string, 10);
    }

    public static UnsignedInteger valueOf(String string, int radix) {
        return UnsignedInteger.fromIntBits(UnsignedInts.parseUnsignedInt(string, radix));
    }

    public UnsignedInteger plus(UnsignedInteger val) {
        return UnsignedInteger.fromIntBits(this.value + Preconditions.checkNotNull(val).value);
    }

    public UnsignedInteger minus(UnsignedInteger val) {
        return UnsignedInteger.fromIntBits(this.value - Preconditions.checkNotNull(val).value);
    }

    @GwtIncompatible
    public UnsignedInteger times(UnsignedInteger val) {
        return UnsignedInteger.fromIntBits(this.value * Preconditions.checkNotNull(val).value);
    }

    public UnsignedInteger dividedBy(UnsignedInteger val) {
        return UnsignedInteger.fromIntBits(UnsignedInts.divide(this.value, Preconditions.checkNotNull(val).value));
    }

    public UnsignedInteger mod(UnsignedInteger val) {
        return UnsignedInteger.fromIntBits(UnsignedInts.remainder(this.value, Preconditions.checkNotNull(val).value));
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return UnsignedInts.toLong(this.value);
    }

    @Override
    public float floatValue() {
        return this.longValue();
    }

    @Override
    public double doubleValue() {
        return this.longValue();
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(this.longValue());
    }

    @Override
    public int compareTo(UnsignedInteger other) {
        Preconditions.checkNotNull(other);
        return UnsignedInts.compare(this.value, other.value);
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof UnsignedInteger) {
            UnsignedInteger other = (UnsignedInteger)obj;
            return this.value == other.value;
        }
        return false;
    }

    public String toString() {
        return this.toString(10);
    }

    public String toString(int radix) {
        return UnsignedInts.toString(this.value, radix);
    }
}

