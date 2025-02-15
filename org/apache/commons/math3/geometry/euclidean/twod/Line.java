/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.twod;

import java.awt.geom.AffineTransform;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.SubLine;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Line
implements Hyperplane<Euclidean2D>,
Embedding<Euclidean2D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10;
    private double angle;
    private double cos;
    private double sin;
    private double originOffset;
    private final double tolerance;
    private Line reverse;

    public Line(Vector2D p1, Vector2D p2, double tolerance) {
        this.reset(p1, p2);
        this.tolerance = tolerance;
    }

    public Line(Vector2D p, double angle, double tolerance) {
        this.reset(p, angle);
        this.tolerance = tolerance;
    }

    private Line(double angle, double cos, double sin, double originOffset, double tolerance) {
        this.angle = angle;
        this.cos = cos;
        this.sin = sin;
        this.originOffset = originOffset;
        this.tolerance = tolerance;
        this.reverse = null;
    }

    @Deprecated
    public Line(Vector2D p1, Vector2D p2) {
        this(p1, p2, 1.0E-10);
    }

    @Deprecated
    public Line(Vector2D p, double angle) {
        this(p, angle, 1.0E-10);
    }

    public Line(Line line) {
        this.angle = MathUtils.normalizeAngle(line.angle, Math.PI);
        this.cos = line.cos;
        this.sin = line.sin;
        this.originOffset = line.originOffset;
        this.tolerance = line.tolerance;
        this.reverse = null;
    }

    public Line copySelf() {
        return new Line(this);
    }

    public void reset(Vector2D p1, Vector2D p2) {
        this.unlinkReverse();
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        double d = FastMath.hypot(dx, dy);
        if (d == 0.0) {
            this.angle = 0.0;
            this.cos = 1.0;
            this.sin = 0.0;
            this.originOffset = p1.getY();
        } else {
            this.angle = Math.PI + FastMath.atan2(-dy, -dx);
            this.cos = dx / d;
            this.sin = dy / d;
            this.originOffset = MathArrays.linearCombination(p2.getX(), p1.getY(), -p1.getX(), p2.getY()) / d;
        }
    }

    public void reset(Vector2D p, double alpha) {
        this.unlinkReverse();
        this.angle = MathUtils.normalizeAngle(alpha, Math.PI);
        this.cos = FastMath.cos(this.angle);
        this.sin = FastMath.sin(this.angle);
        this.originOffset = MathArrays.linearCombination(this.cos, p.getY(), -this.sin, p.getX());
    }

    public void revertSelf() {
        this.unlinkReverse();
        this.angle = this.angle < Math.PI ? (this.angle += Math.PI) : (this.angle -= Math.PI);
        this.cos = -this.cos;
        this.sin = -this.sin;
        this.originOffset = -this.originOffset;
    }

    private void unlinkReverse() {
        if (this.reverse != null) {
            this.reverse.reverse = null;
        }
        this.reverse = null;
    }

    public Line getReverse() {
        if (this.reverse == null) {
            this.reverse = new Line(this.angle < Math.PI ? this.angle + Math.PI : this.angle - Math.PI, -this.cos, -this.sin, -this.originOffset, this.tolerance);
            this.reverse.reverse = this;
        }
        return this.reverse;
    }

    public Vector1D toSubSpace(Vector<Euclidean2D> vector) {
        return this.toSubSpace((Point)vector);
    }

    public Vector2D toSpace(Vector<Euclidean1D> vector) {
        return this.toSpace((Point)vector);
    }

    public Vector1D toSubSpace(Point<Euclidean2D> point) {
        Vector2D p2 = (Vector2D)point;
        return new Vector1D(MathArrays.linearCombination(this.cos, p2.getX(), this.sin, p2.getY()));
    }

    public Vector2D toSpace(Point<Euclidean1D> point) {
        double abscissa = ((Vector1D)point).getX();
        return new Vector2D(MathArrays.linearCombination(abscissa, this.cos, -this.originOffset, this.sin), MathArrays.linearCombination(abscissa, this.sin, this.originOffset, this.cos));
    }

    public Vector2D intersection(Line other) {
        double d = MathArrays.linearCombination(this.sin, other.cos, -other.sin, this.cos);
        if (FastMath.abs(d) < this.tolerance) {
            return null;
        }
        return new Vector2D(MathArrays.linearCombination(this.cos, other.originOffset, -other.cos, this.originOffset) / d, MathArrays.linearCombination(this.sin, other.originOffset, -other.sin, this.originOffset) / d);
    }

    @Override
    public Point<Euclidean2D> project(Point<Euclidean2D> point) {
        return this.toSpace((Vector<Euclidean1D>)this.toSubSpace((Point)point));
    }

    @Override
    public double getTolerance() {
        return this.tolerance;
    }

    public SubLine wholeHyperplane() {
        return new SubLine(this, new IntervalsSet(this.tolerance));
    }

    public PolygonsSet wholeSpace() {
        return new PolygonsSet(this.tolerance);
    }

    public double getOffset(Line line) {
        return this.originOffset + (MathArrays.linearCombination(this.cos, line.cos, this.sin, line.sin) > 0.0 ? -line.originOffset : line.originOffset);
    }

    @Override
    public double getOffset(Vector<Euclidean2D> vector) {
        return this.getOffset((Point<Euclidean2D>)vector);
    }

    @Override
    public double getOffset(Point<Euclidean2D> point) {
        Vector2D p2 = (Vector2D)point;
        return MathArrays.linearCombination(this.sin, p2.getX(), -this.cos, p2.getY(), 1.0, this.originOffset);
    }

    @Override
    public boolean sameOrientationAs(Hyperplane<Euclidean2D> other) {
        Line otherL = (Line)other;
        return MathArrays.linearCombination(this.sin, otherL.sin, this.cos, otherL.cos) >= 0.0;
    }

    public Vector2D getPointAt(Vector1D abscissa, double offset) {
        double x = abscissa.getX();
        double dOffset = offset - this.originOffset;
        return new Vector2D(MathArrays.linearCombination(x, this.cos, dOffset, this.sin), MathArrays.linearCombination(x, this.sin, -dOffset, this.cos));
    }

    public boolean contains(Vector2D p) {
        return FastMath.abs(this.getOffset(p)) < this.tolerance;
    }

    public double distance(Vector2D p) {
        return FastMath.abs(this.getOffset(p));
    }

    public boolean isParallelTo(Line line) {
        return FastMath.abs(MathArrays.linearCombination(this.sin, line.cos, -this.cos, line.sin)) < this.tolerance;
    }

    public void translateToPoint(Vector2D p) {
        this.originOffset = MathArrays.linearCombination(this.cos, p.getY(), -this.sin, p.getX());
    }

    public double getAngle() {
        return MathUtils.normalizeAngle(this.angle, Math.PI);
    }

    public void setAngle(double angle) {
        this.unlinkReverse();
        this.angle = MathUtils.normalizeAngle(angle, Math.PI);
        this.cos = FastMath.cos(this.angle);
        this.sin = FastMath.sin(this.angle);
    }

    public double getOriginOffset() {
        return this.originOffset;
    }

    public void setOriginOffset(double offset) {
        this.unlinkReverse();
        this.originOffset = offset;
    }

    @Deprecated
    public static Transform<Euclidean2D, Euclidean1D> getTransform(AffineTransform transform) throws MathIllegalArgumentException {
        double[] m = new double[6];
        transform.getMatrix(m);
        return new LineTransform(m[0], m[1], m[2], m[3], m[4], m[5]);
    }

    public static Transform<Euclidean2D, Euclidean1D> getTransform(double cXX, double cYX, double cXY, double cYY, double cX1, double cY1) throws MathIllegalArgumentException {
        return new LineTransform(cXX, cYX, cXY, cYY, cX1, cY1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class LineTransform
    implements Transform<Euclidean2D, Euclidean1D> {
        private double cXX;
        private double cYX;
        private double cXY;
        private double cYY;
        private double cX1;
        private double cY1;
        private double c1Y;
        private double c1X;
        private double c11;

        LineTransform(double cXX, double cYX, double cXY, double cYY, double cX1, double cY1) throws MathIllegalArgumentException {
            this.cXX = cXX;
            this.cYX = cYX;
            this.cXY = cXY;
            this.cYY = cYY;
            this.cX1 = cX1;
            this.cY1 = cY1;
            this.c1Y = MathArrays.linearCombination(cXY, cY1, -cYY, cX1);
            this.c1X = MathArrays.linearCombination(cXX, cY1, -cYX, cX1);
            this.c11 = MathArrays.linearCombination(cXX, cYY, -cYX, cXY);
            if (FastMath.abs(this.c11) < 1.0E-20) {
                throw new MathIllegalArgumentException(LocalizedFormats.NON_INVERTIBLE_TRANSFORM, new Object[0]);
            }
        }

        public Vector2D apply(Point<Euclidean2D> point) {
            Vector2D p2D = (Vector2D)point;
            double x = p2D.getX();
            double y = p2D.getY();
            return new Vector2D(MathArrays.linearCombination(this.cXX, x, this.cXY, y, this.cX1, 1.0), MathArrays.linearCombination(this.cYX, x, this.cYY, y, this.cY1, 1.0));
        }

        public Line apply(Hyperplane<Euclidean2D> hyperplane) {
            Line line = (Line)hyperplane;
            double rOffset = MathArrays.linearCombination(this.c1X, line.cos, this.c1Y, line.sin, this.c11, line.originOffset);
            double rCos = MathArrays.linearCombination(this.cXX, line.cos, this.cXY, line.sin);
            double rSin = MathArrays.linearCombination(this.cYX, line.cos, this.cYY, line.sin);
            double inv = 1.0 / FastMath.sqrt(rSin * rSin + rCos * rCos);
            return new Line(Math.PI + FastMath.atan2(-rSin, -rCos), inv * rCos, inv * rSin, inv * rOffset, line.tolerance);
        }

        @Override
        public SubHyperplane<Euclidean1D> apply(SubHyperplane<Euclidean1D> sub, Hyperplane<Euclidean2D> original, Hyperplane<Euclidean2D> transformed) {
            OrientedPoint op = (OrientedPoint)sub.getHyperplane();
            Line originalLine = (Line)original;
            Line transformedLine = (Line)transformed;
            Vector1D newLoc = transformedLine.toSubSpace((Vector<Euclidean2D>)this.apply((Point)originalLine.toSpace(op.getLocation())));
            return new OrientedPoint(newLoc, op.isDirect(), originalLine.tolerance).wholeHyperplane();
        }
    }
}

