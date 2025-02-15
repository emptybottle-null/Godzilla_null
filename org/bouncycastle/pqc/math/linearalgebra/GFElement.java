/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.math.BigInteger;

public interface GFElement {
    public Object clone();

    public boolean equals(Object var1);

    public int hashCode();

    public boolean isZero();

    public boolean isOne();

    public GFElement add(GFElement var1) throws RuntimeException;

    public void addToThis(GFElement var1) throws RuntimeException;

    public GFElement subtract(GFElement var1) throws RuntimeException;

    public void subtractFromThis(GFElement var1);

    public GFElement multiply(GFElement var1) throws RuntimeException;

    public void multiplyThisBy(GFElement var1) throws RuntimeException;

    public GFElement invert() throws ArithmeticException;

    public BigInteger toFlexiBigInt();

    public byte[] toByteArray();

    public String toString();

    public String toString(int var1);
}

