/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import com.google.common.graph.AbstractUndirectedNetworkConnections;
import com.google.common.graph.MultiEdgesConnecting;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

final class UndirectedMultiNetworkConnections<N, E>
extends AbstractUndirectedNetworkConnections<N, E> {
    @LazyInit
    private transient Reference<Multiset<N>> adjacentNodesReference;

    private UndirectedMultiNetworkConnections(Map<E, N> incidentEdges) {
        super(incidentEdges);
    }

    static <N, E> UndirectedMultiNetworkConnections<N, E> of() {
        return new UndirectedMultiNetworkConnections(new HashMap(2, 1.0f));
    }

    static <N, E> UndirectedMultiNetworkConnections<N, E> ofImmutable(Map<E, N> incidentEdges) {
        return new UndirectedMultiNetworkConnections<N, E>(ImmutableMap.copyOf(incidentEdges));
    }

    @Override
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodesMultiset().elementSet());
    }

    private Multiset<N> adjacentNodesMultiset() {
        Multiset<N> adjacentNodes = UndirectedMultiNetworkConnections.getReference(this.adjacentNodesReference);
        if (adjacentNodes == null) {
            adjacentNodes = HashMultiset.create(this.incidentEdgeMap.values());
            this.adjacentNodesReference = new SoftReference<Multiset<N>>(adjacentNodes);
        }
        return adjacentNodes;
    }

    @Override
    public Set<E> edgesConnecting(final N node) {
        return new MultiEdgesConnecting<E>(this.incidentEdgeMap, node){

            @Override
            public int size() {
                return UndirectedMultiNetworkConnections.this.adjacentNodesMultiset().count(node);
            }
        };
    }

    @Override
    public N removeInEdge(E edge, boolean isSelfLoop) {
        if (!isSelfLoop) {
            return this.removeOutEdge(edge);
        }
        return null;
    }

    @Override
    public N removeOutEdge(E edge) {
        Object node = super.removeOutEdge(edge);
        Multiset<N> adjacentNodes = UndirectedMultiNetworkConnections.getReference(this.adjacentNodesReference);
        if (adjacentNodes != null) {
            Preconditions.checkState(adjacentNodes.remove(node));
        }
        return node;
    }

    @Override
    public void addInEdge(E edge, N node, boolean isSelfLoop) {
        if (!isSelfLoop) {
            this.addOutEdge(edge, node);
        }
    }

    @Override
    public void addOutEdge(E edge, N node) {
        super.addOutEdge(edge, node);
        Multiset<N> adjacentNodes = UndirectedMultiNetworkConnections.getReference(this.adjacentNodesReference);
        if (adjacentNodes != null) {
            Preconditions.checkState(adjacentNodes.add(node));
        }
    }

    private static <T> @Nullable T getReference(@Nullable Reference<T> reference) {
        return reference == null ? null : (T)reference.get();
    }
}

