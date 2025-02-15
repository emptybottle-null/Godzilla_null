/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

public class Min
extends AbstractStorelessUnivariateStatistic
implements Serializable {
    private static final long serialVersionUID = -2941995784909003131L;
    private long n;
    private double value;

    public Min() {
        this.n = 0L;
        this.value = Double.NaN;
    }

    public Min(Min original) throws NullArgumentException {
        Min.copy(original, this);
    }

    public void increment(double d) {
        if (d < this.value || Double.isNaN(this.value)) {
            this.value = d;
        }
        ++this.n;
    }

    public void clear() {
        this.value = Double.NaN;
        this.n = 0L;
    }

    public double getResult() {
        return this.value;
    }

    public long getN() {
        return this.n;
    }

    public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
        double min = Double.NaN;
        if (this.test(values, begin, length)) {
            min = values[begin];
            for (int i = begin; i < begin + length; ++i) {
                if (Double.isNaN(values[i])) continue;
                min = min < values[i] ? min : values[i];
            }
        }
        return min;
    }

    public Min copy() {
        Min result = new Min();
        Min.copy(this, result);
        return result;
    }

    public static void copy(Min source, Min dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.value = source.value;
    }
}

