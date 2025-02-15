/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class Arc {
    private final double lower;
    private final double upper;
    private final double middle;
    private final double tolerance;

    public Arc(double lower, double upper, double tolerance) throws NumberIsTooLargeException {
        this.tolerance = tolerance;
        if (Precision.equals(lower, upper, 0) || upper - lower >= Math.PI * 2) {
            this.lower = 0.0;
            this.upper = Math.PI * 2;
            this.middle = Math.PI;
        } else if (lower <= upper) {
            this.lower = MathUtils.normalizeAngle(lower, Math.PI);
            this.upper = this.lower + (upper - lower);
            this.middle = 0.5 * (this.lower + this.upper);
        } else {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, (Number)lower, upper, true);
        }
    }

    public double getInf() {
        return this.lower;
    }

    public double getSup() {
        return this.upper;
    }

    public double getSize() {
        return this.upper - this.lower;
    }

    public double getBarycenter() {
        return this.middle;
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public Region.Location checkPoint(double point) {
        double normalizedPoint = MathUtils.normalizeAngle(point, this.middle);
        if (normalizedPoint < this.lower - this.tolerance || normalizedPoint > this.upper + this.tolerance) {
            return Region.Location.OUTSIDE;
        }
        if (normalizedPoint > this.lower + this.tolerance && normalizedPoint < this.upper - this.tolerance) {
            return Region.Location.INSIDE;
        }
        return this.getSize() >= Math.PI * 2 - this.tolerance ? Region.Location.INSIDE : Region.Location.BOUNDARY;
    }
}

