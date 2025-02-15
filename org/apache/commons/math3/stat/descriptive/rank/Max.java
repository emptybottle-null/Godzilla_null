/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

public class Max
extends AbstractStorelessUnivariateStatistic
implements Serializable {
    private static final long serialVersionUID = -5593383832225844641L;
    private long n;
    private double value;

    public Max() {
        this.n = 0L;
        this.value = Double.NaN;
    }

    public Max(Max original) throws NullArgumentException {
        Max.copy(original, this);
    }

    public void increment(double d) {
        if (d > this.value || Double.isNaN(this.value)) {
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
        double max = Double.NaN;
        if (this.test(values, begin, length)) {
            max = values[begin];
            for (int i = begin; i < begin + length; ++i) {
                if (Double.isNaN(values[i])) continue;
                max = max > values[i] ? max : values[i];
            }
        }
        return max;
    }

    public Max copy() {
        Max result = new Max();
        Max.copy(this, result);
        return result;
    }

    public static void copy(Max source, Max dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.value = source.value;
    }
}

