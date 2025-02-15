/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.graph.AbstractGraphBuilder;
import com.google.common.graph.AbstractValueGraph;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.GraphConnections;
import com.google.common.graph.Graphs;
import com.google.common.graph.MapIteratorCache;
import com.google.common.graph.MapRetrievalCache;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.Nullable;

class ConfigurableValueGraph<N, V>
extends AbstractValueGraph<N, V> {
    private final boolean isDirected;
    private final boolean allowsSelfLoops;
    private final ElementOrder<N> nodeOrder;
    protected final MapIteratorCache<N, GraphConnections<N, V>> nodeConnections;
    protected long edgeCount;

    ConfigurableValueGraph(AbstractGraphBuilder<? super N> builder) {
        this(builder, builder.nodeOrder.createMap(builder.expectedNodeCount.or(10)), 0L);
    }

    ConfigurableValueGraph(AbstractGraphBuilder<? super N> builder, Map<N, GraphConnections<N, V>> nodeConnections, long edgeCount) {
        this.isDirected = builder.directed;
        this.allowsSelfLoops = builder.allowsSelfLoops;
        this.nodeOrder = builder.nodeOrder.cast();
        this.nodeConnections = nodeConnections instanceof TreeMap ? new MapRetrievalCache<N, GraphConnections<N, V>>(nodeConnections) : new MapIteratorCache<N, GraphConnections<N, V>>(nodeConnections);
        this.edgeCount = Graphs.checkNonNegative(edgeCount);
    }

    @Override
    public Set<N> nodes() {
        return this.nodeConnections.unmodifiableKeySet();
    }

    @Override
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override
    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    @Override
    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    @Override
    public Set<N> adjacentNodes(N node) {
        return this.checkedConnections(node).adjacentNodes();
    }

    @Override
    public Set<N> predecessors(N node) {
        return this.checkedConnections(node).predecessors();
    }

    @Override
    public Set<N> successors(N node) {
        return this.checkedConnections(node).successors();
    }

    @Override
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        return this.hasEdgeConnecting_internal(Preconditions.checkNotNull(nodeU), Preconditions.checkNotNull(nodeV));
    }

    @Override
    public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
        Preconditions.checkNotNull(endpoints);
        return this.isOrderingCompatible(endpoints) && this.hasEdgeConnecting_internal(endpoints.nodeU(), endpoints.nodeV());
    }

    @Override
    public @Nullable V edgeValueOrDefault(N nodeU, N nodeV, @Nullable V defaultValue) {
        return this.edgeValueOrDefault_internal(Preconditions.checkNotNull(nodeU), Preconditions.checkNotNull(nodeV), defaultValue);
    }

    @Override
    public @Nullable V edgeValueOrDefault(EndpointPair<N> endpoints, @Nullable V defaultValue) {
        this.validateEndpoints(endpoints);
        return this.edgeValueOrDefault_internal(endpoints.nodeU(), endpoints.nodeV(), defaultValue);
    }

    @Override
    protected long edgeCount() {
        return this.edgeCount;
    }

    protected final GraphConnections<N, V> checkedConnections(N node) {
        GraphConnections<N, V> connections = this.nodeConnections.get(node);
        if (connections == null) {
            Preconditions.checkNotNull(node);
            throw new IllegalArgumentException("Node " + node + " is not an element of this graph.");
        }
        return connections;
    }

    protected final boolean containsNode(@Nullable N node) {
        return this.nodeConnections.containsKey(node);
    }

    protected final boolean hasEdgeConnecting_internal(N nodeU, N nodeV) {
        GraphConnections<N, V> connectionsU = this.nodeConnections.get(nodeU);
        return connectionsU != null && connectionsU.successors().contains(nodeV);
    }

    protected final V edgeValueOrDefault_internal(N nodeU, N nodeV, V defaultValue) {
        GraphConnections<N, V> connectionsU = this.nodeConnections.get(nodeU);
        Object value = connectionsU == null ? null : connectionsU.value(nodeV);
        return (V)(value == null ? defaultValue : value);
    }
}

