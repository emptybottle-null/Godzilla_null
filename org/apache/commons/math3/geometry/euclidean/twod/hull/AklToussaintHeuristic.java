/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.twod.hull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class AklToussaintHeuristic {
    private AklToussaintHeuristic() {
    }

    public static Collection<Vector2D> reducePoints(Collection<Vector2D> points) {
        int size = 0;
        Vector2D minX = null;
        Vector2D maxX = null;
        Vector2D minY = null;
        Vector2D maxY = null;
        for (Vector2D p : points) {
            if (minX == null || p.getX() < minX.getX()) {
                minX = p;
            }
            if (maxX == null || p.getX() > maxX.getX()) {
                maxX = p;
            }
            if (minY == null || p.getY() < minY.getY()) {
                minY = p;
            }
            if (maxY == null || p.getY() > maxY.getY()) {
                maxY = p;
            }
            ++size;
        }
        if (size < 4) {
            return points;
        }
        List<Vector2D> quadrilateral = AklToussaintHeuristic.buildQuadrilateral(minY, maxX, maxY, minX);
        if (quadrilateral.size() < 3) {
            return points;
        }
        ArrayList<Vector2D> reducedPoints = new ArrayList<Vector2D>(quadrilateral);
        for (Vector2D p : points) {
            if (AklToussaintHeuristic.insideQuadrilateral(p, quadrilateral)) continue;
            reducedPoints.add(p);
        }
        return reducedPoints;
    }

    private static List<Vector2D> buildQuadrilateral(Vector2D ... points) {
        ArrayList<Vector2D> quadrilateral = new ArrayList<Vector2D>();
        for (Vector2D p : points) {
            if (quadrilateral.contains(p)) continue;
            quadrilateral.add(p);
        }
        return quadrilateral;
    }

    private static boolean insideQuadrilateral(Vector2D point, List<Vector2D> quadrilateralPoints) {
        Vector2D p1 = quadrilateralPoints.get(0);
        Vector2D p2 = quadrilateralPoints.get(1);
        if (point.equals(p1) || point.equals(p2)) {
            return true;
        }
        double last = point.crossProduct(p1, p2);
        int size = quadrilateralPoints.size();
        for (int i = 1; i < size; ++i) {
            p1 = p2;
            p2 = quadrilateralPoints.get(i + 1 == size ? 0 : i + 1);
            if (point.equals(p1) || point.equals(p2)) {
                return true;
            }
            if (!(last * point.crossProduct(p1, p2) < 0.0)) continue;
            return false;
        }
        return true;
    }
}

