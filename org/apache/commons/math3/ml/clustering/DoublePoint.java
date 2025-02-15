/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.clustering;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.ml.clustering.Clusterable;

public class DoublePoint
implements Clusterable,
Serializable {
    private static final long serialVersionUID = 3946024775784901369L;
    private final double[] point;

    public DoublePoint(double[] point) {
        this.point = point;
    }

    public DoublePoint(int[] point) {
        this.point = new double[point.length];
        for (int i = 0; i < point.length; ++i) {
            this.point[i] = point[i];
        }
    }

    public double[] getPoint() {
        return this.point;
    }

    public boolean equals(Object other) {
        if (!(other instanceof DoublePoint)) {
            return false;
        }
        return Arrays.equals(this.point, ((DoublePoint)other).point);
    }

    public int hashCode() {
        return Arrays.hashCode(this.point);
    }

    public String toString() {
        return Arrays.toString(this.point);
    }
}

