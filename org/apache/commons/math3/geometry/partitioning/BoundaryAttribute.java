/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.NodesSet;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BoundaryAttribute<S extends Space> {
    private final SubHyperplane<S> plusOutside;
    private final SubHyperplane<S> plusInside;
    private final NodesSet<S> splitters;

    @Deprecated
    public BoundaryAttribute(SubHyperplane<S> plusOutside, SubHyperplane<S> plusInside) {
        this(plusOutside, plusInside, null);
    }

    BoundaryAttribute(SubHyperplane<S> plusOutside, SubHyperplane<S> plusInside, NodesSet<S> splitters) {
        this.plusOutside = plusOutside;
        this.plusInside = plusInside;
        this.splitters = splitters;
    }

    public SubHyperplane<S> getPlusOutside() {
        return this.plusOutside;
    }

    public SubHyperplane<S> getPlusInside() {
        return this.plusInside;
    }

    public NodesSet<S> getSplitters() {
        return this.splitters;
    }
}

