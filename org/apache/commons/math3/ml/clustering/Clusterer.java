/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.clustering;

import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Clusterer<T extends Clusterable> {
    private DistanceMeasure measure;

    protected Clusterer(DistanceMeasure measure) {
        this.measure = measure;
    }

    public abstract List<? extends Cluster<T>> cluster(Collection<T> var1) throws MathIllegalArgumentException, ConvergenceException;

    public DistanceMeasure getDistanceMeasure() {
        return this.measure;
    }

    protected double distance(Clusterable p1, Clusterable p2) {
        return this.measure.compute(p1.getPoint(), p2.getPoint());
    }
}

