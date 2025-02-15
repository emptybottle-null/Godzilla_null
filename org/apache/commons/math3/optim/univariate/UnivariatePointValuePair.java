/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.univariate;

import java.io.Serializable;

public class UnivariatePointValuePair
implements Serializable {
    private static final long serialVersionUID = 1003888396256744753L;
    private final double point;
    private final double value;

    public UnivariatePointValuePair(double point, double value) {
        this.point = point;
        this.value = value;
    }

    public double getPoint() {
        return this.point;
    }

    public double getValue() {
        return this.value;
    }
}

