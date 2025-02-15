/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.SubOrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Segment;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SubLine
extends AbstractSubHyperplane<Euclidean2D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10;

    public SubLine(Hyperplane<Euclidean2D> hyperplane, Region<Euclidean1D> remainingRegion) {
        super(hyperplane, remainingRegion);
    }

    public SubLine(Vector2D start, Vector2D end, double tolerance) {
        super(new Line(start, end, tolerance), SubLine.buildIntervalSet(start, end, tolerance));
    }

    @Deprecated
    public SubLine(Vector2D start, Vector2D end) {
        this(start, end, 1.0E-10);
    }

    public SubLine(Segment segment) {
        super(segment.getLine(), SubLine.buildIntervalSet(segment.getStart(), segment.getEnd(), segment.getLine().getTolerance()));
    }

    public List<Segment> getSegments() {
        Line line = (Line)this.getHyperplane();
        List<Interval> list = ((IntervalsSet)this.getRemainingRegion()).asList();
        ArrayList<Segment> segments = new ArrayList<Segment>(list.size());
        for (Interval interval : list) {
            Point start = line.toSpace((Point)new Vector1D(interval.getInf()));
            Point end = line.toSpace((Point)new Vector1D(interval.getSup()));
            segments.add(new Segment((Vector2D)start, (Vector2D)end, line));
        }
        return segments;
    }

    public Vector2D intersection(SubLine subLine, boolean includeEndPoints) {
        Line line2;
        Line line1 = (Line)this.getHyperplane();
        Vector2D v2D = line1.intersection(line2 = (Line)subLine.getHyperplane());
        if (v2D == null) {
            return null;
        }
        Region.Location loc1 = this.getRemainingRegion().checkPoint(line1.toSubSpace((Point)v2D));
        Region.Location loc2 = subLine.getRemainingRegion().checkPoint(line2.toSubSpace((Point)v2D));
        if (includeEndPoints) {
            return loc1 != Region.Location.OUTSIDE && loc2 != Region.Location.OUTSIDE ? v2D : null;
        }
        return loc1 == Region.Location.INSIDE && loc2 == Region.Location.INSIDE ? v2D : null;
    }

    private static IntervalsSet buildIntervalSet(Vector2D start, Vector2D end, double tolerance) {
        Line line = new Line(start, end, tolerance);
        return new IntervalsSet(((Vector1D)line.toSubSpace((Point)start)).getX(), ((Vector1D)line.toSubSpace((Point)end)).getX(), tolerance);
    }

    @Override
    protected AbstractSubHyperplane<Euclidean2D, Euclidean1D> buildNew(Hyperplane<Euclidean2D> hyperplane, Region<Euclidean1D> remainingRegion) {
        return new SubLine(hyperplane, remainingRegion);
    }

    @Override
    public SubHyperplane.SplitSubHyperplane<Euclidean2D> split(Hyperplane<Euclidean2D> hyperplane) {
        Line thisLine = (Line)this.getHyperplane();
        Line otherLine = (Line)hyperplane;
        Vector2D crossing = thisLine.intersection(otherLine);
        double tolerance = thisLine.getTolerance();
        if (crossing == null) {
            double global = otherLine.getOffset(thisLine);
            if (global < -tolerance) {
                return new SubHyperplane.SplitSubHyperplane<Euclidean2D>(null, this);
            }
            if (global > tolerance) {
                return new SubHyperplane.SplitSubHyperplane<Euclidean2D>(this, null);
            }
            return new SubHyperplane.SplitSubHyperplane<Euclidean2D>(null, null);
        }
        boolean direct = FastMath.sin(thisLine.getAngle() - otherLine.getAngle()) < 0.0;
        Point x = thisLine.toSubSpace((Point)crossing);
        SubOrientedPoint subPlus = new OrientedPoint((Vector1D)x, !direct, tolerance).wholeHyperplane();
        SubOrientedPoint subMinus = new OrientedPoint((Vector1D)x, direct, tolerance).wholeHyperplane();
        BSPTree<Euclidean1D> splitTree = this.getRemainingRegion().getTree(false).split(subMinus);
        BSPTree<Euclidean1D> plusTree = this.getRemainingRegion().isEmpty(splitTree.getPlus()) ? new BSPTree<Euclidean1D>(Boolean.FALSE) : new BSPTree<Euclidean1D>(subPlus, new BSPTree(Boolean.FALSE), splitTree.getPlus(), null);
        BSPTree<Euclidean1D> minusTree = this.getRemainingRegion().isEmpty(splitTree.getMinus()) ? new BSPTree<Euclidean1D>(Boolean.FALSE) : new BSPTree<Euclidean1D>(subMinus, new BSPTree(Boolean.FALSE), splitTree.getMinus(), null);
        return new SubHyperplane.SplitSubHyperplane<Euclidean2D>(new SubLine(thisLine.copySelf(), new IntervalsSet(plusTree, tolerance)), new SubLine(thisLine.copySelf(), new IntervalsSet(minusTree, tolerance)));
    }
}

