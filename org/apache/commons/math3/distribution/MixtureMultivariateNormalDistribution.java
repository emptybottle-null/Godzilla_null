/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.MixtureMultivariateRealDistribution;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MixtureMultivariateNormalDistribution
extends MixtureMultivariateRealDistribution<MultivariateNormalDistribution> {
    public MixtureMultivariateNormalDistribution(double[] weights, double[][] means, double[][][] covariances) {
        super(MixtureMultivariateNormalDistribution.createComponents(weights, means, covariances));
    }

    public MixtureMultivariateNormalDistribution(List<Pair<Double, MultivariateNormalDistribution>> components) {
        super(components);
    }

    public MixtureMultivariateNormalDistribution(RandomGenerator rng, List<Pair<Double, MultivariateNormalDistribution>> components) throws NotPositiveException, DimensionMismatchException {
        super(rng, components);
    }

    private static List<Pair<Double, MultivariateNormalDistribution>> createComponents(double[] weights, double[][] means, double[][][] covariances) {
        ArrayList<Pair<Double, MultivariateNormalDistribution>> mvns = new ArrayList<Pair<Double, MultivariateNormalDistribution>>(weights.length);
        for (int i = 0; i < weights.length; ++i) {
            MultivariateNormalDistribution dist = new MultivariateNormalDistribution(means[i], covariances[i]);
            mvns.add(new Pair<Double, MultivariateNormalDistribution>(weights[i], dist));
        }
        return mvns;
    }
}

