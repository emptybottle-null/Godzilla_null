/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class StandardDeviation
extends AbstractStorelessUnivariateStatistic
implements Serializable {
    private static final long serialVersionUID = 5728716329662425188L;
    private Variance variance = null;

    public StandardDeviation() {
        this.variance = new Variance();
    }

    public StandardDeviation(SecondMoment m2) {
        this.variance = new Variance(m2);
    }

    public StandardDeviation(StandardDeviation original) throws NullArgumentException {
        StandardDeviation.copy(original, this);
    }

    public StandardDeviation(boolean isBiasCorrected) {
        this.variance = new Variance(isBiasCorrected);
    }

    public StandardDeviation(boolean isBiasCorrected, SecondMoment m2) {
        this.variance = new Variance(isBiasCorrected, m2);
    }

    public void increment(double d) {
        this.variance.increment(d);
    }

    public long getN() {
        return this.variance.getN();
    }

    public double getResult() {
        return FastMath.sqrt(this.variance.getResult());
    }

    public void clear() {
        this.variance.clear();
    }

    public double evaluate(double[] values) throws MathIllegalArgumentException {
        return FastMath.sqrt(this.variance.evaluate(values));
    }

    public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
        return FastMath.sqrt(this.variance.evaluate(values, begin, length));
    }

    public double evaluate(double[] values, double mean, int begin, int length) throws MathIllegalArgumentException {
        return FastMath.sqrt(this.variance.evaluate(values, mean, begin, length));
    }

    public double evaluate(double[] values, double mean) throws MathIllegalArgumentException {
        return FastMath.sqrt(this.variance.evaluate(values, mean));
    }

    public boolean isBiasCorrected() {
        return this.variance.isBiasCorrected();
    }

    public void setBiasCorrected(boolean isBiasCorrected) {
        this.variance.setBiasCorrected(isBiasCorrected);
    }

    public StandardDeviation copy() {
        StandardDeviation result = new StandardDeviation();
        StandardDeviation.copy(this, result);
        return result;
    }

    public static void copy(StandardDeviation source, StandardDeviation dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.variance = source.variance.copy();
    }
}

