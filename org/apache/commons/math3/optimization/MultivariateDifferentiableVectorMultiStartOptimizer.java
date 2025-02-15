/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer;
import org.apache.commons.math3.optimization.MultivariateDifferentiableVectorOptimizer;
import org.apache.commons.math3.random.RandomVectorGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class MultivariateDifferentiableVectorMultiStartOptimizer
extends BaseMultivariateVectorMultiStartOptimizer<MultivariateDifferentiableVectorFunction>
implements MultivariateDifferentiableVectorOptimizer {
    public MultivariateDifferentiableVectorMultiStartOptimizer(MultivariateDifferentiableVectorOptimizer optimizer, int starts, RandomVectorGenerator generator) {
        super(optimizer, starts, generator);
    }
}

