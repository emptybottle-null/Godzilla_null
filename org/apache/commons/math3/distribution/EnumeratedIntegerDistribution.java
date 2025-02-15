/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class EnumeratedIntegerDistribution
extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 20130308L;
    protected final EnumeratedDistribution<Integer> innerDistribution;

    public EnumeratedIntegerDistribution(int[] singletons, double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        this(new Well19937c(), singletons, probabilities);
    }

    public EnumeratedIntegerDistribution(RandomGenerator rng, int[] singletons, double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        super(rng);
        this.innerDistribution = new EnumeratedDistribution(rng, EnumeratedIntegerDistribution.createDistribution(singletons, probabilities));
    }

    public EnumeratedIntegerDistribution(RandomGenerator rng, int[] data) {
        super(rng);
        HashMap<Integer, Integer> dataMap = new HashMap<Integer, Integer>();
        for (int value : data) {
            Integer count = (Integer)dataMap.get(value);
            if (count == null) {
                count = 0;
            }
            count = count + 1;
            dataMap.put(value, count);
        }
        int massPoints = dataMap.size();
        double denom = data.length;
        int[] values = new int[massPoints];
        double[] probabilities = new double[massPoints];
        int index = 0;
        for (Map.Entry entry : dataMap.entrySet()) {
            values[index] = (Integer)entry.getKey();
            probabilities[index] = (double)((Integer)entry.getValue()).intValue() / denom;
            ++index;
        }
        this.innerDistribution = new EnumeratedDistribution(rng, EnumeratedIntegerDistribution.createDistribution(values, probabilities));
    }

    public EnumeratedIntegerDistribution(int[] data) {
        this(new Well19937c(), data);
    }

    private static List<Pair<Integer, Double>> createDistribution(int[] singletons, double[] probabilities) {
        if (singletons.length != probabilities.length) {
            throw new DimensionMismatchException(probabilities.length, singletons.length);
        }
        ArrayList<Pair<Integer, Double>> samples = new ArrayList<Pair<Integer, Double>>(singletons.length);
        for (int i = 0; i < singletons.length; ++i) {
            samples.add(new Pair<Integer, Double>(singletons[i], probabilities[i]));
        }
        return samples;
    }

    @Override
    public double probability(int x) {
        return this.innerDistribution.probability(x);
    }

    @Override
    public double cumulativeProbability(int x) {
        double probability = 0.0;
        for (Pair<Integer, Double> sample : this.innerDistribution.getPmf()) {
            if (sample.getKey() > x) continue;
            probability += sample.getValue().doubleValue();
        }
        return probability;
    }

    @Override
    public double getNumericalMean() {
        double mean = 0.0;
        for (Pair<Integer, Double> sample : this.innerDistribution.getPmf()) {
            mean += sample.getValue() * (double)sample.getKey().intValue();
        }
        return mean;
    }

    @Override
    public double getNumericalVariance() {
        double mean = 0.0;
        double meanOfSquares = 0.0;
        for (Pair<Integer, Double> sample : this.innerDistribution.getPmf()) {
            mean += sample.getValue() * (double)sample.getKey().intValue();
            meanOfSquares += sample.getValue() * (double)sample.getKey().intValue() * (double)sample.getKey().intValue();
        }
        return meanOfSquares - mean * mean;
    }

    @Override
    public int getSupportLowerBound() {
        int min = Integer.MAX_VALUE;
        for (Pair<Integer, Double> sample : this.innerDistribution.getPmf()) {
            if (sample.getKey() >= min || !(sample.getValue() > 0.0)) continue;
            min = sample.getKey();
        }
        return min;
    }

    @Override
    public int getSupportUpperBound() {
        int max = Integer.MIN_VALUE;
        for (Pair<Integer, Double> sample : this.innerDistribution.getPmf()) {
            if (sample.getKey() <= max || !(sample.getValue() > 0.0)) continue;
            max = sample.getKey();
        }
        return max;
    }

    @Override
    public boolean isSupportConnected() {
        return true;
    }

    @Override
    public int sample() {
        return this.innerDistribution.sample();
    }
}

