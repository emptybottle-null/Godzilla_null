/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.twod.hull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.euclidean.twod.hull.AbstractConvexHullGenerator2D;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MonotoneChain
extends AbstractConvexHullGenerator2D {
    public MonotoneChain() {
        this(false);
    }

    public MonotoneChain(boolean includeCollinearPoints) {
        super(includeCollinearPoints);
    }

    public MonotoneChain(boolean includeCollinearPoints, double tolerance) {
        super(includeCollinearPoints, tolerance);
    }

    @Override
    public Collection<Vector2D> findHullVertices(Collection<Vector2D> points) {
        int idx;
        ArrayList<Vector2D> pointsSortedByXAxis = new ArrayList<Vector2D>(points);
        Collections.sort(pointsSortedByXAxis, new Comparator<Vector2D>(){

            @Override
            public int compare(Vector2D o1, Vector2D o2) {
                double tolerance = MonotoneChain.this.getTolerance();
                int diff = Precision.compareTo(o1.getX(), o2.getX(), tolerance);
                if (diff == 0) {
                    return Precision.compareTo(o1.getY(), o2.getY(), tolerance);
                }
                return diff;
            }
        });
        ArrayList<Vector2D> lowerHull = new ArrayList<Vector2D>();
        for (Vector2D p : pointsSortedByXAxis) {
            this.updateHull(p, lowerHull);
        }
        ArrayList<Vector2D> upperHull = new ArrayList<Vector2D>();
        for (int idx2 = pointsSortedByXAxis.size() - 1; idx2 >= 0; --idx2) {
            Vector2D p = (Vector2D)pointsSortedByXAxis.get(idx2);
            this.updateHull(p, upperHull);
        }
        ArrayList<Vector2D> hullVertices = new ArrayList<Vector2D>(lowerHull.size() + upperHull.size() - 2);
        for (idx = 0; idx < lowerHull.size() - 1; ++idx) {
            hullVertices.add((Vector2D)lowerHull.get(idx));
        }
        for (idx = 0; idx < upperHull.size() - 1; ++idx) {
            hullVertices.add((Vector2D)upperHull.get(idx));
        }
        if (hullVertices.isEmpty() && !lowerHull.isEmpty()) {
            hullVertices.add((Vector2D)lowerHull.get(0));
        }
        return hullVertices;
    }

    private void updateHull(Vector2D point, List<Vector2D> hull) {
        Vector2D p1;
        double tolerance = this.getTolerance();
        if (hull.size() == 1 && (p1 = hull.get(0)).distance(point) < tolerance) {
            return;
        }
        while (hull.size() >= 2) {
            Vector2D p2;
            int size = hull.size();
            Vector2D p12 = hull.get(size - 2);
            double offset = new Line(p12, p2 = hull.get(size - 1), tolerance).getOffset(point);
            if (FastMath.abs(offset) < tolerance) {
                double distanceToCurrent = p12.distance(point);
                if (distanceToCurrent < tolerance || p2.distance(point) < tolerance) {
                    return;
                }
                double distanceToLast = p12.distance(p2);
                if (this.isIncludeCollinearPoints()) {
                    int index = distanceToCurrent < distanceToLast ? size - 1 : size;
                    hull.add(index, point);
                } else if (distanceToCurrent > distanceToLast) {
                    hull.remove(size - 1);
                    hull.add(point);
                }
                return;
            }
            if (!(offset > 0.0)) break;
            hull.remove(size - 1);
        }
        hull.add(point);
    }
}

