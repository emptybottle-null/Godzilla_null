/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import java.io.Serializable;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PointValuePair
extends Pair<double[], Double>
implements Serializable {
    private static final long serialVersionUID = 20120513L;

    public PointValuePair(double[] point, double value) {
        this(point, value, true);
    }

    public PointValuePair(double[] point, double value, boolean copyArray) {
        super(copyArray ? (Object)(point == null ? null : (double[])point.clone()) : point, value);
    }

    public double[] getPoint() {
        double[] p = (double[])this.getKey();
        return p == null ? null : (double[])p.clone();
    }

    public double[] getPointRef() {
        return (double[])this.getKey();
    }

    private Object writeReplace() {
        return new DataTransferObject((double[])this.getKey(), (Double)this.getValue());
    }

    private static class DataTransferObject
    implements Serializable {
        private static final long serialVersionUID = 20120513L;
        private final double[] point;
        private final double value;

        DataTransferObject(double[] point, double value) {
            this.point = (double[])point.clone();
            this.value = value;
        }

        private Object readResolve() {
            return new PointValuePair(this.point, this.value, false);
        }
    }
}

