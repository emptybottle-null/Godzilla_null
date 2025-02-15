/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration.gauss;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LegendreHighPrecisionRuleFactory
extends BaseRuleFactory<BigDecimal> {
    private final MathContext mContext;
    private final BigDecimal two;
    private final BigDecimal minusOne;
    private final BigDecimal oneHalf;

    public LegendreHighPrecisionRuleFactory() {
        this(MathContext.DECIMAL128);
    }

    public LegendreHighPrecisionRuleFactory(MathContext mContext) {
        this.mContext = mContext;
        this.two = new BigDecimal("2", mContext);
        this.minusOne = new BigDecimal("-1", mContext);
        this.oneHalf = new BigDecimal("0.5", mContext);
    }

    @Override
    protected Pair<BigDecimal[], BigDecimal[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        if (numberOfPoints == 1) {
            return new Pair<BigDecimal[], BigDecimal[]>(new BigDecimal[]{BigDecimal.ZERO}, new BigDecimal[]{this.two});
        }
        BigDecimal[] previousPoints = (BigDecimal[])this.getRuleInternal(numberOfPoints - 1).getFirst();
        BigDecimal[] points = new BigDecimal[numberOfPoints];
        BigDecimal[] weights = new BigDecimal[numberOfPoints];
        int iMax = numberOfPoints / 2;
        for (int i = 0; i < iMax; ++i) {
            BigDecimal tmp2;
            BigDecimal tmp1;
            BigDecimal a = i == 0 ? this.minusOne : previousPoints[i - 1];
            BigDecimal b = iMax == 1 ? BigDecimal.ONE : previousPoints[i];
            BigDecimal pma = BigDecimal.ONE;
            BigDecimal pa = a;
            BigDecimal pmb = BigDecimal.ONE;
            BigDecimal pb = b;
            for (int j = 1; j < numberOfPoints; ++j) {
                BigDecimal b_two_j_p_1 = new BigDecimal(2 * j + 1, this.mContext);
                BigDecimal b_j = new BigDecimal(j, this.mContext);
                BigDecimal b_j_p_1 = new BigDecimal(j + 1, this.mContext);
                tmp1 = a.multiply(b_two_j_p_1, this.mContext);
                tmp1 = pa.multiply(tmp1, this.mContext);
                tmp2 = pma.multiply(b_j, this.mContext);
                BigDecimal ppa = tmp1.subtract(tmp2, this.mContext);
                ppa = ppa.divide(b_j_p_1, this.mContext);
                tmp1 = b.multiply(b_two_j_p_1, this.mContext);
                tmp1 = pb.multiply(tmp1, this.mContext);
                tmp2 = pmb.multiply(b_j, this.mContext);
                BigDecimal ppb = tmp1.subtract(tmp2, this.mContext);
                ppb = ppb.divide(b_j_p_1, this.mContext);
                pma = pa;
                pa = ppa;
                pmb = pb;
                pb = ppb;
            }
            BigDecimal c = a.add(b, this.mContext).multiply(this.oneHalf, this.mContext);
            BigDecimal pmc = BigDecimal.ONE;
            BigDecimal pc = c;
            boolean done = false;
            while (!done) {
                tmp1 = b.subtract(a, this.mContext);
                done = tmp1.compareTo(tmp2 = c.ulp().multiply(BigDecimal.TEN, this.mContext)) <= 0;
                pmc = BigDecimal.ONE;
                pc = c;
                for (int j = 1; j < numberOfPoints; ++j) {
                    BigDecimal b_two_j_p_1 = new BigDecimal(2 * j + 1, this.mContext);
                    BigDecimal b_j = new BigDecimal(j, this.mContext);
                    BigDecimal b_j_p_1 = new BigDecimal(j + 1, this.mContext);
                    tmp1 = c.multiply(b_two_j_p_1, this.mContext);
                    tmp1 = pc.multiply(tmp1, this.mContext);
                    tmp2 = pmc.multiply(b_j, this.mContext);
                    BigDecimal ppc = tmp1.subtract(tmp2, this.mContext);
                    ppc = ppc.divide(b_j_p_1, this.mContext);
                    pmc = pc;
                    pc = ppc;
                }
                if (done) continue;
                if (pa.signum() * pc.signum() <= 0) {
                    b = c;
                    pmb = pmc;
                    pb = pc;
                } else {
                    a = c;
                    pma = pmc;
                    pa = pc;
                }
                c = a.add(b, this.mContext).multiply(this.oneHalf, this.mContext);
            }
            BigDecimal nP = new BigDecimal(numberOfPoints, this.mContext);
            BigDecimal tmp12 = pmc.subtract(c.multiply(pc, this.mContext), this.mContext);
            tmp12 = tmp12.multiply(nP);
            tmp12 = tmp12.pow(2, this.mContext);
            BigDecimal tmp22 = c.pow(2, this.mContext);
            tmp22 = BigDecimal.ONE.subtract(tmp22, this.mContext);
            tmp22 = tmp22.multiply(this.two, this.mContext);
            tmp22 = tmp22.divide(tmp12, this.mContext);
            points[i] = c;
            weights[i] = tmp22;
            int idx = numberOfPoints - i - 1;
            points[idx] = c.negate(this.mContext);
            weights[idx] = tmp22;
        }
        if (numberOfPoints % 2 != 0) {
            BigDecimal pmc = BigDecimal.ONE;
            for (int j = 1; j < numberOfPoints; j += 2) {
                BigDecimal b_j = new BigDecimal(j, this.mContext);
                BigDecimal b_j_p_1 = new BigDecimal(j + 1, this.mContext);
                pmc = pmc.multiply(b_j, this.mContext);
                pmc = pmc.divide(b_j_p_1, this.mContext);
                pmc = pmc.negate(this.mContext);
            }
            BigDecimal nP = new BigDecimal(numberOfPoints, this.mContext);
            BigDecimal tmp1 = pmc.multiply(nP, this.mContext);
            tmp1 = tmp1.pow(2, this.mContext);
            BigDecimal tmp2 = this.two.divide(tmp1, this.mContext);
            points[iMax] = BigDecimal.ZERO;
            weights[iMax] = tmp2;
        }
        return new Pair<BigDecimal[], BigDecimal[]>(points, weights);
    }
}

