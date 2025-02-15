/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution.fitting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;

public class MultivariateNormalMixtureExpectationMaximization {
    private static final int DEFAULT_MAX_ITERATIONS = 1000;
    private static final double DEFAULT_THRESHOLD = 1.0E-5;
    private final double[][] data;
    private MixtureMultivariateNormalDistribution fittedModel;
    private double logLikelihood = 0.0;

    public MultivariateNormalMixtureExpectationMaximization(double[][] data) throws NotStrictlyPositiveException, DimensionMismatchException, NumberIsTooSmallException {
        if (data.length < 1) {
            throw new NotStrictlyPositiveException(data.length);
        }
        this.data = new double[data.length][data[0].length];
        for (int i = 0; i < data.length; ++i) {
            if (data[i].length != data[0].length) {
                throw new DimensionMismatchException(data[i].length, data[0].length);
            }
            if (data[i].length < 2) {
                throw new NumberIsTooSmallException((Localizable)LocalizedFormats.NUMBER_TOO_SMALL, (Number)data[i].length, 2, true);
            }
            this.data[i] = MathArrays.copyOf(data[i], data[i].length);
        }
    }

    public void fit(MixtureMultivariateNormalDistribution initialMixture, int maxIterations, double threshold) throws SingularMatrixException, NotStrictlyPositiveException, DimensionMismatchException {
        if (maxIterations < 1) {
            throw new NotStrictlyPositiveException(maxIterations);
        }
        if (threshold < Double.MIN_VALUE) {
            throw new NotStrictlyPositiveException(threshold);
        }
        int n = this.data.length;
        int numCols = this.data[0].length;
        int k = initialMixture.getComponents().size();
        int numMeanColumns = ((MultivariateNormalDistribution)initialMixture.getComponents().get(0).getSecond()).getMeans().length;
        if (numMeanColumns != numCols) {
            throw new DimensionMismatchException(numMeanColumns, numCols);
        }
        int numIterations = 0;
        double previousLogLikelihood = 0.0;
        this.logLikelihood = Double.NEGATIVE_INFINITY;
        this.fittedModel = new MixtureMultivariateNormalDistribution(initialMixture.getComponents());
        while (numIterations++ <= maxIterations && FastMath.abs(previousLogLikelihood - this.logLikelihood) > threshold) {
            int j;
            int j2;
            previousLogLikelihood = this.logLikelihood;
            double sumLogLikelihood = 0.0;
            List components = this.fittedModel.getComponents();
            double[] weights = new double[k];
            MultivariateNormalDistribution[] mvns = new MultivariateNormalDistribution[k];
            for (int j3 = 0; j3 < k; ++j3) {
                weights[j3] = components.get(j3).getFirst();
                mvns[j3] = (MultivariateNormalDistribution)components.get(j3).getSecond();
            }
            double[][] gamma = new double[n][k];
            double[] gammaSums = new double[k];
            double[][] gammaDataProdSums = new double[k][numCols];
            for (int i = 0; i < n; ++i) {
                double rowDensity = this.fittedModel.density(this.data[i]);
                sumLogLikelihood += FastMath.log(rowDensity);
                for (j2 = 0; j2 < k; ++j2) {
                    gamma[i][j2] = weights[j2] * mvns[j2].density(this.data[i]) / rowDensity;
                    int n2 = j2;
                    gammaSums[n2] = gammaSums[n2] + gamma[i][j2];
                    for (int col = 0; col < numCols; ++col) {
                        double[] dArray = gammaDataProdSums[j2];
                        int n3 = col;
                        dArray[n3] = dArray[n3] + gamma[i][j2] * this.data[i][col];
                    }
                }
            }
            this.logLikelihood = sumLogLikelihood / (double)n;
            double[] newWeights = new double[k];
            double[][] newMeans = new double[k][numCols];
            for (int j4 = 0; j4 < k; ++j4) {
                newWeights[j4] = gammaSums[j4] / (double)n;
                for (int col = 0; col < numCols; ++col) {
                    newMeans[j4][col] = gammaDataProdSums[j4][col] / gammaSums[j4];
                }
            }
            RealMatrix[] newCovMats = new RealMatrix[k];
            for (j2 = 0; j2 < k; ++j2) {
                newCovMats[j2] = new Array2DRowRealMatrix(numCols, numCols);
            }
            for (int i = 0; i < n; ++i) {
                for (j = 0; j < k; ++j) {
                    Array2DRowRealMatrix vec = new Array2DRowRealMatrix(MathArrays.ebeSubtract(this.data[i], newMeans[j]));
                    RealMatrix dataCov = vec.multiply(vec.transpose()).scalarMultiply(gamma[i][j]);
                    newCovMats[j] = newCovMats[j].add(dataCov);
                }
            }
            double[][][] newCovMatArrays = new double[k][numCols][numCols];
            for (j = 0; j < k; ++j) {
                newCovMats[j] = newCovMats[j].scalarMultiply(1.0 / gammaSums[j]);
                newCovMatArrays[j] = newCovMats[j].getData();
            }
            this.fittedModel = new MixtureMultivariateNormalDistribution(newWeights, newMeans, newCovMatArrays);
        }
        if (FastMath.abs(previousLogLikelihood - this.logLikelihood) > threshold) {
            throw new ConvergenceException();
        }
    }

