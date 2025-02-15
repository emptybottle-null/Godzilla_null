/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

abstract class MultiEdgesConnecting<E>
extends AbstractSet<E> {
    private final Map<E, ?> outEdgeToNode;
    private final Object targetNode;

    MultiEdgesConnecting(Map<E, ?> outEdgeToNode, Object targetNode) {
        this.outEdgeToNode = Preconditions.checkNotNull(outEdgeToNode);
        this.targetNode = Preconditions.checkNotNull(targetNode);
    }

    @Override
    public UnmodifiableIterator<E> iterator() {
        final Iterator<Map.Entry<E, ?>> entries = this.outEdgeToNode.entrySet().iterator();
        return new AbstractIterator<E>(){

            @Override
            protected E computeNext() {
                while (entries.hasNext()) {
                    Map.Entry entry = (Map.Entry)entries.next();
                    if (!MultiEdgesConnecting.this.targetNode.equals(entry.getValue())) continue;
                    return entry.getKey();
                }
                return this.endOfData();
            }
        };
    }

    @Override
    public boolean contains(@Nullable Object edge) {
        return this.targetNode.equals(this.outEdgeToNode.get(edge));
    }
}

