/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis;

import org.apache.commons.math3.analysis.UnivariateVectorFunction;

@Deprecated
public interface DifferentiableUnivariateVectorFunction
extends UnivariateVectorFunction {
    public UnivariateVectorFunction derivative();
}