    public void fit(MixtureMultivariateNormalDistribution initialMixture) throws SingularMatrixException, NotStrictlyPositiveException {
        this.fit(initialMixture, 1000, 1.0E-5);
    }

    public static MixtureMultivariateNormalDistribution estimate(double[][] data, int numComponents) throws NotStrictlyPositiveException, DimensionMismatchException {
        if (data.length < 2) {
            throw new NotStrictlyPositiveException(data.length);
        }
        if (numComponents < 2) {
            throw new NumberIsTooSmallException(numComponents, (Number)2, true);
        }
        if (numComponents > data.length) {
            throw new NumberIsTooLargeException(numComponents, (Number)data.length, true);
        }
        int numRows = data.length;
        int numCols = data[0].length;
        Object[] sortedData = new DataRow[numRows];
        for (int i = 0; i < numRows; ++i) {
            sortedData[i] = new DataRow(data[i]);
        }
        Arrays.sort(sortedData);
        double weight = 1.0 / (double)numComponents;
        ArrayList<Pair<Double, MultivariateNormalDistribution>> components = new ArrayList<Pair<Double, MultivariateNormalDistribution>>(numComponents);
        for (int binIndex = 0; binIndex < numComponents; ++binIndex) {
            int minIndex = binIndex * numRows / numComponents;
            int maxIndex = (binIndex + 1) * numRows / numComponents;
            int numBinRows = maxIndex - minIndex;
            double[][] binData = new double[numBinRows][numCols];
            double[] columnMeans = new double[numCols];
            int i = minIndex;
            int iBin = 0;
            while (i < maxIndex) {
                for (int j = 0; j < numCols; ++j) {
                    double val = ((DataRow)sortedData[i]).getRow()[j];
                    int n = j;
                    columnMeans[n] = columnMeans[n] + val;
                    binData[iBin][j] = val;
                }
                ++i;
                ++iBin;
            }
            MathArrays.scaleInPlace(1.0 / (double)numBinRows, columnMeans);
            double[][] covMat = new Covariance(binData).getCovarianceMatrix().getData();
            MultivariateNormalDistribution mvn = new MultivariateNormalDistribution(columnMeans, covMat);
            components.add(new Pair<Double, MultivariateNormalDistribution>(weight, mvn));
        }
        return new MixtureMultivariateNormalDistribution((List<Pair<Double, MultivariateNormalDistribution>>)components);
    }

    public double getLogLikelihood() {
        return this.logLikelihood;
    }

    public MixtureMultivariateNormalDistribution getFittedModel() {
        return new MixtureMultivariateNormalDistribution(this.fittedModel.getComponents());
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class DataRow
    implements Comparable<DataRow> {
        private final double[] row;
        private Double mean;

        DataRow(double[] data) {
            this.row = data;
            this.mean = 0.0;
            for (int i = 0; i < data.length; ++i) {
                this.mean = this.mean + data[i];
            }
            this.mean = this.mean / (double)data.length;
        }

        @Override
        public int compareTo(DataRow other) {
            return this.mean.compareTo(other.mean);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof DataRow) {
                return MathArrays.equals(this.row, ((DataRow)other).row);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.row);
        }

        public double[] getRow() {
            return this.row;
        }
    }
}

