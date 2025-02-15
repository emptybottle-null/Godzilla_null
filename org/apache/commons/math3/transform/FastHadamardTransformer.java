/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.transform;

import java.io.Serializable;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.transform.RealTransformer;
import org.apache.commons.math3.transform.TransformType;
import org.apache.commons.math3.transform.TransformUtils;
import org.apache.commons.math3.util.ArithmeticUtils;

public class FastHadamardTransformer
implements RealTransformer,
Serializable {
    static final long serialVersionUID = 20120211L;

    public double[] transform(double[] f, TransformType type) {
        if (type == TransformType.FORWARD) {
            return this.fht(f);
        }
        return TransformUtils.scaleArray(this.fht(f), 1.0 / (double)f.length);
    }

    public double[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) {
        return this.transform(FunctionUtils.sample(f, min, max, n), type);
    }

    public int[] transform(int[] f) {
        return this.fht(f);
    }

    protected double[] fht(double[] x) throws MathIllegalArgumentException {
        int n = x.length;
        int halfN = n / 2;
        if (!ArithmeticUtils.isPowerOfTwo(n)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, n);
        }
        double[] yPrevious = new double[n];
        double[] yCurrent = (double[])x.clone();
        for (int j = 1; j < n; j <<= 1) {
            int twoI;
            int i;
            double[] yTmp = yCurrent;
            yCurrent = yPrevious;
            yPrevious = yTmp;
            for (i = 0; i < halfN; ++i) {
                twoI = 2 * i;
                yCurrent[i] = yPrevious[twoI] + yPrevious[twoI + 1];
            }
            for (i = halfN; i < n; ++i) {
                twoI = 2 * i;
                yCurrent[i] = yPrevious[twoI - n] - yPrevious[twoI - n + 1];
            }
        }
        return yCurrent;
    }

    protected int[] fht(int[] x) throws MathIllegalArgumentException {
        int n = x.length;
        int halfN = n / 2;
        if (!ArithmeticUtils.isPowerOfTwo(n)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, n);
        }
        int[] yPrevious = new int[n];
        int[] yCurrent = (int[])x.clone();
        for (int j = 1; j < n; j <<= 1) {
            int twoI;
            int i;
            int[] yTmp = yCurrent;
            yCurrent = yPrevious;
            yPrevious = yTmp;
            for (i = 0; i < halfN; ++i) {
                twoI = 2 * i;
                yCurrent[i] = yPrevious[twoI] + yPrevious[twoI + 1];
            }
            for (i = halfN; i < n; ++i) {
                twoI = 2 * i;
                yCurrent[i] = yPrevious[twoI - n] - yPrevious[twoI - n + 1];
            }
        }
        return yCurrent;
    }
}

