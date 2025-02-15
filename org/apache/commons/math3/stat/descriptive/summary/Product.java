/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.summary;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class Product
extends AbstractStorelessUnivariateStatistic
implements Serializable,
WeightedEvaluation {
    private static final long serialVersionUID = 2824226005990582538L;
    private long n;
    private double value;

    public Product() {
        this.n = 0L;
        this.value = 1.0;
    }

    public Product(Product original) throws NullArgumentException {
        Product.copy(original, this);
    }

    public void increment(double d) {
        this.value *= d;
        ++this.n;
    }

    public double getResult() {
        return this.value;
    }

    public long getN() {
        return this.n;
    }

    public void clear() {
        this.value = 1.0;
        this.n = 0L;
    }

    public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
        double product = Double.NaN;
        if (this.test(values, begin, length, true)) {
            product = 1.0;
            for (int i = begin; i < begin + length; ++i) {
                product *= values[i];
            }
        }
        return product;
    }

    public double evaluate(double[] values, double[] weights, int begin, int length) throws MathIllegalArgumentException {
        double product = Double.NaN;
        if (this.test(values, weights, begin, length, true)) {
            product = 1.0;
            for (int i = begin; i < begin + length; ++i) {
                product *= FastMath.pow(values[i], weights[i]);
            }
        }
        return product;
    }

    public double evaluate(double[] values, double[] weights) throws MathIllegalArgumentException {
        return this.evaluate(values, weights, 0, values.length);
    }

    public Product copy() {
        Product result = new Product();
        Product.copy(this, result);
        return result;
    }

    public static void copy(Product source, Product dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.value = source.value;
    }
}

