/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.PolyhedronsSet;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

public class OutlineExtractor {
    private Vector3D u;
    private Vector3D v;
    private Vector3D w;

    public OutlineExtractor(Vector3D u, Vector3D v) {
        this.u = u;
        this.v = v;
        this.w = Vector3D.crossProduct(u, v);
    }

    public Vector2D[][] getOutline(PolyhedronsSet polyhedronsSet) {
        BoundaryProjector projector = new BoundaryProjector(polyhedronsSet.getTolerance());
        polyhedronsSet.getTree(true).visit(projector);
        PolygonsSet projected = projector.getProjected();
        Vector2D[][] outline = projected.getVertices();
        for (int i = 0; i < outline.length; ++i) {
            Vector2D[] rawLoop = outline[i];
            int end = rawLoop.length;
            int j = 0;
            while (j < end) {
                if (this.pointIsBetween(rawLoop, end, j)) {
                    for (int k = j; k < end - 1; ++k) {
                        rawLoop[k] = rawLoop[k + 1];
                    }
                    --end;
                    continue;
                }
                ++j;
            }
            if (end == rawLoop.length) continue;
            outline[i] = new Vector2D[end];
            System.arraycopy(rawLoop, 0, outline[i], 0, end);
        }
        return outline;
    }

    private boolean pointIsBetween(Vector2D[] loop, int n, int i) {
        Vector2D previous = loop[(i + n - 1) % n];
        Vector2D current = loop[i];
        Vector2D next = loop[(i + 1) % n];
        double dx1 = current.getX() - previous.getX();
        double dy1 = current.getY() - previous.getY();
        double dx2 = next.getX() - current.getX();
        double dy2 = next.getY() - current.getY();
        double cross = dx1 * dy2 - dx2 * dy1;
        double dot = dx1 * dx2 + dy1 * dy2;
        double d1d2 = FastMath.sqrt((dx1 * dx1 + dy1 * dy1) * (dx2 * dx2 + dy2 * dy2));
        return FastMath.abs(cross) <= 1.0E-6 * d1d2 && dot >= 0.0;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class BoundaryProjector
    implements BSPTreeVisitor<Euclidean3D> {
        private PolygonsSet projected;
        private final double tolerance;

        BoundaryProjector(double tolerance) {
            this.projected = new PolygonsSet(new BSPTree<Euclidean2D>(Boolean.FALSE), tolerance);
            this.tolerance = tolerance;
        }

        @Override
        public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean3D> node) {
            return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
        }

        @Override
        public void visitInternalNode(BSPTree<Euclidean3D> node) {
            BoundaryAttribute attribute = (BoundaryAttribute)node.getAttribute();
            if (attribute.getPlusOutside() != null) {
                this.addContribution(attribute.getPlusOutside(), false);
            }
            if (attribute.getPlusInside() != null) {
                this.addContribution(attribute.getPlusInside(), true);
            }
        }

        @Override
        public void visitLeafNode(BSPTree<Euclidean3D> node) {
        }

        private void addContribution(SubHyperplane<Euclidean3D> facet, boolean reversed) {
            AbstractSubHyperplane absFacet = (AbstractSubHyperplane)facet;
            Plane plane = (Plane)facet.getHyperplane();
            double scal = plane.getNormal().dotProduct(OutlineExtractor.this.w);
            if (FastMath.abs(scal) > 0.001) {
                Vector2D[][] vertices = ((PolygonsSet)absFacet.getRemainingRegion()).getVertices();
                if (scal < 0.0 ^ reversed) {
                    Vector2D[][] newVertices = new Vector2D[vertices.length][];
                    for (int i = 0; i < vertices.length; ++i) {
                        int j;
                        Vector2D[] loop = vertices[i];
                        Vector2D[] newLoop = new Vector2D[loop.length];
                        if (loop[0] == null) {
                            newLoop[0] = null;
                            for (j = 1; j < loop.length; ++j) {
                                newLoop[j] = loop[loop.length - j];
                            }
                        } else {
                            for (j = 0; j < loop.length; ++j) {
                                newLoop[j] = loop[loop.length - (j + 1)];
                            }
                        }
                        newVertices[i] = newLoop;
                    }
                    vertices = newVertices;
                }
                ArrayList<SubHyperplane<Euclidean2D>> edges = new ArrayList<SubHyperplane<Euclidean2D>>();
                for (Vector2D[] loop : vertices) {
                    boolean closed = loop[0] != null;
                    int previous = closed ? loop.length - 1 : 1;
                    Point previous3D = plane.toSpace((Point)loop[previous]);
                    int current = (previous + 1) % loop.length;
                    Vector2D pPoint = new Vector2D(((Vector3D)previous3D).dotProduct(OutlineExtractor.this.u), ((Vector3D)previous3D).dotProduct(OutlineExtractor.this.v));
                    while (current < loop.length) {
                        Line l;
                        double angle;
                        Point current3D = plane.toSpace((Point)loop[current]);
                        Vector2D cPoint = new Vector2D(((Vector3D)current3D).dotProduct(OutlineExtractor.this.u), ((Vector3D)current3D).dotProduct(OutlineExtractor.this.v));
                        Line line = new Line(pPoint, cPoint, this.tolerance);
                        SubHyperplane<Euclidean2D> edge = line.wholeHyperplane();
                        if (closed || previous != 1) {
                            angle = line.getAngle() + 1.5707963267948966;
                            l = new Line(pPoint, angle, this.tolerance);
                            edge = edge.split(l).getPlus();
                        }
                        if (closed || current != loop.length - 1) {
                            angle = line.getAngle() + 1.5707963267948966;
                            l = new Line(cPoint, angle, this.tolerance);
                            edge = edge.split(l).getMinus();
                        }
                        edges.add(edge);
                        previous = current++;
                        previous3D = current3D;
                        pPoint = cPoint;
                    }
                }
                PolygonsSet projectedFacet = new PolygonsSet((Collection<SubHyperplane<Euclidean2D>>)edges, this.tolerance);
                this.projected = (PolygonsSet)new RegionFactory<Euclidean2D>().union(this.projected, projectedFacet);
            }
        }

        public PolygonsSet getProjected() {
            return this.projected;
        }
    }
}

