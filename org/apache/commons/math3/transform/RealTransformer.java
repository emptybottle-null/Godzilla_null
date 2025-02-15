/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.transform;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.transform.TransformType;

public interface RealTransformer {
    public double[] transform(double[] var1, TransformType var2) throws MathIllegalArgumentException;

    public double[] transform(UnivariateFunction var1, double var2, double var4, int var6, TransformType var7) throws NonMonotonicSequenceException, NotStrictlyPositiveException, MathIllegalArgumentException;
}

