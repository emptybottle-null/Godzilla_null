/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.apache.commons.math3.util.MathUtils;

class ThirdMoment
extends SecondMoment
implements Serializable {
    private static final long serialVersionUID = -7818711964045118679L;
    protected double m3;
    protected double nDevSq;

    ThirdMoment() {
        this.m3 = Double.NaN;
        this.nDevSq = Double.NaN;
    }

    ThirdMoment(ThirdMoment original) throws NullArgumentException {
        ThirdMoment.copy(original, this);
    }

    public void increment(double d) {
        if (this.n < 1L) {
            this.m1 = 0.0;
            this.m2 = 0.0;
            this.m3 = 0.0;
        }
        double prevM2 = this.m2;
        super.increment(d);
        this.nDevSq = this.nDev * this.nDev;
        double n0 = this.n;
        this.m3 = this.m3 - 3.0 * this.nDev * prevM2 + (n0 - 1.0) * (n0 - 2.0) * this.nDevSq * this.dev;
    }

    public double getResult() {
        return this.m3;
    }

    public void clear() {
        super.clear();
        this.m3 = Double.NaN;
        this.nDevSq = Double.NaN;
    }

    public ThirdMoment copy() {
        ThirdMoment result = new ThirdMoment();
        ThirdMoment.copy(this, result);
        return result;
    }

    public static void copy(ThirdMoment source, ThirdMoment dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        SecondMoment.copy(source, dest);
        dest.m3 = source.m3;
        dest.nDevSq = source.nDevSq;
    }
}

