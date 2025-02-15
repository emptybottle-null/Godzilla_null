/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.enclosing;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.enclosing.Encloser;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;
import org.apache.commons.math3.geometry.enclosing.SupportBallGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class WelzlEncloser<S extends Space, P extends Point<S>>
implements Encloser<S, P> {
    private final double tolerance;
    private final SupportBallGenerator<S, P> generator;

    public WelzlEncloser(double tolerance, SupportBallGenerator<S, P> generator) {
        this.tolerance = tolerance;
        this.generator = generator;
    }

    @Override
    public EnclosingBall<S, P> enclose(Iterable<P> points) {
        if (points == null || !points.iterator().hasNext()) {
            return this.generator.ballOnSupport(new ArrayList());
        }
        return this.pivotingBall(points);
    }

    private EnclosingBall<S, P> pivotingBall(Iterable<P> points) {
        Point first = (Point)points.iterator().next();
        ArrayList<Point<Object>> extreme = new ArrayList<Point<Object>>(first.getSpace().getDimension() + 1);
        ArrayList<P> support = new ArrayList<P>(first.getSpace().getDimension() + 1);
        extreme.add(first);
        EnclosingBall ball = this.moveToFrontBall(extreme, extreme.size(), support);
        P farthest;
        while (!ball.contains(farthest = this.selectFarthest(points, ball), this.tolerance)) {
            support.clear();
            support.add(farthest);
            EnclosingBall savedBall = ball;
            ball = this.moveToFrontBall(extreme, extreme.size(), support);
            if (ball.getRadius() < savedBall.getRadius()) {
                throw new MathInternalError();
            }
            extreme.add(0, (Point<Object>)farthest);
            extreme.subList(ball.getSupportSize(), extreme.size()).clear();
        }
        return ball;
    }

    private EnclosingBall<S, P> moveToFrontBall(List<P> extreme, int nbExtreme, List<P> support) {
        EnclosingBall<S, Point> ball = this.generator.ballOnSupport(support);
        if (ball.getSupportSize() <= ball.getCenter().getSpace().getDimension()) {
            for (int i = 0; i < nbExtreme; ++i) {
                Point pi = (Point)extreme.get(i);
                if (ball.contains(pi, this.tolerance)) continue;
                support.add(pi);
                ball = this.moveToFrontBall(extreme, i, support);
                support.remove(support.size() - 1);
                for (int j = i; j > 0; --j) {
                    extreme.set(j, extreme.get(j - 1));
                }
                extreme.set(0, pi);
            }
        }
        return ball;
    }

    public P selectFarthest(Iterable<P> points, EnclosingBall<S, P> ball) {
        P center = ball.getCenter();
        Point farthest = null;
        double dMax = -1.0;
        for (Point point : points) {
            double d = point.distance(center);
            if (!(d > dMax)) continue;
            farthest = point;
            dMax = d;
        }
        return (P)farthest;
    }
}

