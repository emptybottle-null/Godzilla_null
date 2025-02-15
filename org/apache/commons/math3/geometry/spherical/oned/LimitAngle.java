/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.S1Point;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.geometry.spherical.oned.SubLimitAngle;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LimitAngle
implements Hyperplane<Sphere1D> {
    private S1Point location;
    private boolean direct;
    private final double tolerance;

    public LimitAngle(S1Point location, boolean direct, double tolerance) {
        this.location = location;
        this.direct = direct;
        this.tolerance = tolerance;
    }

    public LimitAngle copySelf() {
        return this;
    }

    @Override
    public double getOffset(Point<Sphere1D> point) {
        double delta = ((S1Point)point).getAlpha() - this.location.getAlpha();
        return this.direct ? delta : -delta;
    }

    public boolean isDirect() {
        return this.direct;
    }

    public LimitAngle getReverse() {
        return new LimitAngle(this.location, !this.direct, this.tolerance);
    }

    public SubLimitAngle wholeHyperplane() {
        return new SubLimitAngle(this, null);
    }

    public ArcsSet wholeSpace() {
        return new ArcsSet(this.tolerance);
    }

    @Override
    public boolean sameOrientationAs(Hyperplane<Sphere1D> other) {
        return !(this.direct ^ ((LimitAngle)other).direct);
    }

    public S1Point getLocation() {
        return this.location;
    }

    @Override
    public Point<Sphere1D> project(Point<Sphere1D> point) {
        return this.location;
    }

    @Override
    public double getTolerance() {
        return this.tolerance;
    }
}

