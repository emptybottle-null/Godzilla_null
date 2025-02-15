/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.euclidean.threed.CardanEulerSingularityException;
import org.apache.commons.math3.geometry.euclidean.threed.FieldVector3D;
import org.apache.commons.math3.geometry.euclidean.threed.NotARotationMatrixException;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.RotationConvention;
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldRotation<T extends RealFieldElement<T>>
implements Serializable {
    private static final long serialVersionUID = 20130224L;
    private final T q0;
    private final T q1;
    private final T q2;
    private final T q3;

    public FieldRotation(T q0, T q1, T q2, T q3, boolean needsNormalization) {
        if (needsNormalization) {
            RealFieldElement inv = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)q0.multiply(q0)).add(q1.multiply(q1))).add(q2.multiply(q2))).add(q3.multiply(q3))).sqrt()).reciprocal();
            this.q0 = (RealFieldElement)inv.multiply(q0);
            this.q1 = (RealFieldElement)inv.multiply(q1);
            this.q2 = (RealFieldElement)inv.multiply(q2);
            this.q3 = (RealFieldElement)inv.multiply(q3);
        } else {
            this.q0 = q0;
            this.q1 = q1;
            this.q2 = q2;
            this.q3 = q3;
        }
    }

    @Deprecated
    public FieldRotation(FieldVector3D<T> axis, T angle) throws MathIllegalArgumentException {
        this(axis, angle, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation(FieldVector3D<T> axis, T angle, RotationConvention convention) throws MathIllegalArgumentException {
        T norm = axis.getNorm();
        if (norm.getReal() == 0.0) {
            throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS, new Object[0]);
        }
        RealFieldElement halfAngle = (RealFieldElement)angle.multiply(convention == RotationConvention.VECTOR_OPERATOR ? -0.5 : 0.5);
        RealFieldElement coeff = (RealFieldElement)((RealFieldElement)halfAngle.sin()).divide(norm);
        this.q0 = (RealFieldElement)halfAngle.cos();
        this.q1 = (RealFieldElement)coeff.multiply(axis.getX());
        this.q2 = (RealFieldElement)coeff.multiply(axis.getY());
        this.q3 = (RealFieldElement)coeff.multiply(axis.getZ());
    }

    public FieldRotation(T[][] m, double threshold) throws NotARotationMatrixException {
        if (m.length != 3 || m[0].length != 3 || m[1].length != 3 || m[2].length != 3) {
            throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, m.length, m[0].length);
        }
        RealFieldElement[][] ort = this.orthogonalizeMatrix((RealFieldElement[][])m, threshold);
        RealFieldElement d0 = ort[1][1].multiply(ort[2][2]).subtract(ort[2][1].multiply(ort[1][2]));
        RealFieldElement d1 = ort[0][1].multiply(ort[2][2]).subtract(ort[2][1].multiply(ort[0][2]));
        RealFieldElement d2 = ort[0][1].multiply(ort[1][2]).subtract(ort[1][1].multiply(ort[0][2]));
        RealFieldElement det = ort[0][0].multiply(d0).subtract(ort[1][0].multiply(d1)).add(ort[2][0].multiply(d2));
        if (det.getReal() < 0.0) {
            throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, det);
        }
        RealFieldElement[] quat = this.mat2quat(ort);
        this.q0 = quat[0];
        this.q1 = quat[1];
        this.q2 = quat[2];
        this.q3 = quat[3];
    }

    public FieldRotation(FieldVector3D<T> u1, FieldVector3D<T> u2, FieldVector3D<T> v1, FieldVector3D<T> v2) throws MathArithmeticException {
        FieldVector3D<T> u3 = FieldVector3D.crossProduct(u1, u2).normalize();
        u2 = FieldVector3D.crossProduct(u3, u1).normalize();
        u1 = u1.normalize();
        FieldVector3D<T> v3 = FieldVector3D.crossProduct(v1, v2).normalize();
        v2 = FieldVector3D.crossProduct(v3, v1).normalize();
        v1 = v1.normalize();
        RealFieldElement[][] array = (RealFieldElement[][])MathArrays.buildArray(u1.getX().getField(), 3, 3);
        array[0][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)u1.getX().multiply(v1.getX())).add(u2.getX().multiply(v2.getX()))).add(u3.getX().multiply(v3.getX()));
        array[0][1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)u1.getY().multiply(v1.getX())).add(u2.getY().multiply(v2.getX()))).add(u3.getY().multiply(v3.getX()));
        array[0][2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)u1.getZ().multiply(v1.getX())).add(u2.getZ().multiply(v2.getX()))).add(u3.getZ().multiply(v3.getX()));
        array[1][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)u1.getX().multiply(v1.getY())).add(u2.getX().multiply(v2.getY()))).add(u3.getX().multiply(v3.getY()));
        array[1][1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)u1.getY().multiply(v1.getY())).add(u2.getY().multiply(v2.getY()))).add(u3.getY().multiply(v3.getY()));
        array[1][2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)u1.getZ().multiply(v1.getY())).add(u2.getZ().multiply(v2.getY()))).add(u3.getZ().multiply(v3.getY()));
        array[2][0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)u1.getX().multiply(v1.getZ())).add(u2.getX().multiply(v2.getZ()))).add(u3.getX().multiply(v3.getZ()));
        array[2][1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)u1.getY().multiply(v1.getZ())).add(u2.getY().multiply(v2.getZ()))).add(u3.getY().multiply(v3.getZ()));
        array[2][2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)u1.getZ().multiply(v1.getZ())).add(u2.getZ().multiply(v2.getZ()))).add(u3.getZ().multiply(v3.getZ()));
        RealFieldElement[] quat = this.mat2quat(array);
        this.q0 = quat[0];
        this.q1 = quat[1];
        this.q2 = quat[2];
        this.q3 = quat[3];
    }

    public FieldRotation(FieldVector3D<T> u, FieldVector3D<T> v) throws MathArithmeticException {
        RealFieldElement normProduct = (RealFieldElement)u.getNorm().multiply(v.getNorm());
        if (normProduct.getReal() == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR, new Object[0]);
        }
        RealFieldElement dot = FieldVector3D.dotProduct(u, v);
        if (dot.getReal() < -0.999999999999998 * normProduct.getReal()) {
            FieldVector3D<T> w = u.orthogonal();
            this.q0 = (RealFieldElement)normProduct.getField().getZero();
            this.q1 = (RealFieldElement)w.getX().negate();
            this.q2 = (RealFieldElement)w.getY().negate();
            this.q3 = (RealFieldElement)w.getZ().negate();
        } else {
            this.q0 = (RealFieldElement)((RealFieldElement)((RealFieldElement)dot.divide((RealFieldElement)normProduct).add(1.0)).multiply(0.5)).sqrt();
            RealFieldElement coeff = (RealFieldElement)((RealFieldElement)this.q0.multiply((RealFieldElement)normProduct).multiply(2.0)).reciprocal();
            FieldVector3D<T> q = FieldVector3D.crossProduct(v, u);
            this.q1 = (RealFieldElement)coeff.multiply(q.getX());
            this.q2 = (RealFieldElement)coeff.multiply(q.getY());
            this.q3 = (RealFieldElement)coeff.multiply(q.getZ());
        }
    }

    @Deprecated
    public FieldRotation(RotationOrder order, T alpha1, T alpha2, T alpha3) {
        this(order, RotationConvention.VECTOR_OPERATOR, alpha1, alpha2, alpha3);
    }

    public FieldRotation(RotationOrder order, RotationConvention convention, T alpha1, T alpha2, T alpha3) {
        RealFieldElement one = (RealFieldElement)alpha1.getField().getOne();
        FieldRotation<RealFieldElement> r1 = new FieldRotation<RealFieldElement>(new FieldVector3D<RealFieldElement>(one, order.getA1()), (RealFieldElement)alpha1, convention);
        FieldRotation<RealFieldElement> r2 = new FieldRotation<RealFieldElement>(new FieldVector3D<RealFieldElement>(one, order.getA2()), (RealFieldElement)alpha2, convention);
        FieldRotation<RealFieldElement> r3 = new FieldRotation<RealFieldElement>(new FieldVector3D<RealFieldElement>(one, order.getA3()), (RealFieldElement)alpha3, convention);
        FieldRotation<RealFieldElement> composed = r1.compose(r2.compose(r3, convention), convention);
        this.q0 = composed.q0;
        this.q1 = composed.q1;
        this.q2 = composed.q2;
        this.q3 = composed.q3;
    }

    private T[] mat2quat(T[][] ort) {
        RealFieldElement[] quat = (RealFieldElement[])MathArrays.buildArray(ort[0][0].getField(), 4);
        RealFieldElement s = (RealFieldElement)((RealFieldElement)ort[0][0].add(ort[1][1])).add(ort[2][2]);
        if (s.getReal() > -0.19) {
            quat[0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)s.add(1.0)).sqrt()).multiply(0.5);
            RealFieldElement inv = (RealFieldElement)((RealFieldElement)quat[0].reciprocal()).multiply(0.25);
            quat[1] = (RealFieldElement)inv.multiply(ort[1][2].subtract(ort[2][1]));
            quat[2] = (RealFieldElement)inv.multiply(ort[2][0].subtract(ort[0][2]));
            quat[3] = (RealFieldElement)inv.multiply(ort[0][1].subtract(ort[1][0]));
        } else {
            s = (RealFieldElement)((RealFieldElement)ort[0][0].subtract(ort[1][1])).subtract(ort[2][2]);
            if (s.getReal() > -0.19) {
                quat[1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)s.add(1.0)).sqrt()).multiply(0.5);
                RealFieldElement inv = (RealFieldElement)((RealFieldElement)quat[1].reciprocal()).multiply(0.25);
                quat[0] = (RealFieldElement)inv.multiply(ort[1][2].subtract(ort[2][1]));
                quat[2] = (RealFieldElement)inv.multiply(ort[0][1].add(ort[1][0]));
                quat[3] = (RealFieldElement)inv.multiply(ort[0][2].add(ort[2][0]));
            } else {
                s = (RealFieldElement)((RealFieldElement)ort[1][1].subtract(ort[0][0])).subtract(ort[2][2]);
                if (s.getReal() > -0.19) {
                    quat[2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)s.add(1.0)).sqrt()).multiply(0.5);
                    RealFieldElement inv = (RealFieldElement)((RealFieldElement)quat[2].reciprocal()).multiply(0.25);
                    quat[0] = (RealFieldElement)inv.multiply(ort[2][0].subtract(ort[0][2]));
                    quat[1] = (RealFieldElement)inv.multiply(ort[0][1].add(ort[1][0]));
                    quat[3] = (RealFieldElement)inv.multiply(ort[2][1].add(ort[1][2]));
                } else {
                    s = (RealFieldElement)((RealFieldElement)ort[2][2].subtract(ort[0][0])).subtract(ort[1][1]);
                    quat[3] = (RealFieldElement)((RealFieldElement)((RealFieldElement)s.add(1.0)).sqrt()).multiply(0.5);
                    RealFieldElement inv = (RealFieldElement)((RealFieldElement)quat[3].reciprocal()).multiply(0.25);
                    quat[0] = (RealFieldElement)inv.multiply(ort[0][1].subtract(ort[1][0]));
                    quat[1] = (RealFieldElement)inv.multiply(ort[0][2].add(ort[2][0]));
                    quat[2] = (RealFieldElement)inv.multiply(ort[2][1].add(ort[1][2]));
                }
            }
        }
        return quat;
    }

    public FieldRotation<T> revert() {
        return new FieldRotation<RealFieldElement>((RealFieldElement)this.q0.negate(), (RealFieldElement)this.q1, (RealFieldElement)this.q2, (RealFieldElement)this.q3, false);
    }

    public T getQ0() {
        return this.q0;
    }

    public T getQ1() {
        return this.q1;
    }

    public T getQ2() {
        return this.q2;
    }

    public T getQ3() {
        return this.q3;
    }

    @Deprecated
    public FieldVector3D<T> getAxis() {
        return this.getAxis(RotationConvention.VECTOR_OPERATOR);
    }

    public FieldVector3D<T> getAxis(RotationConvention convention) {
        double sgn;
        RealFieldElement squaredSine = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(this.q1)).add(this.q2.multiply(this.q2))).add(this.q3.multiply(this.q3));
        if (squaredSine.getReal() == 0.0) {
            Field field = squaredSine.getField();
            return new FieldVector3D<RealFieldElement>(convention == RotationConvention.VECTOR_OPERATOR ? (RealFieldElement)field.getOne() : (RealFieldElement)((RealFieldElement)field.getOne()).negate(), (RealFieldElement)field.getZero(), (RealFieldElement)field.getZero());
        }
        double d = sgn = convention == RotationConvention.VECTOR_OPERATOR ? 1.0 : -1.0;
        if (this.q0.getReal() < 0.0) {
            RealFieldElement inverse = (RealFieldElement)((RealFieldElement)((RealFieldElement)squaredSine.sqrt()).reciprocal()).multiply(sgn);
            return new FieldVector3D<RealFieldElement>(this.q1.multiply((RealFieldElement)inverse), this.q2.multiply((RealFieldElement)inverse), this.q3.multiply((RealFieldElement)inverse));
        }
        RealFieldElement inverse = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)squaredSine.sqrt()).reciprocal()).negate()).multiply(sgn);
        return new FieldVector3D<RealFieldElement>(this.q1.multiply((RealFieldElement)inverse), this.q2.multiply((RealFieldElement)inverse), this.q3.multiply((RealFieldElement)inverse));
    }

    public T getAngle() {
        if (this.q0.getReal() < -0.1 || this.q0.getReal() > 0.1) {
            return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(this.q1)).add(this.q2.multiply(this.q2))).add(this.q3.multiply(this.q3))).sqrt()).asin()).multiply(2));
        }
        if (this.q0.getReal() < 0.0) {
            return (T)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.negate()).acos()).multiply(2));
        }
        return (T)((RealFieldElement)((RealFieldElement)this.q0.acos()).multiply(2));
    }

    @Deprecated
    public T[] getAngles(RotationOrder order) throws CardanEulerSingularityException {
        return this.getAngles(order, RotationConvention.VECTOR_OPERATOR);
    }

    public T[] getAngles(RotationOrder order, RotationConvention convention) throws CardanEulerSingularityException {
        if (convention == RotationConvention.VECTOR_OPERATOR) {
            if (order == RotationOrder.XYZ) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(0.0, 0.0, 1.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(1.0, 0.0, 0.0));
                if (v2.getZ().getReal() < -0.9999999999 || v2.getZ().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(true);
                }
                return this.buildArray((RealFieldElement)((RealFieldElement)v1.getY().negate()).atan2(v1.getZ()), (RealFieldElement)v2.getZ().asin(), (RealFieldElement)((RealFieldElement)v2.getY().negate()).atan2(v2.getX()));
            }
            if (order == RotationOrder.XZY) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(0.0, 1.0, 0.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(1.0, 0.0, 0.0));
                if (v2.getY().getReal() < -0.9999999999 || v2.getY().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(true);
                }
                return this.buildArray((RealFieldElement)v1.getZ().atan2(v1.getY()), (RealFieldElement)((RealFieldElement)v2.getY().asin()).negate(), (RealFieldElement)v2.getZ().atan2(v2.getX()));
            }
            if (order == RotationOrder.YXZ) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(0.0, 0.0, 1.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(0.0, 1.0, 0.0));
                if (v2.getZ().getReal() < -0.9999999999 || v2.getZ().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(true);
                }
                return this.buildArray((RealFieldElement)v1.getX().atan2(v1.getZ()), (RealFieldElement)((RealFieldElement)v2.getZ().asin()).negate(), (RealFieldElement)v2.getX().atan2(v2.getY()));
            }
            if (order == RotationOrder.YZX) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(1.0, 0.0, 0.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(0.0, 1.0, 0.0));
                if (v2.getX().getReal() < -0.9999999999 || v2.getX().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(true);
                }
                return this.buildArray((RealFieldElement)((RealFieldElement)v1.getZ().negate()).atan2(v1.getX()), (RealFieldElement)v2.getX().asin(), (RealFieldElement)((RealFieldElement)v2.getZ().negate()).atan2(v2.getY()));
            }
            if (order == RotationOrder.ZXY) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(0.0, 1.0, 0.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(0.0, 0.0, 1.0));
                if (v2.getY().getReal() < -0.9999999999 || v2.getY().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(true);
                }
                return this.buildArray((RealFieldElement)((RealFieldElement)v1.getX().negate()).atan2(v1.getY()), (RealFieldElement)v2.getY().asin(), (RealFieldElement)((RealFieldElement)v2.getX().negate()).atan2(v2.getZ()));
            }
            if (order == RotationOrder.ZYX) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(1.0, 0.0, 0.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(0.0, 0.0, 1.0));
                if (v2.getX().getReal() < -0.9999999999 || v2.getX().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(true);
                }
                return this.buildArray((RealFieldElement)v1.getY().atan2(v1.getX()), (RealFieldElement)((RealFieldElement)v2.getX().asin()).negate(), (RealFieldElement)v2.getY().atan2(v2.getZ()));
            }
            if (order == RotationOrder.XYX) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(1.0, 0.0, 0.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(1.0, 0.0, 0.0));
                if (v2.getX().getReal() < -0.9999999999 || v2.getX().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(false);
                }
                return this.buildArray((RealFieldElement)v1.getY().atan2(v1.getZ().negate()), (RealFieldElement)v2.getX().acos(), (RealFieldElement)v2.getY().atan2(v2.getZ()));
            }
            if (order == RotationOrder.XZX) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(1.0, 0.0, 0.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(1.0, 0.0, 0.0));
                if (v2.getX().getReal() < -0.9999999999 || v2.getX().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(false);
                }
                return this.buildArray((RealFieldElement)v1.getZ().atan2(v1.getY()), (RealFieldElement)v2.getX().acos(), (RealFieldElement)v2.getZ().atan2(v2.getY().negate()));
            }
            if (order == RotationOrder.YXY) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(0.0, 1.0, 0.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(0.0, 1.0, 0.0));
                if (v2.getY().getReal() < -0.9999999999 || v2.getY().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(false);
                }
                return this.buildArray((RealFieldElement)v1.getX().atan2(v1.getZ()), (RealFieldElement)v2.getY().acos(), (RealFieldElement)v2.getX().atan2(v2.getZ().negate()));
            }
            if (order == RotationOrder.YZY) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(0.0, 1.0, 0.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(0.0, 1.0, 0.0));
                if (v2.getY().getReal() < -0.9999999999 || v2.getY().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(false);
                }
                return this.buildArray((RealFieldElement)v1.getZ().atan2(v1.getX().negate()), (RealFieldElement)v2.getY().acos(), (RealFieldElement)v2.getZ().atan2(v2.getX()));
            }
            if (order == RotationOrder.ZXZ) {
                FieldVector3D<T> v1 = this.applyTo(this.vector(0.0, 0.0, 1.0));
                FieldVector3D<T> v2 = this.applyInverseTo(this.vector(0.0, 0.0, 1.0));
                if (v2.getZ().getReal() < -0.9999999999 || v2.getZ().getReal() > 0.9999999999) {
                    throw new CardanEulerSingularityException(false);
                }
                return this.buildArray((RealFieldElement)v1.getX().atan2(v1.getY().negate()), (RealFieldElement)v2.getZ().acos(), (RealFieldElement)v2.getX().atan2(v2.getY()));
            }
            FieldVector3D<T> v1 = this.applyTo(this.vector(0.0, 0.0, 1.0));
            FieldVector3D<T> v2 = this.applyInverseTo(this.vector(0.0, 0.0, 1.0));
            if (v2.getZ().getReal() < -0.9999999999 || v2.getZ().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(false);
            }
            return this.buildArray((RealFieldElement)v1.getY().atan2(v1.getX()), (RealFieldElement)v2.getZ().acos(), (RealFieldElement)v2.getY().atan2(v2.getX().negate()));
        }
        if (order == RotationOrder.XYZ) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_I);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_K);
            if (v2.getX().getReal() < -0.9999999999 || v2.getX().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(true);
            }
            return this.buildArray((RealFieldElement)((RealFieldElement)v2.getY().negate()).atan2(v2.getZ()), (RealFieldElement)v2.getX().asin(), (RealFieldElement)((RealFieldElement)v1.getY().negate()).atan2(v1.getX()));
        }
        if (order == RotationOrder.XZY) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_I);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_J);
            if (v2.getX().getReal() < -0.9999999999 || v2.getX().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(true);
            }
            return this.buildArray((RealFieldElement)v2.getZ().atan2(v2.getY()), (RealFieldElement)((RealFieldElement)v2.getX().asin()).negate(), (RealFieldElement)v1.getZ().atan2(v1.getX()));
        }
        if (order == RotationOrder.YXZ) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_J);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_K);
            if (v2.getY().getReal() < -0.9999999999 || v2.getY().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(true);
            }
            return this.buildArray((RealFieldElement)v2.getX().atan2(v2.getZ()), (RealFieldElement)((RealFieldElement)v2.getY().asin()).negate(), (RealFieldElement)v1.getX().atan2(v1.getY()));
        }
        if (order == RotationOrder.YZX) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_J);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_I);
            if (v2.getY().getReal() < -0.9999999999 || v2.getY().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(true);
            }
            return this.buildArray((RealFieldElement)((RealFieldElement)v2.getZ().negate()).atan2(v2.getX()), (RealFieldElement)v2.getY().asin(), (RealFieldElement)((RealFieldElement)v1.getZ().negate()).atan2(v1.getY()));
        }
        if (order == RotationOrder.ZXY) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_K);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_J);
            if (v2.getZ().getReal() < -0.9999999999 || v2.getZ().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(true);
            }
            return this.buildArray((RealFieldElement)((RealFieldElement)v2.getX().negate()).atan2(v2.getY()), (RealFieldElement)v2.getZ().asin(), (RealFieldElement)((RealFieldElement)v1.getX().negate()).atan2(v1.getZ()));
        }
        if (order == RotationOrder.ZYX) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_K);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_I);
            if (v2.getZ().getReal() < -0.9999999999 || v2.getZ().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(true);
            }
            return this.buildArray((RealFieldElement)v2.getY().atan2(v2.getX()), (RealFieldElement)((RealFieldElement)v2.getZ().asin()).negate(), (RealFieldElement)v1.getY().atan2(v1.getZ()));
        }
        if (order == RotationOrder.XYX) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_I);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_I);
            if (v2.getX().getReal() < -0.9999999999 || v2.getX().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(false);
            }
            return this.buildArray((RealFieldElement)v2.getY().atan2(v2.getZ().negate()), (RealFieldElement)v2.getX().acos(), (RealFieldElement)v1.getY().atan2(v1.getZ()));
        }
        if (order == RotationOrder.XZX) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_I);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_I);
            if (v2.getX().getReal() < -0.9999999999 || v2.getX().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(false);
            }
            return this.buildArray((RealFieldElement)v2.getZ().atan2(v2.getY()), (RealFieldElement)v2.getX().acos(), (RealFieldElement)v1.getZ().atan2(v1.getY().negate()));
        }
        if (order == RotationOrder.YXY) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_J);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_J);
            if (v2.getY().getReal() < -0.9999999999 || v2.getY().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(false);
            }
            return this.buildArray((RealFieldElement)v2.getX().atan2(v2.getZ()), (RealFieldElement)v2.getY().acos(), (RealFieldElement)v1.getX().atan2(v1.getZ().negate()));
        }
        if (order == RotationOrder.YZY) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_J);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_J);
            if (v2.getY().getReal() < -0.9999999999 || v2.getY().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(false);
            }
            return this.buildArray((RealFieldElement)v2.getZ().atan2(v2.getX().negate()), (RealFieldElement)v2.getY().acos(), (RealFieldElement)v1.getZ().atan2(v1.getX()));
        }
        if (order == RotationOrder.ZXZ) {
            FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_K);
            FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_K);
            if (v2.getZ().getReal() < -0.9999999999 || v2.getZ().getReal() > 0.9999999999) {
                throw new CardanEulerSingularityException(false);
            }
            return this.buildArray((RealFieldElement)v2.getX().atan2(v2.getY().negate()), (RealFieldElement)v2.getZ().acos(), (RealFieldElement)v1.getX().atan2(v1.getY()));
        }
        FieldVector3D<T> v1 = this.applyTo(Vector3D.PLUS_K);
        FieldVector3D<T> v2 = this.applyInverseTo(Vector3D.PLUS_K);
        if (v2.getZ().getReal() < -0.9999999999 || v2.getZ().getReal() > 0.9999999999) {
            throw new CardanEulerSingularityException(false);
        }
        return this.buildArray((RealFieldElement)v2.getY().atan2(v2.getX()), (RealFieldElement)v2.getZ().acos(), (RealFieldElement)v1.getY().atan2(v1.getX().negate()));
    }

    private T[] buildArray(T a0, T a1, T a2) {
        RealFieldElement[] array = (RealFieldElement[])MathArrays.buildArray(a0.getField(), 3);
        array[0] = a0;
        array[1] = a1;
        array[2] = a2;
        return array;
    }

    private FieldVector3D<T> vector(double x, double y, double z) {
        RealFieldElement zero = (RealFieldElement)this.q0.getField().getZero();
        return new FieldVector3D<RealFieldElement>((RealFieldElement)zero.add(x), (RealFieldElement)zero.add(y), (RealFieldElement)zero.add(z));
    }

    public T[][] getMatrix() {
        RealFieldElement q0q0 = (RealFieldElement)this.q0.multiply(this.q0);
        RealFieldElement q0q1 = (RealFieldElement)this.q0.multiply(this.q1);
        RealFieldElement q0q2 = (RealFieldElement)this.q0.multiply(this.q2);
        RealFieldElement q0q3 = (RealFieldElement)this.q0.multiply(this.q3);
        RealFieldElement q1q1 = (RealFieldElement)this.q1.multiply(this.q1);
        RealFieldElement q1q2 = (RealFieldElement)this.q1.multiply(this.q2);
        RealFieldElement q1q3 = (RealFieldElement)this.q1.multiply(this.q3);
        RealFieldElement q2q2 = (RealFieldElement)this.q2.multiply(this.q2);
        RealFieldElement q2q3 = (RealFieldElement)this.q2.multiply(this.q3);
        RealFieldElement q3q3 = (RealFieldElement)this.q3.multiply(this.q3);
        RealFieldElement[][] m = (RealFieldElement[][])MathArrays.buildArray(this.q0.getField(), 3, 3);
        m[0][0] = (RealFieldElement)((RealFieldElement)q0q0.add(q1q1).multiply(2)).subtract(1.0);
        m[1][0] = (RealFieldElement)q1q2.subtract(q0q3).multiply(2);
        m[2][0] = (RealFieldElement)q1q3.add(q0q2).multiply(2);
        m[0][1] = (RealFieldElement)q1q2.add(q0q3).multiply(2);
        m[1][1] = (RealFieldElement)((RealFieldElement)q0q0.add(q2q2).multiply(2)).subtract(1.0);
        m[2][1] = (RealFieldElement)q2q3.subtract(q0q1).multiply(2);
        m[0][2] = (RealFieldElement)q1q3.subtract(q0q2).multiply(2);
        m[1][2] = (RealFieldElement)q2q3.add(q0q1).multiply(2);
        m[2][2] = (RealFieldElement)((RealFieldElement)q0q0.add(q3q3).multiply(2)).subtract(1.0);
        return m;
    }

    public Rotation toRotation() {
        return new Rotation(this.q0.getReal(), this.q1.getReal(), this.q2.getReal(), this.q3.getReal(), false);
    }

    public FieldVector3D<T> applyTo(FieldVector3D<T> u) {
        T x = u.getX();
        T y = u.getY();
        T z = u.getZ();
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        return new FieldVector3D<RealFieldElement>((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)x.multiply(this.q0)).subtract(((RealFieldElement)this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(s.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)y.multiply(this.q0)).subtract(((RealFieldElement)this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(s.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)z.multiply(this.q0)).subtract(((RealFieldElement)this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(s.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyTo(Vector3D u) {
        double x = u.getX();
        double y = u.getY();
        double z = u.getZ();
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        return new FieldVector3D<RealFieldElement>((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)this.q0.multiply(x)).subtract(((RealFieldElement)this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(s.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)this.q0.multiply(y)).subtract(((RealFieldElement)this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(s.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)this.q0.multiply(z)).subtract(((RealFieldElement)this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(s.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public void applyTo(T[] in, T[] out) {
        T x = in[0];
        T y = in[1];
        T z = in[2];
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        out[0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)x.multiply(this.q0)).subtract(((RealFieldElement)this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(s.multiply(this.q1))).multiply(2)).subtract(x);
        out[1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)y.multiply(this.q0)).subtract(((RealFieldElement)this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(s.multiply(this.q2))).multiply(2)).subtract(y);
        out[2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)z.multiply(this.q0)).subtract(((RealFieldElement)this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(s.multiply(this.q3))).multiply(2)).subtract(z);
    }

    public void applyTo(double[] in, T[] out) {
        double x = in[0];
        double y = in[1];
        double z = in[2];
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        out[0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)this.q0.multiply(x)).subtract(((RealFieldElement)this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(s.multiply(this.q1))).multiply(2)).subtract(x);
        out[1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)this.q0.multiply(y)).subtract(((RealFieldElement)this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(s.multiply(this.q2))).multiply(2)).subtract(y);
        out[2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(((RealFieldElement)this.q0.multiply(z)).subtract(((RealFieldElement)this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(s.multiply(this.q3))).multiply(2)).subtract(z);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyTo(Rotation r, FieldVector3D<T> u) {
        T x = u.getX();
        T y = u.getY();
        T z = u.getZ();
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)x.multiply(r.getQ1())).add(y.multiply(r.getQ2()))).add(z.multiply(r.getQ3()));
        return new FieldVector3D<RealFieldElement>((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)x.multiply(r.getQ0())).subtract(((RealFieldElement)z.multiply(r.getQ2())).subtract(y.multiply(r.getQ3())))).multiply(r.getQ0())).add(s.multiply(r.getQ1()))).multiply(2)).subtract(x), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)y.multiply(r.getQ0())).subtract(((RealFieldElement)x.multiply(r.getQ3())).subtract(z.multiply(r.getQ1())))).multiply(r.getQ0())).add(s.multiply(r.getQ2()))).multiply(2)).subtract(y), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)z.multiply(r.getQ0())).subtract(((RealFieldElement)y.multiply(r.getQ1())).subtract(x.multiply(r.getQ2())))).multiply(r.getQ0())).add(s.multiply(r.getQ3()))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyInverseTo(FieldVector3D<T> u) {
        RealFieldElement x = u.getX();
        RealFieldElement y = u.getY();
        RealFieldElement z = u.getZ();
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        RealFieldElement m0 = (RealFieldElement)this.q0.negate();
        return new FieldVector3D<RealFieldElement>((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(x.multiply((RealFieldElement)m0).subtract(((RealFieldElement)this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(s.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(y.multiply((RealFieldElement)m0).subtract(((RealFieldElement)this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(s.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(z.multiply((RealFieldElement)m0).subtract(((RealFieldElement)this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(s.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public FieldVector3D<T> applyInverseTo(Vector3D u) {
        double x = u.getX();
        double y = u.getY();
        double z = u.getZ();
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        RealFieldElement m0 = (RealFieldElement)this.q0.negate();
        return new FieldVector3D<RealFieldElement>((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(((RealFieldElement)m0.multiply(x)).subtract(((RealFieldElement)this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(s.multiply(this.q1))).multiply(2)).subtract(x), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(((RealFieldElement)m0.multiply(y)).subtract(((RealFieldElement)this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(s.multiply(this.q2))).multiply(2)).subtract(y), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(((RealFieldElement)m0.multiply(z)).subtract(((RealFieldElement)this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(s.multiply(this.q3))).multiply(2)).subtract(z));
    }

    public void applyInverseTo(T[] in, T[] out) {
        RealFieldElement x = in[0];
        RealFieldElement y = in[1];
        RealFieldElement z = in[2];
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        RealFieldElement m0 = (RealFieldElement)this.q0.negate();
        out[0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(x.multiply((RealFieldElement)m0).subtract(((RealFieldElement)this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(s.multiply(this.q1))).multiply(2)).subtract(x);
        out[1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(y.multiply((RealFieldElement)m0).subtract(((RealFieldElement)this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(s.multiply(this.q2))).multiply(2)).subtract(y);
        out[2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(z.multiply((RealFieldElement)m0).subtract(((RealFieldElement)this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(s.multiply(this.q3))).multiply(2)).subtract(z);
    }

    public void applyInverseTo(double[] in, T[] out) {
        double x = in[0];
        double y = in[1];
        double z = in[2];
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(x)).add(this.q2.multiply(y))).add(this.q3.multiply(z));
        RealFieldElement m0 = (RealFieldElement)this.q0.negate();
        out[0] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(((RealFieldElement)m0.multiply(x)).subtract(((RealFieldElement)this.q2.multiply(z)).subtract(this.q3.multiply(y))))).add(s.multiply(this.q1))).multiply(2)).subtract(x);
        out[1] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(((RealFieldElement)m0.multiply(y)).subtract(((RealFieldElement)this.q3.multiply(x)).subtract(this.q1.multiply(z))))).add(s.multiply(this.q2))).multiply(2)).subtract(y);
        out[2] = (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)m0.multiply(((RealFieldElement)m0.multiply(z)).subtract(((RealFieldElement)this.q1.multiply(y)).subtract(this.q2.multiply(x))))).add(s.multiply(this.q3))).multiply(2)).subtract(z);
    }

    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyInverseTo(Rotation r, FieldVector3D<T> u) {
        T x = u.getX();
        T y = u.getY();
        T z = u.getZ();
        RealFieldElement s = (RealFieldElement)((RealFieldElement)((RealFieldElement)x.multiply(r.getQ1())).add(y.multiply(r.getQ2()))).add(z.multiply(r.getQ3()));
        double m0 = -r.getQ0();
        return new FieldVector3D<RealFieldElement>((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)x.multiply(m0)).subtract(((RealFieldElement)z.multiply(r.getQ2())).subtract(y.multiply(r.getQ3())))).multiply(m0)).add(s.multiply(r.getQ1()))).multiply(2)).subtract(x), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)y.multiply(m0)).subtract(((RealFieldElement)x.multiply(r.getQ3())).subtract(z.multiply(r.getQ1())))).multiply(m0)).add(s.multiply(r.getQ2()))).multiply(2)).subtract(y), (RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)((RealFieldElement)z.multiply(m0)).subtract(((RealFieldElement)y.multiply(r.getQ1())).subtract(x.multiply(r.getQ2())))).multiply(m0)).add(s.multiply(r.getQ3()))).multiply(2)).subtract(z));
    }

    public FieldRotation<T> applyTo(FieldRotation<T> r) {
        return this.compose(r, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> compose(FieldRotation<T> r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? this.composeInternal(r) : super.composeInternal(this);
    }

    private FieldRotation<T> composeInternal(FieldRotation<T> r) {
        return new FieldRotation<RealFieldElement>((RealFieldElement)((RealFieldElement)r.q0.multiply(this.q0)).subtract(((RealFieldElement)((RealFieldElement)r.q1.multiply(this.q1)).add(r.q2.multiply(this.q2))).add(r.q3.multiply(this.q3))), (RealFieldElement)((RealFieldElement)((RealFieldElement)r.q1.multiply(this.q0)).add(r.q0.multiply(this.q1))).add(((RealFieldElement)r.q2.multiply(this.q3)).subtract(r.q3.multiply(this.q2))), (RealFieldElement)((RealFieldElement)((RealFieldElement)r.q2.multiply(this.q0)).add(r.q0.multiply(this.q2))).add(((RealFieldElement)r.q3.multiply(this.q1)).subtract(r.q1.multiply(this.q3))), (RealFieldElement)((RealFieldElement)((RealFieldElement)r.q3.multiply(this.q0)).add(r.q0.multiply(this.q3))).add(((RealFieldElement)r.q1.multiply(this.q2)).subtract(r.q2.multiply(this.q1))), false);
    }

    public FieldRotation<T> applyTo(Rotation r) {
        return this.compose(r, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> compose(Rotation r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? this.composeInternal(r) : FieldRotation.applyTo(r, this);
    }

    private FieldRotation<T> composeInternal(Rotation r) {
        return new FieldRotation<RealFieldElement>((RealFieldElement)((RealFieldElement)this.q0.multiply(r.getQ0())).subtract(((RealFieldElement)((RealFieldElement)this.q1.multiply(r.getQ1())).add(this.q2.multiply(r.getQ2()))).add(this.q3.multiply(r.getQ3()))), (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(r.getQ1())).add(this.q1.multiply(r.getQ0()))).add(((RealFieldElement)this.q3.multiply(r.getQ2())).subtract(this.q2.multiply(r.getQ3()))), (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(r.getQ2())).add(this.q2.multiply(r.getQ0()))).add(((RealFieldElement)this.q1.multiply(r.getQ3())).subtract(this.q3.multiply(r.getQ1()))), (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(r.getQ3())).add(this.q3.multiply(r.getQ0()))).add(((RealFieldElement)this.q2.multiply(r.getQ1())).subtract(this.q1.multiply(r.getQ2()))), false);
    }

    public static <T extends RealFieldElement<T>> FieldRotation<T> applyTo(Rotation r1, FieldRotation<T> rInner) {
        return new FieldRotation<RealFieldElement>((RealFieldElement)((RealFieldElement)rInner.q0.multiply(r1.getQ0())).subtract(((RealFieldElement)((RealFieldElement)rInner.q1.multiply(r1.getQ1())).add(rInner.q2.multiply(r1.getQ2()))).add(rInner.q3.multiply(r1.getQ3()))), (RealFieldElement)((RealFieldElement)((RealFieldElement)rInner.q1.multiply(r1.getQ0())).add(rInner.q0.multiply(r1.getQ1()))).add(((RealFieldElement)rInner.q2.multiply(r1.getQ3())).subtract(rInner.q3.multiply(r1.getQ2()))), (RealFieldElement)((RealFieldElement)((RealFieldElement)rInner.q2.multiply(r1.getQ0())).add(rInner.q0.multiply(r1.getQ2()))).add(((RealFieldElement)rInner.q3.multiply(r1.getQ1())).subtract(rInner.q1.multiply(r1.getQ3()))), (RealFieldElement)((RealFieldElement)((RealFieldElement)rInner.q3.multiply(r1.getQ0())).add(rInner.q0.multiply(r1.getQ3()))).add(((RealFieldElement)rInner.q1.multiply(r1.getQ2())).subtract(rInner.q2.multiply(r1.getQ1()))), false);
    }

    public FieldRotation<T> applyInverseTo(FieldRotation<T> r) {
        return this.composeInverse(r, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> composeInverse(FieldRotation<T> r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? this.composeInverseInternal(r) : super.composeInternal(this.revert());
    }

    private FieldRotation<T> composeInverseInternal(FieldRotation<T> r) {
        return new FieldRotation<RealFieldElement>((RealFieldElement)((RealFieldElement)((RealFieldElement)r.q0.multiply(this.q0)).add(((RealFieldElement)((RealFieldElement)r.q1.multiply(this.q1)).add(r.q2.multiply(this.q2))).add(r.q3.multiply(this.q3)))).negate(), (RealFieldElement)((RealFieldElement)((RealFieldElement)r.q0.multiply(this.q1)).add(((RealFieldElement)r.q2.multiply(this.q3)).subtract(r.q3.multiply(this.q2)))).subtract(r.q1.multiply(this.q0)), (RealFieldElement)((RealFieldElement)((RealFieldElement)r.q0.multiply(this.q2)).add(((RealFieldElement)r.q3.multiply(this.q1)).subtract(r.q1.multiply(this.q3)))).subtract(r.q2.multiply(this.q0)), (RealFieldElement)((RealFieldElement)((RealFieldElement)r.q0.multiply(this.q3)).add(((RealFieldElement)r.q1.multiply(this.q2)).subtract(r.q2.multiply(this.q1)))).subtract(r.q3.multiply(this.q0)), false);
    }

    public FieldRotation<T> applyInverseTo(Rotation r) {
        return this.composeInverse(r, RotationConvention.VECTOR_OPERATOR);
    }

    public FieldRotation<T> composeInverse(Rotation r, RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? this.composeInverseInternal(r) : FieldRotation.applyTo(r, this.revert());
    }

    private FieldRotation<T> composeInverseInternal(Rotation r) {
        return new FieldRotation<RealFieldElement>((RealFieldElement)((RealFieldElement)((RealFieldElement)this.q0.multiply(r.getQ0())).add(((RealFieldElement)((RealFieldElement)this.q1.multiply(r.getQ1())).add(this.q2.multiply(r.getQ2()))).add(this.q3.multiply(r.getQ3())))).negate(), (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q1.multiply(r.getQ0())).add(((RealFieldElement)this.q3.multiply(r.getQ2())).subtract(this.q2.multiply(r.getQ3())))).subtract(this.q0.multiply(r.getQ1())), (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q2.multiply(r.getQ0())).add(((RealFieldElement)this.q1.multiply(r.getQ3())).subtract(this.q3.multiply(r.getQ1())))).subtract(this.q0.multiply(r.getQ2())), (RealFieldElement)((RealFieldElement)((RealFieldElement)this.q3.multiply(r.getQ0())).add(((RealFieldElement)this.q2.multiply(r.getQ1())).subtract(this.q1.multiply(r.getQ2())))).subtract(this.q0.multiply(r.getQ3())), false);
    }

    public static <T extends RealFieldElement<T>> FieldRotation<T> applyInverseTo(Rotation rOuter, FieldRotation<T> rInner) {
        return new FieldRotation<RealFieldElement>((RealFieldElement)((RealFieldElement)((RealFieldElement)rInner.q0.multiply(rOuter.getQ0())).add(((RealFieldElement)((RealFieldElement)rInner.q1.multiply(rOuter.getQ1())).add(rInner.q2.multiply(rOuter.getQ2()))).add(rInner.q3.multiply(rOuter.getQ3())))).negate(), (RealFieldElement)((RealFieldElement)((RealFieldElement)rInner.q0.multiply(rOuter.getQ1())).add(((RealFieldElement)rInner.q2.multiply(rOuter.getQ3())).subtract(rInner.q3.multiply(rOuter.getQ2())))).subtract(rInner.q1.multiply(rOuter.getQ0())), (RealFieldElement)((RealFieldElement)((RealFieldElement)rInner.q0.multiply(rOuter.getQ2())).add(((RealFieldElement)rInner.q3.multiply(rOuter.getQ1())).subtract(rInner.q1.multiply(rOuter.getQ3())))).subtract(rInner.q2.multiply(rOuter.getQ0())), (RealFieldElement)((RealFieldElement)((RealFieldElement)rInner.q0.multiply(rOuter.getQ3())).add(((RealFieldElement)rInner.q1.multiply(rOuter.getQ2())).subtract(rInner.q2.multiply(rOuter.getQ1())))).subtract(rInner.q3.multiply(rOuter.getQ0())), false);
    }

    private T[][] orthogonalizeMatrix(T[][] m, double threshold) throws NotARotationMatrixException {
        RealFieldElement x00 = m[0][0];
        RealFieldElement x01 = m[0][1];
        RealFieldElement x02 = m[0][2];
        RealFieldElement x10 = m[1][0];
        RealFieldElement x11 = m[1][1];
        RealFieldElement x12 = m[1][2];
        RealFieldElement x20 = m[2][0];
        RealFieldElement x21 = m[2][1];
        RealFieldElement x22 = m[2][2];
        double fn = 0.0;
        RealFieldElement[][] o = (RealFieldElement[][])MathArrays.buildArray(m[0][0].getField(), 3, 3);
        int i = 0;
        while (++i < 11) {
            double corr22;
            double corr21;
            double corr20;
            double corr12;
            double corr11;
            double corr10;
            double corr02;
            double corr01;
            RealFieldElement mx00 = (RealFieldElement)((RealFieldElement)((RealFieldElement)m[0][0].multiply(x00)).add(m[1][0].multiply(x10))).add(m[2][0].multiply(x20));
            RealFieldElement mx10 = (RealFieldElement)((RealFieldElement)((RealFieldElement)m[0][1].multiply(x00)).add(m[1][1].multiply(x10))).add(m[2][1].multiply(x20));
            RealFieldElement mx20 = (RealFieldElement)((RealFieldElement)((RealFieldElement)m[0][2].multiply(x00)).add(m[1][2].multiply(x10))).add(m[2][2].multiply(x20));
            RealFieldElement mx01 = (RealFieldElement)((RealFieldElement)((RealFieldElement)m[0][0].multiply(x01)).add(m[1][0].multiply(x11))).add(m[2][0].multiply(x21));
            RealFieldElement mx11 = (RealFieldElement)((RealFieldElement)((RealFieldElement)m[0][1].multiply(x01)).add(m[1][1].multiply(x11))).add(m[2][1].multiply(x21));
            RealFieldElement mx21 = (RealFieldElement)((RealFieldElement)((RealFieldElement)m[0][2].multiply(x01)).add(m[1][2].multiply(x11))).add(m[2][2].multiply(x21));
            RealFieldElement mx02 = (RealFieldElement)((RealFieldElement)((RealFieldElement)m[0][0].multiply(x02)).add(m[1][0].multiply(x12))).add(m[2][0].multiply(x22));
            RealFieldElement mx12 = (RealFieldElement)((RealFieldElement)((RealFieldElement)m[0][1].multiply(x02)).add(m[1][1].multiply(x12))).add(m[2][1].multiply(x22));
            RealFieldElement mx22 = (RealFieldElement)((RealFieldElement)((RealFieldElement)m[0][2].multiply(x02)).add(m[1][2].multiply(x12))).add(m[2][2].multiply(x22));
            o[0][0] = (RealFieldElement)x00.subtract(((RealFieldElement)x00.multiply((RealFieldElement)mx00).add(x01.multiply((RealFieldElement)mx10)).add(x02.multiply((RealFieldElement)mx20)).subtract(m[0][0])).multiply(0.5));
            o[0][1] = (RealFieldElement)x01.subtract(((RealFieldElement)x00.multiply((RealFieldElement)mx01).add(x01.multiply((RealFieldElement)mx11)).add(x02.multiply((RealFieldElement)mx21)).subtract(m[0][1])).multiply(0.5));
            o[0][2] = (RealFieldElement)x02.subtract(((RealFieldElement)x00.multiply((RealFieldElement)mx02).add(x01.multiply((RealFieldElement)mx12)).add(x02.multiply((RealFieldElement)mx22)).subtract(m[0][2])).multiply(0.5));
            o[1][0] = (RealFieldElement)x10.subtract(((RealFieldElement)x10.multiply((RealFieldElement)mx00).add(x11.multiply((RealFieldElement)mx10)).add(x12.multiply((RealFieldElement)mx20)).subtract(m[1][0])).multiply(0.5));
            o[1][1] = (RealFieldElement)x11.subtract(((RealFieldElement)x10.multiply((RealFieldElement)mx01).add(x11.multiply((RealFieldElement)mx11)).add(x12.multiply((RealFieldElement)mx21)).subtract(m[1][1])).multiply(0.5));
            o[1][2] = (RealFieldElement)x12.subtract(((RealFieldElement)x10.multiply((RealFieldElement)mx02).add(x11.multiply((RealFieldElement)mx12)).add(x12.multiply((RealFieldElement)mx22)).subtract(m[1][2])).multiply(0.5));
            o[2][0] = (RealFieldElement)x20.subtract(((RealFieldElement)x20.multiply((RealFieldElement)mx00).add(x21.multiply((RealFieldElement)mx10)).add(x22.multiply((RealFieldElement)mx20)).subtract(m[2][0])).multiply(0.5));
            o[2][1] = (RealFieldElement)x21.subtract(((RealFieldElement)x20.multiply((RealFieldElement)mx01).add(x21.multiply((RealFieldElement)mx11)).add(x22.multiply((RealFieldElement)mx21)).subtract(m[2][1])).multiply(0.5));
            o[2][2] = (RealFieldElement)x22.subtract(((RealFieldElement)x20.multiply((RealFieldElement)mx02).add(x21.multiply((RealFieldElement)mx12)).add(x22.multiply((RealFieldElement)mx22)).subtract(m[2][2])).multiply(0.5));
            double corr00 = o[0][0].getReal() - m[0][0].getReal();
            double fn1 = corr00 * corr00 + (corr01 = o[0][1].getReal() - m[0][1].getReal()) * corr01 + (corr02 = o[0][2].getReal() - m[0][2].getReal()) * corr02 + (corr10 = o[1][0].getReal() - m[1][0].getReal()) * corr10 + (corr11 = o[1][1].getReal() - m[1][1].getReal()) * corr11 + (corr12 = o[1][2].getReal() - m[1][2].getReal()) * corr12 + (corr20 = o[2][0].getReal() - m[2][0].getReal()) * corr20 + (corr21 = o[2][1].getReal() - m[2][1].getReal()) * corr21 + (corr22 = o[2][2].getReal() - m[2][2].getReal()) * corr22;
            if (FastMath.abs(fn1 - fn) <= threshold) {
                return o;
            }
            x00 = o[0][0];
            x01 = o[0][1];
            x02 = o[0][2];
            x10 = o[1][0];
            x11 = o[1][1];
            x12 = o[1][2];
            x20 = o[2][0];
            x21 = o[2][1];
            x22 = o[2][2];
            fn = fn1;
        }
        throw new NotARotationMatrixException(LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX, i - 1);
    }

    public static <T extends RealFieldElement<T>> T distance(FieldRotation<T> r1, FieldRotation<T> r2) {
        return super.composeInverseInternal(r2).getAngle();
    }
}

