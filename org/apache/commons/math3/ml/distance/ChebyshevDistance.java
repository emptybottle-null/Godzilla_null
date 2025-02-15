/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.distance;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.util.MathArrays;

public class ChebyshevDistance
implements DistanceMeasure {
    private static final long serialVersionUID = -4694868171115238296L;

    public double compute(double[] a, double[] b) throws DimensionMismatchException {
        return MathArrays.distanceInf(a, b);
    }
}

