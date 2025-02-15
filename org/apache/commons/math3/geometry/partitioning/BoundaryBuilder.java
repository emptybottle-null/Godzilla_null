/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Characterization;
import org.apache.commons.math3.geometry.partitioning.NodesSet;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class BoundaryBuilder<S extends Space>
implements BSPTreeVisitor<S> {
    BoundaryBuilder() {
    }

    @Override
    public BSPTreeVisitor.Order visitOrder(BSPTree<S> node) {
        return BSPTreeVisitor.Order.PLUS_MINUS_SUB;
    }

    @Override
    public void visitInternalNode(BSPTree<S> node) {
        Characterization<S> minusChar;
        SubHyperplane<S> plusOutside = null;
        SubHyperplane<S> plusInside = null;
        NodesSet<S> splitters = null;
        Characterization<S> plusChar = new Characterization<S>(node.getPlus(), node.getCut().copySelf());
        if (plusChar.touchOutside() && (minusChar = new Characterization<S>(node.getMinus(), plusChar.outsideTouching())).touchInside()) {
            plusOutside = minusChar.insideTouching();
            splitters = new NodesSet<S>();
            splitters.addAll(minusChar.getInsideSplitters());
            splitters.addAll(plusChar.getOutsideSplitters());
        }
        if (plusChar.touchInside() && (minusChar = new Characterization<S>(node.getMinus(), plusChar.insideTouching())).touchOutside()) {
            plusInside = minusChar.outsideTouching();
            if (splitters == null) {
                splitters = new NodesSet();
            }
            splitters.addAll(minusChar.getOutsideSplitters());
            splitters.addAll(plusChar.getInsideSplitters());
        }
        if (splitters != null) {
            for (BSPTree<S> up = node.getParent(); up != null; up = up.getParent()) {
                splitters.add(up);
            }
        }
        node.setAttribute(new BoundaryAttribute(plusOutside, plusInside, splitters));
    }

    @Override
    public void visitLeafNode(BSPTree<S> node) {
    }
}

