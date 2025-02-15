/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathArrays;

public class StepFunction
implements UnivariateFunction {
    private final double[] abscissa;
    private final double[] ordinate;

    public StepFunction(double[] x, double[] y) throws NullArgumentException, NoDataException, DimensionMismatchException, NonMonotonicSequenceException {
        if (x == null || y == null) {
            throw new NullArgumentException();
        }
        if (x.length == 0 || y.length == 0) {
            throw new NoDataException();
        }
        if (y.length != x.length) {
            throw new DimensionMismatchException(y.length, x.length);
        }
        MathArrays.checkOrder(x);
        this.abscissa = MathArrays.copyOf(x);
        this.ordinate = MathArrays.copyOf(y);
    }

    public double value(double x) {
        int index = Arrays.binarySearch(this.abscissa, x);
        double fx = 0.0;
        fx = index < -1 ? this.ordinate[-index - 2] : (index >= 0 ? this.ordinate[index] : this.ordinate[0]);
        return fx;
    }
}

