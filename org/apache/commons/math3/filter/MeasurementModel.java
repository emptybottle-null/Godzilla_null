/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.filter;

import org.apache.commons.math3.linear.RealMatrix;

public interface MeasurementModel {
    public RealMatrix getMeasurementMatrix();

    public RealMatrix getMeasurementNoise();
}

