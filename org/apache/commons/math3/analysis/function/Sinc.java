/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

public class Sinc
implements UnivariateDifferentiableFunction,
DifferentiableUnivariateFunction {
    private static final double SHORTCUT = 0.006;
    private final boolean normalized;

    public Sinc() {
        this(false);
    }

    public Sinc(boolean normalized) {
        this.normalized = normalized;
    }

    public double value(double x) {
        double scaledX;
        double d = scaledX = this.normalized ? Math.PI * x : x;
        if (FastMath.abs(scaledX) <= 0.006) {
            double scaledX2 = scaledX * scaledX;
            return ((scaledX2 - 20.0) * scaledX2 + 120.0) / 120.0;
        }
        return FastMath.sin(scaledX) / scaledX;
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    public DerivativeStructure value(DerivativeStructure t) throws DimensionMismatchException {
        double scaledX = (this.normalized ? Math.PI : 1.0) * t.getValue();
        double scaledX2 = scaledX * scaledX;
        double[] f = new double[t.getOrder() + 1];
        if (FastMath.abs(scaledX) <= 0.006) {
            for (int i = 0; i < f.length; ++i) {
                int k = i / 2;
                f[i] = (i & 1) == 0 ? (double)((k & 1) == 0 ? 1 : -1) * (1.0 / (double)(i + 1) - scaledX2 * (1.0 / (double)(2 * i + 6) - scaledX2 / (double)(24 * i + 120))) : ((k & 1) == 0 ? -scaledX : scaledX) * (1.0 / (double)(i + 2) - scaledX2 * (1.0 / (double)(6 * i + 24) - scaledX2 / (double)(120 * i + 720)));
            }
        } else {
            double inv = 1.0 / scaledX;
            double cos = FastMath.cos(scaledX);
            double sin = FastMath.sin(scaledX);
            f[0] = inv * sin;
            double[] sc = new double[f.length];
            sc[0] = 1.0;
            double coeff = inv;
            for (int n = 1; n < f.length; ++n) {
                int kStart;
                double s = 0.0;
                double c = 0.0;
                if ((n & 1) == 0) {
                    sc[n] = 0.0;
                    kStart = n;
                } else {
                    sc[n] = sc[n - 1];
                    c = sc[n];
                    kStart = n - 1;
                }
                for (int k = kStart; k > 1; k -= 2) {
                    sc[k] = (double)(k - n) * sc[k] - sc[k - 1];
                    s = s * scaledX2 + sc[k];
                    sc[k - 1] = (double)(k - 1 - n) * sc[k - 1] + sc[k - 2];
                    c = c * scaledX2 + sc[k - 1];
                }
                sc[0] = sc[0] * (double)(-n);
                s = s * scaledX2 + sc[0];
                f[n] = (coeff *= inv) * (s * sin + c * scaledX * cos);
            }
        }
        if (this.normalized) {
            double scale = Math.PI;
            int i = 1;
            while (i < f.length) {
                int n = i++;
                f[n] = f[n] * scale;
                scale *= Math.PI;
            }
        }
        return t.compose(f);
    }
}

