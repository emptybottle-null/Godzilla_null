/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.NodesSet;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class RegionFactory<S extends Space> {
    private final NodesCleaner nodeCleaner = new NodesCleaner();

    public Region<S> buildConvex(Hyperplane<S> ... hyperplanes) {
        if (hyperplanes == null || hyperplanes.length == 0) {
            return null;
        }
        Region<S> region = hyperplanes[0].wholeSpace();
        BSPTree<S> node = region.getTree(false);
        node.setAttribute(Boolean.TRUE);
        for (Hyperplane<S> hyperplane : hyperplanes) {
            if (node.insertCut(hyperplane)) {
                node.setAttribute(null);
                node.getPlus().setAttribute(Boolean.FALSE);
                node = node.getMinus();
                node.setAttribute(Boolean.TRUE);
                continue;
            }
            SubHyperplane<S> s = hyperplane.wholeHyperplane();
            BSPTree<S> tree = node;
            while (tree.getParent() != null && s != null) {
                Hyperplane<S> other = tree.getParent().getCut().getHyperplane();
                SubHyperplane.SplitSubHyperplane<S> split = s.split(other);
                switch (split.getSide()) {
                    case HYPER: {
                        if (hyperplane.sameOrientationAs(other)) break;
                        return this.getComplement(hyperplanes[0].wholeSpace());
                    }
                    case PLUS: {
                        throw new MathIllegalArgumentException(LocalizedFormats.NOT_CONVEX_HYPERPLANES, new Object[0]);
                    }
                    default: {
                        s = split.getMinus();
                    }
                }
                tree = tree.getParent();
            }
        }
        return region;
    }

    public Region<S> union(Region<S> region1, Region<S> region2) {
        BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new UnionMerger());
        tree.visit(this.nodeCleaner);
        return region1.buildNew(tree);
    }

    public Region<S> intersection(Region<S> region1, Region<S> region2) {
        BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new IntersectionMerger());
        tree.visit(this.nodeCleaner);
        return region1.buildNew(tree);
    }

    public Region<S> xor(Region<S> region1, Region<S> region2) {
        BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new XorMerger());
        tree.visit(this.nodeCleaner);
        return region1.buildNew(tree);
    }

    public Region<S> difference(Region<S> region1, Region<S> region2) {
        BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new DifferenceMerger(region1, region2));
        tree.visit(this.nodeCleaner);
        return region1.buildNew(tree);
    }

    public Region<S> getComplement(Region<S> region) {
        return region.buildNew(this.recurseComplement(region.getTree(false)));
    }

    private BSPTree<S> recurseComplement(BSPTree<S> node) {
        HashMap<BSPTree<S>, BSPTree<S>> map = new HashMap<BSPTree<S>, BSPTree<S>>();
        BSPTree<S> transformedTree = this.recurseComplement(node, map);
        for (Map.Entry entry : map.entrySet()) {
            BoundaryAttribute original;
            if (((BSPTree)entry.getKey()).getCut() == null || (original = (BoundaryAttribute)((BSPTree)entry.getKey()).getAttribute()) == null) continue;
            BoundaryAttribute transformed = (BoundaryAttribute)((BSPTree)entry.getValue()).getAttribute();
            for (BSPTree splitter : original.getSplitters()) {
                transformed.getSplitters().add((BSPTree)map.get(splitter));
            }
        }
        return transformedTree;
    }

    private BSPTree<S> recurseComplement(BSPTree<S> node, Map<BSPTree<S>, BSPTree<S>> map) {
        BSPTree transformedNode;
        if (node.getCut() == null) {
            transformedNode = new BSPTree((Boolean)node.getAttribute() != false ? Boolean.FALSE : Boolean.TRUE);
        } else {
            BoundaryAttribute attribute = (BoundaryAttribute)node.getAttribute();
            if (attribute != null) {
                SubHyperplane plusOutside = attribute.getPlusInside() == null ? null : attribute.getPlusInside().copySelf();
                SubHyperplane plusInside = attribute.getPlusOutside() == null ? null : attribute.getPlusOutside().copySelf();
                attribute = new BoundaryAttribute(plusOutside, plusInside, new NodesSet());
            }
            transformedNode = new BSPTree<S>(node.getCut().copySelf(), this.recurseComplement(node.getPlus(), map), this.recurseComplement(node.getMinus(), map), attribute);
        }
        map.put(node, transformedNode);
        return transformedNode;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class VanishingToLeaf
    implements BSPTree.VanishingCutHandler<S> {
        private final boolean inside;

        VanishingToLeaf(boolean inside) {
            this.inside = inside;
        }

        @Override
        public BSPTree<S> fixNode(BSPTree<S> node) {
            if (node.getPlus().getAttribute().equals(node.getMinus().getAttribute())) {
                return new BSPTree(node.getPlus().getAttribute());
            }
            return new BSPTree(this.inside);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class NodesCleaner
    implements BSPTreeVisitor<S> {
        private NodesCleaner() {
        }

        @Override
        public BSPTreeVisitor.Order visitOrder(BSPTree<S> node) {
            return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
        }

        @Override
        public void visitInternalNode(BSPTree<S> node) {
            node.setAttribute(null);
        }

        @Override
        public void visitLeafNode(BSPTree<S> node) {
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class DifferenceMerger
    implements BSPTree.LeafMerger<S>,
    BSPTree.VanishingCutHandler<S> {
        private final Region<S> region1;
        private final Region<S> region2;

        DifferenceMerger(Region<S> region1, Region<S> region2) {
            this.region1 = region1.copySelf();
            this.region2 = region2.copySelf();
        }

        @Override
        public BSPTree<S> merge(BSPTree<S> leaf, BSPTree<S> tree, BSPTree<S> parentTree, boolean isPlusChild, boolean leafFromInstance) {
            if (((Boolean)leaf.getAttribute()).booleanValue()) {
                BSPTree argTree = RegionFactory.this.recurseComplement(leafFromInstance ? tree : leaf);
                argTree.insertInTree(parentTree, isPlusChild, this);
                return argTree;
            }
            BSPTree instanceTree = leafFromInstance ? leaf : tree;
            instanceTree.insertInTree(parentTree, isPlusChild, this);
            return instanceTree;
        }

        @Override
        public BSPTree<S> fixNode(BSPTree<S> node) {
            BSPTree cell = node.pruneAroundConvexCell(Boolean.TRUE, Boolean.FALSE, null);
            Region r = this.region1.buildNew(cell);
            Point p = r.getBarycenter();
            return new BSPTree(this.region1.checkPoint(p) == Region.Location.INSIDE && this.region2.checkPoint(p) == Region.Location.OUTSIDE);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class XorMerger
    implements BSPTree.LeafMerger<S> {
        private XorMerger() {
        }

        @Override
        public BSPTree<S> merge(BSPTree<S> leaf, BSPTree<S> tree, BSPTree<S> parentTree, boolean isPlusChild, boolean leafFromInstance) {
            BSPTree t = tree;
            if (((Boolean)leaf.getAttribute()).booleanValue()) {
                t = RegionFactory.this.recurseComplement(t);
            }
            t.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(true));
            return t;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class IntersectionMerger
    implements BSPTree.LeafMerger<S> {
        private IntersectionMerger() {
        }

        @Override
        public BSPTree<S> merge(BSPTree<S> leaf, BSPTree<S> tree, BSPTree<S> parentTree, boolean isPlusChild, boolean leafFromInstance) {
            if (((Boolean)leaf.getAttribute()).booleanValue()) {
                tree.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(true));
                return tree;
            }
            leaf.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(false));
            return leaf;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private class UnionMerger
    implements BSPTree.LeafMerger<S> {
        private UnionMerger() {
        }

        @Override
        public BSPTree<S> merge(BSPTree<S> leaf, BSPTree<S> tree, BSPTree<S> parentTree, boolean isPlusChild, boolean leafFromInstance) {
            if (((Boolean)leaf.getAttribute()).booleanValue()) {
                leaf.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(true));
                return leaf;
            }
            tree.insertInTree(parentTree, isPlusChild, new VanishingToLeaf(false));
            return tree;
        }
    }
}

