/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optimization.BaseOptimizer;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public interface BaseUnivariateOptimizer<FUNC extends UnivariateFunction>
extends BaseOptimizer<UnivariatePointValuePair> {
    public UnivariatePointValuePair optimize(int var1, FUNC var2, GoalType var3, double var4, double var6);

    public UnivariatePointValuePair optimize(int var1, FUNC var2, GoalType var3, double var4, double var6, double var8);
}

