/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class BoundarySizeVisitor<S extends Space>
implements BSPTreeVisitor<S> {
    private double boundarySize = 0.0;

    BoundarySizeVisitor() {
    }

    @Override
    public BSPTreeVisitor.Order visitOrder(BSPTree<S> node) {
        return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
    }

    @Override
    public void visitInternalNode(BSPTree<S> node) {
        BoundaryAttribute attribute = (BoundaryAttribute)node.getAttribute();
        if (attribute.getPlusOutside() != null) {
            this.boundarySize += attribute.getPlusOutside().getSize();
        }
        if (attribute.getPlusInside() != null) {
            this.boundarySize += attribute.getPlusInside().getSize();
        }
    }

    @Override
    public void visitLeafNode(BSPTree<S> node) {
    }

    public double getSize() {
        return this.boundarySize;
    }
}

