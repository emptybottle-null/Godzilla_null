/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.distance;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class CanberraDistance
implements DistanceMeasure {
    private static final long serialVersionUID = -6972277381587032228L;

    public double compute(double[] a, double[] b) throws DimensionMismatchException {
        MathArrays.checkEqualLength(a, b);
        double sum = 0.0;
        for (int i = 0; i < a.length; ++i) {
            double num = FastMath.abs(a[i] - b[i]);
            double denom = FastMath.abs(a[i]) + FastMath.abs(b[i]);
            sum += num == 0.0 && denom == 0.0 ? 0.0 : num / denom;
        }
        return sum;
    }
}

