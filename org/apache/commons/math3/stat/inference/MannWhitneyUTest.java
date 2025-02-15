/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.util.FastMath;

public class MannWhitneyUTest {
    private NaturalRanking naturalRanking;

    public MannWhitneyUTest() {
        this.naturalRanking = new NaturalRanking(NaNStrategy.FIXED, TiesStrategy.AVERAGE);
    }

    public MannWhitneyUTest(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
        this.naturalRanking = new NaturalRanking(nanStrategy, tiesStrategy);
    }

    private void ensureDataConformance(double[] x, double[] y) throws NullArgumentException, NoDataException {
        if (x == null || y == null) {
            throw new NullArgumentException();
        }
        if (x.length == 0 || y.length == 0) {
            throw new NoDataException();
        }
    }

    private double[] concatenateSamples(double[] x, double[] y) {
        double[] z = new double[x.length + y.length];
        System.arraycopy(x, 0, z, 0, x.length);
        System.arraycopy(y, 0, z, x.length, y.length);
        return z;
    }

    public double mannWhitneyU(double[] x, double[] y) throws NullArgumentException, NoDataException {
        this.ensureDataConformance(x, y);
        double[] z = this.concatenateSamples(x, y);
        double[] ranks = this.naturalRanking.rank(z);
        double sumRankX = 0.0;
        for (int i = 0; i < x.length; ++i) {
            sumRankX += ranks[i];
        }
        double U1 = sumRankX - (double)((long)x.length * (long)(x.length + 1) / 2L);
        double U2 = (double)((long)x.length * (long)y.length) - U1;
        return FastMath.max(U1, U2);
    }

    private double calculateAsymptoticPValue(double Umin, int n1, int n2) throws ConvergenceException, MaxCountExceededException {
        long n1n2prod = (long)n1 * (long)n2;
        double EU = (double)n1n2prod / 2.0;
        double VarU = (double)(n1n2prod * (long)(n1 + n2 + 1)) / 12.0;
        double z = (Umin - EU) / FastMath.sqrt(VarU);
        NormalDistribution standardNormal = new NormalDistribution(null, 0.0, 1.0);
        return 2.0 * standardNormal.cumulativeProbability(z);
    }

    public double mannWhitneyUTest(double[] x, double[] y) throws NullArgumentException, NoDataException, ConvergenceException, MaxCountExceededException {
        this.ensureDataConformance(x, y);
        double Umax = this.mannWhitneyU(x, y);
        double Umin = (double)((long)x.length * (long)y.length) - Umax;
        return this.calculateAsymptoticPValue(Umin, x.length, y.length);
    }
}

