/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.clustering.Cluster;
import org.apache.commons.math3.stat.clustering.Clusterable;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class DBSCANClusterer<T extends Clusterable<T>> {
    private final double eps;
    private final int minPts;

    public DBSCANClusterer(double eps, int minPts) throws NotPositiveException {
        if (eps < 0.0) {
            throw new NotPositiveException(eps);
        }
        if (minPts < 0) {
            throw new NotPositiveException(minPts);
        }
        this.eps = eps;
        this.minPts = minPts;
    }

    public double getEps() {
        return this.eps;
    }

    public int getMinPts() {
        return this.minPts;
    }

    public List<Cluster<T>> cluster(Collection<T> points) throws NullArgumentException {
        MathUtils.checkNotNull(points);
        ArrayList<Cluster<T>> clusters = new ArrayList<Cluster<T>>();
        HashMap<Clusterable<T>, PointStatus> visited = new HashMap<Clusterable<T>, PointStatus>();
        for (Clusterable point : points) {
            if (visited.get(point) != null) continue;
            List<Clusterable> neighbors = this.getNeighbors(point, points);
            if (neighbors.size() >= this.minPts) {
                Cluster<Object> cluster = new Cluster<Object>(null);
                clusters.add(this.expandCluster(cluster, point, neighbors, points, visited));
                continue;
            }
            visited.put(point, PointStatus.NOISE);
        }
        return clusters;
    }

    private Cluster<T> expandCluster(Cluster<T> cluster, T point, List<T> neighbors, Collection<T> points, Map<Clusterable<T>, PointStatus> visited) {
        cluster.addPoint((Clusterable)point);
        visited.put((Clusterable<T>)point, PointStatus.PART_OF_CLUSTER);
        List<Object> seeds = new ArrayList<T>(neighbors);
        for (int index = 0; index < seeds.size(); ++index) {
            List<Clusterable> currentNeighbors;
            Clusterable current = (Clusterable)seeds.get(index);
            PointStatus pStatus = visited.get(current);
            if (pStatus == null && (currentNeighbors = this.getNeighbors(current, points)).size() >= this.minPts) {
                seeds = this.merge(seeds, currentNeighbors);
            }
            if (pStatus == PointStatus.PART_OF_CLUSTER) continue;
            visited.put(current, PointStatus.PART_OF_CLUSTER);
            cluster.addPoint(current);
        }
        return cluster;
    }

    private List<T> getNeighbors(T point, Collection<T> points) {
        ArrayList<Clusterable> neighbors = new ArrayList<Clusterable>();
        for (Clusterable neighbor : points) {
            if (point == neighbor || !(neighbor.distanceFrom(point) <= this.eps)) continue;
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    private List<T> merge(List<T> one, List<T> two) {
        HashSet<T> oneSet = new HashSet<T>(one);
        for (Clusterable item : two) {
            if (oneSet.contains(item)) continue;
            one.add(item);
        }
        return one;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static enum PointStatus {
        NOISE,
        PART_OF_CLUSTER;

    }
}

