/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface BSPTreeVisitor<S extends Space> {
    public Order visitOrder(BSPTree<S> var1);

    public void visitInternalNode(BSPTree<S> var1);

    public void visitLeafNode(BSPTree<S> var1);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Order {
        PLUS_MINUS_SUB,
        PLUS_SUB_MINUS,
        MINUS_PLUS_SUB,
        MINUS_SUB_PLUS,
        SUB_PLUS_MINUS,
        SUB_MINUS_PLUS;

    }
}

