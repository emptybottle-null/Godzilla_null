/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.SubOrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IntervalsSet
extends AbstractRegion<Euclidean1D, Euclidean1D>
implements Iterable<double[]> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10;

    public IntervalsSet(double tolerance) {
        super(tolerance);
    }

    public IntervalsSet(double lower, double upper, double tolerance) {
        super(IntervalsSet.buildTree(lower, upper, tolerance), tolerance);
    }

    public IntervalsSet(BSPTree<Euclidean1D> tree, double tolerance) {
        super(tree, tolerance);
    }

    public IntervalsSet(Collection<SubHyperplane<Euclidean1D>> boundary, double tolerance) {
        super(boundary, tolerance);
    }

    @Deprecated
    public IntervalsSet() {
        this(1.0E-10);
    }

    @Deprecated
    public IntervalsSet(double lower, double upper) {
        this(lower, upper, 1.0E-10);
    }

    @Deprecated
    public IntervalsSet(BSPTree<Euclidean1D> tree) {
        this(tree, 1.0E-10);
    }

    @Deprecated
    public IntervalsSet(Collection<SubHyperplane<Euclidean1D>> boundary) {
        this(boundary, 1.0E-10);
    }

    private static BSPTree<Euclidean1D> buildTree(double lower, double upper, double tolerance) {
        if (Double.isInfinite(lower) && lower < 0.0) {
            if (Double.isInfinite(upper) && upper > 0.0) {
                return new BSPTree<Euclidean1D>(Boolean.TRUE);
            }
            SubOrientedPoint upperCut = new OrientedPoint(new Vector1D(upper), true, tolerance).wholeHyperplane();
            return new BSPTree<Euclidean1D>(upperCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null);
        }
        SubOrientedPoint lowerCut = new OrientedPoint(new Vector1D(lower), false, tolerance).wholeHyperplane();
        if (Double.isInfinite(upper) && upper > 0.0) {
            return new BSPTree<Euclidean1D>(lowerCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null);
        }
        SubOrientedPoint upperCut = new OrientedPoint(new Vector1D(upper), true, tolerance).wholeHyperplane();
        return new BSPTree<Euclidean1D>(lowerCut, new BSPTree(Boolean.FALSE), new BSPTree<Euclidean1D>(upperCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), null);
    }

    public IntervalsSet buildNew(BSPTree<Euclidean1D> tree) {
        return new IntervalsSet(tree, this.getTolerance());
    }

    @Override
    protected void computeGeometricalProperties() {
        if (this.getTree(false).getCut() == null) {
            this.setBarycenter(Vector1D.NaN);
            this.setSize((Boolean)this.getTree(false).getAttribute() != false ? Double.POSITIVE_INFINITY : 0.0);
        } else {
            double size = 0.0;
            double sum = 0.0;
            for (Interval interval : this.asList()) {
                size += interval.getSize();
                sum += interval.getSize() * interval.getBarycenter();
            }
            this.setSize(size);
            if (Double.isInfinite(size)) {
                this.setBarycenter(Vector1D.NaN);
            } else if (size >= Precision.SAFE_MIN) {
                this.setBarycenter(new Vector1D(sum / size));
            } else {
                this.setBarycenter(((OrientedPoint)this.getTree(false).getCut().getHyperplane()).getLocation());
            }
        }
    }

    public double getInf() {
        BSPTree node = this.getTree(false);
        double inf = Double.POSITIVE_INFINITY;
        while (node.getCut() != null) {
            OrientedPoint op = (OrientedPoint)node.getCut().getHyperplane();
            inf = op.getLocation().getX();
            node = op.isDirect() ? node.getMinus() : node.getPlus();
        }
        return (Boolean)node.getAttribute() != false ? Double.NEGATIVE_INFINITY : inf;
    }

    public double getSup() {
        BSPTree node = this.getTree(false);
        double sup = Double.NEGATIVE_INFINITY;
        while (node.getCut() != null) {
            OrientedPoint op = (OrientedPoint)node.getCut().getHyperplane();
            sup = op.getLocation().getX();
            node = op.isDirect() ? node.getPlus() : node.getMinus();
        }
        return (Boolean)node.getAttribute() != false ? Double.POSITIVE_INFINITY : sup;
    }

    @Override
    public BoundaryProjection<Euclidean1D> projectToBoundary(Point<Euclidean1D> point) {
        double x = ((Vector1D)point).getX();
        double previous = Double.NEGATIVE_INFINITY;
        for (double[] a : this) {
            if (x < a[0]) {
                double previousOffset = x - previous;
                double currentOffset = a[0] - x;
                if (previousOffset < currentOffset) {
                    return new BoundaryProjection<Euclidean1D>(point, this.finiteOrNullPoint(previous), previousOffset);
                }
                return new BoundaryProjection<Euclidean1D>(point, this.finiteOrNullPoint(a[0]), currentOffset);
            }
            if (x <= a[1]) {
                double offset0 = a[0] - x;
                double offset1 = x - a[1];
                if (offset0 < offset1) {
                    return new BoundaryProjection<Euclidean1D>(point, this.finiteOrNullPoint(a[1]), offset1);
                }
                return new BoundaryProjection<Euclidean1D>(point, this.finiteOrNullPoint(a[0]), offset0);
            }
            previous = a[1];
        }
        return new BoundaryProjection<Euclidean1D>(point, this.finiteOrNullPoint(previous), x - previous);
    }

    private Vector1D finiteOrNullPoint(double x) {
        return Double.isInfinite(x) ? null : new Vector1D(x);
    }

    public List<Interval> asList() {
        ArrayList<Interval> list = new ArrayList<Interval>();
        for (double[] a : this) {
            list.add(new Interval(a[0], a[1]));
        }
        return list;
    }

    private BSPTree<Euclidean1D> getFirstLeaf(BSPTree<Euclidean1D> root) {
        if (root.getCut() == null) {
            return root;
        }
        BSPTree<Euclidean1D> smallest = null;
        BSPTree<Euclidean1D> n = root;
        while (n != null) {
            smallest = n;
            n = this.previousInternalNode(n);
        }
        return this.leafBefore(smallest);
    }

    private BSPTree<Euclidean1D> getFirstIntervalBoundary() {
        BSPTree<Euclidean1D> node = this.getTree(false);
        if (node.getCut() == null) {
            return null;
        }
        node = this.getFirstLeaf(node).getParent();
        while (node != null && !this.isIntervalStart(node) && !this.isIntervalEnd(node)) {
            node = this.nextInternalNode(node);
        }
        return node;
    }

    private boolean isIntervalStart(BSPTree<Euclidean1D> node) {
        if (((Boolean)this.leafBefore(node).getAttribute()).booleanValue()) {
            return false;
        }
        return (Boolean)this.leafAfter(node).getAttribute() != false;
    }

    private boolean isIntervalEnd(BSPTree<Euclidean1D> node) {
        if (!((Boolean)this.leafBefore(node).getAttribute()).booleanValue()) {
            return false;
        }
        return (Boolean)this.leafAfter(node).getAttribute() == false;
    }

    private BSPTree<Euclidean1D> nextInternalNode(BSPTree<Euclidean1D> node) {
        if (this.childAfter(node).getCut() != null) {
            return this.leafAfter(node).getParent();
        }
        while (this.isAfterParent(node)) {
            node = node.getParent();
        }
        return node.getParent();
    }

    private BSPTree<Euclidean1D> previousInternalNode(BSPTree<Euclidean1D> node) {
        if (this.childBefore(node).getCut() != null) {
            return this.leafBefore(node).getParent();
        }
        while (this.isBeforeParent(node)) {
            node = node.getParent();
        }
        return node.getParent();
    }

    private BSPTree<Euclidean1D> leafBefore(BSPTree<Euclidean1D> node) {
        node = this.childBefore(node);
        while (node.getCut() != null) {
            node = this.childAfter(node);
        }
        return node;
    }

    private BSPTree<Euclidean1D> leafAfter(BSPTree<Euclidean1D> node) {
        node = this.childAfter(node);
        while (node.getCut() != null) {
            node = this.childBefore(node);
        }
        return node;
    }

    private boolean isBeforeParent(BSPTree<Euclidean1D> node) {
        BSPTree<Euclidean1D> parent = node.getParent();
        if (parent == null) {
            return false;
        }
        return node == this.childBefore(parent);
    }

    private boolean isAfterParent(BSPTree<Euclidean1D> node) {
        BSPTree<Euclidean1D> parent = node.getParent();
        if (parent == null) {
            return false;
        }
        return node == this.childAfter(parent);
    }

    private BSPTree<Euclidean1D> childBefore(BSPTree<Euclidean1D> node) {
        if (this.isDirect(node)) {
            return node.getMinus();
        }
        return node.getPlus();
    }

    private BSPTree<Euclidean1D> childAfter(BSPTree<Euclidean1D> node) {
        if (this.isDirect(node)) {
            return node.getPlus();
        }
        return node.getMinus();
    }

    private boolean isDirect(BSPTree<Euclidean1D> node) {
        return ((OrientedPoint)node.getCut().getHyperplane()).isDirect();
    }

    private double getAngle(BSPTree<Euclidean1D> node) {
        return ((OrientedPoint)node.getCut().getHyperplane()).getLocation().getX();
    }

    @Override
    public Iterator<double[]> iterator() {
        return new SubIntervalsIterator();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class SubIntervalsIterator
    implements Iterator<double[]> {
        private BSPTree<Euclidean1D> current;
        private double[] pending;

        SubIntervalsIterator() {
            this.current = IntervalsSet.this.getFirstIntervalBoundary();
            if (this.current == null) {
                this.pending = (double[])(((Boolean)IntervalsSet.this.getFirstLeaf(IntervalsSet.this.getTree(false)).getAttribute()).booleanValue() ? new double[]{Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY} : null);
            } else if (IntervalsSet.this.isIntervalEnd(this.current)) {
                this.pending = new double[]{Double.NEGATIVE_INFINITY, IntervalsSet.this.getAngle(this.current)};
            } else {
                this.selectPending();
            }
        }

        private void selectPending() {
            BSPTree start = this.current;
            while (start != null && !IntervalsSet.this.isIntervalStart(start)) {
                start = IntervalsSet.this.nextInternalNode(start);
            }
            if (start == null) {
                this.current = null;
                this.pending = null;
                return;
            }
            BSPTree end = start;
            while (end != null && !IntervalsSet.this.isIntervalEnd(end)) {
                end = IntervalsSet.this.nextInternalNode(end);
            }
            if (end != null) {
                this.pending = new double[]{IntervalsSet.this.getAngle(start), IntervalsSet.this.getAngle(end)};
                this.current = end;
            } else {
                this.pending = new double[]{IntervalsSet.this.getAngle(start), Double.POSITIVE_INFINITY};
                this.current = null;
            }
        }

        @Override
        public boolean hasNext() {
            return this.pending != null;
        }

        @Override
        public double[] next() {
            if (this.pending == null) {
                throw new NoSuchElementException();
            }
            double[] next = this.pending;
            this.selectPending();
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

