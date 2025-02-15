/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;
import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.SubPlane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.SubLine;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PolyhedronsSet
extends AbstractRegion<Euclidean3D, Euclidean2D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10;

    public PolyhedronsSet(double tolerance) {
        super(tolerance);
    }

    public PolyhedronsSet(BSPTree<Euclidean3D> tree, double tolerance) {
        super(tree, tolerance);
    }

    public PolyhedronsSet(Collection<SubHyperplane<Euclidean3D>> boundary, double tolerance) {
        super(boundary, tolerance);
    }

    public PolyhedronsSet(List<Vector3D> vertices, List<int[]> facets, double tolerance) {
        super(PolyhedronsSet.buildBoundary(vertices, facets, tolerance), tolerance);
    }

    public PolyhedronsSet(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double tolerance) {
        super(PolyhedronsSet.buildBoundary(xMin, xMax, yMin, yMax, zMin, zMax, tolerance), tolerance);
    }

    @Deprecated
    public PolyhedronsSet() {
        this(1.0E-10);
    }

    @Deprecated
    public PolyhedronsSet(BSPTree<Euclidean3D> tree) {
        this(tree, 1.0E-10);
    }

    @Deprecated
    public PolyhedronsSet(Collection<SubHyperplane<Euclidean3D>> boundary) {
        this(boundary, 1.0E-10);
    }

    @Deprecated
    public PolyhedronsSet(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
        this(xMin, xMax, yMin, yMax, zMin, zMax, 1.0E-10);
    }

    private static BSPTree<Euclidean3D> buildBoundary(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax, double tolerance) {
        if (xMin >= xMax - tolerance || yMin >= yMax - tolerance || zMin >= zMax - tolerance) {
            return new BSPTree<Euclidean3D>(Boolean.FALSE);
        }
        Plane pxMin = new Plane(new Vector3D(xMin, 0.0, 0.0), Vector3D.MINUS_I, tolerance);
        Plane pxMax = new Plane(new Vector3D(xMax, 0.0, 0.0), Vector3D.PLUS_I, tolerance);
        Plane pyMin = new Plane(new Vector3D(0.0, yMin, 0.0), Vector3D.MINUS_J, tolerance);
        Plane pyMax = new Plane(new Vector3D(0.0, yMax, 0.0), Vector3D.PLUS_J, tolerance);
        Plane pzMin = new Plane(new Vector3D(0.0, 0.0, zMin), Vector3D.MINUS_K, tolerance);
        Plane pzMax = new Plane(new Vector3D(0.0, 0.0, zMax), Vector3D.PLUS_K, tolerance);
        Region boundary = new RegionFactory().buildConvex(pxMin, pxMax, pyMin, pyMax, pzMin, pzMax);
        return boundary.getTree(false);
    }

    private static List<SubHyperplane<Euclidean3D>> buildBoundary(List<Vector3D> vertices, List<int[]> facets, double tolerance) {
        for (int i = 0; i < vertices.size() - 1; ++i) {
            Vector3D vi = vertices.get(i);
            for (int j = i + 1; j < vertices.size(); ++j) {
                if (!(Vector3D.distance(vi, vertices.get(j)) <= tolerance)) continue;
                throw new MathIllegalArgumentException(LocalizedFormats.CLOSE_VERTICES, vi.getX(), vi.getY(), vi.getZ());
            }
        }
        int[][] references = PolyhedronsSet.findReferences(vertices, facets);
        int[][] successors = PolyhedronsSet.successors(vertices, facets, references);
        for (int vA = 0; vA < vertices.size(); ++vA) {
            for (int vB : successors[vA]) {
                if (vB < 0) continue;
                boolean found = false;
                for (int v : successors[vB]) {
                    found = found || v == vA;
                }
                if (found) continue;
                Vector3D start = vertices.get(vA);
                Vector3D end = vertices.get(vB);
                throw new MathIllegalArgumentException(LocalizedFormats.EDGE_CONNECTED_TO_ONE_FACET, start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ());
            }
        }
        ArrayList<SubHyperplane<Euclidean3D>> boundary = new ArrayList<SubHyperplane<Euclidean3D>>();
        for (int[] facet : facets) {
            Plane plane = new Plane(vertices.get(facet[0]), vertices.get(facet[1]), vertices.get(facet[2]), tolerance);
            Vector2D[] two2Points = new Vector2D[facet.length];
            for (int i = 0; i < facet.length; ++i) {
                Vector3D v = vertices.get(facet[i]);
                if (!plane.contains(v)) {
                    throw new MathIllegalArgumentException(LocalizedFormats.OUT_OF_PLANE, v.getX(), v.getY(), v.getZ());
                }
                two2Points[i] = plane.toSubSpace(v);
            }
            boundary.add(new SubPlane(plane, new PolygonsSet(tolerance, two2Points)));
        }
        return boundary;
    }

    private static int[][] findReferences(List<Vector3D> vertices, List<int[]> facets) {
        int[][] references;
        int[] nbFacets = new int[vertices.size()];
        int maxFacets = 0;
        for (int[] facet : facets) {
            if (facet.length < 3) {
                throw new NumberIsTooSmallException((Localizable)LocalizedFormats.WRONG_NUMBER_OF_POINTS, (Number)3, facet.length, true);
            }
            int[] arr$ = facet;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; ++i$) {
                int index;
                int n = index = arr$[i$];
                int n2 = nbFacets[n] + 1;
                nbFacets[n] = n2;
                maxFacets = FastMath.max(maxFacets, n2);
            }
        }
        for (int[] r : references = new int[vertices.size()][maxFacets]) {
            Arrays.fill(r, -1);
        }
        for (int f = 0; f < facets.size(); ++f) {
            for (int v : facets.get(f)) {
                for (int k = 0; k < maxFacets && references[v][k] >= 0; ++k) {
                }
                references[v][k] = f;
            }
        }
        return references;
    }

    private static int[][] successors(List<Vector3D> vertices, List<int[]> facets, int[][] references) {
        int[][] successors;
        for (int[] s : successors = new int[vertices.size()][references[0].length]) {
            Arrays.fill(s, -1);
        }
        for (int v = 0; v < vertices.size(); ++v) {
            for (int k = 0; k < successors[v].length && references[v][k] >= 0; ++k) {
                int i;
                int[] facet = facets.get(references[v][k]);
                for (i = 0; i < facet.length && facet[i] != v; ++i) {
                }
                successors[v][k] = facet[(i + 1) % facet.length];
                for (int l = 0; l < k; ++l) {
                    if (successors[v][l] != successors[v][k]) continue;
                    Vector3D start = vertices.get(v);
                    Vector3D end = vertices.get(successors[v][k]);
                    throw new MathIllegalArgumentException(LocalizedFormats.FACET_ORIENTATION_MISMATCH, start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ());
                }
            }
        }
        return successors;
    }

    public PolyhedronsSet buildNew(BSPTree<Euclidean3D> tree) {
        return new PolyhedronsSet(tree, this.getTolerance());
    }

    @Override
    protected void computeGeometricalProperties() {
        this.getTree(true).visit(new FacetsContributionVisitor());
        if (this.getSize() < 0.0) {
            this.setSize(Double.POSITIVE_INFINITY);
            this.setBarycenter(Vector3D.NaN);
        } else {
            this.setSize(this.getSize() / 3.0);
            this.setBarycenter(new Vector3D(1.0 / (4.0 * this.getSize()), (Vector3D)this.getBarycenter()));
        }
    }

    public SubHyperplane<Euclidean3D> firstIntersection(Vector3D point, Line line) {
        return this.recurseFirstIntersection(this.getTree(true), point, line);
    }

    private SubHyperplane<Euclidean3D> recurseFirstIntersection(BSPTree<Euclidean3D> node, Vector3D point, Line line) {
        SubHyperplane<Euclidean3D> facet;
        Vector3D hit3D;
        SubHyperplane<Euclidean3D> facet2;
        BSPTree<Euclidean3D> far;
        BSPTree<Euclidean3D> near;
        boolean in;
        SubHyperplane<Euclidean3D> cut = node.getCut();
        if (cut == null) {
            return null;
        }
        BSPTree<Euclidean3D> minus = node.getMinus();
        BSPTree<Euclidean3D> plus = node.getPlus();
        Plane plane = (Plane)cut.getHyperplane();
        double offset = plane.getOffset(point);
        boolean bl = in = FastMath.abs(offset) < this.getTolerance();
        if (offset < 0.0) {
            near = minus;
            far = plus;
        } else {
            near = plus;
            far = minus;
        }
        if (in && (facet2 = this.boundaryFacet(point, node)) != null) {
            return facet2;
        }
        SubHyperplane<Euclidean3D> crossed = this.recurseFirstIntersection(near, point, line);
        if (crossed != null) {
            return crossed;
        }
        if (!in && (hit3D = plane.intersection(line)) != null && line.getAbscissa(hit3D) > line.getAbscissa(point) && (facet = this.boundaryFacet(hit3D, node)) != null) {
            return facet;
        }
        return this.recurseFirstIntersection(far, point, line);
    }

    private SubHyperplane<Euclidean3D> boundaryFacet(Vector3D point, BSPTree<Euclidean3D> node) {
        Point point2D = ((Plane)node.getCut().getHyperplane()).toSubSpace((Point)point);
        BoundaryAttribute attribute = (BoundaryAttribute)node.getAttribute();
        if (attribute.getPlusOutside() != null && ((SubPlane)attribute.getPlusOutside()).getRemainingRegion().checkPoint(point2D) == Region.Location.INSIDE) {
            return attribute.getPlusOutside();
        }
        if (attribute.getPlusInside() != null && ((SubPlane)attribute.getPlusInside()).getRemainingRegion().checkPoint(point2D) == Region.Location.INSIDE) {
            return attribute.getPlusInside();
        }
        return null;
    }

    public PolyhedronsSet rotate(Vector3D center, Rotation rotation) {
        return (PolyhedronsSet)this.applyTransform(new RotationTransform(center, rotation));
    }

    public PolyhedronsSet translate(Vector3D translation) {
        return (PolyhedronsSet)this.applyTransform(new TranslationTransform(translation));
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class TranslationTransform
    implements Transform<Euclidean3D, Euclidean2D> {
        private Vector3D translation;
        private Plane cachedOriginal;
        private Transform<Euclidean2D, Euclidean1D> cachedTransform;

        TranslationTransform(Vector3D translation) {
            this.translation = translation;
        }

        public Vector3D apply(Point<Euclidean3D> point) {
            return new Vector3D(1.0, (Vector3D)point, 1.0, this.translation);
        }

        public Plane apply(Hyperplane<Euclidean3D> hyperplane) {
            return ((Plane)hyperplane).translate(this.translation);
        }

        @Override
        public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> sub, Hyperplane<Euclidean3D> original, Hyperplane<Euclidean3D> transformed) {
            if (original != this.cachedOriginal) {
                Plane oPlane = (Plane)original;
                Plane tPlane = (Plane)transformed;
                Point shift = tPlane.toSubSpace(this.apply((Point)oPlane.getOrigin()));
                this.cachedOriginal = (Plane)original;
                this.cachedTransform = org.apache.commons.math3.geometry.euclidean.twod.Line.getTransform(1.0, 0.0, 0.0, 1.0, ((Vector2D)shift).getX(), ((Vector2D)shift).getY());
            }
            return ((SubLine)sub).applyTransform(this.cachedTransform);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class RotationTransform
    implements Transform<Euclidean3D, Euclidean2D> {
        private Vector3D center;
        private Rotation rotation;
        private Plane cachedOriginal;
        private Transform<Euclidean2D, Euclidean1D> cachedTransform;

        RotationTransform(Vector3D center, Rotation rotation) {
            this.center = center;
            this.rotation = rotation;
        }

        public Vector3D apply(Point<Euclidean3D> point) {
            Vector delta = ((Vector3D)point).subtract((Vector)this.center);
            return new Vector3D(1.0, this.center, 1.0, this.rotation.applyTo((Vector3D)delta));
        }

        public Plane apply(Hyperplane<Euclidean3D> hyperplane) {
            return ((Plane)hyperplane).rotate(this.center, this.rotation);
        }

        @Override
        public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> sub, Hyperplane<Euclidean3D> original, Hyperplane<Euclidean3D> transformed) {
            if (original != this.cachedOriginal) {
                Plane oPlane = (Plane)original;
                Plane tPlane = (Plane)transformed;
                Vector3D p00 = oPlane.getOrigin();
                Point p10 = oPlane.toSpace((Point)new Vector2D(1.0, 0.0));
                Point p01 = oPlane.toSpace((Point)new Vector2D(0.0, 1.0));
                Point tP00 = tPlane.toSubSpace(this.apply((Point)p00));
                Point tP10 = tPlane.toSubSpace(this.apply(p10));
                Point tP01 = tPlane.toSubSpace(this.apply(p01));
                this.cachedOriginal = (Plane)original;
                this.cachedTransform = org.apache.commons.math3.geometry.euclidean.twod.Line.getTransform(((Vector2D)tP10).getX() - ((Vector2D)tP00).getX(), ((Vector2D)tP10).getY() - ((Vector2D)tP00).getY(), ((Vector2D)tP01).getX() - ((Vector2D)tP00).getX(), ((Vector2D)tP01).getY() - ((Vector2D)tP00).getY(), ((Vector2D)tP00).getX(), ((Vector2D)tP00).getY());
            }
            return ((SubLine)sub).applyTransform(this.cachedTransform);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class FacetsContributionVisitor
    implements BSPTreeVisitor<Euclidean3D> {
        FacetsContributionVisitor() {
            PolyhedronsSet.this.setSize(0.0);
            PolyhedronsSet.this.setBarycenter(new Vector3D(0.0, 0.0, 0.0));
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
            Region polygon = ((SubPlane)facet).getRemainingRegion();
            double area = polygon.getSize();
            if (Double.isInfinite(area)) {
                PolyhedronsSet.this.setSize(Double.POSITIVE_INFINITY);
                PolyhedronsSet.this.setBarycenter(Vector3D.NaN);
            } else {
                Plane plane = (Plane)facet.getHyperplane();
                Point facetB = plane.toSpace(polygon.getBarycenter());
                double scaled = area * ((Vector3D)facetB).dotProduct(plane.getNormal());
                if (reversed) {
                    scaled = -scaled;
                }
                PolyhedronsSet.this.setSize(PolyhedronsSet.this.getSize() + scaled);
                PolyhedronsSet.this.setBarycenter(new Vector3D(1.0, (Vector3D)PolyhedronsSet.this.getBarycenter(), scaled, (Vector3D)facetB));
            }
        }
    }
}

