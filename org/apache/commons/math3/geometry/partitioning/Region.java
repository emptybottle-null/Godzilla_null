/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Region<S extends Space> {
    public Region<S> buildNew(BSPTree<S> var1);

    public Region<S> copySelf();

    public boolean isEmpty();

    public boolean isEmpty(BSPTree<S> var1);

    public boolean isFull();

    public boolean isFull(BSPTree<S> var1);

    public boolean contains(Region<S> var1);

    public Location checkPoint(Point<S> var1);

    public BoundaryProjection<S> projectToBoundary(Point<S> var1);

    public BSPTree<S> getTree(boolean var1);

    public double getBoundarySize();

    public double getSize();

    public Point<S> getBarycenter();

    @Deprecated
    public Side side(Hyperplane<S> var1);

    public SubHyperplane<S> intersection(SubHyperplane<S> var1);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Location {
        INSIDE,
        OUTSIDE,
        BOUNDARY;

    }
}

