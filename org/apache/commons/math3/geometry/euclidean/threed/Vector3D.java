/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3DFormat;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Vector3D
implements Serializable,
Vector<Euclidean3D> {
    public static final Vector3D ZERO = new Vector3D(0.0, 0.0, 0.0);
    public static final Vector3D PLUS_I = new Vector3D(1.0, 0.0, 0.0);
    public static final Vector3D MINUS_I = new Vector3D(-1.0, 0.0, 0.0);
    public static final Vector3D PLUS_J = new Vector3D(0.0, 1.0, 0.0);
    public static final Vector3D MINUS_J = new Vector3D(0.0, -1.0, 0.0);
    public static final Vector3D PLUS_K = new Vector3D(0.0, 0.0, 1.0);
    public static final Vector3D MINUS_K = new Vector3D(0.0, 0.0, -1.0);
    public static final Vector3D NaN = new Vector3D(Double.NaN, Double.NaN, Double.NaN);
    public static final Vector3D POSITIVE_INFINITY = new Vector3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Vector3D NEGATIVE_INFINITY = new Vector3D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    private static final long serialVersionUID = 1313493323784566947L;
    private final double x;
    private final double y;
    private final double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(double[] v) throws DimensionMismatchException {
        if (v.length != 3) {
            throw new DimensionMismatchException(v.length, 3);
        }
        this.x = v[0];
        this.y = v[1];
        this.z = v[2];
    }

    public Vector3D(double alpha, double delta) {
        double cosDelta = FastMath.cos(delta);
        this.x = FastMath.cos(alpha) * cosDelta;
        this.y = FastMath.sin(alpha) * cosDelta;
        this.z = FastMath.sin(delta);
    }

    public Vector3D(double a, Vector3D u) {
        this.x = a * u.x;
        this.y = a * u.y;
        this.z = a * u.z;
    }

    public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2) {
        this.x = MathArrays.linearCombination(a1, u1.x, a2, u2.x);
        this.y = MathArrays.linearCombination(a1, u1.y, a2, u2.y);
        this.z = MathArrays.linearCombination(a1, u1.z, a2, u2.z);
    }

    public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2, double a3, Vector3D u3) {
        this.x = MathArrays.linearCombination(a1, u1.x, a2, u2.x, a3, u3.x);
        this.y = MathArrays.linearCombination(a1, u1.y, a2, u2.y, a3, u3.y);
        this.z = MathArrays.linearCombination(a1, u1.z, a2, u2.z, a3, u3.z);
    }

    public Vector3D(double a1, Vector3D u1, double a2, Vector3D u2, double a3, Vector3D u3, double a4, Vector3D u4) {
        this.x = MathArrays.linearCombination(a1, u1.x, a2, u2.x, a3, u3.x, a4, u4.x);
        this.y = MathArrays.linearCombination(a1, u1.y, a2, u2.y, a3, u3.y, a4, u4.y);
        this.z = MathArrays.linearCombination(a1, u1.z, a2, u2.z, a3, u3.z, a4, u4.z);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double[] toArray() {
        return new double[]{this.x, this.y, this.z};
    }

    @Override
    public Space getSpace() {
        return Euclidean3D.getInstance();
    }

    public Vector3D getZero() {
        return ZERO;
    }

    @Override
    public double getNorm1() {
        return FastMath.abs(this.x) + FastMath.abs(this.y) + FastMath.abs(this.z);
    }

    @Override
    public double getNorm() {
        return FastMath.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    @Override
    public double getNormSq() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    @Override
    public double getNormInf() {
        return FastMath.max(FastMath.max(FastMath.abs(this.x), FastMath.abs(this.y)), FastMath.abs(this.z));
    }

    public double getAlpha() {
        return FastMath.atan2(this.y, this.x);
    }

    public double getDelta() {
        return FastMath.asin(this.z / this.getNorm());
    }

    public Vector3D add(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D)v;
        return new Vector3D(this.x + v3.x, this.y + v3.y, this.z + v3.z);
    }

    public Vector3D add(double factor, Vector<Euclidean3D> v) {
        return new Vector3D(1.0, this, factor, (Vector3D)v);
    }

    public Vector3D subtract(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D)v;
        return new Vector3D(this.x - v3.x, this.y - v3.y, this.z - v3.z);
    }

    public Vector3D subtract(double factor, Vector<Euclidean3D> v) {
        return new Vector3D(1.0, this, -factor, (Vector3D)v);
    }

    public Vector3D normalize() throws MathArithmeticException {
        double s = this.getNorm();
        if (s == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return this.scalarMultiply(1.0 / s);
    }

    public Vector3D orthogonal() throws MathArithmeticException {
        double threshold = 0.6 * this.getNorm();
        if (threshold == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        if (FastMath.abs(this.x) <= threshold) {
            double inverse = 1.0 / FastMath.sqrt(this.y * this.y + this.z * this.z);
            return new Vector3D(0.0, inverse * this.z, -inverse * this.y);
        }
        if (FastMath.abs(this.y) <= threshold) {
            double inverse = 1.0 / FastMath.sqrt(this.x * this.x + this.z * this.z);
            return new Vector3D(-inverse * this.z, 0.0, inverse * this.x);
        }
        double inverse = 1.0 / FastMath.sqrt(this.x * this.x + this.y * this.y);
        return new Vector3D(inverse * this.y, -inverse * this.x, 0.0);
    }

    public static double angle(Vector3D v1, Vector3D v2) throws MathArithmeticException {
        double threshold;
        double normProduct = v1.getNorm() * v2.getNorm();
        if (normProduct == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        double dot = v1.dotProduct(v2);
        if (dot < -(threshold = normProduct * 0.9999) || dot > threshold) {
            Vector3D v3 = Vector3D.crossProduct(v1, v2);
            if (dot >= 0.0) {
                return FastMath.asin(v3.getNorm() / normProduct);
            }
            return Math.PI - FastMath.asin(v3.getNorm() / normProduct);
        }
        return FastMath.acos(dot / normProduct);
    }

    public Vector3D negate() {
        return new Vector3D(-this.x, -this.y, -this.z);
    }

    public Vector3D scalarMultiply(double a) {
        return new Vector3D(a * this.x, a * this.y, a * this.z);
    }

    @Override
    public boolean isNaN() {
        return Double.isNaN(this.x) || Double.isNaN(this.y) || Double.isNaN(this.z);
    }

    @Override
    public boolean isInfinite() {
        return !this.isNaN() && (Double.isInfinite(this.x) || Double.isInfinite(this.y) || Double.isInfinite(this.z));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Vector3D) {
            Vector3D rhs = (Vector3D)other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return this.x == rhs.x && this.y == rhs.y && this.z == rhs.z;
        }
        return false;
    }

    public int hashCode() {
        if (this.isNaN()) {
            return 642;
        }
        return 643 * (164 * MathUtils.hash(this.x) + 3 * MathUtils.hash(this.y) + MathUtils.hash(this.z));
    }

    @Override
    public double dotProduct(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D)v;
        return MathArrays.linearCombination(this.x, v3.x, this.y, v3.y, this.z, v3.z);
    }

    public Vector3D crossProduct(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D)v;
        return new Vector3D(MathArrays.linearCombination(this.y, v3.z, -this.z, v3.y), MathArrays.linearCombination(this.z, v3.x, -this.x, v3.z), MathArrays.linearCombination(this.x, v3.y, -this.y, v3.x));
    }

    @Override
    public double distance1(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D)v;
        double dx = FastMath.abs(v3.x - this.x);
        double dy = FastMath.abs(v3.y - this.y);
        double dz = FastMath.abs(v3.z - this.z);
        return dx + dy + dz;
    }

    @Override
    public double distance(Vector<Euclidean3D> v) {
        return this.distance((Point<Euclidean3D>)v);
    }

    @Override
    public double distance(Point<Euclidean3D> v) {
        Vector3D v3 = (Vector3D)v;
        double dx = v3.x - this.x;
        double dy = v3.y - this.y;
        double dz = v3.z - this.z;
        return FastMath.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public double distanceInf(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D)v;
        double dx = FastMath.abs(v3.x - this.x);
        double dy = FastMath.abs(v3.y - this.y);
        double dz = FastMath.abs(v3.z - this.z);
        return FastMath.max(FastMath.max(dx, dy), dz);
    }

    @Override
    public double distanceSq(Vector<Euclidean3D> v) {
        Vector3D v3 = (Vector3D)v;
        double dx = v3.x - this.x;
        double dy = v3.y - this.y;
        double dz = v3.z - this.z;
        return dx * dx + dy * dy + dz * dz;
    }

    public static double dotProduct(Vector3D v1, Vector3D v2) {
        return v1.dotProduct(v2);
    }

    public static Vector3D crossProduct(Vector3D v1, Vector3D v2) {
        return v1.crossProduct(v2);
    }

    public static double distance1(Vector3D v1, Vector3D v2) {
        return v1.distance1(v2);
    }

    public static double distance(Vector3D v1, Vector3D v2) {
        return v1.distance(v2);
    }

    public static double distanceInf(Vector3D v1, Vector3D v2) {
        return v1.distanceInf(v2);
    }

    public static double distanceSq(Vector3D v1, Vector3D v2) {
        return v1.distanceSq(v2);
    }

    public String toString() {
        return Vector3DFormat.getInstance().format(this);
    }

    @Override
    public String toString(NumberFormat format) {
        return new Vector3DFormat(format).format(this);
    }
}

