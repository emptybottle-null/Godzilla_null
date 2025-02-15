/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.transform;

import java.io.Serializable;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.DstNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.RealTransformer;
import org.apache.commons.math3.transform.TransformType;
import org.apache.commons.math3.transform.TransformUtils;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

public class FastSineTransformer
implements RealTransformer,
Serializable {
    static final long serialVersionUID = 20120211L;
    private final DstNormalization normalization;

    public FastSineTransformer(DstNormalization normalization) {
        this.normalization = normalization;
    }

    public double[] transform(double[] f, TransformType type) {
        if (this.normalization == DstNormalization.ORTHOGONAL_DST_I) {
            double s = FastMath.sqrt(2.0 / (double)f.length);
            return TransformUtils.scaleArray(this.fst(f), s);
        }
        if (type == TransformType.FORWARD) {
            return this.fst(f);
        }
        double s = 2.0 / (double)f.length;
        return TransformUtils.scaleArray(this.fst(f), s);
    }

    public double[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) {
        double[] data = FunctionUtils.sample(f, min, max, n);
        data[0] = 0.0;
        return this.transform(data, type);
    }

    protected double[] fst(double[] f) throws MathIllegalArgumentException {
        double[] transformed = new double[f.length];
        if (!ArithmeticUtils.isPowerOfTwo(f.length)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, f.length);
        }
        if (f[0] != 0.0) {
            throw new MathIllegalArgumentException(LocalizedFormats.FIRST_ELEMENT_NOT_ZERO, f[0]);
        }
        int n = f.length;
        if (n == 1) {
            transformed[0] = 0.0;
            return transformed;
        }
        double[] x = new double[n];
        x[0] = 0.0;
        x[n >> 1] = 2.0 * f[n >> 1];
        for (int i = 1; i < n >> 1; ++i) {
            double a = FastMath.sin((double)i * Math.PI / (double)n) * (f[i] + f[n - i]);
            double b = 0.5 * (f[i] - f[n - i]);
            x[i] = a + b;
            x[n - i] = a - b;
        }
        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] y = transformer.transform(x, TransformType.FORWARD);
        transformed[0] = 0.0;
        transformed[1] = 0.5 * y[0].getReal();
        for (int i = 1; i < n >> 1; ++i) {
            transformed[2 * i] = -y[i].getImaginary();
            transformed[2 * i + 1] = y[i].getReal() + transformed[2 * i - 1];
        }
        return transformed;
    }
}

