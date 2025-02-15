/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.NodesSet;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class Characterization<S extends Space> {
    private SubHyperplane<S> outsideTouching = null;
    private SubHyperplane<S> insideTouching = null;
    private final NodesSet<S> outsideSplitters = new NodesSet();
    private final NodesSet<S> insideSplitters = new NodesSet();

    Characterization(BSPTree<S> node, SubHyperplane<S> sub) {
        this.characterize(node, sub, new ArrayList<BSPTree<S>>());
    }

    private void characterize(BSPTree<S> node, SubHyperplane<S> sub, List<BSPTree<S>> splitters) {
        if (node.getCut() == null) {
            boolean inside = (Boolean)node.getAttribute();
            if (inside) {
                this.addInsideTouching(sub, splitters);
            } else {
                this.addOutsideTouching(sub, splitters);
            }
        } else {
            Hyperplane<S> hyperplane = node.getCut().getHyperplane();
            SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
            switch (split.getSide()) {
                case PLUS: {
                    this.characterize(node.getPlus(), sub, splitters);
                    break;
                }
                case MINUS: {
                    this.characterize(node.getMinus(), sub, splitters);
                    break;
                }
                case BOTH: {
                    splitters.add(node);
                    this.characterize(node.getPlus(), split.getPlus(), splitters);
                    this.characterize(node.getMinus(), split.getMinus(), splitters);
                    splitters.remove(splitters.size() - 1);
                    break;
                }
                default: {
                    throw new MathInternalError();
                }
            }
        }
    }

    private void addOutsideTouching(SubHyperplane<S> sub, List<BSPTree<S>> splitters) {
        this.outsideTouching = this.outsideTouching == null ? sub : this.outsideTouching.reunite(sub);
        this.outsideSplitters.addAll(splitters);
    }

    private void addInsideTouching(SubHyperplane<S> sub, List<BSPTree<S>> splitters) {
        this.insideTouching = this.insideTouching == null ? sub : this.insideTouching.reunite(sub);
        this.insideSplitters.addAll(splitters);
    }

    public boolean touchOutside() {
        return this.outsideTouching != null && !this.outsideTouching.isEmpty();
    }

    public SubHyperplane<S> outsideTouching() {
        return this.outsideTouching;
    }

    public NodesSet<S> getOutsideSplitters() {
        return this.outsideSplitters;
    }

    public boolean touchInside() {
        return this.insideTouching != null && !this.insideTouching.isEmpty();
    }

    public SubHyperplane<S> insideTouching() {
        return this.insideTouching;
    }

    public NodesSet<S> getInsideSplitters() {
        return this.insideSplitters;
    }
}

