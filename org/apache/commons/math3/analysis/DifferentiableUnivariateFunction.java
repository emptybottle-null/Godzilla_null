/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis;

import org.apache.commons.math3.analysis.UnivariateFunction;

@Deprecated
public interface DifferentiableUnivariateFunction
extends UnivariateFunction {
    public UnivariateFunction derivative();
}

