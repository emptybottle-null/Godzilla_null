/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class EnumeratedRealDistribution
extends AbstractRealDistribution {
    private static final long serialVersionUID = 20130308L;
    protected final EnumeratedDistribution<Double> innerDistribution;

    public EnumeratedRealDistribution(double[] singletons, double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        this(new Well19937c(), singletons, probabilities);
    }

    public EnumeratedRealDistribution(RandomGenerator rng, double[] singletons, double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        super(rng);
        this.innerDistribution = new EnumeratedDistribution(rng, EnumeratedRealDistribution.createDistribution(singletons, probabilities));
    }

    public EnumeratedRealDistribution(RandomGenerator rng, double[] data) {
        super(rng);
        HashMap<Double, Integer> dataMap = new HashMap<Double, Integer>();
        for (double value : data) {
            Integer count = (Integer)dataMap.get(value);
            if (count == null) {
                count = 0;
            }
            count = count + 1;
            dataMap.put(value, count);
        }
        int massPoints = dataMap.size();
        double denom = data.length;
        double[] values = new double[massPoints];
        double[] probabilities = new double[massPoints];
        int index = 0;
        for (Map.Entry entry : dataMap.entrySet()) {
            values[index] = (Double)entry.getKey();
            probabilities[index] = (double)((Integer)entry.getValue()).intValue() / denom;
            ++index;
        }
        this.innerDistribution = new EnumeratedDistribution(rng, EnumeratedRealDistribution.createDistribution(values, probabilities));
    }

    public EnumeratedRealDistribution(double[] data) {
        this(new Well19937c(), data);
    }

    private static List<Pair<Double, Double>> createDistribution(double[] singletons, double[] probabilities) {
        if (singletons.length != probabilities.length) {
            throw new DimensionMismatchException(probabilities.length, singletons.length);
        }
        ArrayList<Pair<Double, Double>> samples = new ArrayList<Pair<Double, Double>>(singletons.length);
        for (int i = 0; i < singletons.length; ++i) {
            samples.add(new Pair<Double, Double>(singletons[i], probabilities[i]));
        }
        return samples;
    }

    @Override
    public double probability(double x) {
        return this.innerDistribution.probability(x);
    }

    @Override
    public double density(double x) {
        return this.probability(x);
    }

    @Override
    public double cumulativeProbability(double x) {
        double probability = 0.0;
        for (Pair<Double, Double> sample : this.innerDistribution.getPmf()) {
            if (!(sample.getKey() <= x)) continue;
            probability += sample.getValue().doubleValue();
        }
        return probability;
    }

    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0, 1);
        }
        double probability = 0.0;
        double x = this.getSupportLowerBound();
        for (Pair<Double, Double> sample : this.innerDistribution.getPmf()) {
            if (sample.getValue() == 0.0) continue;
            probability += sample.getValue().doubleValue();
            x = sample.getKey();
            if (!(probability >= p)) continue;
            break;
        }
        return x;
    }

    @Override
    public double getNumericalMean() {
        double mean = 0.0;
        for (Pair<Double, Double> sample : this.innerDistribution.getPmf()) {
            mean += sample.getValue() * sample.getKey();
        }
        return mean;
    }

    @Override
    public double getNumericalVariance() {
        double mean = 0.0;
        double meanOfSquares = 0.0;
        for (Pair<Double, Double> sample : this.innerDistribution.getPmf()) {
            mean += sample.getValue() * sample.getKey();
            meanOfSquares += sample.getValue() * sample.getKey() * sample.getKey();
        }
        return meanOfSquares - mean * mean;
    }

    @Override
    public double getSupportLowerBound() {
        double min = Double.POSITIVE_INFINITY;
        for (Pair<Double, Double> sample : this.innerDistribution.getPmf()) {
            if (!(sample.getKey() < min) || !(sample.getValue() > 0.0)) continue;
            min = sample.getKey();
        }
        return min;
    }

    @Override
    public double getSupportUpperBound() {
        double max = Double.NEGATIVE_INFINITY;
        for (Pair<Double, Double> sample : this.innerDistribution.getPmf()) {
            if (!(sample.getKey() > max) || !(sample.getValue() > 0.0)) continue;
            max = sample.getKey();
        }
        return max;
    }

    @Override
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    @Override
    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    @Override
    public boolean isSupportConnected() {
        return true;
    }

    @Override
    public double sample() {
        return this.innerDistribution.sample();
    }
}

