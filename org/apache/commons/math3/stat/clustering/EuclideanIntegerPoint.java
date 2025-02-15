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
public class EuclideanIntegerPoint
implements Clusterable<EuclideanIntegerPoint>,
Serializable {
    private static final long serialVersionUID = 3946024775784901369L;
    private final int[] point;

    public EuclideanIntegerPoint(int[] point) {
        this.point = point;
    }

    public int[] getPoint() {
        return this.point;
    }

    @Override
    public double distanceFrom(EuclideanIntegerPoint p) {
        return MathArrays.distance(this.point, p.getPoint());
    }

    @Override
    public EuclideanIntegerPoint centroidOf(Collection<EuclideanIntegerPoint> points) {
        int[] centroid = new int[this.getPoint().length];
        for (EuclideanIntegerPoint p : points) {
            for (int i = 0; i < centroid.length; ++i) {
                int n = i;
                centroid[n] = centroid[n] + p.getPoint()[i];
            }
        }
        int i = 0;
        while (i < centroid.length) {
            int n = i++;
            centroid[n] = centroid[n] / points.size();
        }
        return new EuclideanIntegerPoint(centroid);
    }

    public boolean equals(Object other) {
        if (!(other instanceof EuclideanIntegerPoint)) {
            return false;
        }
        return Arrays.equals(this.point, ((EuclideanIntegerPoint)other).point);
    }

    public int hashCode() {
        return Arrays.hashCode(this.point);
    }

    public String toString() {
        return Arrays.toString(this.point);
    }
}

