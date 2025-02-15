/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.BaseMultiStartMultivariateOptimizer;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;
import org.apache.commons.math3.random.RandomVectorGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class MultiStartMultivariateVectorOptimizer
extends BaseMultiStartMultivariateOptimizer<PointVectorValuePair> {
    private final MultivariateVectorOptimizer optimizer;
    private final List<PointVectorValuePair> optima = new ArrayList<PointVectorValuePair>();

    public MultiStartMultivariateVectorOptimizer(MultivariateVectorOptimizer optimizer, int starts, RandomVectorGenerator generator) throws NullArgumentException, NotStrictlyPositiveException {
        super(optimizer, starts, generator);
        this.optimizer = optimizer;
    }

    public PointVectorValuePair[] getOptima() {
        Collections.sort(this.optima, this.getPairComparator());
        return this.optima.toArray(new PointVectorValuePair[0]);
    }

    @Override
    protected void store(PointVectorValuePair optimum) {
        this.optima.add(optimum);
    }

    @Override
    protected void clear() {
        this.optima.clear();
    }

    private Comparator<PointVectorValuePair> getPairComparator() {
        return new Comparator<PointVectorValuePair>(){
            private final RealVector target;
            private final RealMatrix weight;
            {
                this.target = new ArrayRealVector(MultiStartMultivariateVectorOptimizer.this.optimizer.getTarget(), false);
                this.weight = MultiStartMultivariateVectorOptimizer.this.optimizer.getWeight();
            }

            @Override
            public int compare(PointVectorValuePair o1, PointVectorValuePair o2) {
                if (o1 == null) {
                    return o2 == null ? 0 : 1;
                }
                if (o2 == null) {
                    return -1;
                }
                return Double.compare(this.weightedResidual(o1), this.weightedResidual(o2));
            }

            private double weightedResidual(PointVectorValuePair pv) {
                ArrayRealVector v = new ArrayRealVector(pv.getValueRef(), false);
                RealVector r = this.target.subtract(v);
                return r.dotProduct(this.weight.operate(r));
            }
        };
    }
}

