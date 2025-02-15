/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.Clusterer;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FuzzyKMeansClusterer<T extends Clusterable>
extends Clusterer<T> {
    private static final double DEFAULT_EPSILON = 0.001;
    private final int k;
    private final int maxIterations;
    private final double fuzziness;
    private final double epsilon;
    private final RandomGenerator random;
    private double[][] membershipMatrix;
    private List<T> points;
    private List<CentroidCluster<T>> clusters;

    public FuzzyKMeansClusterer(int k, double fuzziness) throws NumberIsTooSmallException {
        this(k, fuzziness, -1, new EuclideanDistance());
    }

    public FuzzyKMeansClusterer(int k, double fuzziness, int maxIterations, DistanceMeasure measure) throws NumberIsTooSmallException {
        this(k, fuzziness, maxIterations, measure, 0.001, new JDKRandomGenerator());
    }

    public FuzzyKMeansClusterer(int k, double fuzziness, int maxIterations, DistanceMeasure measure, double epsilon, RandomGenerator random) throws NumberIsTooSmallException {
        super(measure);
        if (fuzziness <= 1.0) {
            throw new NumberIsTooSmallException(fuzziness, (Number)1.0, false);
        }
        this.k = k;
        this.fuzziness = fuzziness;
        this.maxIterations = maxIterations;
        this.epsilon = epsilon;
        this.random = random;
        this.membershipMatrix = null;
        this.points = null;
        this.clusters = null;
    }

    public int getK() {
        return this.k;
    }

    public double getFuzziness() {
        return this.fuzziness;
    }

    public int getMaxIterations() {
        return this.maxIterations;
    }

    public double getEpsilon() {
        return this.epsilon;
    }

    public RandomGenerator getRandomGenerator() {
        return this.random;
    }

    public RealMatrix getMembershipMatrix() {
        if (this.membershipMatrix == null) {
            throw new MathIllegalStateException();
        }
        return MatrixUtils.createRealMatrix(this.membershipMatrix);
    }

    public List<T> getDataPoints() {
        return this.points;
    }

    public List<CentroidCluster<T>> getClusters() {
        return this.clusters;
    }

    public double getObjectiveFunctionValue() {
        if (this.points == null || this.clusters == null) {
            throw new MathIllegalStateException();
        }
        int i = 0;
        double objFunction = 0.0;
        for (Clusterable point : this.points) {
            int j = 0;
            for (CentroidCluster<T> cluster : this.clusters) {
                double dist = this.distance(point, cluster.getCenter());
                objFunction += dist * dist * FastMath.pow(this.membershipMatrix[i][j], this.fuzziness);
                ++j;
            }
            ++i;
        }
        return objFunction;
    }

    @Override
    public List<CentroidCluster<T>> cluster(Collection<T> dataPoints) throws MathIllegalArgumentException {
        MathUtils.checkNotNull(dataPoints);
        int size = dataPoints.size();
        if (size < this.k) {
            throw new NumberIsTooSmallException(size, (Number)this.k, false);
        }
        this.points = Collections.unmodifiableList(new ArrayList<T>(dataPoints));
        this.clusters = new ArrayList<CentroidCluster<T>>();
        this.membershipMatrix = new double[size][this.k];
        double[][] oldMatrix = new double[size][this.k];
        if (size == 0) {
            return this.clusters;
        }
        this.initializeMembershipMatrix();
        int pointDimension = ((Clusterable)this.points.get(0)).getPoint().length;
        for (int i = 0; i < this.k; ++i) {
            this.clusters.add(new CentroidCluster(new DoublePoint(new double[pointDimension])));
        }
        int iteration = 0;
        int max = this.maxIterations < 0 ? Integer.MAX_VALUE : this.maxIterations;
        double difference = 0.0;
        do {
            this.saveMembershipMatrix(oldMatrix);
            this.updateClusterCenters();
            this.updateMembershipMatrix();
        } while ((difference = this.calculateMaxMembershipChange(oldMatrix)) > this.epsilon && ++iteration < max);
        return this.clusters;
    }

    private void updateClusterCenters() {
        int j = 0;
        ArrayList<CentroidCluster<T>> newClusters = new ArrayList<CentroidCluster<T>>(this.k);
        for (CentroidCluster<T> cluster : this.clusters) {
            Clusterable center = cluster.getCenter();
            int i = 0;
            double[] arr = new double[center.getPoint().length];
            double sum = 0.0;
            for (Clusterable point : this.points) {
                double u = FastMath.pow(this.membershipMatrix[i][j], this.fuzziness);
                double[] pointArr = point.getPoint();
                for (int idx = 0; idx < arr.length; ++idx) {
                    int n = idx;
                    arr[n] = arr[n] + u * pointArr[idx];
                }
                sum += u;
                ++i;
            }
            MathArrays.scaleInPlace(1.0 / sum, arr);
            newClusters.add(new CentroidCluster(new DoublePoint(arr)));
            ++j;
        }
        this.clusters.clear();
        this.clusters = newClusters;
    }

    private void updateMembershipMatrix() {
        for (int i = 0; i < this.points.size(); ++i) {
            Clusterable point = (Clusterable)this.points.get(i);
            double maxMembership = Double.MIN_VALUE;
            int newCluster = -1;
            for (int j = 0; j < this.clusters.size(); ++j) {
                double sum = 0.0;
                double distA = FastMath.abs(this.distance(point, this.clusters.get(j).getCenter()));
                if (distA != 0.0) {
                    for (CentroidCluster<T> c : this.clusters) {
                        double distB = FastMath.abs(this.distance(point, c.getCenter()));
                        if (distB == 0.0) {
                            sum = Double.POSITIVE_INFINITY;
                            break;
                        }
                        sum += FastMath.pow(distA / distB, 2.0 / (this.fuzziness - 1.0));
                    }
                }
                double membership = sum == 0.0 ? 1.0 : (sum == Double.POSITIVE_INFINITY ? 0.0 : 1.0 / sum);
                this.membershipMatrix[i][j] = membership;
                if (!(this.membershipMatrix[i][j] > maxMembership)) continue;
                maxMembership = this.membershipMatrix[i][j];
                newCluster = j;
            }
            this.clusters.get(newCluster).addPoint(point);
        }
    }

    private void initializeMembershipMatrix() {
        for (int i = 0; i < this.points.size(); ++i) {
            for (int j = 0; j < this.k; ++j) {
                this.membershipMatrix[i][j] = this.random.nextDouble();
            }
            this.membershipMatrix[i] = MathArrays.normalizeArray(this.membershipMatrix[i], 1.0);
        }
    }

    private double calculateMaxMembershipChange(double[][] matrix) {
        double maxMembership = 0.0;
        for (int i = 0; i < this.points.size(); ++i) {
            for (int j = 0; j < this.clusters.size(); ++j) {
                double v = FastMath.abs(this.membershipMatrix[i][j] - matrix[i][j]);
                maxMembership = FastMath.max(v, maxMembership);
            }
        }
        return maxMembership;
    }

    private void saveMembershipMatrix(double[][] matrix) {
        for (int i = 0; i < this.points.size(); ++i) {
            System.arraycopy(this.membershipMatrix[i], 0, matrix[i], 0, this.clusters.size());
        }
    }
}

