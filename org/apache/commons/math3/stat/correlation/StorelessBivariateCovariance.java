/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.correlation;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

class StorelessBivariateCovariance {
    private double meanX = 0.0;
    private double meanY = 0.0;
    private double n = 0.0;
    private double covarianceNumerator = 0.0;
    private boolean biasCorrected;

    StorelessBivariateCovariance() {
        this(true);
    }

    StorelessBivariateCovariance(boolean biasCorrection) {
        this.biasCorrected = biasCorrection;
    }

    public void increment(double x, double y) {
        this.n += 1.0;
        double deltaX = x - this.meanX;
        double deltaY = y - this.meanY;
        this.meanX += deltaX / this.n;
        this.meanY += deltaY / this.n;
        this.covarianceNumerator += (this.n - 1.0) / this.n * deltaX * deltaY;
    }

    public void append(StorelessBivariateCovariance cov) {
        double oldN = this.n;
        this.n += cov.n;
        double deltaX = cov.meanX - this.meanX;
        double deltaY = cov.meanY - this.meanY;
        this.meanX += deltaX * cov.n / this.n;
        this.meanY += deltaY * cov.n / this.n;
        this.covarianceNumerator += cov.covarianceNumerator + oldN * cov.n / this.n * deltaX * deltaY;
    }

    public double getN() {
        return this.n;
    }

    public double getResult() throws NumberIsTooSmallException {
        if (this.n < 2.0) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.INSUFFICIENT_DIMENSION, (Number)this.n, 2, true);
        }
        if (this.biasCorrected) {
            return this.covarianceNumerator / (this.n - 1.0);
        }
        return this.covarianceNumerator / this.n;
    }
}

