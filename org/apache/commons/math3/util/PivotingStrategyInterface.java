/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.MathIllegalArgumentException;

public interface PivotingStrategyInterface {
    public int pivotIndex(double[] var1, int var2, int var3) throws MathIllegalArgumentException;
}

