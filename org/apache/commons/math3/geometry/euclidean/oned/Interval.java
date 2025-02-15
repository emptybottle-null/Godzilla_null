/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.partitioning.Region;

public class Interval {
    private final double lower;
    private final double upper;

    public Interval(double lower, double upper) {
        if (upper < lower) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, (Number)upper, lower, true);
        }
        this.lower = lower;
        this.upper = upper;
    }

    public double getInf() {
        return this.lower;
    }

    @Deprecated
    public double getLower() {
        return this.getInf();
    }

    public double getSup() {
        return this.upper;
    }

    @Deprecated
    public double getUpper() {
        return this.getSup();
    }

    public double getSize() {
        return this.upper - this.lower;
    }

    @Deprecated
    public double getLength() {
        return this.getSize();
    }

    public double getBarycenter() {
        return 0.5 * (this.lower + this.upper);
    }

    @Deprecated
    public double getMidPoint() {
        return this.getBarycenter();
    }

    public Region.Location checkPoint(double point, double tolerance) {
        if (point < this.lower - tolerance || point > this.upper + tolerance) {
            return Region.Location.OUTSIDE;
        }
        if (point > this.lower + tolerance && point < this.upper - tolerance) {
            return Region.Location.INSIDE;
        }
        return Region.Location.BOUNDARY;
    }
}

