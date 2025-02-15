/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet;
import org.apache.commons.math3.geometry.spherical.oned.S1Point;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.geometry.spherical.twod.S2Point;
import org.apache.commons.math3.geometry.spherical.twod.Sphere2D;
import org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet;
import org.apache.commons.math3.geometry.spherical.twod.SubCircle;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Circle
implements Hyperplane<Sphere2D>,
Embedding<Sphere2D, Sphere1D> {
    private Vector3D pole;
    private Vector3D x;
    private Vector3D y;
    private final double tolerance;

    public Circle(Vector3D pole, double tolerance) {
        this.reset(pole);
        this.tolerance = tolerance;
    }

    public Circle(S2Point first, S2Point second, double tolerance) {
        this.reset(first.getVector().crossProduct(second.getVector()));
        this.tolerance = tolerance;
    }

    private Circle(Vector3D pole, Vector3D x, Vector3D y, double tolerance) {
        this.pole = pole;
        this.x = x;
        this.y = y;
        this.tolerance = tolerance;
    }

    public Circle(Circle circle) {
        this(circle.pole, circle.x, circle.y, circle.tolerance);
    }

    public Circle copySelf() {
        return new Circle(this);
    }

    public void reset(Vector3D newPole) {
        this.pole = newPole.normalize();
        this.x = newPole.orthogonal();
        this.y = Vector3D.crossProduct(newPole, this.x).normalize();
    }

    public void revertSelf() {
        this.y = this.y.negate();
        this.pole = this.pole.negate();
    }

    public Circle getReverse() {
        return new Circle(this.pole.negate(), this.x, this.y.negate(), this.tolerance);
    }

    @Override
    public Point<Sphere2D> project(Point<Sphere2D> point) {
        return this.toSpace(this.toSubSpace((Point)point));
    }

    @Override
    public double getTolerance() {
        return this.tolerance;
    }

    public S1Point toSubSpace(Point<Sphere2D> point) {
        return new S1Point(this.getPhase(((S2Point)point).getVector()));
    }

    public double getPhase(Vector3D direction) {
        return Math.PI + FastMath.atan2(-direction.dotProduct(this.y), -direction.dotProduct(this.x));
    }

    public S2Point toSpace(Point<Sphere1D> point) {
        return new S2Point(this.getPointAt(((S1Point)point).getAlpha()));
    }

    public Vector3D getPointAt(double alpha) {
        return new Vector3D(FastMath.cos(alpha), this.x, FastMath.sin(alpha), this.y);
    }

    public Vector3D getXAxis() {
        return this.x;
    }

    public Vector3D getYAxis() {
        return this.y;
    }

    public Vector3D getPole() {
        return this.pole;
    }

    public Arc getInsideArc(Circle other) {
        double alpha = this.getPhase(other.pole);
        double halfPi = 1.5707963267948966;
        return new Arc(alpha - 1.5707963267948966, alpha + 1.5707963267948966, this.tolerance);
    }

    public SubCircle wholeHyperplane() {
        return new SubCircle(this, new ArcsSet(this.tolerance));
    }

    public SphericalPolygonsSet wholeSpace() {
        return new SphericalPolygonsSet(this.tolerance);
    }

    @Override
    public double getOffset(Point<Sphere2D> point) {
        return this.getOffset(((S2Point)point).getVector());
    }

    public double getOffset(Vector3D direction) {
        return Vector3D.angle(this.pole, direction) - 1.5707963267948966;
    }

    @Override
    public boolean sameOrientationAs(Hyperplane<Sphere2D> other) {
        Circle otherC = (Circle)other;
        return Vector3D.dotProduct(this.pole, otherC.pole) >= 0.0;
    }

    public static Transform<Sphere2D, Sphere1D> getTransform(Rotation rotation) {
        return new CircleTransform(rotation);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class CircleTransform
    implements Transform<Sphere2D, Sphere1D> {
        private final Rotation rotation;

        CircleTransform(Rotation rotation) {
            this.rotation = rotation;
        }

        public S2Point apply(Point<Sphere2D> point) {
            return new S2Point(this.rotation.applyTo(((S2Point)point).getVector()));
        }

        public Circle apply(Hyperplane<Sphere2D> hyperplane) {
            Circle circle = (Circle)hyperplane;
            return new Circle(this.rotation.applyTo(circle.pole), this.rotation.applyTo(circle.x), this.rotation.applyTo(circle.y), circle.tolerance);
        }

        @Override
        public SubHyperplane<Sphere1D> apply(SubHyperplane<Sphere1D> sub, Hyperplane<Sphere2D> original, Hyperplane<Sphere2D> transformed) {
            return sub;
        }
    }
}

