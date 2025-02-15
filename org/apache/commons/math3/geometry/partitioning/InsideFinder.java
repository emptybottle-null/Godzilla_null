/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class InsideFinder<S extends Space> {
    private final Region<S> region;
    private boolean plusFound;
    private boolean minusFound;

    InsideFinder(Region<S> region) {
        this.region = region;
        this.plusFound = false;
        this.minusFound = false;
    }

    public void recurseSides(BSPTree<S> node, SubHyperplane<S> sub) {
        if (node.getCut() == null) {
            if (((Boolean)node.getAttribute()).booleanValue()) {
                this.plusFound = true;
                this.minusFound = true;
            }
            return;
        }
        Hyperplane<S> hyperplane = node.getCut().getHyperplane();
        SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
        switch (split.getSide()) {
            case PLUS: {
                if (node.getCut().split(sub.getHyperplane()).getSide() == Side.PLUS) {
                    if (!this.region.isEmpty(node.getMinus())) {
                        this.plusFound = true;
                    }
                } else if (!this.region.isEmpty(node.getMinus())) {
                    this.minusFound = true;
                }
                if (this.plusFound && this.minusFound) break;
                this.recurseSides(node.getPlus(), sub);
                break;
            }
            case MINUS: {
                if (node.getCut().split(sub.getHyperplane()).getSide() == Side.PLUS) {
                    if (!this.region.isEmpty(node.getPlus())) {
                        this.plusFound = true;
                    }
                } else if (!this.region.isEmpty(node.getPlus())) {
                    this.minusFound = true;
                }
                if (this.plusFound && this.minusFound) break;
                this.recurseSides(node.getMinus(), sub);
                break;
            }
            case BOTH: {
                this.recurseSides(node.getPlus(), split.getPlus());
                if (this.plusFound && this.minusFound) break;
                this.recurseSides(node.getMinus(), split.getMinus());
                break;
            }
            default: {
                if (node.getCut().getHyperplane().sameOrientationAs(sub.getHyperplane())) {
                    if (node.getPlus().getCut() != null || ((Boolean)node.getPlus().getAttribute()).booleanValue()) {
                        this.plusFound = true;
                    }
                    if (node.getMinus().getCut() == null && !((Boolean)node.getMinus().getAttribute()).booleanValue()) break;
                    this.minusFound = true;
                    break;
                }
                if (node.getPlus().getCut() != null || ((Boolean)node.getPlus().getAttribute()).booleanValue()) {
                    this.minusFound = true;
                }
                if (node.getMinus().getCut() == null && !((Boolean)node.getMinus().getAttribute()).booleanValue()) break;
                this.plusFound = true;
            }
        }
    }

    public boolean plusFound() {
        return this.plusFound;
    }

    public boolean minusFound() {
        return this.minusFound;
    }
}

