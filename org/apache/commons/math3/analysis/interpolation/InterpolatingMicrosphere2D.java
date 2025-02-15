/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere;
import org.apache.commons.math3.util.FastMath;

public class InterpolatingMicrosphere2D
extends InterpolatingMicrosphere {
    private static final int DIMENSION = 2;

    public InterpolatingMicrosphere2D(int size, double maxDarkFraction, double darkThreshold, double background) {
        super(2, size, maxDarkFraction, darkThreshold, background);
        for (int i = 0; i < size; ++i) {
            double angle = (double)i * (Math.PI * 2) / (double)size;
            this.add(new double[]{FastMath.cos(angle), FastMath.sin(angle)}, false);
        }
    }

    protected InterpolatingMicrosphere2D(InterpolatingMicrosphere2D other) {
        super(other);
    }

    public InterpolatingMicrosphere2D copy() {
        return new InterpolatingMicrosphere2D(this);
    }
}

