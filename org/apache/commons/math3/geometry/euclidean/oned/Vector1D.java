/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.oned;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1DFormat;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Vector1D
implements Vector<Euclidean1D> {
    public static final Vector1D ZERO = new Vector1D(0.0);
    public static final Vector1D ONE = new Vector1D(1.0);
    public static final Vector1D NaN = new Vector1D(Double.NaN);
    public static final Vector1D POSITIVE_INFINITY = new Vector1D(Double.POSITIVE_INFINITY);
    public static final Vector1D NEGATIVE_INFINITY = new Vector1D(Double.NEGATIVE_INFINITY);
    private static final long serialVersionUID = 7556674948671647925L;
    private final double x;

    public Vector1D(double x) {
        this.x = x;
    }

    public Vector1D(double a, Vector1D u) {
        this.x = a * u.x;
    }

    public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2) {
        this.x = a1 * u1.x + a2 * u2.x;
    }

    public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3) {
        this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x;
    }

    public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3, double a4, Vector1D u4) {
        this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x + a4 * u4.x;
    }

    public double getX() {
        return this.x;
    }

    @Override
    public Space getSpace() {
        return Euclidean1D.getInstance();
    }

    public Vector1D getZero() {
        return ZERO;
    }

    @Override
    public double getNorm1() {
        return FastMath.abs(this.x);
    }

    @Override
    public double getNorm() {
        return FastMath.abs(this.x);
    }

    @Override
    public double getNormSq() {
        return this.x * this.x;
    }

    @Override
    public double getNormInf() {
        return FastMath.abs(this.x);
    }

    public Vector1D add(Vector<Euclidean1D> v) {
        Vector1D v1 = (Vector1D)v;
        return new Vector1D(this.x + v1.getX());
    }

    public Vector1D add(double factor, Vector<Euclidean1D> v) {
        Vector1D v1 = (Vector1D)v;
        return new Vector1D(this.x + factor * v1.getX());
    }

    public Vector1D subtract(Vector<Euclidean1D> p) {
        Vector1D p3 = (Vector1D)p;
        return new Vector1D(this.x - p3.x);
    }

    public Vector1D subtract(double factor, Vector<Euclidean1D> v) {
        Vector1D v1 = (Vector1D)v;
        return new Vector1D(this.x - factor * v1.getX());
    }

    public Vector1D normalize() throws MathArithmeticException {
        double s = this.getNorm();
        if (s == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return this.scalarMultiply(1.0 / s);
    }

    public Vector1D negate() {
        return new Vector1D(-this.x);
    }

    public Vector1D scalarMultiply(double a) {
        return new Vector1D(a * this.x);
    }

    @Override
    public boolean isNaN() {
        return Double.isNaN(this.x);
    }

    @Override
    public boolean isInfinite() {
        return !this.isNaN() && Double.isInfinite(this.x);
    }

    @Override
    public double distance1(Vector<Euclidean1D> p) {
        Vector1D p3 = (Vector1D)p;
        double dx = FastMath.abs(p3.x - this.x);
        return dx;
    }

    @Override
    @Deprecated
    public double distance(Vector<Euclidean1D> p) {
        return this.distance((Point<Euclidean1D>)p);
    }

    @Override
    public double distance(Point<Euclidean1D> p) {
        Vector1D p3 = (Vector1D)p;
        double dx = p3.x - this.x;
        return FastMath.abs(dx);
    }

    @Override
    public double distanceInf(Vector<Euclidean1D> p) {
        Vector1D p3 = (Vector1D)p;
        double dx = FastMath.abs(p3.x - this.x);
        return dx;
    }

    @Override
    public double distanceSq(Vector<Euclidean1D> p) {
        Vector1D p3 = (Vector1D)p;
        double dx = p3.x - this.x;
        return dx * dx;
    }

    @Override
    public double dotProduct(Vector<Euclidean1D> v) {
        Vector1D v1 = (Vector1D)v;
        return this.x * v1.x;
    }

    public static double distance(Vector1D p1, Vector1D p2) {
        return p1.distance(p2);
    }

    public static double distanceInf(Vector1D p1, Vector1D p2) {
        return p1.distanceInf(p2);
    }

    public static double distanceSq(Vector1D p1, Vector1D p2) {
        return p1.distanceSq(p2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Vector1D) {
            Vector1D rhs = (Vector1D)other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return this.x == rhs.x;
        }
        return false;
    }

    public int hashCode() {
        if (this.isNaN()) {
            return 7785;
        }
        return 997 * MathUtils.hash(this.x);
    }

    public String toString() {
        return Vector1DFormat.getInstance().format(this);
    }

    @Override
    public String toString(NumberFormat format) {
        return new Vector1DFormat(format).format(this);
    }
}

