/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.clustering.evaluation;

import java.util.List;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class ClusterEvaluator<T extends Clusterable> {
    private final DistanceMeasure measure;

    public ClusterEvaluator() {
        this(new EuclideanDistance());
    }

    public ClusterEvaluator(DistanceMeasure measure) {
        this.measure = measure;
    }

    public abstract double score(List<? extends Cluster<T>> var1);

    public boolean isBetterScore(double score1, double score2) {
        return score1 < score2;
    }

    protected double distance(Clusterable p1, Clusterable p2) {
        return this.measure.compute(p1.getPoint(), p2.getPoint());
    }

    protected Clusterable centroidOf(Cluster<T> cluster) {
        List<T> points = cluster.getPoints();
        if (points.isEmpty()) {
            return null;
        }
        if (cluster instanceof CentroidCluster) {
            return ((CentroidCluster)cluster).getCenter();
        }
        int dimension = ((Clusterable)points.get(0)).getPoint().length;
        double[] centroid = new double[dimension];
        for (Clusterable p : points) {
            double[] point = p.getPoint();
            for (int i = 0; i < centroid.length; ++i) {
                int n = i;
                centroid[n] = centroid[n] + point[i];
            }
        }
        int i = 0;
        while (i < centroid.length) {
            int n = i++;
            centroid[n] = centroid[n] / (double)points.size();
        }
        return new DoublePoint(centroid);
    }
}

