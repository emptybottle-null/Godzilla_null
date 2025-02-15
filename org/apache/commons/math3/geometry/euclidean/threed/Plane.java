/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;
import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.SubPlane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Plane
implements Hyperplane<Euclidean3D>,
Embedding<Euclidean3D, Euclidean2D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10;
    private double originOffset;
    private Vector3D origin;
    private Vector3D u;
    private Vector3D v;
    private Vector3D w;
    private final double tolerance;

    public Plane(Vector3D normal, double tolerance) throws MathArithmeticException {
        this.setNormal(normal);
        this.tolerance = tolerance;
        this.originOffset = 0.0;
        this.setFrame();
    }

    public Plane(Vector3D p, Vector3D normal, double tolerance) throws MathArithmeticException {
        this.setNormal(normal);
        this.tolerance = tolerance;
        this.originOffset = -p.dotProduct(this.w);
        this.setFrame();
    }

    public Plane(Vector3D p1, Vector3D p2, Vector3D p3, double tolerance) throws MathArithmeticException {
        this(p1, ((Vector3D)p2.subtract((Vector)p1)).crossProduct(p3.subtract((Vector)p1)), tolerance);
    }

    @Deprecated
    public Plane(Vector3D normal) throws MathArithmeticException {
        this(normal, 1.0E-10);
    }

    @Deprecated
    public Plane(Vector3D p, Vector3D normal) throws MathArithmeticException {
        this(p, normal, 1.0E-10);
    }

    @Deprecated
    public Plane(Vector3D p1, Vector3D p2, Vector3D p3) throws MathArithmeticException {
        this(p1, p2, p3, 1.0E-10);
    }

    public Plane(Plane plane) {
        this.originOffset = plane.originOffset;
        this.origin = plane.origin;
        this.u = plane.u;
        this.v = plane.v;
        this.w = plane.w;
        this.tolerance = plane.tolerance;
    }

    public Plane copySelf() {
        return new Plane(this);
    }

    public void reset(Vector3D p, Vector3D normal) throws MathArithmeticException {
        this.setNormal(normal);
        this.originOffset = -p.dotProduct(this.w);
        this.setFrame();
    }

    public void reset(Plane original) {
        this.originOffset = original.originOffset;
        this.origin = original.origin;
        this.u = original.u;
        this.v = original.v;
        this.w = original.w;
    }

    private void setNormal(Vector3D normal) throws MathArithmeticException {
        double norm = normal.getNorm();
        if (norm < 1.0E-10) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        this.w = new Vector3D(1.0 / norm, normal);
    }

    private void setFrame() {
        this.origin = new Vector3D(-this.originOffset, this.w);
        this.u = this.w.orthogonal();
        this.v = Vector3D.crossProduct(this.w, this.u);
    }

    public Vector3D getOrigin() {
        return this.origin;
    }

    public Vector3D getNormal() {
        return this.w;
    }

    public Vector3D getU() {
        return this.u;
    }

    public Vector3D getV() {
        return this.v;
    }

    @Override
    public Point<Euclidean3D> project(Point<Euclidean3D> point) {
        return this.toSpace((Vector<Euclidean2D>)this.toSubSpace((Point)point));
    }

    @Override
    public double getTolerance() {
        return this.tolerance;
    }

    public void revertSelf() {
        Vector3D tmp = this.u;
        this.u = this.v;
        this.v = tmp;
        this.w = this.w.negate();
        this.originOffset = -this.originOffset;
    }

    public Vector2D toSubSpace(Vector<Euclidean3D> vector) {
        return this.toSubSpace((Point)vector);
    }

    public Vector3D toSpace(Vector<Euclidean2D> vector) {
        return this.toSpace((Point)vector);
    }

    public Vector2D toSubSpace(Point<Euclidean3D> point) {
        Vector3D p3D = (Vector3D)point;
        return new Vector2D(p3D.dotProduct(this.u), p3D.dotProduct(this.v));
    }

    public Vector3D toSpace(Point<Euclidean2D> point) {
        Vector2D p2D = (Vector2D)point;
        return new Vector3D(p2D.getX(), this.u, p2D.getY(), this.v, -this.originOffset, this.w);
    }

    public Vector3D getPointAt(Vector2D inPlane, double offset) {
        return new Vector3D(inPlane.getX(), this.u, inPlane.getY(), this.v, offset - this.originOffset, this.w);
    }

    public boolean isSimilarTo(Plane plane) {
        double angle = Vector3D.angle(this.w, plane.w);
        return angle < 1.0E-10 && FastMath.abs(this.originOffset - plane.originOffset) < this.tolerance || angle > 3.141592653489793 && FastMath.abs(this.originOffset + plane.originOffset) < this.tolerance;
    }

    public Plane rotate(Vector3D center, Rotation rotation) {
        Vector delta = this.origin.subtract((Vector)center);
        Plane plane = new Plane((Vector3D)center.add((Vector)rotation.applyTo((Vector3D)delta)), rotation.applyTo(this.w), this.tolerance);
        plane.u = rotation.applyTo(this.u);
        plane.v = rotation.applyTo(this.v);
        return plane;
    }

    public Plane translate(Vector3D translation) {
        Plane plane = new Plane((Vector3D)this.origin.add((Vector)translation), this.w, this.tolerance);
        plane.u = this.u;
        plane.v = this.v;
        return plane;
    }

    public Vector3D intersection(Line line) {
        Vector3D direction = line.getDirection();
        double dot = this.w.dotProduct(direction);
        if (FastMath.abs(dot) < 1.0E-10) {
            return null;
        }
        Point point = line.toSpace((Point)Vector1D.ZERO);
        double k = -(this.originOffset + this.w.dotProduct((Vector<Euclidean3D>)point)) / dot;
        return new Vector3D(1.0, (Vector3D)point, k, direction);
    }

    public Line intersection(Plane other) {
        Vector3D direction = Vector3D.crossProduct(this.w, other.w);
        if (direction.getNorm() < this.tolerance) {
            return null;
        }
        Vector3D point = Plane.intersection(this, other, new Plane(direction, this.tolerance));
        return new Line(point, (Vector3D)point.add((Vector)direction), this.tolerance);
    }

    public static Vector3D intersection(Plane plane1, Plane plane2, Plane plane3) {
        double a1 = plane1.w.getX();
        double b1 = plane1.w.getY();
        double c1 = plane1.w.getZ();
        double d1 = plane1.originOffset;
        double a2 = plane2.w.getX();
        double b2 = plane2.w.getY();
        double c2 = plane2.w.getZ();
        double d2 = plane2.originOffset;
        double a3 = plane3.w.getX();
        double b3 = plane3.w.getY();
        double c3 = plane3.w.getZ();
        double d3 = plane3.originOffset;
        double a23 = b2 * c3 - b3 * c2;
        double b23 = c2 * a3 - c3 * a2;
        double c23 = a2 * b3 - a3 * b2;
        double determinant = a1 * a23 + b1 * b23 + c1 * c23;
        if (FastMath.abs(determinant) < 1.0E-10) {
            return null;
        }
        double r = 1.0 / determinant;
        return new Vector3D((-a23 * d1 - (c1 * b3 - c3 * b1) * d2 - (c2 * b1 - c1 * b2) * d3) * r, (-b23 * d1 - (c3 * a1 - c1 * a3) * d2 - (c1 * a2 - c2 * a1) * d3) * r, (-c23 * d1 - (b1 * a3 - b3 * a1) * d2 - (b2 * a1 - b1 * a2) * d3) * r);
    }

    public SubPlane wholeHyperplane() {
        return new SubPlane(this, new PolygonsSet(this.tolerance));
    }

    public PolyhedronsSet wholeSpace() {
        return new PolyhedronsSet(this.tolerance);
    }

    public boolean contains(Vector3D p) {
        return FastMath.abs(this.getOffset(p)) < this.tolerance;
    }

    public double getOffset(Plane plane) {
        return this.originOffset + (this.sameOrientationAs(plane) ? -plane.originOffset : plane.originOffset);
    }

    @Override
    public double getOffset(Vector<Euclidean3D> vector) {
        return this.getOffset((Point<Euclidean3D>)vector);
    }

    @Override
    public double getOffset(Point<Euclidean3D> point) {
        return ((Vector3D)point).dotProduct(this.w) + this.originOffset;
    }

    @Override
    public boolean sameOrientationAs(Hyperplane<Euclidean3D> other) {
        return ((Plane)other).w.dotProduct(this.w) > 0.0;
    }
}

