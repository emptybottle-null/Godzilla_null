/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.SupportBallGenerator;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.DiskGenerator;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SphereGenerator
implements SupportBallGenerator<Euclidean3D, Vector3D> {
    @Override
    public EnclosingBall<Euclidean3D, Vector3D> ballOnSupport(List<Vector3D> support) {
        if (support.size() < 1) {
            return new EnclosingBall((Point)Vector3D.ZERO, Double.NEGATIVE_INFINITY, (Point[])new Vector3D[0]);
        }
        Vector3D vA = support.get(0);
        if (support.size() < 2) {
            return new EnclosingBall((Point)vA, 0.0, (Point[])new Vector3D[]{vA});
        }
        Vector3D vB = support.get(1);
        if (support.size() < 3) {
            return new EnclosingBall((Point)new Vector3D(0.5, vA, 0.5, vB), 0.5 * vA.distance(vB), (Point[])new Vector3D[]{vA, vB});
        }
        Vector3D vC = support.get(2);
        if (support.size() < 4) {
            Plane p = new Plane(vA, vB, vC, 1.0E-10 * (vA.getNorm1() + vB.getNorm1() + vC.getNorm1()));
            EnclosingBall<Euclidean2D, Vector2D> disk = new DiskGenerator().ballOnSupport(Arrays.asList(p.toSubSpace(vA), p.toSubSpace(vB), p.toSubSpace(vC)));
            return new EnclosingBall((Point)p.toSpace(disk.getCenter()), disk.getRadius(), (Point[])new Vector3D[]{vA, vB, vC});
        }
        Vector3D vD = support.get(3);
        BigFraction[] c2 = new BigFraction[]{new BigFraction(vA.getX()), new BigFraction(vB.getX()), new BigFraction(vC.getX()), new BigFraction(vD.getX())};
        BigFraction[] c3 = new BigFraction[]{new BigFraction(vA.getY()), new BigFraction(vB.getY()), new BigFraction(vC.getY()), new BigFraction(vD.getY())};
        BigFraction[] c4 = new BigFraction[]{new BigFraction(vA.getZ()), new BigFraction(vB.getZ()), new BigFraction(vC.getZ()), new BigFraction(vD.getZ())};
        BigFraction[] c1 = new BigFraction[]{c2[0].multiply(c2[0]).add(c3[0].multiply(c3[0])).add(c4[0].multiply(c4[0])), c2[1].multiply(c2[1]).add(c3[1].multiply(c3[1])).add(c4[1].multiply(c4[1])), c2[2].multiply(c2[2]).add(c3[2].multiply(c3[2])).add(c4[2].multiply(c4[2])), c2[3].multiply(c2[3]).add(c3[3].multiply(c3[3])).add(c4[3].multiply(c4[3]))};
        BigFraction twoM11 = this.minor(c2, c3, c4).multiply(2);
        BigFraction m12 = this.minor(c1, c3, c4);
        BigFraction m13 = this.minor(c1, c2, c4);
        BigFraction m14 = this.minor(c1, c2, c3);
        BigFraction centerX = m12.divide(twoM11);
        BigFraction centerY = m13.divide(twoM11).negate();
        BigFraction centerZ = m14.divide(twoM11);
        BigFraction dx = c2[0].subtract(centerX);
        BigFraction dy = c3[0].subtract(centerY);
        BigFraction dz = c4[0].subtract(centerZ);
        BigFraction r2 = dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
        return new EnclosingBall((Point)new Vector3D(centerX.doubleValue(), centerY.doubleValue(), centerZ.doubleValue()), FastMath.sqrt(r2.doubleValue()), (Point[])new Vector3D[]{vA, vB, vC, vD});
    }

    private BigFraction minor(BigFraction[] c1, BigFraction[] c2, BigFraction[] c3) {
        return c2[0].multiply(c3[1]).multiply(c1[2].subtract(c1[3])).add(c2[0].multiply(c3[2]).multiply(c1[3].subtract(c1[1]))).add(c2[0].multiply(c3[3]).multiply(c1[1].subtract(c1[2]))).add(c2[1].multiply(c3[0]).multiply(c1[3].subtract(c1[2]))).add(c2[1].multiply(c3[2]).multiply(c1[0].subtract(c1[3]))).add(c2[1].multiply(c3[3]).multiply(c1[2].subtract(c1[0]))).add(c2[2].multiply(c3[0]).multiply(c1[1].subtract(c1[3]))).add(c2[2].multiply(c3[1]).multiply(c1[3].subtract(c1[0]))).add(c2[2].multiply(c3[3]).multiply(c1[0].subtract(c1[1]))).add(c2[3].multiply(c3[0]).multiply(c1[2].subtract(c1[1]))).add(c2[3].multiply(c3[1]).multiply(c1[0].subtract(c1[2]))).add(c2[3].multiply(c3[2]).multiply(c1[1].subtract(c1[0])));
    }
}

