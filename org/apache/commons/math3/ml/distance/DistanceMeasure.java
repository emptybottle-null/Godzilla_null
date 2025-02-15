/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.distance;

import java.io.Serializable;
import org.apache.commons.math3.exception.DimensionMismatchException;

public interface DistanceMeasure
extends Serializable {
    public double compute(double[] var1, double[] var2) throws DimensionMismatchException;
}

