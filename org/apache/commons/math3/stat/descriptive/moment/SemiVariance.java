/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.util.MathUtils;

public class SemiVariance
extends AbstractUnivariateStatistic
implements Serializable {
    public static final Direction UPSIDE_VARIANCE = Direction.UPSIDE;
    public static final Direction DOWNSIDE_VARIANCE = Direction.DOWNSIDE;
    private static final long serialVersionUID = -2653430366886024994L;
    private boolean biasCorrected = true;
    private Direction varianceDirection = Direction.DOWNSIDE;

    public SemiVariance() {
    }

    public SemiVariance(boolean biasCorrected) {
        this.biasCorrected = biasCorrected;
    }

    public SemiVariance(Direction direction) {
        this.varianceDirection = direction;
    }

    public SemiVariance(boolean corrected, Direction direction) {
        this.biasCorrected = corrected;
        this.varianceDirection = direction;
    }

    public SemiVariance(SemiVariance original) throws NullArgumentException {
        SemiVariance.copy(original, this);
    }

    public SemiVariance copy() {
        SemiVariance result = new SemiVariance();
        SemiVariance.copy(this, result);
        return result;
    }

    public static void copy(SemiVariance source, SemiVariance dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.biasCorrected = source.biasCorrected;
        dest.varianceDirection = source.varianceDirection;
    }

    public double evaluate(double[] values, int start, int length) throws MathIllegalArgumentException {
        double m = new Mean().evaluate(values, start, length);
        return this.evaluate(values, m, this.varianceDirection, this.biasCorrected, 0, values.length);
    }

    public double evaluate(double[] values, Direction direction) throws MathIllegalArgumentException {
        double m = new Mean().evaluate(values);
        return this.evaluate(values, m, direction, this.biasCorrected, 0, values.length);
    }

    public double evaluate(double[] values, double cutoff) throws MathIllegalArgumentException {
        return this.evaluate(values, cutoff, this.varianceDirection, this.biasCorrected, 0, values.length);
    }

    public double evaluate(double[] values, double cutoff, Direction direction) throws MathIllegalArgumentException {
        return this.evaluate(values, cutoff, direction, this.biasCorrected, 0, values.length);
    }

    public double evaluate(double[] values, double cutoff, Direction direction, boolean corrected, int start, int length) throws MathIllegalArgumentException {
        this.test(values, start, length);
        if (values.length == 0) {
            return Double.NaN;
        }
        if (values.length == 1) {
            return 0.0;
        }
        boolean booleanDirection = direction.getDirection();
        double dev = 0.0;
        double sumsq = 0.0;
        for (int i = start; i < length; ++i) {
            if (values[i] > cutoff != booleanDirection) continue;
            dev = values[i] - cutoff;
            sumsq += dev * dev;
        }
        if (corrected) {
            return sumsq / ((double)length - 1.0);
        }
        return sumsq / (double)length;
    }

    public boolean isBiasCorrected() {
        return this.biasCorrected;
    }

    public void setBiasCorrected(boolean biasCorrected) {
        this.biasCorrected = biasCorrected;
    }

    public Direction getVarianceDirection() {
        return this.varianceDirection;
    }

    public void setVarianceDirection(Direction varianceDirection) {
        this.varianceDirection = varianceDirection;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Direction {
        UPSIDE(true),
        DOWNSIDE(false);

        private boolean direction;

        private Direction(boolean b) {
            this.direction = b;
        }

        boolean getDirection() {
            return this.direction;
        }
    }
}

