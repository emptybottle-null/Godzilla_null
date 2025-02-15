/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.clustering.evaluation;

import java.util.List;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.evaluation.ClusterEvaluator;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SumOfClusterVariances<T extends Clusterable>
extends ClusterEvaluator<T> {
    public SumOfClusterVariances(DistanceMeasure measure) {
        super(measure);
    }

    @Override
    public double score(List<? extends Cluster<T>> clusters) {
        double varianceSum = 0.0;
        for (Cluster<T> cluster : clusters) {
            if (cluster.getPoints().isEmpty()) continue;
            Clusterable center = this.centroidOf(cluster);
            Variance stat = new Variance();
            for (Clusterable point : cluster.getPoints()) {
                stat.increment(this.distance(point, center));
            }
            varianceSum += stat.getResult();
        }
        return varianceSum;
    }
}

