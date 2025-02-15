/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis;

import org.apache.commons.math3.analysis.UnivariateMatrixFunction;

@Deprecated
public interface DifferentiableUnivariateMatrixFunction
extends UnivariateMatrixFunction {
    public UnivariateMatrixFunction derivative();
}

