/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.regression;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.stat.regression.ModelSpecificationException;
import org.apache.commons.math3.stat.regression.RegressionResults;

public interface UpdatingMultipleLinearRegression {
    public boolean hasIntercept();

    public long getN();

    public void addObservation(double[] var1, double var2) throws ModelSpecificationException;

    public void addObservations(double[][] var1, double[] var2) throws ModelSpecificationException;

    public void clear();

    public RegressionResults regress() throws ModelSpecificationException, NoDataException;

    public RegressionResults regress(int[] var1) throws ModelSpecificationException, MathIllegalArgumentException;
}

