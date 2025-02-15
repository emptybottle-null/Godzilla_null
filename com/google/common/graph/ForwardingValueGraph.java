/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.graph.AbstractValueGraph;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.ValueGraph;
import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

abstract class ForwardingValueGraph<N, V>
extends AbstractValueGraph<N, V> {
    ForwardingValueGraph() {
    }

    protected abstract ValueGraph<N, V> delegate();

    @Override
    public Set<N> nodes() {
        return this.delegate().nodes();
    }

    @Override
    protected long edgeCount() {
        return this.delegate().edges().size();
    }

    @Override
    public boolean isDirected() {
        return this.delegate().isDirected();
    }

    @Override
    public boolean allowsSelfLoops() {
        return this.delegate().allowsSelfLoops();
    }

    @Override
    public ElementOrder<N> nodeOrder() {
        return this.delegate().nodeOrder();
    }

    @Override
    public Set<N> adjacentNodes(N node) {
        return this.delegate().adjacentNodes(node);
    }

    @Override
    public Set<N> predecessors(N node) {
        return this.delegate().predecessors((Object)node);
    }

    @Override
    public Set<N> successors(N node) {
        return this.delegate().successors((Object)node);
    }

    @Override
    public int degree(N node) {
        return this.delegate().degree(node);
    }

    @Override
    public int inDegree(N node) {
        return this.delegate().inDegree(node);
    }

    @Override
    public int outDegree(N node) {
        return this.delegate().outDegree(node);
    }

    @Override
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        return this.delegate().hasEdgeConnecting(nodeU, nodeV);
    }

    @Override
    public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
        return this.delegate().hasEdgeConnecting(endpoints);
    }

    @Override
    public Optional<V> edgeValue(N nodeU, N nodeV) {
        return this.delegate().edgeValue(nodeU, nodeV);
    }

    @Override
    public Optional<V> edgeValue(EndpointPair<N> endpoints) {
        return this.delegate().edgeValue(endpoints);
    }

    @Override
    public @Nullable V edgeValueOrDefault(N nodeU, N nodeV, @Nullable V defaultValue) {
        return this.delegate().edgeValueOrDefault(nodeU, nodeV, defaultValue);
    }

    @Override
    public @Nullable V edgeValueOrDefault(EndpointPair<N> endpoints, @Nullable V defaultValue) {
        return this.delegate().edgeValueOrDefault(endpoints, defaultValue);
    }
}

