/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.BaseOptimizer;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public interface BaseMultivariateOptimizer<FUNC extends MultivariateFunction>
extends BaseOptimizer<PointValuePair> {
    @Deprecated
    public PointValuePair optimize(int var1, FUNC var2, GoalType var3, double[] var4);
}

