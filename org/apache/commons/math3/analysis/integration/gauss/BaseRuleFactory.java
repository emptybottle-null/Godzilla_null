/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration.gauss;

import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class BaseRuleFactory<T extends Number> {
    private final Map<Integer, Pair<T[], T[]>> pointsAndWeights = new TreeMap<Integer, Pair<T[], T[]>>();
    private final Map<Integer, Pair<double[], double[]>> pointsAndWeightsDouble = new TreeMap<Integer, Pair<double[], double[]>>();

    public Pair<double[], double[]> getRule(int numberOfPoints) throws NotStrictlyPositiveException, DimensionMismatchException {
        if (numberOfPoints <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NUMBER_OF_POINTS, numberOfPoints);
        }
        Pair<double[], double[]> cached = this.pointsAndWeightsDouble.get(numberOfPoints);
        if (cached == null) {
            Pair<T[], T[]> rule = this.getRuleInternal(numberOfPoints);
            cached = BaseRuleFactory.convertToDouble(rule);
            this.pointsAndWeightsDouble.put(numberOfPoints, cached);
        }
        return new Pair<Object, Object>(cached.getFirst().clone(), cached.getSecond().clone());
    }

    protected synchronized Pair<T[], T[]> getRuleInternal(int numberOfPoints) throws DimensionMismatchException {
        Pair<T[], T[]> rule = this.pointsAndWeights.get(numberOfPoints);
        if (rule == null) {
            this.addRule(this.computeRule(numberOfPoints));
            return this.getRuleInternal(numberOfPoints);
        }
        return rule;
    }

    protected void addRule(Pair<T[], T[]> rule) throws DimensionMismatchException {
        if (((Number[])rule.getFirst()).length != ((Number[])rule.getSecond()).length) {
            throw new DimensionMismatchException(((Number[])rule.getFirst()).length, ((Number[])rule.getSecond()).length);
        }
        this.pointsAndWeights.put(((Number[])rule.getFirst()).length, rule);
    }

    protected abstract Pair<T[], T[]> computeRule(int var1) throws DimensionMismatchException;

    private static <T extends Number> Pair<double[], double[]> convertToDouble(Pair<T[], T[]> rule) {
        Number[] pT = (Number[])rule.getFirst();
        Number[] wT = (Number[])rule.getSecond();
        int len = pT.length;
        double[] pD = new double[len];
        double[] wD = new double[len];
        for (int i = 0; i < len; ++i) {
            pD[i] = pT[i].doubleValue();
            wD[i] = wT[i].doubleValue();
        }
        return new Pair<double[], double[]>(pD, wD);
    }
}

