/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.clustering;

import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.Clusterer;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.clustering.evaluation.ClusterEvaluator;
import org.apache.commons.math3.ml.clustering.evaluation.SumOfClusterVariances;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MultiKMeansPlusPlusClusterer<T extends Clusterable>
extends Clusterer<T> {
    private final KMeansPlusPlusClusterer<T> clusterer;
    private final int numTrials;
    private final ClusterEvaluator<T> evaluator;

    public MultiKMeansPlusPlusClusterer(KMeansPlusPlusClusterer<T> clusterer, int numTrials) {
        this(clusterer, numTrials, new SumOfClusterVariances(clusterer.getDistanceMeasure()));
    }

    public MultiKMeansPlusPlusClusterer(KMeansPlusPlusClusterer<T> clusterer, int numTrials, ClusterEvaluator<T> evaluator) {
        super(clusterer.getDistanceMeasure());
        this.clusterer = clusterer;
        this.numTrials = numTrials;
        this.evaluator = evaluator;
    }

    public KMeansPlusPlusClusterer<T> getClusterer() {
        return this.clusterer;
    }

    public int getNumTrials() {
        return this.numTrials;
    }

    public ClusterEvaluator<T> getClusterEvaluator() {
        return this.evaluator;
    }

    @Override
    public List<CentroidCluster<T>> cluster(Collection<T> points) throws MathIllegalArgumentException, ConvergenceException {
        List<CentroidCluster<T>> best = null;
        double bestVarianceSum = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.numTrials; ++i) {
            List<CentroidCluster<T>> clusters = this.clusterer.cluster(points);
            double varianceSum = this.evaluator.score(clusters);
            if (!this.evaluator.isBetterScore(varianceSum, bestVarianceSum)) continue;
            best = clusters;
            bestVarianceSum = varianceSum;
        }
        return best;
    }
}

