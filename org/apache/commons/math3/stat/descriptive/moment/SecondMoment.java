/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.moment.FirstMoment;
import org.apache.commons.math3.util.MathUtils;

public class SecondMoment
extends FirstMoment
implements Serializable {
    private static final long serialVersionUID = 3942403127395076445L;
    protected double m2;

    public SecondMoment() {
        this.m2 = Double.NaN;
    }

    public SecondMoment(SecondMoment original) throws NullArgumentException {
        super(original);
        this.m2 = original.m2;
    }

    public void increment(double d) {
        if (this.n < 1L) {
            this.m2 = 0.0;
            this.m1 = 0.0;
        }
        super.increment(d);
        this.m2 += ((double)this.n - 1.0) * this.dev * this.nDev;
    }

    public void clear() {
        super.clear();
        this.m2 = Double.NaN;
    }

    public double getResult() {
        return this.m2;
    }

    public SecondMoment copy() {
        SecondMoment result = new SecondMoment();
        SecondMoment.copy(this, result);
        return result;
    }

    public static void copy(SecondMoment source, SecondMoment dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        FirstMoment.copy(source, dest);
        dest.m2 = source.m2;
    }
}

