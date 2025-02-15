/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
import org.apache.commons.math3.stat.descriptive.moment.FirstMoment;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.util.MathUtils;

public class Mean
extends AbstractStorelessUnivariateStatistic
implements Serializable,
WeightedEvaluation {
    private static final long serialVersionUID = -1296043746617791564L;
    protected FirstMoment moment;
    protected boolean incMoment;

    public Mean() {
        this.incMoment = true;
        this.moment = new FirstMoment();
    }

    public Mean(FirstMoment m1) {
        this.moment = m1;
        this.incMoment = false;
    }

    public Mean(Mean original) throws NullArgumentException {
        Mean.copy(original, this);
    }

    public void increment(double d) {
        if (this.incMoment) {
            this.moment.increment(d);
        }
    }

    public void clear() {
        if (this.incMoment) {
            this.moment.clear();
        }
    }

    public double getResult() {
        return this.moment.m1;
    }

    public long getN() {
        return this.moment.getN();
    }

    public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
        if (this.test(values, begin, length)) {
            Sum sum = new Sum();
            double sampleSize = length;
            double xbar = sum.evaluate(values, begin, length) / sampleSize;
            double correction = 0.0;
            for (int i = begin; i < begin + length; ++i) {
                correction += values[i] - xbar;
            }
            return xbar + correction / sampleSize;
        }
        return Double.NaN;
    }

    public double evaluate(double[] values, double[] weights, int begin, int length) throws MathIllegalArgumentException {
        if (this.test(values, weights, begin, length)) {
            Sum sum = new Sum();
            double sumw = sum.evaluate(weights, begin, length);
            double xbarw = sum.evaluate(values, weights, begin, length) / sumw;
            double correction = 0.0;
            for (int i = begin; i < begin + length; ++i) {
                correction += weights[i] * (values[i] - xbarw);
            }
            return xbarw + correction / sumw;
        }
        return Double.NaN;
    }

    public double evaluate(double[] values, double[] weights) throws MathIllegalArgumentException {
        return this.evaluate(values, weights, 0, values.length);
    }

    public Mean copy() {
        Mean result = new Mean();
        Mean.copy(this, result);
        return result;
    }

    public static void copy(Mean source, Mean dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.incMoment = source.incMoment;
        dest.moment = source.moment.copy();
    }
}

