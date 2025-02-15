/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.twod;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2DFormat;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Vector2D
implements Vector<Euclidean2D> {
    public static final Vector2D ZERO = new Vector2D(0.0, 0.0);
    public static final Vector2D NaN = new Vector2D(Double.NaN, Double.NaN);
    public static final Vector2D POSITIVE_INFINITY = new Vector2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Vector2D NEGATIVE_INFINITY = new Vector2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    private static final long serialVersionUID = 266938651998679754L;
    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(double[] v) throws DimensionMismatchException {
        if (v.length != 2) {
            throw new DimensionMismatchException(v.length, 2);
        }
        this.x = v[0];
        this.y = v[1];
    }

    public Vector2D(double a, Vector2D u) {
        this.x = a * u.x;
        this.y = a * u.y;
    }

    public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2) {
        this.x = a1 * u1.x + a2 * u2.x;
        this.y = a1 * u1.y + a2 * u2.y;
    }

    public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2, double a3, Vector2D u3) {
        this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x;
        this.y = a1 * u1.y + a2 * u2.y + a3 * u3.y;
    }

    public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2, double a3, Vector2D u3, double a4, Vector2D u4) {
        this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x + a4 * u4.x;
        this.y = a1 * u1.y + a2 * u2.y + a3 * u3.y + a4 * u4.y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double[] toArray() {
        return new double[]{this.x, this.y};
    }

    @Override
    public Space getSpace() {
        return Euclidean2D.getInstance();
    }

    public Vector2D getZero() {
        return ZERO;
    }

    @Override
    public double getNorm1() {
        return FastMath.abs(this.x) + FastMath.abs(this.y);
    }

    @Override
    public double getNorm() {
        return FastMath.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public double getNormSq() {
        return this.x * this.x + this.y * this.y;
    }

    @Override
    public double getNormInf() {
        return FastMath.max(FastMath.abs(this.x), FastMath.abs(this.y));
    }

    public Vector2D add(Vector<Euclidean2D> v) {
        Vector2D v2 = (Vector2D)v;
        return new Vector2D(this.x + v2.getX(), this.y + v2.getY());
    }

    public Vector2D add(double factor, Vector<Euclidean2D> v) {
        Vector2D v2 = (Vector2D)v;
        return new Vector2D(this.x + factor * v2.getX(), this.y + factor * v2.getY());
    }

    public Vector2D subtract(Vector<Euclidean2D> p) {
        Vector2D p3 = (Vector2D)p;
        return new Vector2D(this.x - p3.x, this.y - p3.y);
    }

    public Vector2D subtract(double factor, Vector<Euclidean2D> v) {
        Vector2D v2 = (Vector2D)v;
        return new Vector2D(this.x - factor * v2.getX(), this.y - factor * v2.getY());
    }

    public Vector2D normalize() throws MathArithmeticException {
        double s = this.getNorm();
        if (s == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return this.scalarMultiply(1.0 / s);
    }

    public static double angle(Vector2D v1, Vector2D v2) throws MathArithmeticException {
        double threshold;
        double normProduct = v1.getNorm() * v2.getNorm();
        if (normProduct == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        double dot = v1.dotProduct(v2);
        if (dot < -(threshold = normProduct * 0.9999) || dot > threshold) {
            double n = FastMath.abs(MathArrays.linearCombination(v1.x, v2.y, -v1.y, v2.x));
            if (dot >= 0.0) {
                return FastMath.asin(n / normProduct);
            }
            return Math.PI - FastMath.asin(n / normProduct);
        }
        return FastMath.acos(dot / normProduct);
    }

    public Vector2D negate() {
        return new Vector2D(-this.x, -this.y);
    }

    public Vector2D scalarMultiply(double a) {
        return new Vector2D(a * this.x, a * this.y);
    }

    @Override
    public boolean isNaN() {
        return Double.isNaN(this.x) || Double.isNaN(this.y);
    }

    @Override
    public boolean isInfinite() {
        return !this.isNaN() && (Double.isInfinite(this.x) || Double.isInfinite(this.y));
    }

    @Override
    public double distance1(Vector<Euclidean2D> p) {
        Vector2D p3 = (Vector2D)p;
        double dx = FastMath.abs(p3.x - this.x);
        double dy = FastMath.abs(p3.y - this.y);
        return dx + dy;
    }

    @Override
    public double distance(Vector<Euclidean2D> p) {
        return this.distance((Point<Euclidean2D>)p);
    }

    @Override
    public double distance(Point<Euclidean2D> p) {
        Vector2D p3 = (Vector2D)p;
        double dx = p3.x - this.x;
        double dy = p3.y - this.y;
        return FastMath.sqrt(dx * dx + dy * dy);
    }

    @Override
    public double distanceInf(Vector<Euclidean2D> p) {
        Vector2D p3 = (Vector2D)p;
        double dx = FastMath.abs(p3.x - this.x);
        double dy = FastMath.abs(p3.y - this.y);
        return FastMath.max(dx, dy);
    }

    @Override
    public double distanceSq(Vector<Euclidean2D> p) {
        Vector2D p3 = (Vector2D)p;
        double dx = p3.x - this.x;
        double dy = p3.y - this.y;
        return dx * dx + dy * dy;
    }

    @Override
    public double dotProduct(Vector<Euclidean2D> v) {
        Vector2D v2 = (Vector2D)v;
        return MathArrays.linearCombination(this.x, v2.x, this.y, v2.y);
    }

    public double crossProduct(Vector2D p1, Vector2D p2) {
        double x1 = p2.getX() - p1.getX();
        double y1 = this.getY() - p1.getY();
        double x2 = this.getX() - p1.getX();
        double y2 = p2.getY() - p1.getY();
        return MathArrays.linearCombination(x1, y1, -x2, y2);
    }

    public static double distance(Vector2D p1, Vector2D p2) {
        return p1.distance(p2);
    }

    public static double distanceInf(Vector2D p1, Vector2D p2) {
        return p1.distanceInf(p2);
    }

    public static double distanceSq(Vector2D p1, Vector2D p2) {
        return p1.distanceSq(p2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Vector2D) {
            Vector2D rhs = (Vector2D)other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return this.x == rhs.x && this.y == rhs.y;
        }
        return false;
    }

    public int hashCode() {
        if (this.isNaN()) {
            return 542;
        }
        return 122 * (76 * MathUtils.hash(this.x) + MathUtils.hash(this.y));
    }

    public String toString() {
        return Vector2DFormat.getInstance().format(this);
    }

    @Override
    public String toString(NumberFormat format) {
        return new Vector2DFormat(format).format(this);
    }
}

