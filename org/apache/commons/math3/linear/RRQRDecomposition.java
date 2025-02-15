/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;

public class RRQRDecomposition
extends QRDecomposition {
    private int[] p;
    private RealMatrix cachedP;

    public RRQRDecomposition(RealMatrix matrix) {
        this(matrix, 0.0);
    }

    public RRQRDecomposition(RealMatrix matrix, double threshold) {
        super(matrix, threshold);
    }

    protected void decompose(double[][] qrt) {
        this.p = new int[qrt.length];
        for (int i = 0; i < this.p.length; ++i) {
            this.p[i] = i;
        }
        super.decompose(qrt);
    }

    protected void performHouseholderReflection(int minor, double[][] qrt) {
        double l2NormSquaredMax = 0.0;
        int l2NormSquaredMaxIndex = minor;
        for (int i = minor; i < qrt.length; ++i) {
            double l2NormSquared = 0.0;
            for (int j = 0; j < qrt[i].length; ++j) {
                l2NormSquared += qrt[i][j] * qrt[i][j];
            }
            if (!(l2NormSquared > l2NormSquaredMax)) continue;
            l2NormSquaredMax = l2NormSquared;
            l2NormSquaredMaxIndex = i;
        }
        if (l2NormSquaredMaxIndex != minor) {
            double[] tmp1 = qrt[minor];
            qrt[minor] = qrt[l2NormSquaredMaxIndex];
            qrt[l2NormSquaredMaxIndex] = tmp1;
            int tmp2 = this.p[minor];
            this.p[minor] = this.p[l2NormSquaredMaxIndex];
            this.p[l2NormSquaredMaxIndex] = tmp2;
        }
        super.performHouseholderReflection(minor, qrt);
    }

    public RealMatrix getP() {
        if (this.cachedP == null) {
            int n = this.p.length;
            this.cachedP = MatrixUtils.createRealMatrix(n, n);
            for (int i = 0; i < n; ++i) {
                this.cachedP.setEntry(this.p[i], i, 1.0);
            }
        }
        return this.cachedP;
    }

    public int getRank(double dropThreshold) {
        double thisNorm;
        int rank;
        double lastNorm;
        RealMatrix r = this.getR();
        int rows = r.getRowDimension();
        int columns = r.getColumnDimension();
        double rNorm = lastNorm = r.getFrobeniusNorm();
        for (rank = 1; rank < FastMath.min(rows, columns) && (thisNorm = r.getSubMatrix(rank, rows - 1, rank, columns - 1).getFrobeniusNorm()) != 0.0 && !(thisNorm / lastNorm * rNorm < dropThreshold); ++rank) {
            lastNorm = thisNorm;
        }
        return rank;
    }

    public DecompositionSolver getSolver() {
        return new Solver(super.getSolver(), this.getP());
    }

    private static class Solver
    implements DecompositionSolver {
        private final DecompositionSolver upper;
        private RealMatrix p;

        private Solver(DecompositionSolver upper, RealMatrix p) {
            this.upper = upper;
            this.p = p;
        }

        public boolean isNonSingular() {
            return this.upper.isNonSingular();
        }

        public RealVector solve(RealVector b) {
            return this.p.operate(this.upper.solve(b));
        }

        public RealMatrix solve(RealMatrix b) {
            return this.p.multiply(this.upper.solve(b));
        }

        public RealMatrix getInverse() {
            return this.solve(MatrixUtils.createRealIdentityMatrix(this.p.getRowDimension()));
        }
    }
}

