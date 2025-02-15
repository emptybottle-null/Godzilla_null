/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.correlation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.RankingAlgorithm;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SpearmansCorrelation {
    private final RealMatrix data;
    private final RankingAlgorithm rankingAlgorithm;
    private final PearsonsCorrelation rankCorrelation;

    public SpearmansCorrelation() {
        this(new NaturalRanking());
    }

    public SpearmansCorrelation(RankingAlgorithm rankingAlgorithm) {
        this.data = null;
        this.rankingAlgorithm = rankingAlgorithm;
        this.rankCorrelation = null;
    }

    public SpearmansCorrelation(RealMatrix dataMatrix) {
        this(dataMatrix, new NaturalRanking());
    }

    public SpearmansCorrelation(RealMatrix dataMatrix, RankingAlgorithm rankingAlgorithm) {
        this.rankingAlgorithm = rankingAlgorithm;
        this.data = this.rankTransform(dataMatrix);
        this.rankCorrelation = new PearsonsCorrelation(this.data);
    }

    public RealMatrix getCorrelationMatrix() {
        return this.rankCorrelation.getCorrelationMatrix();
    }

    public PearsonsCorrelation getRankCorrelation() {
        return this.rankCorrelation;
    }

    public RealMatrix computeCorrelationMatrix(RealMatrix matrix) {
        RealMatrix matrixCopy = this.rankTransform(matrix);
        return new PearsonsCorrelation().computeCorrelationMatrix(matrixCopy);
    }

    public RealMatrix computeCorrelationMatrix(double[][] matrix) {
        return this.computeCorrelationMatrix(new BlockRealMatrix(matrix));
    }

    public double correlation(double[] xArray, double[] yArray) {
        if (xArray.length != yArray.length) {
            throw new DimensionMismatchException(xArray.length, yArray.length);
        }
        if (xArray.length < 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, xArray.length, 2);
        }
        double[] x = xArray;
        double[] y = yArray;
        if (this.rankingAlgorithm instanceof NaturalRanking && NaNStrategy.REMOVED == ((NaturalRanking)this.rankingAlgorithm).getNanStrategy()) {
            HashSet<Integer> nanPositions = new HashSet<Integer>();
            nanPositions.addAll(this.getNaNPositions(xArray));
            nanPositions.addAll(this.getNaNPositions(yArray));
            x = this.removeValues(xArray, nanPositions);
            y = this.removeValues(yArray, nanPositions);
        }
        return new PearsonsCorrelation().correlation(this.rankingAlgorithm.rank(x), this.rankingAlgorithm.rank(y));
    }

    private RealMatrix rankTransform(RealMatrix matrix) {
        RealMatrix transformed = null;
        if (this.rankingAlgorithm instanceof NaturalRanking && ((NaturalRanking)this.rankingAlgorithm).getNanStrategy() == NaNStrategy.REMOVED) {
            int i;
            HashSet<Integer> nanPositions = new HashSet<Integer>();
            for (i = 0; i < matrix.getColumnDimension(); ++i) {
                nanPositions.addAll(this.getNaNPositions(matrix.getColumn(i)));
            }
            if (!nanPositions.isEmpty()) {
                transformed = new BlockRealMatrix(matrix.getRowDimension() - nanPositions.size(), matrix.getColumnDimension());
                for (i = 0; i < transformed.getColumnDimension(); ++i) {
                    transformed.setColumn(i, this.removeValues(matrix.getColumn(i), nanPositions));
                }
            }
        }
        if (transformed == null) {
            transformed = matrix.copy();
        }
        for (int i = 0; i < transformed.getColumnDimension(); ++i) {
            transformed.setColumn(i, this.rankingAlgorithm.rank(transformed.getColumn(i)));
        }
        return transformed;
    }

    private List<Integer> getNaNPositions(double[] input) {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for (int i = 0; i < input.length; ++i) {
            if (!Double.isNaN(input[i])) continue;
            positions.add(i);
        }
        return positions;
    }

    private double[] removeValues(double[] input, Set<Integer> indices) {
        if (indices.isEmpty()) {
            return input;
        }
        double[] result = new double[input.length - indices.size()];
        int j = 0;
        for (int i = 0; i < input.length; ++i) {
            if (indices.contains(i)) continue;
            result[j++] = input[i];
        }
        return result;
    }
}

