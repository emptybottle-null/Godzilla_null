/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.threed.Euclidean3D;
import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Plane;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
import org.apache.commons.math3.geometry.euclidean.twod.SubLine;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SubPlane
extends AbstractSubHyperplane<Euclidean3D, Euclidean2D> {
    public SubPlane(Hyperplane<Euclidean3D> hyperplane, Region<Euclidean2D> remainingRegion) {
        super(hyperplane, remainingRegion);
    }

    @Override
    protected AbstractSubHyperplane<Euclidean3D, Euclidean2D> buildNew(Hyperplane<Euclidean3D> hyperplane, Region<Euclidean2D> remainingRegion) {
        return new SubPlane(hyperplane, remainingRegion);
    }

    @Override
    public SubHyperplane.SplitSubHyperplane<Euclidean3D> split(Hyperplane<Euclidean3D> hyperplane) {
        Plane otherPlane = (Plane)hyperplane;
        Plane thisPlane = (Plane)this.getHyperplane();
        Line inter = otherPlane.intersection(thisPlane);
        double tolerance = thisPlane.getTolerance();
        if (inter == null) {
            double global = otherPlane.getOffset(thisPlane);
            if (global < -tolerance) {
                return new SubHyperplane.SplitSubHyperplane<Euclidean3D>(null, this);
            }
            if (global > tolerance) {
                return new SubHyperplane.SplitSubHyperplane<Euclidean3D>(this, null);
            }
            return new SubHyperplane.SplitSubHyperplane<Euclidean3D>(null, null);
        }
        Point p = thisPlane.toSubSpace(inter.toSpace((Point)Vector1D.ZERO));
        Point q = thisPlane.toSubSpace(inter.toSpace((Point)Vector1D.ONE));
        Vector3D crossP = Vector3D.crossProduct(inter.getDirection(), thisPlane.getNormal());
        if (crossP.dotProduct(otherPlane.getNormal()) < 0.0) {
            Point tmp = p;
            p = q;
            q = tmp;
        }
        SubLine l2DMinus = new org.apache.commons.math3.geometry.euclidean.twod.Line((Vector2D)p, (Vector2D)q, tolerance).wholeHyperplane();
        SubLine l2DPlus = new org.apache.commons.math3.geometry.euclidean.twod.Line((Vector2D)q, (Vector2D)p, tolerance).wholeHyperplane();
        BSPTree<Euclidean2D> splitTree = this.getRemainingRegion().getTree(false).split(l2DMinus);
        BSPTree<Euclidean2D> plusTree = this.getRemainingRegion().isEmpty(splitTree.getPlus()) ? new BSPTree<Euclidean2D>(Boolean.FALSE) : new BSPTree<Euclidean2D>(l2DPlus, new BSPTree(Boolean.FALSE), splitTree.getPlus(), null);
        BSPTree<Euclidean2D> minusTree = this.getRemainingRegion().isEmpty(splitTree.getMinus()) ? new BSPTree<Euclidean2D>(Boolean.FALSE) : new BSPTree<Euclidean2D>(l2DMinus, new BSPTree(Boolean.FALSE), splitTree.getMinus(), null);
        return new SubHyperplane.SplitSubHyperplane<Euclidean3D>(new SubPlane(thisPlane.copySelf(), new PolygonsSet(plusTree, tolerance)), new SubPlane(thisPlane.copySelf(), new PolygonsSet(minusTree, tolerance)));
    }
}

