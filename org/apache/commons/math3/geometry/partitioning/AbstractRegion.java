/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.BoundaryBuilder;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjector;
import org.apache.commons.math3.geometry.partitioning.BoundarySizeVisitor;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.InsideFinder;
import org.apache.commons.math3.geometry.partitioning.NodesSet;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractRegion<S extends Space, T extends Space>
implements Region<S> {
    private BSPTree<S> tree;
    private final double tolerance;
    private double size;
    private Point<S> barycenter;

    protected AbstractRegion(double tolerance) {
        this.tree = new BSPTree(Boolean.TRUE);
        this.tolerance = tolerance;
    }

    protected AbstractRegion(BSPTree<S> tree, double tolerance) {
        this.tree = tree;
        this.tolerance = tolerance;
    }

    protected AbstractRegion(Collection<SubHyperplane<S>> boundary, double tolerance) {
        this.tolerance = tolerance;
        if (boundary.size() == 0) {
            this.tree = new BSPTree(Boolean.TRUE);
        } else {
            TreeSet<SubHyperplane<S>> ordered = new TreeSet<SubHyperplane<S>>(new Comparator<SubHyperplane<S>>(){

                @Override
                public int compare(SubHyperplane<S> o1, SubHyperplane<S> o2) {
                    double size1 = o1.getSize();
                    double size2 = o2.getSize();
                    return size2 < size1 ? -1 : (o1 == o2 ? 0 : 1);
                }
            });
            ordered.addAll(boundary);
            this.tree = new BSPTree();
            this.insertCuts(this.tree, ordered);
            this.tree.visit(new BSPTreeVisitor<S>(){

                @Override
                public BSPTreeVisitor.Order visitOrder(BSPTree<S> node) {
                    return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
                }

                @Override
                public void visitInternalNode(BSPTree<S> node) {
                }

                @Override
                public void visitLeafNode(BSPTree<S> node) {
                    if (node.getParent() == null || node == node.getParent().getMinus()) {
                        node.setAttribute(Boolean.TRUE);
                    } else {
                        node.setAttribute(Boolean.FALSE);
                    }
                }
            });
        }
    }

    public AbstractRegion(Hyperplane<S>[] hyperplanes, double tolerance) {
        this.tolerance = tolerance;
        if (hyperplanes == null || hyperplanes.length == 0) {
            this.tree = new BSPTree(Boolean.FALSE);
        } else {
            this.tree = hyperplanes[0].wholeSpace().getTree(false);
            BSPTree<S> node = this.tree;
            node.setAttribute(Boolean.TRUE);
            for (Hyperplane<S> hyperplane : hyperplanes) {
                if (!node.insertCut(hyperplane)) continue;
                node.setAttribute(null);
                node.getPlus().setAttribute(Boolean.FALSE);
                node = node.getMinus();
                node.setAttribute(Boolean.TRUE);
            }
        }
    }

    public abstract AbstractRegion<S, T> buildNew(BSPTree<S> var1);

    public double getTolerance() {
        return this.tolerance;
    }

    private void insertCuts(BSPTree<S> node, Collection<SubHyperplane<S>> boundary) {
        Iterator<SubHyperplane<S>> iterator = boundary.iterator();
        Hyperplane<S> inserted = null;
        while (inserted == null && iterator.hasNext()) {
            inserted = iterator.next().getHyperplane();
            if (node.insertCut(inserted.copySelf())) continue;
            inserted = null;
        }
        if (!iterator.hasNext()) {
            return;
        }
        ArrayList<SubHyperplane<S>> plusList = new ArrayList<SubHyperplane<S>>();
        ArrayList<SubHyperplane<S>> minusList = new ArrayList<SubHyperplane<S>>();
        while (iterator.hasNext()) {
            SubHyperplane<S> other = iterator.next();
            SubHyperplane.SplitSubHyperplane<S> split = other.split(inserted);
            switch (split.getSide()) {
                case PLUS: {
                    plusList.add(other);
                    break;
                }
                case MINUS: {
                    minusList.add(other);
                    break;
                }
                case BOTH: {
                    plusList.add(split.getPlus());
                    minusList.add(split.getMinus());
                    break;
                }
            }
        }
        this.insertCuts(node.getPlus(), plusList);
        this.insertCuts(node.getMinus(), minusList);
    }

    public AbstractRegion<S, T> copySelf() {
        return this.buildNew((BSPTree)this.tree.copySelf());
    }

    @Override
    public boolean isEmpty() {
        return this.isEmpty(this.tree);
    }

    @Override
    public boolean isEmpty(BSPTree<S> node) {
        if (node.getCut() == null) {
            return (Boolean)node.getAttribute() == false;
        }
        return this.isEmpty(node.getMinus()) && this.isEmpty(node.getPlus());
    }

    @Override
    public boolean isFull() {
        return this.isFull(this.tree);
    }

    @Override
    public boolean isFull(BSPTree<S> node) {
        if (node.getCut() == null) {
            return (Boolean)node.getAttribute();
        }
        return this.isFull(node.getMinus()) && this.isFull(node.getPlus());
    }

    @Override
    public boolean contains(Region<S> region) {
        return new RegionFactory<S>().difference(region, this).isEmpty();
    }

    @Override
    public BoundaryProjection<S> projectToBoundary(Point<S> point) {
        BoundaryProjector projector = new BoundaryProjector(point);
        this.getTree(true).visit(projector);
        return projector.getProjection();
    }

    @Override
    public Region.Location checkPoint(Vector<S> point) {
        return this.checkPoint((Point<S>)point);
    }

    @Override
    public Region.Location checkPoint(Point<S> point) {
        return this.checkPoint(this.tree, point);
    }

    protected Region.Location checkPoint(BSPTree<S> node, Vector<S> point) {
        return this.checkPoint(node, (Point<S>)point);
    }

    protected Region.Location checkPoint(BSPTree<S> node, Point<S> point) {
        Region.Location plusCode;
        BSPTree<S> cell = node.getCell(point, this.tolerance);
        if (cell.getCut() == null) {
            return (Boolean)cell.getAttribute() != false ? Region.Location.INSIDE : Region.Location.OUTSIDE;
        }
        Region.Location minusCode = this.checkPoint(cell.getMinus(), point);
        return minusCode == (plusCode = this.checkPoint(cell.getPlus(), point)) ? minusCode : Region.Location.BOUNDARY;
    }

    @Override
    public BSPTree<S> getTree(boolean includeBoundaryAttributes) {
        if (includeBoundaryAttributes && this.tree.getCut() != null && this.tree.getAttribute() == null) {
            this.tree.visit(new BoundaryBuilder());
        }
        return this.tree;
    }

    @Override
    public double getBoundarySize() {
        BoundarySizeVisitor visitor = new BoundarySizeVisitor();
        this.getTree(true).visit(visitor);
        return visitor.getSize();
    }

    @Override
    public double getSize() {
        if (this.barycenter == null) {
            this.computeGeometricalProperties();
        }
        return this.size;
    }

    protected void setSize(double size) {
        this.size = size;
    }

    @Override
    public Point<S> getBarycenter() {
        if (this.barycenter == null) {
            this.computeGeometricalProperties();
        }
        return this.barycenter;
    }

    protected void setBarycenter(Vector<S> barycenter) {
        this.setBarycenter((Point<S>)barycenter);
    }

    protected void setBarycenter(Point<S> barycenter) {
        this.barycenter = barycenter;
    }

    protected abstract void computeGeometricalProperties();

    @Override
    @Deprecated
    public Side side(Hyperplane<S> hyperplane) {
        InsideFinder<S> finder = new InsideFinder<S>(this);
        finder.recurseSides(this.tree, hyperplane.wholeHyperplane());
        return finder.plusFound() ? (finder.minusFound() ? Side.BOTH : Side.PLUS) : (finder.minusFound() ? Side.MINUS : Side.HYPER);
    }

    @Override
    public SubHyperplane<S> intersection(SubHyperplane<S> sub) {
        return this.recurseIntersection(this.tree, sub);
    }

    private SubHyperplane<S> recurseIntersection(BSPTree<S> node, SubHyperplane<S> sub) {
        if (node.getCut() == null) {
            return (Boolean)node.getAttribute() != false ? sub.copySelf() : null;
        }
        Hyperplane<S> hyperplane = node.getCut().getHyperplane();
        SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
        if (split.getPlus() != null) {
            if (split.getMinus() != null) {
                SubHyperplane<S> plus = this.recurseIntersection(node.getPlus(), split.getPlus());
                SubHyperplane<S> minus = this.recurseIntersection(node.getMinus(), split.getMinus());
                if (plus == null) {
                    return minus;
                }
                if (minus == null) {
                    return plus;
                }
                return plus.reunite(minus);
            }
            return this.recurseIntersection(node.getPlus(), sub);
        }
        if (split.getMinus() != null) {
            return this.recurseIntersection(node.getMinus(), sub);
        }
        return this.recurseIntersection(node.getPlus(), this.recurseIntersection(node.getMinus(), sub));
    }

    public AbstractRegion<S, T> applyTransform(Transform<S, T> transform) {
        HashMap<BSPTree<S>, BSPTree<S>> map = new HashMap<BSPTree<S>, BSPTree<S>>();
        BSPTree<S> transformedTree = this.recurseTransform(this.getTree(false), transform, map);
        for (Map.Entry entry : map.entrySet()) {
            BoundaryAttribute original;
            if (((BSPTree)entry.getKey()).getCut() == null || (original = (BoundaryAttribute)((BSPTree)entry.getKey()).getAttribute()) == null) continue;
            BoundaryAttribute transformed = (BoundaryAttribute)((BSPTree)entry.getValue()).getAttribute();
            for (BSPTree splitter : original.getSplitters()) {
                transformed.getSplitters().add((BSPTree)map.get(splitter));
            }
        }
        return this.buildNew((BSPTree)transformedTree);
    }

    private BSPTree<S> recurseTransform(BSPTree<S> node, Transform<S, T> transform, Map<BSPTree<S>, BSPTree<S>> map) {
        BSPTree transformedNode;
        if (node.getCut() == null) {
            transformedNode = new BSPTree(node.getAttribute());
        } else {
            SubHyperplane<S> sub = node.getCut();
            AbstractSubHyperplane<S, T> tSub = ((AbstractSubHyperplane)sub).applyTransform(transform);
            BoundaryAttribute attribute = (BoundaryAttribute)node.getAttribute();
            if (attribute != null) {
                AbstractSubHyperplane<S, T> tPO = attribute.getPlusOutside() == null ? null : ((AbstractSubHyperplane)attribute.getPlusOutside()).applyTransform(transform);
                AbstractSubHyperplane<S, T> tPI = attribute.getPlusInside() == null ? null : ((AbstractSubHyperplane)attribute.getPlusInside()).applyTransform(transform);
                attribute = new BoundaryAttribute(tPO, tPI, new NodesSet());
            }
            transformedNode = new BSPTree<S>(tSub, this.recurseTransform(node.getPlus(), transform, map), this.recurseTransform(node.getMinus(), transform, map), attribute);
        }
        map.put(node, transformedNode);
        return transformedNode;
    }
}

