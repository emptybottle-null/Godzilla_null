/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class NodesSet<S extends Space>
implements Iterable<BSPTree<S>> {
    private List<BSPTree<S>> list = new ArrayList<BSPTree<S>>();

    public void add(BSPTree<S> node) {
        for (BSPTree<S> existing : this.list) {
            if (node != existing) continue;
            return;
        }
        this.list.add(node);
    }

    public void addAll(Iterable<BSPTree<S>> iterator) {
        for (BSPTree<S> node : iterator) {
            this.add(node);
        }
    }

    @Override
    public Iterator<BSPTree<S>> iterator() {
        return this.list.iterator();
    }
}

