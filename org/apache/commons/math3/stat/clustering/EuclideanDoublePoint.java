/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.clustering;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.math3.stat.clustering.Clusterable;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class EuclideanDoublePoint
implements Clusterable<EuclideanDoublePoint>,
Serializable {
    private static final long serialVersionUID = 8026472786091227632L;
    private final double[] point;

    public EuclideanDoublePoint(double[] point) {
        this.point = point;
    }

    @Override
    public EuclideanDoublePoint centroidOf(Collection<EuclideanDoublePoint> points) {
        double[] centroid = new double[this.getPoint().length];
        for (EuclideanDoublePoint p : points) {
            for (int i = 0; i < centroid.length; ++i) {
                int n = i;
                centroid[n] = centroid[n] + p.getPoint()[i];
            }
        }
        int i = 0;
        while (i < centroid.length) {
            int n = i++;
            centroid[n] = centroid[n] / (double)points.size();
        }
        return new EuclideanDoublePoint(centroid);
    }

    @Override
    public double distanceFrom(EuclideanDoublePoint p) {
        return MathArrays.distance(this.point, p.getPoint());
    }

    public boolean equals(Object other) {
        if (!(other instanceof EuclideanDoublePoint)) {
            return false;
        }
        return Arrays.equals(this.point, ((EuclideanDoublePoint)other).point);
    }

    public double[] getPoint() {
        return this.point;
    }

    public int hashCode() {
        return Arrays.hashCode(this.point);
    }

    public String toString() {
        return Arrays.toString(this.point);
    }
}

