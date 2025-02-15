/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.optimization.BaseOptimizer;
import org.apache.commons.math3.optimization.PointVectorValuePair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public interface BaseMultivariateVectorOptimizer<FUNC extends MultivariateVectorFunction>
extends BaseOptimizer<PointVectorValuePair> {
    @Deprecated
    public PointVectorValuePair optimize(int var1, FUNC var2, double[] var3, double[] var4, double[] var5);
}

