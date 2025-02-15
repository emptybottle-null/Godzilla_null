/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.twod;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.spherical.twod.Sphere2D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class S2Point
implements Point<Sphere2D> {
    public static final S2Point PLUS_I = new S2Point(0.0, 1.5707963267948966, Vector3D.PLUS_I);
    public static final S2Point PLUS_J = new S2Point(1.5707963267948966, 1.5707963267948966, Vector3D.PLUS_J);
    public static final S2Point PLUS_K = new S2Point(0.0, 0.0, Vector3D.PLUS_K);
    public static final S2Point MINUS_I = new S2Point(Math.PI, 1.5707963267948966, Vector3D.MINUS_I);
    public static final S2Point MINUS_J = new S2Point(4.71238898038469, 1.5707963267948966, Vector3D.MINUS_J);
    public static final S2Point MINUS_K = new S2Point(0.0, Math.PI, Vector3D.MINUS_K);
    public static final S2Point NaN = new S2Point(Double.NaN, Double.NaN, Vector3D.NaN);
    private static final long serialVersionUID = 20131218L;
    private final double theta;
    private final double phi;
    private final Vector3D vector;

    public S2Point(double theta, double phi) throws OutOfRangeException {
        this(theta, phi, S2Point.vector(theta, phi));
    }

    public S2Point(Vector3D vector) throws MathArithmeticException {
        this(FastMath.atan2(vector.getY(), vector.getX()), Vector3D.angle(Vector3D.PLUS_K, vector), vector.normalize());
    }

    private S2Point(double theta, double phi, Vector3D vector) {
        this.theta = theta;
        this.phi = phi;
        this.vector = vector;
    }

    private static Vector3D vector(double theta, double phi) throws OutOfRangeException {
        if (phi < 0.0 || phi > Math.PI) {
            throw new OutOfRangeException(phi, (Number)0, Math.PI);
        }
        double cosTheta = FastMath.cos(theta);
        double sinTheta = FastMath.sin(theta);
        double cosPhi = FastMath.cos(phi);
        double sinPhi = FastMath.sin(phi);
        return new Vector3D(cosTheta * sinPhi, sinTheta * sinPhi, cosPhi);
    }

    public double getTheta() {
        return this.theta;
    }

    public double getPhi() {
        return this.phi;
    }

    public Vector3D getVector() {
        return this.vector;
    }

    @Override
    public Space getSpace() {
        return Sphere2D.getInstance();
    }

    @Override
    public boolean isNaN() {
        return Double.isNaN(this.theta) || Double.isNaN(this.phi);
    }

    public S2Point negate() {
        return new S2Point(-this.theta, Math.PI - this.phi, this.vector.negate());
    }

    @Override
    public double distance(Point<Sphere2D> point) {
        return S2Point.distance(this, (S2Point)point);
    }

    public static double distance(S2Point p1, S2Point p2) {
        return Vector3D.angle(p1.vector, p2.vector);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof S2Point) {
            S2Point rhs = (S2Point)other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return this.theta == rhs.theta && this.phi == rhs.phi;
        }
        return false;
    }

    public int hashCode() {
        if (this.isNaN()) {
            return 542;
        }
        return 134 * (37 * MathUtils.hash(this.theta) + MathUtils.hash(this.phi));
    }
}

