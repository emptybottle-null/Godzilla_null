/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Embedding;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class BoundaryProjector<S extends Space, T extends Space>
implements BSPTreeVisitor<S> {
    private final Point<S> original;
    private Point<S> projected;
    private BSPTree<S> leaf;
    private double offset;

    BoundaryProjector(Point<S> original) {
        this.original = original;
        this.projected = null;
        this.leaf = null;
        this.offset = Double.POSITIVE_INFINITY;
    }

    @Override
    public BSPTreeVisitor.Order visitOrder(BSPTree<S> node) {
        if (node.getCut().getHyperplane().getOffset(this.original) <= 0.0) {
            return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
        }
        return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
    }

    @Override
    public void visitInternalNode(BSPTree<S> node) {
        Hyperplane<S> hyperplane = node.getCut().getHyperplane();
        double signedOffset = hyperplane.getOffset(this.original);
        if (FastMath.abs(signedOffset) < this.offset) {
            Point<S> regular = hyperplane.project(this.original);
            List<Region<T>> boundaryParts = this.boundaryRegions(node);
            boolean regularFound = false;
            for (Region<T> part : boundaryParts) {
                if (regularFound || !this.belongsToPart(regular, hyperplane, part)) continue;
                this.projected = regular;
                this.offset = FastMath.abs(signedOffset);
                regularFound = true;
            }
            if (!regularFound) {
                for (Region<T> part : boundaryParts) {
                    double distance;
                    Point<S> spI = this.singularProjection(regular, hyperplane, part);
                    if (spI == null || !((distance = this.original.distance(spI)) < this.offset)) continue;
                    this.projected = spI;
                    this.offset = distance;
                }
            }
        }
    }

    @Override
    public void visitLeafNode(BSPTree<S> node) {
        if (this.leaf == null) {
            this.leaf = node;
        }
    }

    public BoundaryProjection<S> getProjection() {
        this.offset = FastMath.copySign(this.offset, (Boolean)this.leaf.getAttribute() != false ? -1.0 : 1.0);
        return new BoundaryProjection<S>(this.original, this.projected, this.offset);
    }

    private List<Region<T>> boundaryRegions(BSPTree<S> node) {
        ArrayList<Region<T>> regions = new ArrayList<Region<T>>(2);
        BoundaryAttribute ba = (BoundaryAttribute)node.getAttribute();
        this.addRegion(ba.getPlusInside(), regions);
        this.addRegion(ba.getPlusOutside(), regions);
        return regions;
    }

    private void addRegion(SubHyperplane<S> sub, List<Region<T>> list) {
        Region region;
        if (sub != null && (region = ((AbstractSubHyperplane)sub).getRemainingRegion()) != null) {
            list.add(region);
        }
    }

    private boolean belongsToPart(Point<S> point, Hyperplane<S> hyperplane, Region<T> part) {
        Embedding embedding = (Embedding)((Object)hyperplane);
        return part.checkPoint(embedding.toSubSpace(point)) != Region.Location.OUTSIDE;
    }

    private Point<S> singularProjection(Point<S> point, Hyperplane<S> hyperplane, Region<T> part) {
        Embedding embedding = (Embedding)((Object)hyperplane);
        BoundaryProjection<T> bp = part.projectToBoundary(embedding.toSubSpace(point));
        return bp.getProjected() == null ? null : embedding.toSpace(bp.getProjected());
    }
}

