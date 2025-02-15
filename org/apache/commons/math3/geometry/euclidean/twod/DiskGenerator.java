/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.List;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.SupportBallGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DiskGenerator
implements SupportBallGenerator<Euclidean2D, Vector2D> {
    @Override
    public EnclosingBall<Euclidean2D, Vector2D> ballOnSupport(List<Vector2D> support) {
        if (support.size() < 1) {
            return new EnclosingBall((Point)Vector2D.ZERO, Double.NEGATIVE_INFINITY, (Point[])new Vector2D[0]);
        }
        Vector2D vA = support.get(0);
        if (support.size() < 2) {
            return new EnclosingBall((Point)vA, 0.0, (Point[])new Vector2D[]{vA});
        }
        Vector2D vB = support.get(1);
        if (support.size() < 3) {
            return new EnclosingBall((Point)new Vector2D(0.5, vA, 0.5, vB), 0.5 * vA.distance(vB), (Point[])new Vector2D[]{vA, vB});
        }
        Vector2D vC = support.get(2);
        BigFraction[] c2 = new BigFraction[]{new BigFraction(vA.getX()), new BigFraction(vB.getX()), new BigFraction(vC.getX())};
        BigFraction[] c3 = new BigFraction[]{new BigFraction(vA.getY()), new BigFraction(vB.getY()), new BigFraction(vC.getY())};
        BigFraction[] c1 = new BigFraction[]{c2[0].multiply(c2[0]).add(c3[0].multiply(c3[0])), c2[1].multiply(c2[1]).add(c3[1].multiply(c3[1])), c2[2].multiply(c2[2]).add(c3[2].multiply(c3[2]))};
        BigFraction twoM11 = this.minor(c2, c3).multiply(2);
        BigFraction m12 = this.minor(c1, c3);
        BigFraction m13 = this.minor(c1, c2);
        BigFraction centerX = m12.divide(twoM11);
        BigFraction centerY = m13.divide(twoM11).negate();
        BigFraction dx = c2[0].subtract(centerX);
        BigFraction dy = c3[0].subtract(centerY);
        BigFraction r2 = dx.multiply(dx).add(dy.multiply(dy));
        return new EnclosingBall((Point)new Vector2D(centerX.doubleValue(), centerY.doubleValue()), FastMath.sqrt(r2.doubleValue()), (Point[])new Vector2D[]{vA, vB, vC});
    }

    private BigFraction minor(BigFraction[] c1, BigFraction[] c2) {
        return c2[0].multiply(c1[2].subtract(c1[1])).add(c2[1].multiply(c1[0].subtract(c1[2]))).add(c2[2].multiply(c1[1].subtract(c1[0])));
    }
}

