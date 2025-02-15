/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.geometry.spherical.twod.Circle;
import org.apache.commons.math3.geometry.spherical.twod.Sphere2D;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SubCircle
extends AbstractSubHyperplane<Sphere2D, Sphere1D> {
    public SubCircle(Hyperplane<Sphere2D> hyperplane, Region<Sphere1D> remainingRegion) {
        super(hyperplane, remainingRegion);
    }

    @Override
    protected AbstractSubHyperplane<Sphere2D, Sphere1D> buildNew(Hyperplane<Sphere2D> hyperplane, Region<Sphere1D> remainingRegion) {
        return new SubCircle(hyperplane, remainingRegion);
    }

    @Override
    public SubHyperplane.SplitSubHyperplane<Sphere2D> split(Hyperplane<Sphere2D> hyperplane) {
        Circle thisCircle = (Circle)this.getHyperplane();
        Circle otherCircle = (Circle)hyperplane;
        double angle = Vector3D.angle(thisCircle.getPole(), otherCircle.getPole());
        if (angle < thisCircle.getTolerance() || angle > Math.PI - thisCircle.getTolerance()) {
            return new SubHyperplane.SplitSubHyperplane<Sphere2D>(null, null);
        }
        Arc arc = thisCircle.getInsideArc(otherCircle);
        ArcsSet.Split split = ((ArcsSet)this.getRemainingRegion()).split(arc);
        ArcsSet plus = split.getPlus();
        ArcsSet minus = split.getMinus();
        return new SubHyperplane.SplitSubHyperplane<Sphere2D>(plus == null ? null : new SubCircle(thisCircle.copySelf(), plus), minus == null ? null : new SubCircle(thisCircle.copySelf(), minus));
    }
}

