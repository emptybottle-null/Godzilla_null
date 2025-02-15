/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
import org.apache.commons.math3.util.MathArrays;

public abstract class AbstractUnivariateStatistic
implements UnivariateStatistic {
    private double[] storedData;

    public void setData(double[] values) {
        this.storedData = values == null ? null : (double[])values.clone();
    }

    public double[] getData() {
        return this.storedData == null ? null : (double[])this.storedData.clone();
    }

    protected double[] getDataRef() {
        return this.storedData;
    }

    public void setData(double[] values, int begin, int length) throws MathIllegalArgumentException {
        if (values == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        }
        if (begin < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.START_POSITION, begin);
        }
        if (length < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.LENGTH, length);
        }
        if (begin + length > values.length) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END, (Number)(begin + length), values.length, true);
        }
        this.storedData = new double[length];
        System.arraycopy(values, begin, this.storedData, 0, length);
    }

    public double evaluate() throws MathIllegalArgumentException {
        return this.evaluate(this.storedData);
    }

    public double evaluate(double[] values) throws MathIllegalArgumentException {
        this.test(values, 0, 0);
        return this.evaluate(values, 0, values.length);
    }

    public abstract double evaluate(double[] var1, int var2, int var3) throws MathIllegalArgumentException;

    public abstract UnivariateStatistic copy();

    protected boolean test(double[] values, int begin, int length) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(values, begin, length, false);
    }

    protected boolean test(double[] values, int begin, int length, boolean allowEmpty) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(values, begin, length, allowEmpty);
    }

    protected boolean test(double[] values, double[] weights, int begin, int length) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(values, weights, begin, length, false);
    }

    protected boolean test(double[] values, double[] weights, int begin, int length, boolean allowEmpty) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(values, weights, begin, length, allowEmpty);
    }
}

