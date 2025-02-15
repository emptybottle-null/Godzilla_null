/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.random.RandomVectorGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MultiStartMultivariateOptimizer
extends BaseMultiStartMultivariateOptimizer<PointValuePair> {
    private final MultivariateOptimizer optimizer;
    private final List<PointValuePair> optima = new ArrayList<PointValuePair>();

    public MultiStartMultivariateOptimizer(MultivariateOptimizer optimizer, int starts, RandomVectorGenerator generator) throws NullArgumentException, NotStrictlyPositiveException {
        super(optimizer, starts, generator);
        this.optimizer = optimizer;
    }

    public PointValuePair[] getOptima() {
        Collections.sort(this.optima, this.getPairComparator());
        return this.optima.toArray(new PointValuePair[0]);
    }

    @Override
    protected void store(PointValuePair optimum) {
        this.optima.add(optimum);
    }

    @Override
    protected void clear() {
        this.optima.clear();
    }

    private Comparator<PointValuePair> getPairComparator() {
        return new Comparator<PointValuePair>(){

            @Override
            public int compare(PointValuePair o1, PointValuePair o2) {
                if (o1 == null) {
                    return o2 == null ? 0 : 1;
                }
                if (o2 == null) {
                    return -1;
                }
                double v1 = (Double)o1.getValue();
                double v2 = (Double)o2.getValue();
                return MultiStartMultivariateOptimizer.this.optimizer.getGoalType() == GoalType.MINIMIZE ? Double.compare(v1, v2) : Double.compare(v2, v1);
            }
        };
    }
}

