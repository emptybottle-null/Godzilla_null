/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.twod;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.spherical.twod.Edge;
import org.apache.commons.math3.geometry.spherical.twod.S2Point;
import org.apache.commons.math3.geometry.spherical.twod.Sphere2D;
import org.apache.commons.math3.geometry.spherical.twod.SphericalPolygonsSet;
import org.apache.commons.math3.geometry.spherical.twod.Vertex;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class PropertiesComputer
implements BSPTreeVisitor<Sphere2D> {
    private final double tolerance;
    private double summedArea;
    private Vector3D summedBarycenter;
    private final List<Vector3D> convexCellsInsidePoints;

    PropertiesComputer(double tolerance) {
        this.tolerance = tolerance;
        this.summedArea = 0.0;
        this.summedBarycenter = Vector3D.ZERO;
        this.convexCellsInsidePoints = new ArrayList<Vector3D>();
    }

    @Override
    public BSPTreeVisitor.Order visitOrder(BSPTree<Sphere2D> node) {
        return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
    }

    @Override
    public void visitInternalNode(BSPTree<Sphere2D> node) {
    }

    @Override
    public void visitLeafNode(BSPTree<Sphere2D> node) {
        if (((Boolean)node.getAttribute()).booleanValue()) {
            SphericalPolygonsSet convex = new SphericalPolygonsSet(node.pruneAroundConvexCell(Boolean.TRUE, Boolean.FALSE, null), this.tolerance);
            List<Vertex> boundary = convex.getBoundaryLoops();
            if (boundary.size() != 1) {
                throw new MathInternalError();
            }
            double area = this.convexCellArea(boundary.get(0));
            Vector3D barycenter = this.convexCellBarycenter(boundary.get(0));
            this.convexCellsInsidePoints.add(barycenter);
            this.summedArea += area;
            this.summedBarycenter = new Vector3D(1.0, this.summedBarycenter, area, barycenter);
        }
    }

    private double convexCellArea(Vertex start) {
        int n;
        double sum = 0.0;
        Edge e = start.getOutgoing();
        for (n = 0; n == 0 || e.getStart() != start; ++n) {
            Vector3D point;
            Vector3D previousPole = e.getCircle().getPole();
            Vector3D nextPole = e.getEnd().getOutgoing().getCircle().getPole();
            double alpha = FastMath.atan2(Vector3D.dotProduct(nextPole, Vector3D.crossProduct(point = e.getEnd().getLocation().getVector(), previousPole)), -Vector3D.dotProduct(nextPole, previousPole));
            if (alpha < 0.0) {
                alpha += Math.PI * 2;
            }
            sum += alpha;
            e = e.getEnd().getOutgoing();
        }
        return sum - (double)(n - 2) * Math.PI;
    }

    private Vector3D convexCellBarycenter(Vertex start) {
        Vector3D sumB = Vector3D.ZERO;
        Edge e = start.getOutgoing();
        for (int n = 0; n == 0 || e.getStart() != start; ++n) {
            sumB = new Vector3D(1.0, sumB, e.getLength(), e.getCircle().getPole());
            e = e.getEnd().getOutgoing();
        }
        return sumB.normalize();
    }

    public double getArea() {
        return this.summedArea;
    }

    public S2Point getBarycenter() {
        if (this.summedBarycenter.getNormSq() == 0.0) {
            return S2Point.NaN;
        }
        return new S2Point(this.summedBarycenter);
    }

    public List<Vector3D> getConvexCellsInsidePoints() {
        return this.convexCellsInsidePoints;
    }
}

