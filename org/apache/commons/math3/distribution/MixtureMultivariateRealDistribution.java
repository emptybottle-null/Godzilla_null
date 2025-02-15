/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.AbstractMultivariateRealDistribution;
import org.apache.commons.math3.distribution.MultivariateRealDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MixtureMultivariateRealDistribution<T extends MultivariateRealDistribution>
extends AbstractMultivariateRealDistribution {
    private final double[] weight;
    private final List<T> distribution;

    public MixtureMultivariateRealDistribution(List<Pair<Double, T>> components) {
        this((RandomGenerator)new Well19937c(), components);
    }

    public MixtureMultivariateRealDistribution(RandomGenerator rng, List<Pair<Double, T>> components) {
        super(rng, ((MultivariateRealDistribution)components.get(0).getSecond()).getDimension());
        Pair<Double, T> comp;
        int i;
        int numComp = components.size();
        int dim = this.getDimension();
        double weightSum = 0.0;
        for (i = 0; i < numComp; ++i) {
            comp = components.get(i);
            if (((MultivariateRealDistribution)comp.getSecond()).getDimension() != dim) {
                throw new DimensionMismatchException(((MultivariateRealDistribution)comp.getSecond()).getDimension(), dim);
            }
            if (comp.getFirst() < 0.0) {
                throw new NotPositiveException(comp.getFirst());
            }
            weightSum += comp.getFirst().doubleValue();
        }
        if (Double.isInfinite(weightSum)) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
        }
        this.distribution = new ArrayList<T>();
        this.weight = new double[numComp];
        for (i = 0; i < numComp; ++i) {
            comp = components.get(i);
            this.weight[i] = comp.getFirst() / weightSum;
            this.distribution.add(comp.getSecond());
        }
    }

    @Override
    public double density(double[] values) {
        double p = 0.0;
        for (int i = 0; i < this.weight.length; ++i) {
            p += this.weight[i] * ((MultivariateRealDistribution)this.distribution.get(i)).density(values);
        }
        return p;
    }

    @Override
    public double[] sample() {
        double[] vals = null;
        double randomValue = this.random.nextDouble();
        double sum = 0.0;
        for (int i = 0; i < this.weight.length; ++i) {
            if (!(randomValue <= (sum += this.weight[i]))) continue;
            vals = ((MultivariateRealDistribution)this.distribution.get(i)).sample();
            break;
        }
        if (vals == null) {
            vals = ((MultivariateRealDistribution)this.distribution.get(this.weight.length - 1)).sample();
        }
        return vals;
    }

    @Override
    public void reseedRandomGenerator(long seed) {
        super.reseedRandomGenerator(seed);
        for (int i = 0; i < this.distribution.size(); ++i) {
            ((MultivariateRealDistribution)this.distribution.get(i)).reseedRandomGenerator((long)(i + 1) + seed);
        }
    }

    public List<Pair<Double, T>> getComponents() {
        ArrayList<Pair<Double, T>> list = new ArrayList<Pair<Double, T>>(this.weight.length);
        for (int i = 0; i < this.weight.length; ++i) {
            list.add(new Pair<Double, T>(this.weight[i], this.distribution.get(i)));
        }
        return list;
    }
}

