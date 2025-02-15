/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.optimization.BaseMultivariateMultiStartOptimizer;
import org.apache.commons.math3.optimization.MultivariateDifferentiableOptimizer;
import org.apache.commons.math3.random.RandomVectorGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class MultivariateDifferentiableMultiStartOptimizer
extends BaseMultivariateMultiStartOptimizer<MultivariateDifferentiableFunction>
implements MultivariateDifferentiableOptimizer {
    public MultivariateDifferentiableMultiStartOptimizer(MultivariateDifferentiableOptimizer optimizer, int starts, RandomVectorGenerator generator) {
        super(optimizer, starts, generator);
    }
}

