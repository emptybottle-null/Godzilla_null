/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import java.text.NumberFormat;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3DFormat;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldVector3D<T extends RealFieldElement<T>>
implements Serializable {
    private static final long serialVersionUID = 20130224L;
    private final T x;
    private final T y;
    private final T z;

    public FieldVector3D(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public FieldVector3D(T[] v) throws DimensionMismatchException {
        if (v.length != 3) {
            throw new DimensionMismatchException(v.length, 3);
        }
        this.x = v[0];
        this.y = v[1];
        this.z = v[2];
    }

    public FieldVector3D(T alpha, T delta) {
        RealFieldElement cosDelta = (RealFieldElement)delta.cos();
        this.x = ((RealFieldElement)alpha.cos()).multiply(cosDelta);
        this.y = ((RealFieldElement)alpha.sin()).multiply(cosDelta);
        this.z = (RealFieldElement)delta.sin();
    }

    public FieldVector3D(T a, FieldVector3D<T> u) {
        this.x = (RealFieldElement)a.multiply(u.x);
        this.y = (RealFieldElement)a.multiply(u.y);
        this.z = (RealFieldElement)a.multiply(u.z);
    }

    public FieldVector3D(T a, Vector3D u) {
        this.x = (RealFieldElement)a.multiply(u.getX());
        this.y = (RealFieldElement)a.multiply(u.getY());
        this.z = (RealFieldElement)a.multiply(u.getZ());
    }

    public FieldVector3D(double a, FieldVector3D<T> u) {
        this.x = (RealFieldElement)u.x.multiply(a);
        this.y = (RealFieldElement)u.y.multiply(a);
        this.z = (RealFieldElement)u.z.multiply(a);
    }

    public FieldVector3D(T a1, FieldVector3D<T> u1, T a2, FieldVector3D<T> u2) {
        T prototype = a1;
        this.x = (RealFieldElement)prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
        this.y = (RealFieldElement)prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
        this.z = (RealFieldElement)prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
    }

    public FieldVector3D(T a1, Vector3D u1, T a2, Vector3D u2) {
        T prototype = a1;
        this.x = (RealFieldElement)prototype.linearCombination(u1.getX(), a1, u2.getX(), a2);
        this.y = (RealFieldElement)prototype.linearCombination(u1.getY(), a1, u2.getY(), a2);
        this.z = (RealFieldElement)prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2);
    }

    public FieldVector3D(double a1, FieldVector3D<T> u1, double a2, FieldVector3D<T> u2) {
        T prototype = u1.getX();
        this.x = (RealFieldElement)prototype.linearCombination(a1, u1.getX(), a2, u2.getX());
        this.y = (RealFieldElement)prototype.linearCombination(a1, u1.getY(), a2, u2.getY());
        this.z = (RealFieldElement)prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ());
    }

    public FieldVector3D(T a1, FieldVector3D<T> u1, T a2, FieldVector3D<T> u2, T a3, FieldVector3D<T> u3) {
        T prototype = a1;
        this.x = (RealFieldElement)prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
        this.y = (RealFieldElement)prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
        this.z = (RealFieldElement)prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
    }

    public FieldVector3D(T a1, Vector3D u1, T a2, Vector3D u2, T a3, Vector3D u3) {
        T prototype = a1;
        this.x = (RealFieldElement)prototype.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3);
        this.y = (RealFieldElement)prototype.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3);
        this.z = (RealFieldElement)prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3);
    }

    public FieldVector3D(double a1, FieldVector3D<T> u1, double a2, FieldVector3D<T> u2, double a3, FieldVector3D<T> u3) {
        T prototype = u1.getX();
        this.x = (RealFieldElement)prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX());
        this.y = (RealFieldElement)prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY());
        this.z = (RealFieldElement)prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ());
    }

    public FieldVector3D(T a1, FieldVector3D<T> u1, T a2, FieldVector3D<T> u2, T a3, FieldVector3D<T> u3, T a4, FieldVector3D<T> u4) {
        T prototype = a1;
        this.x = (RealFieldElement)prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
        this.y = (RealFieldElement)prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
        this.z = (RealFieldElement)prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
    }

    public FieldVector3D(T a1, Vector3D u1, T a2, Vector3D u2, T a3, Vector3D u3, T a4, Vector3D u4) {
        T prototype = a1;
        this.x = (RealFieldElement)prototype.linearCombination(u1.getX(), a1, u2.getX(), a2, u3.getX(), a3, u4.getX(), a4);
        this.y = (RealFieldElement)prototype.linearCombination(u1.getY(), a1, u2.getY(), a2, u3.getY(), a3, u4.getY(), a4);
        this.z = (RealFieldElement)prototype.linearCombination(u1.getZ(), a1, u2.getZ(), a2, u3.getZ(), a3, u4.getZ(), a4);
    }

    public FieldVector3D(double a1, FieldVector3D<T> u1, double a2, FieldVector3D<T> u2, double a3, FieldVector3D<T> u3, double a4, FieldVector3D<T> u4) {
        T prototype = u1.getX();
        this.x = (RealFieldElement)prototype.linearCombination(a1, u1.getX(), a2, u2.getX(), a3, u3.getX(), a4, u4.getX());
        this.y = (RealFieldElement)prototype.linearCombination(a1, u1.getY(), a2, u2.getY(), a3, u3.getY(), a4, u4.getY());
        this.z = (RealFieldElement)prototype.linearCombination(a1, u1.getZ(), a2, u2.getZ(), a3, u3.getZ(), a4, u4.getZ());
    }

    public T getX() {
        return this.x;
    }

    public T getY() {
        return this.y;
    }

    public T getZ() {
        return this.z;
    }

    public T[] toArray() {
        RealFieldElement[] array = (RealFieldElement[])MathArrays.buildArray(this.x.getField(), 3);
        array[0] = this.x;
        array[1] = this.y;
        array[2] = this.z;
        return array;
    }

    public Vector3D toVector3D() {
        return new Vector3D(this.x.getReal(), this.y.getReal(), this.z.getReal());
    }

    public T getNorm1() {
        return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.abs()).add(this.y.abs())).add(this.z.abs()));
    }

    public T getNorm() {
        return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.multiply(this.x)).add(this.y.multiply(this.y))).add(this.z.multiply(this.z))).sqrt());
    }

    public T getNormSq() {
        return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.multiply(this.x)).add(this.y.multiply(this.y))).add(this.z.multiply(this.z)));
    }

    public T getNormInf() {
        RealFieldElement xAbs = (RealFieldElement)this.x.abs();
        RealFieldElement yAbs = (RealFieldElement)this.y.abs();
        RealFieldElement zAbs = (RealFieldElement)this.z.abs();
        if (xAbs.getReal() <= yAbs.getReal()) {
            if (yAbs.getReal() <= zAbs.getReal()) {
                return (T)zAbs;
            }
            return (T)yAbs;
        }
        if (xAbs.getReal() <= zAbs.getReal()) {
            return (T)zAbs;
        }
        return (T)xAbs;
    }

    public T getAlpha() {
        return (T)((RealFieldElement)this.y.atan2(this.x));
    }

    public T getDelta() {
        return (T)((RealFieldElement)((RealFieldElement)this.z.divide(this.getNorm())).asin());
    }

    public FieldVector3D<T> add(FieldVector3D<T> v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.add(v.x), (RealFieldElement)this.y.add(v.y), (RealFieldElement)this.z.add(v.z));
    }

    public FieldVector3D<T> add(Vector3D v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.add(v.getX()), (RealFieldElement)this.y.add(v.getY()), (RealFieldElement)this.z.add(v.getZ()));
    }

    public FieldVector3D<T> add(T factor, FieldVector3D<T> v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.getField().getOne(), (FieldVector3D<RealFieldElement>)this, (RealFieldElement)factor, (FieldVector3D<RealFieldElement>)v);
    }

    public FieldVector3D<T> add(T factor, Vector3D v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.add(factor.multiply(v.getX())), (RealFieldElement)this.y.add(factor.multiply(v.getY())), (RealFieldElement)this.z.add(factor.multiply(v.getZ())));
    }

    public FieldVector3D<T> add(double factor, FieldVector3D<T> v) {
        return new FieldVector3D<T>(1.0, this, factor, v);
    }

    public FieldVector3D<T> add(double factor, Vector3D v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.add(factor * v.getX()), (RealFieldElement)this.y.add(factor * v.getY()), (RealFieldElement)this.z.add(factor * v.getZ()));
    }

    public FieldVector3D<T> subtract(FieldVector3D<T> v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.subtract(v.x), (RealFieldElement)this.y.subtract(v.y), (RealFieldElement)this.z.subtract(v.z));
    }

    public FieldVector3D<T> subtract(Vector3D v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.subtract(v.getX()), (RealFieldElement)this.y.subtract(v.getY()), (RealFieldElement)this.z.subtract(v.getZ()));
    }

    public FieldVector3D<T> subtract(T factor, FieldVector3D<T> v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.getField().getOne(), this, (RealFieldElement)factor.negate(), v);
    }

    public FieldVector3D<T> subtract(T factor, Vector3D v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.subtract(factor.multiply(v.getX())), (RealFieldElement)this.y.subtract(factor.multiply(v.getY())), (RealFieldElement)this.z.subtract(factor.multiply(v.getZ())));
    }

    public FieldVector3D<T> subtract(double factor, FieldVector3D<T> v) {
        return new FieldVector3D<T>(1.0, this, -factor, v);
    }

    public FieldVector3D<T> subtract(double factor, Vector3D v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.subtract(factor * v.getX()), (RealFieldElement)this.y.subtract(factor * v.getY()), (RealFieldElement)this.z.subtract(factor * v.getZ()));
    }

    public FieldVector3D<T> normalize() throws MathArithmeticException {
        T s = this.getNorm();
        if (s.getReal() == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]);
        }
        return this.scalarMultiply((RealFieldElement)s.reciprocal());
    }

    public FieldVector3D<T> orthogonal() throws MathArithmeticException {
        double threshold = 0.6 * this.getNorm().getReal();
        if (threshold == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        if (FastMath.abs(this.x.getReal()) <= threshold) {
            RealFieldElement inverse = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.y.multiply(this.y)).add(this.z.multiply(this.z))).sqrt()).reciprocal();
            return new FieldVector3D<RealFieldElement>((RealFieldElement)inverse.getField().getZero(), (RealFieldElement)inverse.multiply(this.z), (RealFieldElement)((RealFieldElement)inverse.multiply(this.y)).negate());
        }
        if (FastMath.abs(this.y.getReal()) <= threshold) {
            RealFieldElement inverse = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.multiply(this.x)).add(this.z.multiply(this.z))).sqrt()).reciprocal();
            return new FieldVector3D<RealFieldElement>((RealFieldElement)((RealFieldElement)inverse.multiply(this.z)).negate(), (RealFieldElement)inverse.getField().getZero(), (RealFieldElement)inverse.multiply(this.x));
        }
        RealFieldElement inverse = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.x.multiply(this.x)).add(this.y.multiply(this.y))).sqrt()).reciprocal();
        return new FieldVector3D<RealFieldElement>((RealFieldElement)inverse.multiply(this.y), (RealFieldElement)((RealFieldElement)inverse.multiply(this.x)).negate(), (RealFieldElement)inverse.getField().getZero());
    }

    public static <T extends RealFieldElement<T>> T angle(FieldVector3D<T> v1, FieldVector3D<T> v2) throws MathArithmeticException {
        RealFieldElement normProduct = (RealFieldElement)v1.getNorm().multiply(v2.getNorm());
        if (normProduct.getReal() == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        RealFieldElement dot = FieldVector3D.dotProduct(v1, v2);
        double threshold = normProduct.getReal() * 0.9999;
        if (dot.getReal() < -threshold || dot.getReal() > threshold) {
            FieldVector3D<T> v3 = FieldVector3D.crossProduct(v1, v2);
            if (dot.getReal() >= 0.0) {
                return (T)((RealFieldElement)v3.getNorm().divide((RealFieldElement)normProduct).asin());
            }
            return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)v3.getNorm().divide((RealFieldElement)normProduct).asin()).subtract(Math.PI)).negate());
        }
        return (T)((RealFieldElement)dot.divide((RealFieldElement)normProduct).acos());
    }

    public static <T extends RealFieldElement<T>> T angle(FieldVector3D<T> v1, Vector3D v2) throws MathArithmeticException {
        RealFieldElement normProduct = (RealFieldElement)v1.getNorm().multiply(v2.getNorm());
        if (normProduct.getReal() == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        RealFieldElement dot = FieldVector3D.dotProduct(v1, v2);
        double threshold = normProduct.getReal() * 0.9999;
        if (dot.getReal() < -threshold || dot.getReal() > threshold) {
            FieldVector3D<T> v3 = FieldVector3D.crossProduct(v1, v2);
            if (dot.getReal() >= 0.0) {
                return (T)((RealFieldElement)v3.getNorm().divide((RealFieldElement)normProduct).asin());
            }
            return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)v3.getNorm().divide((RealFieldElement)normProduct).asin()).subtract(Math.PI)).negate());
        }
        return (T)((RealFieldElement)dot.divide((RealFieldElement)normProduct).acos());
    }

    public static <T extends RealFieldElement<T>> T angle(Vector3D v1, FieldVector3D<T> v2) throws MathArithmeticException {
        return FieldVector3D.angle(v2, v1);
    }

    public FieldVector3D<T> negate() {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.negate(), (RealFieldElement)this.y.negate(), (RealFieldElement)this.z.negate());
    }

    public FieldVector3D<T> scalarMultiply(T a) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.multiply(a), (RealFieldElement)this.y.multiply(a), (RealFieldElement)this.z.multiply(a));
    }

    public FieldVector3D<T> scalarMultiply(double a) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.multiply(a), (RealFieldElement)this.y.multiply(a), (RealFieldElement)this.z.multiply(a));
    }

    public boolean isNaN() {
        return Double.isNaN(this.x.getReal()) || Double.isNaN(this.y.getReal()) || Double.isNaN(this.z.getReal());
    }

    public boolean isInfinite() {
        return !this.isNaN() && (Double.isInfinite(this.x.getReal()) || Double.isInfinite(this.y.getReal()) || Double.isInfinite(this.z.getReal()));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof FieldVector3D) {
            FieldVector3D rhs = (FieldVector3D)other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return this.x.equals(rhs.x) && this.y.equals(rhs.y) && this.z.equals(rhs.z);
        }
        return false;
    }

    public int hashCode() {
        if (this.isNaN()) {
            return 409;
        }
        return 311 * (107 * this.x.hashCode() + 83 * this.y.hashCode() + this.z.hashCode());
    }

    public T dotProduct(FieldVector3D<T> v) {
        return (T)((RealFieldElement)this.x.linearCombination(this.x, v.x, this.y, v.y, this.z, v.z));
    }

    public T dotProduct(Vector3D v) {
        return (T)((RealFieldElement)this.x.linearCombination(v.getX(), this.x, v.getY(), this.y, v.getZ(), this.z));
    }

    public FieldVector3D<T> crossProduct(FieldVector3D<T> v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.linearCombination(this.y, v.z, this.z.negate(), v.y), (RealFieldElement)this.y.linearCombination(this.z, v.x, this.x.negate(), v.z), (RealFieldElement)this.z.linearCombination(this.x, v.y, this.y.negate(), v.x));
    }

    public FieldVector3D<T> crossProduct(Vector3D v) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)this.x.linearCombination(v.getZ(), this.y, -v.getY(), this.z), (RealFieldElement)this.y.linearCombination(v.getX(), this.z, -v.getZ(), this.x), (RealFieldElement)this.z.linearCombination(v.getY(), this.x, -v.getX(), this.y));
    }

    public T distance1(FieldVector3D<T> v) {
        RealFieldElement dx = (RealFieldElement)((RealFieldElement)v.x.subtract(this.x)).abs();
        RealFieldElement dy = (RealFieldElement)((RealFieldElement)v.y.subtract(this.y)).abs();
        RealFieldElement dz = (RealFieldElement)((RealFieldElement)v.z.subtract(this.z)).abs();
        return (T)dx.add(dy).add(dz);
    }

    public T distance1(Vector3D v) {
        RealFieldElement dx = (RealFieldElement)((RealFieldElement)this.x.subtract(v.getX())).abs();
        RealFieldElement dy = (RealFieldElement)((RealFieldElement)this.y.subtract(v.getY())).abs();
        RealFieldElement dz = (RealFieldElement)((RealFieldElement)this.z.subtract(v.getZ())).abs();
        return (T)dx.add(dy).add(dz);
    }

    public T distance(FieldVector3D<T> v) {
        RealFieldElement dx = (RealFieldElement)v.x.subtract(this.x);
        RealFieldElement dy = (RealFieldElement)v.y.subtract(this.y);
        RealFieldElement dz = (RealFieldElement)v.z.subtract(this.z);
        return (T)((RealFieldElement)dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)).sqrt());
    }

    public T distance(Vector3D v) {
        RealFieldElement dx = (RealFieldElement)this.x.subtract(v.getX());
        RealFieldElement dy = (RealFieldElement)this.y.subtract(v.getY());
        RealFieldElement dz = (RealFieldElement)this.z.subtract(v.getZ());
        return (T)((RealFieldElement)dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz)).sqrt());
    }

    public T distanceInf(FieldVector3D<T> v) {
        RealFieldElement dx = (RealFieldElement)((RealFieldElement)v.x.subtract(this.x)).abs();
        RealFieldElement dy = (RealFieldElement)((RealFieldElement)v.y.subtract(this.y)).abs();
        RealFieldElement dz = (RealFieldElement)((RealFieldElement)v.z.subtract(this.z)).abs();
        if (dx.getReal() <= dy.getReal()) {
            if (dy.getReal() <= dz.getReal()) {
                return (T)dz;
            }
            return (T)dy;
        }
        if (dx.getReal() <= dz.getReal()) {
            return (T)dz;
        }
        return (T)dx;
    }

    public T distanceInf(Vector3D v) {
        RealFieldElement dx = (RealFieldElement)((RealFieldElement)this.x.subtract(v.getX())).abs();
        RealFieldElement dy = (RealFieldElement)((RealFieldElement)this.y.subtract(v.getY())).abs();
        RealFieldElement dz = (RealFieldElement)((RealFieldElement)this.z.subtract(v.getZ())).abs();
        if (dx.getReal() <= dy.getReal()) {
            if (dy.getReal() <= dz.getReal()) {
                return (T)dz;
            }
            return (T)dy;
        }
        if (dx.getReal() <= dz.getReal()) {
            return (T)dz;
        }
        return (T)dx;
    }

    public T distanceSq(FieldVector3D<T> v) {
        RealFieldElement dx = (RealFieldElement)v.x.subtract(this.x);
        RealFieldElement dy = (RealFieldElement)v.y.subtract(this.y);
        RealFieldElement dz = (RealFieldElement)v.z.subtract(this.z);
        return (T)dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
    }

    public T distanceSq(Vector3D v) {
        RealFieldElement dx = (RealFieldElement)this.x.subtract(v.getX());
        RealFieldElement dy = (RealFieldElement)this.y.subtract(v.getY());
        RealFieldElement dz = (RealFieldElement)this.z.subtract(v.getZ());
        return (T)dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
    }

    public static <T extends RealFieldElement<T>> T dotProduct(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.dotProduct(v2);
    }

    public static <T extends RealFieldElement<T>> T dotProduct(FieldVector3D<T> v1, Vector3D v2) {
        return v1.dotProduct(v2);
    }

    public static <T extends RealFieldElement<T>> T dotProduct(Vector3D v1, FieldVector3D<T> v2) {
        return v2.dotProduct(v1);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.crossProduct(v2);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(FieldVector3D<T> v1, Vector3D v2) {
        return v1.crossProduct(v2);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(Vector3D v1, FieldVector3D<T> v2) {
        return new FieldVector3D<RealFieldElement>((RealFieldElement)v2.x.linearCombination(v1.getY(), v2.z, -v1.getZ(), v2.y), (RealFieldElement)v2.y.linearCombination(v1.getZ(), v2.x, -v1.getX(), v2.z), (RealFieldElement)v2.z.linearCombination(v1.getX(), v2.y, -v1.getY(), v2.x));
    }

    public static <T extends RealFieldElement<T>> T distance1(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.distance1(v2);
    }

    public static <T extends RealFieldElement<T>> T distance1(FieldVector3D<T> v1, Vector3D v2) {
        return v1.distance1(v2);
    }

    public static <T extends RealFieldElement<T>> T distance1(Vector3D v1, FieldVector3D<T> v2) {
        return v2.distance1(v1);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.distance(v2);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldVector3D<T> v1, Vector3D v2) {
        return v1.distance(v2);
    }

    public static <T extends RealFieldElement<T>> T distance(Vector3D v1, FieldVector3D<T> v2) {
        return v2.distance(v1);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.distanceInf(v2);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(FieldVector3D<T> v1, Vector3D v2) {
        return v1.distanceInf(v2);
    }

    public static <T extends RealFieldElement<T>> T distanceInf(Vector3D v1, FieldVector3D<T> v2) {
        return v2.distanceInf(v1);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(FieldVector3D<T> v1, FieldVector3D<T> v2) {
        return v1.distanceSq(v2);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(FieldVector3D<T> v1, Vector3D v2) {
        return v1.distanceSq(v2);
    }

    public static <T extends RealFieldElement<T>> T distanceSq(Vector3D v1, FieldVector3D<T> v2) {
        return v2.distanceSq(v1);
    }

    public String toString() {
        return Vector3DFormat.getInstance().format(this.toVector3D());
    }

    public String toString(NumberFormat format) {
        return new Vector3DFormat(format).format(this.toVector3D());
    }
}

