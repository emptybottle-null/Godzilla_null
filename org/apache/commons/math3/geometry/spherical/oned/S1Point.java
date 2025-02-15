/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class S1Point
implements Point<Sphere1D> {
    public static final S1Point NaN = new S1Point(Double.NaN, Vector2D.NaN);
    private static final long serialVersionUID = 20131218L;
    private final double alpha;
    private final Vector2D vector;

    public S1Point(double alpha) {
        this(MathUtils.normalizeAngle(alpha, Math.PI), new Vector2D(FastMath.cos(alpha), FastMath.sin(alpha)));
    }

    private S1Point(double alpha, Vector2D vector) {
        this.alpha = alpha;
        this.vector = vector;
    }

    public double getAlpha() {
        return this.alpha;
    }

    public Vector2D getVector() {
        return this.vector;
    }

    @Override
    public Space getSpace() {
        return Sphere1D.getInstance();
    }

    @Override
    public boolean isNaN() {
        return Double.isNaN(this.alpha);
    }

    @Override
    public double distance(Point<Sphere1D> point) {
        return S1Point.distance(this, (S1Point)point);
    }

    public static double distance(S1Point p1, S1Point p2) {
        return Vector2D.angle(p1.vector, p2.vector);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof S1Point) {
            S1Point rhs = (S1Point)other;
            if (rhs.isNaN()) {
                return this.isNaN();
            }
            return this.alpha == rhs.alpha;
        }
        return false;
    }

    public int hashCode() {
        if (this.isNaN()) {
            return 542;
        }
        return 1759 * MathUtils.hash(this.alpha);
    }
}

