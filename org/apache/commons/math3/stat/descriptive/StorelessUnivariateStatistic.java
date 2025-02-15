/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;

public interface StorelessUnivariateStatistic
extends UnivariateStatistic {
    public void increment(double var1);

    public void incrementAll(double[] var1) throws MathIllegalArgumentException;

    public void incrementAll(double[] var1, int var2, int var3) throws MathIllegalArgumentException;

    public double getResult();

    public long getN();

    public void clear();

    public StorelessUnivariateStatistic copy();
}

