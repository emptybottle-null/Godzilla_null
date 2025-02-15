/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SubOrientedPoint
extends AbstractSubHyperplane<Euclidean1D, Euclidean1D> {
    public SubOrientedPoint(Hyperplane<Euclidean1D> hyperplane, Region<Euclidean1D> remainingRegion) {
        super(hyperplane, remainingRegion);
    }

    @Override
    public double getSize() {
        return 0.0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    protected AbstractSubHyperplane<Euclidean1D, Euclidean1D> buildNew(Hyperplane<Euclidean1D> hyperplane, Region<Euclidean1D> remainingRegion) {
        return new SubOrientedPoint(hyperplane, remainingRegion);
    }

    @Override
    public SubHyperplane.SplitSubHyperplane<Euclidean1D> split(Hyperplane<Euclidean1D> hyperplane) {
        double global = hyperplane.getOffset(((OrientedPoint)this.getHyperplane()).getLocation());
        if (global < -1.0E-10) {
            return new SubHyperplane.SplitSubHyperplane<Euclidean1D>(null, this);
        }
        if (global > 1.0E-10) {
            return new SubHyperplane.SplitSubHyperplane<Euclidean1D>(this, null);
        }
        return new SubHyperplane.SplitSubHyperplane<Euclidean1D>(null, null);
    }
}

