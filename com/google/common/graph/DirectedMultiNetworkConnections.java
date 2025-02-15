/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import com.google.common.graph.AbstractDirectedNetworkConnections;
import com.google.common.graph.MultiEdgesConnecting;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

final class DirectedMultiNetworkConnections<N, E>
extends AbstractDirectedNetworkConnections<N, E> {
    @LazyInit
    private transient Reference<Multiset<N>> predecessorsReference;
    @LazyInit
    private transient Reference<Multiset<N>> successorsReference;

    private DirectedMultiNetworkConnections(Map<E, N> inEdges, Map<E, N> outEdges, int selfLoopCount) {
        super(inEdges, outEdges, selfLoopCount);
    }

    static <N, E> DirectedMultiNetworkConnections<N, E> of() {
        return new DirectedMultiNetworkConnections(new HashMap(2, 1.0f), new HashMap(2, 1.0f), 0);
    }

    static <N, E> DirectedMultiNetworkConnections<N, E> ofImmutable(Map<E, N> inEdges, Map<E, N> outEdges, int selfLoopCount) {
        return new DirectedMultiNetworkConnections<N, E>(ImmutableMap.copyOf(inEdges), ImmutableMap.copyOf(outEdges), selfLoopCount);
    }

    @Override
    public Set<N> predecessors() {
        return Collections.unmodifiableSet(this.predecessorsMultiset().elementSet());
    }

    private Multiset<N> predecessorsMultiset() {
        Multiset<N> predecessors = DirectedMultiNetworkConnections.getReference(this.predecessorsReference);
        if (predecessors == null) {
            predecessors = HashMultiset.create(this.inEdgeMap.values());
            this.predecessorsReference = new SoftReference<Multiset<N>>(predecessors);
        }
        return predecessors;
    }

    @Override
    public Set<N> successors() {
        return Collections.unmodifiableSet(this.successorsMultiset().elementSet());
    }

    private Multiset<N> successorsMultiset() {
        Multiset<N> successors = DirectedMultiNetworkConnections.getReference(this.successorsReference);
        if (successors == null) {
            successors = HashMultiset.create(this.outEdgeMap.values());
            this.successorsReference = new SoftReference<Multiset<N>>(successors);
        }
        return successors;
    }

    @Override
    public Set<E> edgesConnecting(final N node) {
        return new MultiEdgesConnecting<E>(this.outEdgeMap, node){

            @Override
            public int size() {
                return DirectedMultiNetworkConnections.this.successorsMultiset().count(node);
            }
        };
    }

    @Override
    public N removeInEdge(E edge, boolean isSelfLoop) {
        Object node = super.removeInEdge(edge, isSelfLoop);
        Multiset<N> predecessors = DirectedMultiNetworkConnections.getReference(this.predecessorsReference);
        if (predecessors != null) {
            Preconditions.checkState(predecessors.remove(node));
        }
        return node;
    }

    @Override
    public N removeOutEdge(E edge) {
        Object node = super.removeOutEdge(edge);
        Multiset<N> successors = DirectedMultiNetworkConnections.getReference(this.successorsReference);
        if (successors != null) {
            Preconditions.checkState(successors.remove(node));
        }
        return node;
    }

    @Override
    public void addInEdge(E edge, N node, boolean isSelfLoop) {
        super.addInEdge(edge, node, isSelfLoop);
        Multiset<N> predecessors = DirectedMultiNetworkConnections.getReference(this.predecessorsReference);
        if (predecessors != null) {
            Preconditions.checkState(predecessors.add(node));
        }
    }

    @Override
    public void addOutEdge(E edge, N node) {
        super.addOutEdge(edge, node);
        Multiset<N> successors = DirectedMultiNetworkConnections.getReference(this.successorsReference);
        if (successors != null) {
            Preconditions.checkState(successors.add(node));
        }
    }

    private static <T> @Nullable T getReference(@Nullable Reference<T> reference) {
        return reference == null ? null : (T)reference.get();
    }
}

