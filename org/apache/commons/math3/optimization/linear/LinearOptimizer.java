/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.linear;

import java.util.Collection;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.linear.LinearConstraint;
import org.apache.commons.math3.optimization.linear.LinearObjectiveFunction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public interface LinearOptimizer {
    public void setMaxIterations(int var1);

    public int getMaxIterations();

    public int getIterations();

    public PointValuePair optimize(LinearObjectiveFunction var1, Collection<LinearConstraint> var2, GoalType var3, boolean var4) throws MathIllegalStateException;
}

