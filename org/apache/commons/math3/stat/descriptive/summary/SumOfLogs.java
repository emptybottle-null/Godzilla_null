/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.summary;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class SumOfLogs
extends AbstractStorelessUnivariateStatistic
implements Serializable {
    private static final long serialVersionUID = -370076995648386763L;
    private int n;
    private double value;

    public SumOfLogs() {
        this.value = 0.0;
        this.n = 0;
    }

    public SumOfLogs(SumOfLogs original) throws NullArgumentException {
        SumOfLogs.copy(original, this);
    }

    public void increment(double d) {
        this.value += FastMath.log(d);
        ++this.n;
    }

    public double getResult() {
        return this.value;
    }

    public long getN() {
        return this.n;
    }

    public void clear() {
        this.value = 0.0;
        this.n = 0;
    }

    public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
        double sumLog = Double.NaN;
        if (this.test(values, begin, length, true)) {
            sumLog = 0.0;
            for (int i = begin; i < begin + length; ++i) {
                sumLog += FastMath.log(values[i]);
            }
        }
        return sumLog;
    }

    public SumOfLogs copy() {
        SumOfLogs result = new SumOfLogs();
        SumOfLogs.copy(this, result);
        return result;
    }

    public static void copy(SumOfLogs source, SumOfLogs dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.value = source.value;
    }
}

