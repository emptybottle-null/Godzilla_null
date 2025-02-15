/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.linear.RealVector;

public interface ParameterValidator {
    public RealVector validate(RealVector var1);
}

