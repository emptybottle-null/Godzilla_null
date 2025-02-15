/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.Graphs;
import com.google.common.graph.NetworkConnections;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

abstract class AbstractDirectedNetworkConnections<N, E>
implements NetworkConnections<N, E> {
    protected final Map<E, N> inEdgeMap;
    protected final Map<E, N> outEdgeMap;
    private int selfLoopCount;

    protected AbstractDirectedNetworkConnections(Map<E, N> inEdgeMap, Map<E, N> outEdgeMap, int selfLoopCount) {
        this.inEdgeMap = Preconditions.checkNotNull(inEdgeMap);
        this.outEdgeMap = Preconditions.checkNotNull(outEdgeMap);
        this.selfLoopCount = Graphs.checkNonNegative(selfLoopCount);
        Preconditions.checkState(selfLoopCount <= inEdgeMap.size() && selfLoopCount <= outEdgeMap.size());
    }

    @Override
    public Set<N> adjacentNodes() {
        return Sets.union(this.predecessors(), this.successors());
    }

    @Override
    public Set<E> incidentEdges() {
        return new AbstractSet<E>(){

            @Override
            public UnmodifiableIterator<E> iterator() {
                Iterable incidentEdges = AbstractDirectedNetworkConnections.this.selfLoopCount == 0 ? Iterables.concat(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet()) : Sets.union(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet());
                return Iterators.unmodifiableIterator(incidentEdges.iterator());
            }

            @Override
            public int size() {
                return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.inEdgeMap.size(), AbstractDirectedNetworkConnections.this.outEdgeMap.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
            }

            @Override
            public boolean contains(@Nullable Object obj) {
                return AbstractDirectedNetworkConnections.this.inEdgeMap.containsKey(obj) || AbstractDirectedNetworkConnections.this.outEdgeMap.containsKey(obj);
            }
        };
    }

    @Override
    public Set<E> inEdges() {
        return Collections.unmodifiableSet(this.inEdgeMap.keySet());
    }

    @Override
    public Set<E> outEdges() {
        return Collections.unmodifiableSet(this.outEdgeMap.keySet());
    }

    @Override
    public N adjacentNode(E edge) {
        return Preconditions.checkNotNull(this.outEdgeMap.get(edge));
    }

    @Override
    public N removeInEdge(E edge, boolean isSelfLoop) {
        if (isSelfLoop) {
            Graphs.checkNonNegative(--this.selfLoopCount);
        }
        N previousNode = this.inEdgeMap.remove(edge);
        return Preconditions.checkNotNull(previousNode);
    }

    @Override
    public N removeOutEdge(E edge) {
        N previousNode = this.outEdgeMap.remove(edge);
        return Preconditions.checkNotNull(previousNode);
    }

    @Override
    public void addInEdge(E edge, N node, boolean isSelfLoop) {
        N previousNode;
        if (isSelfLoop) {
            Graphs.checkPositive(++this.selfLoopCount);
        }
        Preconditions.checkState((previousNode = this.inEdgeMap.put(edge, node)) == null);
    }

    @Override
    public void addOutEdge(E edge, N node) {
        N previousNode = this.outEdgeMap.put(edge, node);
        Preconditions.checkState(previousNode == null);
    }
}

