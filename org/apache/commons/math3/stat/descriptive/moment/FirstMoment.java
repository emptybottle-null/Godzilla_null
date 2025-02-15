/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

class FirstMoment
extends AbstractStorelessUnivariateStatistic
implements Serializable {
    private static final long serialVersionUID = 6112755307178490473L;
    protected long n;
    protected double m1;
    protected double dev;
    protected double nDev;

    FirstMoment() {
        this.n = 0L;
        this.m1 = Double.NaN;
        this.dev = Double.NaN;
        this.nDev = Double.NaN;
    }

    FirstMoment(FirstMoment original) throws NullArgumentException {
        FirstMoment.copy(original, this);
    }

    public void increment(double d) {
        if (this.n == 0L) {
            this.m1 = 0.0;
        }
        ++this.n;
        double n0 = this.n;
        this.dev = d - this.m1;
        this.nDev = this.dev / n0;
        this.m1 += this.nDev;
    }

    public void clear() {
        this.m1 = Double.NaN;
        this.n = 0L;
        this.dev = Double.NaN;
        this.nDev = Double.NaN;
    }

    public double getResult() {
        return this.m1;
    }

    public long getN() {
        return this.n;
    }

    public FirstMoment copy() {
        FirstMoment result = new FirstMoment();
        FirstMoment.copy(this, result);
        return result;
    }

    public static void copy(FirstMoment source, FirstMoment dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.m1 = source.m1;
        dest.dev = source.dev;
        dest.nDev = source.nDev;
    }
}

