/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.field;

import java.math.BigInteger;

public interface FiniteField {
    public BigInteger getCharacteristic();

    public int getDimension();
}

