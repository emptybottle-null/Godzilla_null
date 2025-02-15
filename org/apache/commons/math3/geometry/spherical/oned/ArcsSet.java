/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.oned.LimitAngle;
import org.apache.commons.math3.geometry.spherical.oned.S1Point;
import org.apache.commons.math3.geometry.spherical.oned.Sphere1D;
import org.apache.commons.math3.geometry.spherical.oned.SubLimitAngle;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ArcsSet
extends AbstractRegion<Sphere1D, Sphere1D>
implements Iterable<double[]> {
    public ArcsSet(double tolerance) {
        super(tolerance);
    }

    public ArcsSet(double lower, double upper, double tolerance) throws NumberIsTooLargeException {
        super(ArcsSet.buildTree(lower, upper, tolerance), tolerance);
    }

    public ArcsSet(BSPTree<Sphere1D> tree, double tolerance) throws InconsistentStateAt2PiWrapping {
        super(tree, tolerance);
        this.check2PiConsistency();
    }

    public ArcsSet(Collection<SubHyperplane<Sphere1D>> boundary, double tolerance) throws InconsistentStateAt2PiWrapping {
        super(boundary, tolerance);
        this.check2PiConsistency();
    }

    private static BSPTree<Sphere1D> buildTree(double lower, double upper, double tolerance) throws NumberIsTooLargeException {
        if (Precision.equals(lower, upper, 0) || upper - lower >= Math.PI * 2) {
            return new BSPTree<Sphere1D>(Boolean.TRUE);
        }
        if (lower > upper) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, (Number)lower, upper, true);
        }
        double normalizedLower = MathUtils.normalizeAngle(lower, Math.PI);
        double normalizedUpper = normalizedLower + (upper - lower);
        SubLimitAngle lowerCut = new LimitAngle(new S1Point(normalizedLower), false, tolerance).wholeHyperplane();
        if (normalizedUpper <= Math.PI * 2) {
            SubLimitAngle upperCut = new LimitAngle(new S1Point(normalizedUpper), true, tolerance).wholeHyperplane();
            return new BSPTree<Sphere1D>(lowerCut, new BSPTree(Boolean.FALSE), new BSPTree<Sphere1D>(upperCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), null);
        }
        SubLimitAngle upperCut = new LimitAngle(new S1Point(normalizedUpper - Math.PI * 2), true, tolerance).wholeHyperplane();
        return new BSPTree<Sphere1D>(lowerCut, new BSPTree<Sphere1D>(upperCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), new BSPTree(Boolean.TRUE), null);
    }

    private void check2PiConsistency() throws InconsistentStateAt2PiWrapping {
        BSPTree<Sphere1D> root = this.getTree(false);
        if (root.getCut() == null) {
            return;
        }
        Boolean stateBefore = (Boolean)this.getFirstLeaf(root).getAttribute();
        Boolean stateAfter = (Boolean)this.getLastLeaf(root).getAttribute();
        if (stateBefore ^ stateAfter) {
            throw new InconsistentStateAt2PiWrapping();
        }
    }

    private BSPTree<Sphere1D> getFirstLeaf(BSPTree<Sphere1D> root) {
        if (root.getCut() == null) {
            return root;
        }
        BSPTree<Sphere1D> smallest = null;
        BSPTree<Sphere1D> n = root;
        while (n != null) {
            smallest = n;
            n = this.previousInternalNode(n);
        }
        return this.leafBefore(smallest);
    }

    private BSPTree<Sphere1D> getLastLeaf(BSPTree<Sphere1D> root) {
        if (root.getCut() == null) {
            return root;
        }
        BSPTree<Sphere1D> largest = null;
        BSPTree<Sphere1D> n = root;
        while (n != null) {
            largest = n;
            n = this.nextInternalNode(n);
        }
        return this.leafAfter(largest);
    }

    private BSPTree<Sphere1D> getFirstArcStart() {
        BSPTree<Sphere1D> node = this.getTree(false);
        if (node.getCut() == null) {
            return null;
        }
        node = this.getFirstLeaf(node).getParent();
        while (node != null && !this.isArcStart(node)) {
            node = this.nextInternalNode(node);
        }
        return node;
    }

    private boolean isArcStart(BSPTree<Sphere1D> node) {
        if (((Boolean)this.leafBefore(node).getAttribute()).booleanValue()) {
            return false;
        }
        return (Boolean)this.leafAfter(node).getAttribute() != false;
    }

    private boolean isArcEnd(BSPTree<Sphere1D> node) {
        if (!((Boolean)this.leafBefore(node).getAttribute()).booleanValue()) {
            return false;
        }
        return (Boolean)this.leafAfter(node).getAttribute() == false;
    }

    private BSPTree<Sphere1D> nextInternalNode(BSPTree<Sphere1D> node) {
        if (this.childAfter(node).getCut() != null) {
            return this.leafAfter(node).getParent();
        }
        while (this.isAfterParent(node)) {
            node = node.getParent();
        }
        return node.getParent();
    }

    private BSPTree<Sphere1D> previousInternalNode(BSPTree<Sphere1D> node) {
        if (this.childBefore(node).getCut() != null) {
            return this.leafBefore(node).getParent();
        }
        while (this.isBeforeParent(node)) {
            node = node.getParent();
        }
        return node.getParent();
    }

    private BSPTree<Sphere1D> leafBefore(BSPTree<Sphere1D> node) {
        node = this.childBefore(node);
        while (node.getCut() != null) {
            node = this.childAfter(node);
        }
        return node;
    }

    private BSPTree<Sphere1D> leafAfter(BSPTree<Sphere1D> node) {
        node = this.childAfter(node);
        while (node.getCut() != null) {
            node = this.childBefore(node);
        }
        return node;
    }

    private boolean isBeforeParent(BSPTree<Sphere1D> node) {
        BSPTree<Sphere1D> parent = node.getParent();
        if (parent == null) {
            return false;
        }
        return node == this.childBefore(parent);
    }

    private boolean isAfterParent(BSPTree<Sphere1D> node) {
        BSPTree<Sphere1D> parent = node.getParent();
        if (parent == null) {
            return false;
        }
        return node == this.childAfter(parent);
    }

    private BSPTree<Sphere1D> childBefore(BSPTree<Sphere1D> node) {
        if (this.isDirect(node)) {
            return node.getMinus();
        }
        return node.getPlus();
    }

    private BSPTree<Sphere1D> childAfter(BSPTree<Sphere1D> node) {
        if (this.isDirect(node)) {
            return node.getPlus();
        }
        return node.getMinus();
    }

    private boolean isDirect(BSPTree<Sphere1D> node) {
        return ((LimitAngle)node.getCut().getHyperplane()).isDirect();
    }

    private double getAngle(BSPTree<Sphere1D> node) {
        return ((LimitAngle)node.getCut().getHyperplane()).getLocation().getAlpha();
    }

    public ArcsSet buildNew(BSPTree<Sphere1D> tree) {
        return new ArcsSet(tree, this.getTolerance());
    }

    @Override
    protected void computeGeometricalProperties() {
        if (this.getTree(false).getCut() == null) {
            this.setBarycenter(S1Point.NaN);
            this.setSize((Boolean)this.getTree(false).getAttribute() != false ? Math.PI * 2 : 0.0);
        } else {
            double size = 0.0;
            double sum = 0.0;
            for (double[] a : this) {
                double length = a[1] - a[0];
                size += length;
                sum += length * (a[0] + a[1]);
            }
            this.setSize(size);
            if (Precision.equals(size, Math.PI * 2, 0)) {
                this.setBarycenter(S1Point.NaN);
            } else if (size >= Precision.SAFE_MIN) {
                this.setBarycenter(new S1Point(sum / (2.0 * size)));
            } else {
                LimitAngle limit = (LimitAngle)this.getTree(false).getCut().getHyperplane();
                this.setBarycenter(limit.getLocation());
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public BoundaryProjection<Sphere1D> projectToBoundary(Point<Sphere1D> point) {
        double alpha = ((S1Point)point).getAlpha();
        boolean wrapFirst = false;
        double first = Double.NaN;
        double previous = Double.NaN;
        for (double[] a : this) {
            block13: {
                if (Double.isNaN(first)) {
                    first = a[0];
                }
                if (!wrapFirst) {
                    if (alpha < a[0]) {
                        if (Double.isNaN(previous)) {
                            wrapFirst = true;
                            break block13;
                        } else {
                            double previousOffset = alpha - previous;
                            double currentOffset = a[0] - alpha;
                            if (previousOffset < currentOffset) {
                                return new BoundaryProjection<Sphere1D>(point, new S1Point(previous), previousOffset);
                            }
                            return new BoundaryProjection<Sphere1D>(point, new S1Point(a[0]), currentOffset);
                        }
                    }
                    if (alpha <= a[1]) {
                        double offset0 = a[0] - alpha;
                        double offset1 = alpha - a[1];
                        if (offset0 < offset1) {
                            return new BoundaryProjection<Sphere1D>(point, new S1Point(a[1]), offset1);
                        }
                        return new BoundaryProjection<Sphere1D>(point, new S1Point(a[0]), offset0);
                    }
                }
            }
            previous = a[1];
        }
        if (Double.isNaN(previous)) {
            return new BoundaryProjection<Sphere1D>(point, null, Math.PI * 2);
        }
        if (wrapFirst) {
            double previousOffset = alpha - (previous - Math.PI * 2);
            double currentOffset = first - alpha;
            if (previousOffset < currentOffset) {
                return new BoundaryProjection<Sphere1D>(point, new S1Point(previous), previousOffset);
            }
            return new BoundaryProjection<Sphere1D>(point, new S1Point(first), currentOffset);
        }
        double previousOffset = alpha - previous;
        double currentOffset = first + Math.PI * 2 - alpha;
        if (previousOffset < currentOffset) {
            return new BoundaryProjection<Sphere1D>(point, new S1Point(previous), previousOffset);
        }
        return new BoundaryProjection<Sphere1D>(point, new S1Point(first), currentOffset);
    }

    public List<Arc> asList() {
        ArrayList<Arc> list = new ArrayList<Arc>();
        for (double[] a : this) {
            list.add(new Arc(a[0], a[1], this.getTolerance()));
        }
        return list;
    }

    @Override
    public Iterator<double[]> iterator() {
        return new SubArcsIterator();
    }

    @Deprecated
    public Side side(Arc arc) {
        return this.split(arc).getSide();
    }

    public Split split(Arc arc) {
        ArrayList<Double> minus = new ArrayList<Double>();
        ArrayList<Double> plus = new ArrayList<Double>();
        double reference = Math.PI + arc.getInf();
        double arcLength = arc.getSup() - arc.getInf();
        for (double[] a : this) {
            double syncedStart = MathUtils.normalizeAngle(a[0], reference) - arc.getInf();
            double arcOffset = a[0] - syncedStart;
            double syncedEnd = a[1] - arcOffset;
            if (syncedStart < arcLength) {
                minus.add(a[0]);
                if (syncedEnd > arcLength) {
                    double minusToPlus = arcLength + arcOffset;
                    minus.add(minusToPlus);
                    plus.add(minusToPlus);
                    if (syncedEnd > Math.PI * 2) {
                        double plusToMinus = Math.PI * 2 + arcOffset;
                        plus.add(plusToMinus);
                        minus.add(plusToMinus);
                        minus.add(a[1]);
                        continue;
                    }
                    plus.add(a[1]);
                    continue;
                }
                minus.add(a[1]);
                continue;
            }
            plus.add(a[0]);
            if (syncedEnd > Math.PI * 2) {
                double plusToMinus = Math.PI * 2 + arcOffset;
                plus.add(plusToMinus);
                minus.add(plusToMinus);
                if (syncedEnd > Math.PI * 2 + arcLength) {
                    double minusToPlus = Math.PI * 2 + arcLength + arcOffset;
                    minus.add(minusToPlus);
                    plus.add(minusToPlus);
                    plus.add(a[1]);
                    continue;
                }
                minus.add(a[1]);
                continue;
            }
            plus.add(a[1]);
        }
        return new Split(this.createSplitPart(plus), this.createSplitPart(minus));
    }

    private void addArcLimit(BSPTree<Sphere1D> tree, double alpha, boolean isStart) {
        LimitAngle limit = new LimitAngle(new S1Point(alpha), !isStart, this.getTolerance());
        BSPTree<Sphere1D> node = tree.getCell(limit.getLocation(), this.getTolerance());
        if (node.getCut() != null) {
            throw new MathInternalError();
        }
        node.insertCut(limit);
        node.setAttribute(null);
        node.getPlus().setAttribute(Boolean.FALSE);
        node.getMinus().setAttribute(Boolean.TRUE);
    }

    private ArcsSet createSplitPart(List<Double> limits) {
        if (limits.isEmpty()) {
            return null;
        }
        for (int i = 0; i < limits.size(); ++i) {
            int j = (i + 1) % limits.size();
            double lA = limits.get(i);
            double lB = MathUtils.normalizeAngle(limits.get(j), lA);
            if (!(FastMath.abs(lB - lA) <= this.getTolerance())) continue;
            if (j > 0) {
                limits.remove(j);
                limits.remove(i);
                --i;
                continue;
            }
            double lEnd = limits.remove(limits.size() - 1);
            double lStart = limits.remove(0);
            if (limits.isEmpty()) {
                if (lEnd - lStart > Math.PI) {
                    return new ArcsSet(new BSPTree<Sphere1D>(Boolean.TRUE), this.getTolerance());
                }
                return null;
            }
            limits.add(limits.remove(0) + Math.PI * 2);
        }
        BSPTree<Sphere1D> tree = new BSPTree<Sphere1D>(Boolean.FALSE);
        for (int i = 0; i < limits.size() - 1; i += 2) {
            this.addArcLimit(tree, limits.get(i), true);
            this.addArcLimit(tree, limits.get(i + 1), false);
        }
        if (tree.getCut() == null) {
            return null;
        }
        return new ArcsSet(tree, this.getTolerance());
    }

    public static class InconsistentStateAt2PiWrapping
    extends MathIllegalArgumentException {
        private static final long serialVersionUID = 20140107L;

        public InconsistentStateAt2PiWrapping() {
            super(LocalizedFormats.INCONSISTENT_STATE_AT_2_PI_WRAPPING, new Object[0]);
        }
    }

    public static class Split {
        private final ArcsSet plus;
        private final ArcsSet minus;

        private Split(ArcsSet plus, ArcsSet minus) {
            this.plus = plus;
            this.minus = minus;
        }

        public ArcsSet getPlus() {
            return this.plus;
        }

        public ArcsSet getMinus() {
            return this.minus;
        }

        public Side getSide() {
            if (this.plus != null) {
                if (this.minus != null) {
                    return Side.BOTH;
                }
                return Side.PLUS;
            }
            if (this.minus != null) {
                return Side.MINUS;
            }
            return Side.HYPER;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class SubArcsIterator
    implements Iterator<double[]> {
        private final BSPTree<Sphere1D> firstStart;
        private BSPTree<Sphere1D> current;
        private double[] pending;

        SubArcsIterator() {
            this.firstStart = ArcsSet.this.getFirstArcStart();
            this.current = this.firstStart;
            if (this.firstStart == null) {
                this.pending = (double[])(((Boolean)ArcsSet.this.getFirstLeaf(ArcsSet.this.getTree(false)).getAttribute()).booleanValue() ? new double[]{0.0, Math.PI * 2} : null);
            } else {
                this.selectPending();
            }
        }

        private void selectPending() {
            BSPTree start = this.current;
            while (start != null && !ArcsSet.this.isArcStart(start)) {
                start = ArcsSet.this.nextInternalNode(start);
            }
            if (start == null) {
                this.current = null;
                this.pending = null;
                return;
            }
            BSPTree end = start;
            while (end != null && !ArcsSet.this.isArcEnd(end)) {
                end = ArcsSet.this.nextInternalNode(end);
            }
            if (end != null) {
                this.pending = new double[]{ArcsSet.this.getAngle(start), ArcsSet.this.getAngle(end)};
                this.current = end;
            } else {
                end = this.firstStart;
                while (end != null && !ArcsSet.this.isArcEnd(end)) {
                    end = ArcsSet.this.previousInternalNode(end);
                }
                if (end == null) {
                    throw new MathInternalError();
                }
                this.pending = new double[]{ArcsSet.this.getAngle(start), ArcsSet.this.getAngle(end) + Math.PI * 2};
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

