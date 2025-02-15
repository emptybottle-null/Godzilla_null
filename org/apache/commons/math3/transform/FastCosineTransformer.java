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
import org.apache.commons.math3.transform.DctNormalization;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.RealTransformer;
import org.apache.commons.math3.transform.TransformType;
import org.apache.commons.math3.transform.TransformUtils;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;

public class FastCosineTransformer
implements RealTransformer,
Serializable {
    static final long serialVersionUID = 20120212L;
    private final DctNormalization normalization;

    public FastCosineTransformer(DctNormalization normalization) {
        this.normalization = normalization;
    }

    public double[] transform(double[] f, TransformType type) throws MathIllegalArgumentException {
        if (type == TransformType.FORWARD) {
            if (this.normalization == DctNormalization.ORTHOGONAL_DCT_I) {
                double s = FastMath.sqrt(2.0 / (double)(f.length - 1));
                return TransformUtils.scaleArray(this.fct(f), s);
            }
            return this.fct(f);
        }
        double s2 = 2.0 / (double)(f.length - 1);
        double s1 = this.normalization == DctNormalization.ORTHOGONAL_DCT_I ? FastMath.sqrt(s2) : s2;
        return TransformUtils.scaleArray(this.fct(f), s1);
    }

    public double[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) throws MathIllegalArgumentException {
        double[] data = FunctionUtils.sample(f, min, max, n);
        return this.transform(data, type);
    }

    protected double[] fct(double[] f) throws MathIllegalArgumentException {
        double[] transformed = new double[f.length];
        int n = f.length - 1;
        if (!ArithmeticUtils.isPowerOfTwo(n)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_PLUS_ONE, f.length);
        }
        if (n == 1) {
            transformed[0] = 0.5 * (f[0] + f[1]);
            transformed[1] = 0.5 * (f[0] - f[1]);
            return transformed;
        }
        double[] x = new double[n];
        x[0] = 0.5 * (f[0] + f[n]);
        x[n >> 1] = f[n >> 1];
        double t1 = 0.5 * (f[0] - f[n]);
        for (int i = 1; i < n >> 1; ++i) {
            double a = 0.5 * (f[i] + f[n - i]);
            double b = FastMath.sin((double)i * Math.PI / (double)n) * (f[i] - f[n - i]);
            double c = FastMath.cos((double)i * Math.PI / (double)n) * (f[i] - f[n - i]);
            x[i] = a - b;
            x[n - i] = a + b;
            t1 += c;
        }
        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] y = transformer.transform(x, TransformType.FORWARD);
        transformed[0] = y[0].getReal();
        transformed[1] = t1;
        for (int i = 1; i < n >> 1; ++i) {
            transformed[2 * i] = y[i].getReal();
            transformed[2 * i + 1] = transformed[2 * i - 1] - y[i].getImaginary();
        }
        transformed[n] = y[n >> 1].getReal();
        return transformed;
    }
}

