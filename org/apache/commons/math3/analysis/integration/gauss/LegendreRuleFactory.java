/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LegendreRuleFactory
extends BaseRuleFactory<Double> {
    @Override
    protected Pair<Double[], Double[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        if (numberOfPoints == 1) {
            return new Pair<Double[], Double[]>(new Double[]{0.0}, new Double[]{2.0});
        }
        Double[] previousPoints = (Double[])this.getRuleInternal(numberOfPoints - 1).getFirst();
        Double[] points = new Double[numberOfPoints];
        Double[] weights = new Double[numberOfPoints];
        int iMax = numberOfPoints / 2;
        for (int i = 0; i < iMax; ++i) {
            double a = i == 0 ? -1.0 : previousPoints[i - 1];
            double b = iMax == 1 ? 1.0 : previousPoints[i];
            double pma = 1.0;
            double pa = a;
            double pmb = 1.0;
            double pb = b;
            for (int j = 1; j < numberOfPoints; ++j) {
                int two_j_p_1 = 2 * j + 1;
                int j_p_1 = j + 1;
                double ppa = ((double)two_j_p_1 * a * pa - (double)j * pma) / (double)j_p_1;
                double ppb = ((double)two_j_p_1 * b * pb - (double)j * pmb) / (double)j_p_1;
                pma = pa;
                pa = ppa;
                pmb = pb;
                pb = ppb;
            }
            double c = 0.5 * (a + b);
            double pmc = 1.0;
            double pc = c;
            boolean done = false;
            while (!done) {
                done = b - a <= Math.ulp(c);
                pmc = 1.0;
                pc = c;
                for (int j = 1; j < numberOfPoints; ++j) {
                    double ppc = ((double)(2 * j + 1) * c * pc - (double)j * pmc) / (double)(j + 1);
                    pmc = pc;
                    pc = ppc;
                }
                if (done) continue;
                if (pa * pc <= 0.0) {
                    b = c;
                    pmb = pmc;
                    pb = pc;
                } else {
                    a = c;
                    pma = pmc;
                    pa = pc;
                }
                c = 0.5 * (a + b);
            }
            double d = (double)numberOfPoints * (pmc - c * pc);
            double w = 2.0 * (1.0 - c * c) / (d * d);
            points[i] = c;
            weights[i] = w;
            int idx = numberOfPoints - i - 1;
            points[idx] = -c;
            weights[idx] = w;
        }
        if (numberOfPoints % 2 != 0) {
            double pmc = 1.0;
            for (int j = 1; j < numberOfPoints; j += 2) {
                pmc = (double)(-j) * pmc / (double)(j + 1);
            }
            double d = (double)numberOfPoints * pmc;
            double w = 2.0 / (d * d);
            points[iMax] = 0.0;
            weights[iMax] = w;
        }
        return new Pair<Double[], Double[]>(points, weights);
    }
}

