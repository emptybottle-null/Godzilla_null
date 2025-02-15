/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class HermiteRuleFactory
extends BaseRuleFactory<Double> {
    private static final double SQRT_PI = 1.772453850905516;
    private static final double H0 = 0.7511255444649425;
    private static final double H1 = 1.0622519320271968;

    @Override
    protected Pair<Double[], Double[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        if (numberOfPoints == 1) {
            return new Pair<Double[], Double[]>(new Double[]{0.0}, new Double[]{1.772453850905516});
        }
        int lastNumPoints = numberOfPoints - 1;
        Double[] previousPoints = (Double[])this.getRuleInternal(lastNumPoints).getFirst();
        Double[] points = new Double[numberOfPoints];
        Double[] weights = new Double[numberOfPoints];
        double sqrtTwoTimesLastNumPoints = FastMath.sqrt(2 * lastNumPoints);
        double sqrtTwoTimesNumPoints = FastMath.sqrt(2 * numberOfPoints);
        int iMax = numberOfPoints / 2;
        for (int i = 0; i < iMax; ++i) {
            double a = i == 0 ? -sqrtTwoTimesLastNumPoints : previousPoints[i - 1];
            double b = iMax == 1 ? -0.5 : previousPoints[i];
            double hma = 0.7511255444649425;
            double ha = 1.0622519320271968 * a;
            double hmb = 0.7511255444649425;
            double hb = 1.0622519320271968 * b;
            for (int j = 1; j < numberOfPoints; ++j) {
                double jp1 = j + 1;
                double s = FastMath.sqrt(2.0 / jp1);
                double sm = FastMath.sqrt((double)j / jp1);
                double hpa = s * a * ha - sm * hma;
                double hpb = s * b * hb - sm * hmb;
                hma = ha;
                ha = hpa;
                hmb = hb;
                hb = hpb;
            }
            double c = 0.5 * (a + b);
            double hmc = 0.7511255444649425;
            double hc = 1.0622519320271968 * c;
            boolean done = false;
            while (!done) {
                done = b - a <= Math.ulp(c);
                hmc = 0.7511255444649425;
                hc = 1.0622519320271968 * c;
                for (int j = 1; j < numberOfPoints; ++j) {
                    double jp1 = j + 1;
                    double s = FastMath.sqrt(2.0 / jp1);
                    double sm = FastMath.sqrt((double)j / jp1);
                    double hpc = s * c * hc - sm * hmc;
                    hmc = hc;
                    hc = hpc;
                }
                if (done) continue;
                if (ha * hc < 0.0) {
                    b = c;
                    hmb = hmc;
                    hb = hc;
                } else {
                    a = c;
                    hma = hmc;
                    ha = hc;
                }
                c = 0.5 * (a + b);
            }
            double d = sqrtTwoTimesNumPoints * hmc;
            double w = 2.0 / (d * d);
            points[i] = c;
            weights[i] = w;
            int idx = lastNumPoints - i;
            points[idx] = -c;
            weights[idx] = w;
        }
        if (numberOfPoints % 2 != 0) {
            double hm = 0.7511255444649425;
            for (int j = 1; j < numberOfPoints; j += 2) {
                double jp1 = j + 1;
                hm = -FastMath.sqrt((double)j / jp1) * hm;
            }
            double d = sqrtTwoTimesNumPoints * hm;
            double w = 2.0 / (d * d);
            points[iMax] = 0.0;
            weights[iMax] = w;
        }
        return new Pair<Double[], Double[]>(points, weights);
    }
}

