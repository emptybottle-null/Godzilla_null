/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration.gauss;

import java.math.BigDecimal;
import org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator;
import org.apache.commons.math3.analysis.integration.gauss.HermiteRuleFactory;
import org.apache.commons.math3.analysis.integration.gauss.LegendreHighPrecisionRuleFactory;
import org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory;
import org.apache.commons.math3.analysis.integration.gauss.SymmetricGaussIntegrator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class GaussIntegratorFactory {
    private final BaseRuleFactory<Double> legendre = new LegendreRuleFactory();
    private final BaseRuleFactory<BigDecimal> legendreHighPrecision = new LegendreHighPrecisionRuleFactory();
    private final BaseRuleFactory<Double> hermite = new HermiteRuleFactory();

    public GaussIntegrator legendre(int numberOfPoints) {
        return new GaussIntegrator(GaussIntegratorFactory.getRule(this.legendre, numberOfPoints));
    }

    public GaussIntegrator legendre(int numberOfPoints, double lowerBound, double upperBound) throws NotStrictlyPositiveException {
        return new GaussIntegrator(GaussIntegratorFactory.transform(GaussIntegratorFactory.getRule(this.legendre, numberOfPoints), lowerBound, upperBound));
    }

    public GaussIntegrator legendreHighPrecision(int numberOfPoints) throws NotStrictlyPositiveException {
        return new GaussIntegrator(GaussIntegratorFactory.getRule(this.legendreHighPrecision, numberOfPoints));
    }

    public GaussIntegrator legendreHighPrecision(int numberOfPoints, double lowerBound, double upperBound) throws NotStrictlyPositiveException {
        return new GaussIntegrator(GaussIntegratorFactory.transform(GaussIntegratorFactory.getRule(this.legendreHighPrecision, numberOfPoints), lowerBound, upperBound));
    }

    public SymmetricGaussIntegrator hermite(int numberOfPoints) {
        return new SymmetricGaussIntegrator(GaussIntegratorFactory.getRule(this.hermite, numberOfPoints));
    }

    private static Pair<double[], double[]> getRule(BaseRuleFactory<? extends Number> factory, int numberOfPoints) throws NotStrictlyPositiveException, DimensionMismatchException {
        return factory.getRule(numberOfPoints);
    }

    private static Pair<double[], double[]> transform(Pair<double[], double[]> rule, double a, double b) {
        double[] points = rule.getFirst();
        double[] weights = rule.getSecond();
        double scale = (b - a) / 2.0;
        double shift = a + scale;
        int i = 0;
        while (i < points.length) {
            points[i] = points[i] * scale + shift;
            int n = i++;
            weights[n] = weights[n] * scale;
        }
        return new Pair<double[], double[]>(points, weights);
    }
}

