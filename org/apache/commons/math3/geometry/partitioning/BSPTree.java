/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BSPTree<S extends Space> {
    private SubHyperplane<S> cut;
    private BSPTree<S> plus;
    private BSPTree<S> minus;
    private BSPTree<S> parent;
    private Object attribute;

    public BSPTree() {
        this.cut = null;
        this.plus = null;
        this.minus = null;
        this.parent = null;
        this.attribute = null;
    }

    public BSPTree(Object attribute) {
        this.cut = null;
        this.plus = null;
        this.minus = null;
        this.parent = null;
        this.attribute = attribute;
    }

    public BSPTree(SubHyperplane<S> cut, BSPTree<S> plus, BSPTree<S> minus, Object attribute) {
        this.cut = cut;
        this.plus = plus;
        this.minus = minus;
        this.parent = null;
        this.attribute = attribute;
        plus.parent = this;
        minus.parent = this;
    }

    public boolean insertCut(Hyperplane<S> hyperplane) {
        SubHyperplane<S> chopped;
        if (this.cut != null) {
            this.plus.parent = null;
            this.minus.parent = null;
        }
        if ((chopped = this.fitToCell(hyperplane.wholeHyperplane())) == null || chopped.isEmpty()) {
            this.cut = null;
            this.plus = null;
            this.minus = null;
            return false;
        }
        this.cut = chopped;
        this.plus = new BSPTree<S>();
        this.plus.parent = this;
        this.minus = new BSPTree<S>();
        this.minus.parent = this;
        return true;
    }

    public BSPTree<S> copySelf() {
        if (this.cut == null) {
            return new BSPTree<S>(this.attribute);
        }
        return new BSPTree<S>(this.cut.copySelf(), this.plus.copySelf(), this.minus.copySelf(), this.attribute);
    }

    public SubHyperplane<S> getCut() {
        return this.cut;
    }

    public BSPTree<S> getPlus() {
        return this.plus;
    }

    public BSPTree<S> getMinus() {
        return this.minus;
    }

    public BSPTree<S> getParent() {
        return this.parent;
    }

    public void setAttribute(Object attribute) {
        this.attribute = attribute;
    }

    public Object getAttribute() {
        return this.attribute;
    }

    public void visit(BSPTreeVisitor<S> visitor) {
        if (this.cut == null) {
            visitor.visitLeafNode(this);
        } else {
            switch (visitor.visitOrder(this)) {
                case PLUS_MINUS_SUB: {
                    this.plus.visit(visitor);
                    this.minus.visit(visitor);
                    visitor.visitInternalNode(this);
                    break;
                }
                case PLUS_SUB_MINUS: {
                    this.plus.visit(visitor);
                    visitor.visitInternalNode(this);
                    this.minus.visit(visitor);
                    break;
                }
                case MINUS_PLUS_SUB: {
                    this.minus.visit(visitor);
                    this.plus.visit(visitor);
                    visitor.visitInternalNode(this);
                    break;
                }
                case MINUS_SUB_PLUS: {
                    this.minus.visit(visitor);
                    visitor.visitInternalNode(this);
                    this.plus.visit(visitor);
                    break;
                }
                case SUB_PLUS_MINUS: {
                    visitor.visitInternalNode(this);
                    this.plus.visit(visitor);
                    this.minus.visit(visitor);
                    break;
                }
                case SUB_MINUS_PLUS: {
                    visitor.visitInternalNode(this);
                    this.minus.visit(visitor);
                    this.plus.visit(visitor);
                    break;
                }
                default: {
                    throw new MathInternalError();
                }
            }
        }
    }

    private SubHyperplane<S> fitToCell(SubHyperplane<S> sub) {
        SubHyperplane<S> s = sub;
        BSPTree<S> tree = this;
        while (tree.parent != null && s != null) {
            s = tree == tree.parent.plus ? s.split(tree.parent.cut.getHyperplane()).getPlus() : s.split(tree.parent.cut.getHyperplane()).getMinus();
            tree = tree.parent;
        }
        return s;
    }

    @Deprecated
    public BSPTree<S> getCell(Vector<S> point) {
        return this.getCell(point, 1.0E-10);
    }

    public BSPTree<S> getCell(Point<S> point, double tolerance) {
        if (this.cut == null) {
            return this;
        }
        double offset = this.cut.getHyperplane().getOffset(point);
        if (FastMath.abs(offset) < tolerance) {
            return this;
        }
        if (offset <= 0.0) {
            return this.minus.getCell(point, tolerance);
        }
        return this.plus.getCell(point, tolerance);
    }

    public List<BSPTree<S>> getCloseCuts(Point<S> point, double maxOffset) {
        ArrayList<BSPTree<S>> close = new ArrayList<BSPTree<S>>();
        this.recurseCloseCuts(point, maxOffset, close);
        return close;
    }

    private void recurseCloseCuts(Point<S> point, double maxOffset, List<BSPTree<S>> close) {
        if (this.cut != null) {
            double offset = this.cut.getHyperplane().getOffset(point);
            if (offset < -maxOffset) {
                super.recurseCloseCuts(point, maxOffset, close);
            } else if (offset > maxOffset) {
                super.recurseCloseCuts(point, maxOffset, close);
            } else {
                close.add(this);
                super.recurseCloseCuts(point, maxOffset, close);
                super.recurseCloseCuts(point, maxOffset, close);
            }
        }
    }

    private void condense() {
        if (this.cut != null && this.plus.cut == null && this.minus.cut == null && (this.plus.attribute == null && this.minus.attribute == null || this.plus.attribute != null && this.plus.attribute.equals(this.minus.attribute))) {
            this.attribute = this.plus.attribute == null ? this.minus.attribute : this.plus.attribute;
            this.cut = null;
            this.plus = null;
            this.minus = null;
        }
    }

    public BSPTree<S> merge(BSPTree<S> tree, LeafMerger<S> leafMerger) {
        return this.merge(tree, leafMerger, null, false);
    }

    private BSPTree<S> merge(BSPTree<S> tree, LeafMerger<S> leafMerger, BSPTree<S> parentTree, boolean isPlusChild) {
        if (this.cut == null) {
            return leafMerger.merge(this, tree, parentTree, isPlusChild, true);
        }
        if (tree.cut == null) {
            return leafMerger.merge(tree, this, parentTree, isPlusChild, false);
        }
        BSPTree<S> merged = tree.split(this.cut);
        if (parentTree != null) {
            merged.parent = parentTree;
            if (isPlusChild) {
                parentTree.plus = merged;
            } else {
                parentTree.minus = merged;
            }
        }
        super.merge(merged.plus, leafMerger, merged, true);
        super.merge(merged.minus, leafMerger, merged, false);
        super.condense();
        if (merged.cut != null) {
            merged.cut = super.fitToCell(merged.cut.getHyperplane().wholeHyperplane());
        }
        return merged;
    }

    public BSPTree<S> split(SubHyperplane<S> sub) {
        if (this.cut == null) {
            return new BSPTree<S>(sub, this.copySelf(), new BSPTree<S>(this.attribute), null);
        }
        Hyperplane<S> cHyperplane = this.cut.getHyperplane();
        Hyperplane<S> sHyperplane = sub.getHyperplane();
        SubHyperplane.SplitSubHyperplane<S> subParts = sub.split(cHyperplane);
        switch (subParts.getSide()) {
            case PLUS: {
                BSPTree<S> split = this.plus.split(sub);
                if (this.cut.split(sHyperplane).getSide() == Side.PLUS) {
                    split.plus = new BSPTree<S>(this.cut.copySelf(), split.plus, this.minus.copySelf(), this.attribute);
                    super.condense();
                    split.plus.parent = split;
                } else {
                    split.minus = new BSPTree<S>(this.cut.copySelf(), split.minus, this.minus.copySelf(), this.attribute);
                    super.condense();
                    split.minus.parent = split;
                }
                return split;
            }
            case MINUS: {
                BSPTree<S> split = this.minus.split(sub);
                if (this.cut.split(sHyperplane).getSide() == Side.PLUS) {
                    split.plus = new BSPTree<S>(this.cut.copySelf(), this.plus.copySelf(), split.plus, this.attribute);
                    super.condense();
                    split.plus.parent = split;
                } else {
                    split.minus = new BSPTree<S>(this.cut.copySelf(), this.plus.copySelf(), split.minus, this.attribute);
                    super.condense();
                    split.minus.parent = split;
                }
                return split;
            }
            case BOTH: {
                SubHyperplane.SplitSubHyperplane<S> cutParts = this.cut.split(sHyperplane);
                BSPTree<S> split = new BSPTree<S>(sub, this.plus.split(subParts.getPlus()), this.minus.split(subParts.getMinus()), null);
                split.plus.cut = cutParts.getPlus();
                split.minus.cut = cutParts.getMinus();
                BSPTree<S> tmp = split.plus.minus;
                split.plus.minus = split.minus.plus;
                split.plus.minus.parent = split.plus;
                split.minus.plus = tmp;
                split.minus.plus.parent = split.minus;
                super.condense();
                super.condense();
                return split;
            }
        }
        return cHyperplane.sameOrientationAs(sHyperplane) ? new BSPTree<S>(sub, this.plus.copySelf(), this.minus.copySelf(), this.attribute) : new BSPTree<S>(sub, this.minus.copySelf(), this.plus.copySelf(), this.attribute);
    }

    @Deprecated
    public void insertInTree(BSPTree<S> parentTree, boolean isPlusChild) {
        this.insertInTree(parentTree, isPlusChild, new VanishingCutHandler<S>(){

            @Override
            public BSPTree<S> fixNode(BSPTree<S> node) {
                throw new MathIllegalStateException(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
            }
        });
    }

    public void insertInTree(BSPTree<S> parentTree, boolean isPlusChild, VanishingCutHandler<S> vanishingHandler) {
        this.parent = parentTree;
        if (parentTree != null) {
            if (isPlusChild) {
                parentTree.plus = this;
            } else {
                parentTree.minus = this;
            }
        }
        if (this.cut != null) {
            BSPTree<S> tree = this;
            while (tree.parent != null) {
                Hyperplane<S> hyperplane = tree.parent.cut.getHyperplane();
                if (tree == tree.parent.plus) {
                    this.cut = this.cut.split(hyperplane).getPlus();
                    super.chopOffMinus(hyperplane, vanishingHandler);
                    super.chopOffMinus(hyperplane, vanishingHandler);
                } else {
                    this.cut = this.cut.split(hyperplane).getMinus();
                    super.chopOffPlus(hyperplane, vanishingHandler);
                    super.chopOffPlus(hyperplane, vanishingHandler);
                }
                if (this.cut == null) {
                    BSPTree<S> fixed = vanishingHandler.fixNode(this);
                    this.cut = fixed.cut;
                    this.plus = fixed.plus;
                    this.minus = fixed.minus;
                    this.attribute = fixed.attribute;
                    if (this.cut == null) break;
                }
                tree = tree.parent;
            }
            this.condense();
        }
    }

    public BSPTree<S> pruneAroundConvexCell(Object cellAttribute, Object otherLeafsAttributes, Object internalAttributes) {
        BSPTree<S> tree = new BSPTree<S>(cellAttribute);
        BSPTree<S> current = this;
        while (current.parent != null) {
            SubHyperplane<S> parentCut = current.parent.cut.copySelf();
            BSPTree<S> sibling = new BSPTree<S>(otherLeafsAttributes);
            tree = current == current.parent.plus ? new BSPTree<S>(parentCut, tree, sibling, internalAttributes) : new BSPTree<S>(parentCut, sibling, tree, internalAttributes);
            current = current.parent;
        }
        return tree;
    }

    private void chopOffMinus(Hyperplane<S> hyperplane, VanishingCutHandler<S> vanishingHandler) {
        if (this.cut != null) {
            this.cut = this.cut.split(hyperplane).getPlus();
            super.chopOffMinus(hyperplane, vanishingHandler);
            super.chopOffMinus(hyperplane, vanishingHandler);
            if (this.cut == null) {
                BSPTree<S> fixed = vanishingHandler.fixNode(this);
                this.cut = fixed.cut;
                this.plus = fixed.plus;
                this.minus = fixed.minus;
                this.attribute = fixed.attribute;
            }
        }
    }

    private void chopOffPlus(Hyperplane<S> hyperplane, VanishingCutHandler<S> vanishingHandler) {
        if (this.cut != null) {
            this.cut = this.cut.split(hyperplane).getMinus();
            super.chopOffPlus(hyperplane, vanishingHandler);
            super.chopOffPlus(hyperplane, vanishingHandler);
            if (this.cut == null) {
                BSPTree<S> fixed = vanishingHandler.fixNode(this);
                this.cut = fixed.cut;
                this.plus = fixed.plus;
                this.minus = fixed.minus;
                this.attribute = fixed.attribute;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface VanishingCutHandler<S extends Space> {
        public BSPTree<S> fixNode(BSPTree<S> var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface LeafMerger<S extends Space> {
        public BSPTree<S> merge(BSPTree<S> var1, BSPTree<S> var2, BSPTree<S> var3, boolean var4, boolean var5);
    }
}

