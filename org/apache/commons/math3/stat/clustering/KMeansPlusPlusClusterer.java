/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.clustering.Cluster;
import org.apache.commons.math3.stat.clustering.Clusterable;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class KMeansPlusPlusClusterer<T extends Clusterable<T>> {
    private final Random random;
    private final EmptyClusterStrategy emptyStrategy;

    public KMeansPlusPlusClusterer(Random random) {
        this(random, EmptyClusterStrategy.LARGEST_VARIANCE);
    }

    public KMeansPlusPlusClusterer(Random random, EmptyClusterStrategy emptyStrategy) {
        this.random = random;
        this.emptyStrategy = emptyStrategy;
    }

    public List<Cluster<T>> cluster(Collection<T> points, int k, int numTrials, int maxIterationsPerTrial) throws MathIllegalArgumentException, ConvergenceException {
        List<Cluster<T>> best = null;
        double bestVarianceSum = Double.POSITIVE_INFINITY;
        for (int i = 0; i < numTrials; ++i) {
            List<Cluster<T>> clusters = this.cluster(points, k, maxIterationsPerTrial);
            double varianceSum = 0.0;
            for (Cluster<T> cluster : clusters) {
                if (cluster.getPoints().isEmpty()) continue;
                T center = cluster.getCenter();
                Variance stat = new Variance();
                for (Clusterable point : cluster.getPoints()) {
                    stat.increment(point.distanceFrom(center));
                }
                varianceSum += stat.getResult();
            }
            if (!(varianceSum <= bestVarianceSum)) continue;
            best = clusters;
            bestVarianceSum = varianceSum;
        }
        return best;
    }

    public List<Cluster<T>> cluster(Collection<T> points, int k, int maxIterations) throws MathIllegalArgumentException, ConvergenceException {
        MathUtils.checkNotNull(points);
        if (points.size() < k) {
            throw new NumberIsTooSmallException(points.size(), (Number)k, false);
        }
        List<Cluster<T>> clusters = KMeansPlusPlusClusterer.chooseInitialCenters(points, k, this.random);
        int[] assignments = new int[points.size()];
        KMeansPlusPlusClusterer.assignPointsToClusters(clusters, points, assignments);
        int max = maxIterations < 0 ? Integer.MAX_VALUE : maxIterations;
        for (int count = 0; count < max; ++count) {
            boolean emptyCluster = false;
            ArrayList<Cluster<T>> newClusters = new ArrayList<Cluster<T>>();
            for (Cluster<T> cluster : clusters) {
                Clusterable newCenter;
                if (cluster.getPoints().isEmpty()) {
                    switch (this.emptyStrategy) {
                        case LARGEST_VARIANCE: {
                            newCenter = this.getPointFromLargestVarianceCluster(clusters);
                            break;
                        }
                        case LARGEST_POINTS_NUMBER: {
                            newCenter = this.getPointFromLargestNumberCluster(clusters);
                            break;
                        }
                        case FARTHEST_POINT: {
                            newCenter = this.getFarthestPoint(clusters);
                            break;
                        }
                        default: {
                            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
                        }
                    }
                    emptyCluster = true;
                } else {
                    newCenter = (Clusterable)cluster.getCenter().centroidOf(cluster.getPoints());
                }
                newClusters.add(new Cluster<Clusterable>(newCenter));
            }
            int changes = KMeansPlusPlusClusterer.assignPointsToClusters(newClusters, points, assignments);
            clusters = newClusters;
            if (changes != 0 || emptyCluster) continue;
            return clusters;
        }
        return clusters;
    }

    private static <T extends Clusterable<T>> int assignPointsToClusters(List<Cluster<T>> clusters, Collection<T> points, int[] assignments) {
        int assignedDifferently = 0;
        int pointIndex = 0;
        for (Clusterable p : points) {
            int clusterIndex = KMeansPlusPlusClusterer.getNearestCluster(clusters, p);
            if (clusterIndex != assignments[pointIndex]) {
                ++assignedDifferently;
            }
            Cluster<Clusterable> cluster = clusters.get(clusterIndex);
            cluster.addPoint(p);
            assignments[pointIndex++] = clusterIndex;
        }
        return assignedDifferently;
    }

    private static <T extends Clusterable<T>> List<Cluster<T>> chooseInitialCenters(Collection<T> points, int k, Random random) {
        List<T> pointList = Collections.unmodifiableList(new ArrayList<T>(points));
        int numPoints = pointList.size();
        boolean[] taken = new boolean[numPoints];
        ArrayList<Cluster<T>> resultSet = new ArrayList<Cluster<T>>();
        int firstPointIndex = random.nextInt(numPoints);
        Clusterable firstPoint = (Clusterable)pointList.get(firstPointIndex);
        resultSet.add(new Cluster<Clusterable>(firstPoint));
        taken[firstPointIndex] = true;
        double[] minDistSquared = new double[numPoints];
        for (int i = 0; i < numPoints; ++i) {
            if (i == firstPointIndex) continue;
            double d = firstPoint.distanceFrom(pointList.get(i));
            minDistSquared[i] = d * d;
        }
        while (resultSet.size() < k) {
            int i;
            double distSqSum = 0.0;
            for (int i2 = 0; i2 < numPoints; ++i2) {
                if (taken[i2]) continue;
                distSqSum += minDistSquared[i2];
            }
            double r = random.nextDouble() * distSqSum;
            int nextPointIndex = -1;
            double sum = 0.0;
            for (i = 0; i < numPoints; ++i) {
                if (taken[i] || !((sum += minDistSquared[i]) >= r)) continue;
                nextPointIndex = i;
                break;
            }
            if (nextPointIndex == -1) {
                for (i = numPoints - 1; i >= 0; --i) {
                    if (taken[i]) continue;
                    nextPointIndex = i;
                    break;
                }
            }
            if (nextPointIndex < 0) break;
            Clusterable p = (Clusterable)pointList.get(nextPointIndex);
            resultSet.add(new Cluster<Clusterable>(p));
            taken[nextPointIndex] = true;
            if (resultSet.size() >= k) continue;
            for (int j = 0; j < numPoints; ++j) {
                double d;
                double d2;
                if (taken[j] || !((d2 = (d = p.distanceFrom(pointList.get(j))) * d) < minDistSquared[j])) continue;
                minDistSquared[j] = d2;
            }
        }
        return resultSet;
    }

    private T getPointFromLargestVarianceCluster(Collection<Cluster<T>> clusters) throws ConvergenceException {
        double maxVariance = Double.NEGATIVE_INFINITY;
        Cluster<T> selected = null;
        for (Cluster<T> cluster : clusters) {
            if (cluster.getPoints().isEmpty()) continue;
            T center = cluster.getCenter();
            Variance stat = new Variance();
            for (Clusterable point : cluster.getPoints()) {
                stat.increment(point.distanceFrom(center));
            }
            double variance = stat.getResult();
            if (!(variance > maxVariance)) continue;
            maxVariance = variance;
            selected = cluster;
        }
        if (selected == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        List selectedPoints = selected.getPoints();
        return (T)((Clusterable)selectedPoints.remove(this.random.nextInt(selectedPoints.size())));
    }

    private T getPointFromLargestNumberCluster(Collection<Cluster<T>> clusters) throws ConvergenceException {
        int maxNumber = 0;
        Cluster<T> selected = null;
        for (Cluster<T> cluster : clusters) {
            int number = cluster.getPoints().size();
            if (number <= maxNumber) continue;
            maxNumber = number;
            selected = cluster;
        }
        if (selected == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        List selectedPoints = selected.getPoints();
        return (T)((Clusterable)selectedPoints.remove(this.random.nextInt(selectedPoints.size())));
    }

    private T getFarthestPoint(Collection<Cluster<T>> clusters) throws ConvergenceException {
        double maxDistance = Double.NEGATIVE_INFINITY;
        Cluster<T> selectedCluster = null;
        int selectedPoint = -1;
        for (Cluster<T> cluster : clusters) {
            T center = cluster.getCenter();
            List<T> points = cluster.getPoints();
            for (int i = 0; i < points.size(); ++i) {
                double distance = ((Clusterable)points.get(i)).distanceFrom(center);
                if (!(distance > maxDistance)) continue;
                maxDistance = distance;
                selectedCluster = cluster;
                selectedPoint = i;
            }
        }
        if (selectedCluster == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        return (T)((Clusterable)selectedCluster.getPoints().remove(selectedPoint));
    }

    private static <T extends Clusterable<T>> int getNearestCluster(Collection<Cluster<T>> clusters, T point) {
        double minDistance = Double.MAX_VALUE;
        int clusterIndex = 0;
        int minCluster = 0;
        for (Cluster<T> c : clusters) {
            double distance = point.distanceFrom(c.getCenter());
            if (distance < minDistance) {
                minDistance = distance;
                minCluster = clusterIndex;
            }
            ++clusterIndex;
        }
        return minCluster;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum EmptyClusterStrategy {
        LARGEST_VARIANCE,
        LARGEST_POINTS_NUMBER,
        FARTHEST_POINT,
        ERROR;

    }
}

