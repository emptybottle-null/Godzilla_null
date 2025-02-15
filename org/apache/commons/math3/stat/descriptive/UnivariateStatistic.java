/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.util.MathArrays;

public interface UnivariateStatistic
extends MathArrays.Function {
    public double evaluate(double[] var1) throws MathIllegalArgumentException;

    public double evaluate(double[] var1, int var2, int var3) throws MathIllegalArgumentException;

    public UnivariateStatistic copy();
}

