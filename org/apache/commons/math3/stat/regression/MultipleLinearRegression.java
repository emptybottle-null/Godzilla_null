/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.regression;

public interface MultipleLinearRegression {
    public double[] estimateRegressionParameters();

    public double[][] estimateRegressionParametersVariance();

    public double[] estimateResiduals();

    public double estimateRegressandVariance();

    public double[] estimateRegressionParametersStandardErrors();
}

