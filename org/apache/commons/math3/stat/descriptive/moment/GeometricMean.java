/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class GeometricMean
extends AbstractStorelessUnivariateStatistic
implements Serializable {
    private static final long serialVersionUID = -8178734905303459453L;
    private StorelessUnivariateStatistic sumOfLogs;

    public GeometricMean() {
        this.sumOfLogs = new SumOfLogs();
    }

    public GeometricMean(GeometricMean original) throws NullArgumentException {
        GeometricMean.copy(original, this);
    }

    public GeometricMean(SumOfLogs sumOfLogs) {
        this.sumOfLogs = sumOfLogs;
    }

    public GeometricMean copy() {
        GeometricMean result = new GeometricMean();
        GeometricMean.copy(this, result);
        return result;
    }

    public void increment(double d) {
        this.sumOfLogs.increment(d);
    }

    public double getResult() {
        if (this.sumOfLogs.getN() > 0L) {
            return FastMath.exp(this.sumOfLogs.getResult() / (double)this.sumOfLogs.getN());
        }
        return Double.NaN;
    }

    public void clear() {
        this.sumOfLogs.clear();
    }

    public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
        return FastMath.exp(this.sumOfLogs.evaluate(values, begin, length) / (double)length);
    }

    public long getN() {
        return this.sumOfLogs.getN();
    }

    public void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) throws MathIllegalStateException {
        this.checkEmpty();
        this.sumOfLogs = sumLogImpl;
    }

    public StorelessUnivariateStatistic getSumLogImpl() {
        return this.sumOfLogs;
    }

    public static void copy(GeometricMean source, GeometricMean dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.sumOfLogs = source.sumOfLogs.copy();
    }

    private void checkEmpty() throws MathIllegalStateException {
        if (this.getN() > 0L) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, this.getN());
        }
    }
}

