/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.AbstractNetwork;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.MapIteratorCache;
import com.google.common.graph.MapRetrievalCache;
import com.google.common.graph.NetworkBuilder;
import com.google.common.graph.NetworkConnections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.Nullable;

class ConfigurableNetwork<N, E>
extends AbstractNetwork<N, E> {
    private final boolean isDirected;
    private final boolean allowsParallelEdges;
    private final boolean allowsSelfLoops;
    private final ElementOrder<N> nodeOrder;
    private final ElementOrder<E> edgeOrder;
    protected final MapIteratorCache<N, NetworkConnections<N, E>> nodeConnections;
    protected final MapIteratorCache<E, N> edgeToReferenceNode;

    ConfigurableNetwork(NetworkBuilder<? super N, ? super E> builder) {
        this(builder, builder.nodeOrder.createMap(builder.expectedNodeCount.or(10)), builder.edgeOrder.createMap(builder.expectedEdgeCount.or(20)));
    }

    ConfigurableNetwork(NetworkBuilder<? super N, ? super E> builder, Map<N, NetworkConnections<N, E>> nodeConnections, Map<E, N> edgeToReferenceNode) {
        this.isDirected = builder.directed;
        this.allowsParallelEdges = builder.allowsParallelEdges;
        this.allowsSelfLoops = builder.allowsSelfLoops;
        this.nodeOrder = builder.nodeOrder.cast();
        this.edgeOrder = builder.edgeOrder.cast();
        this.nodeConnections = nodeConnections instanceof TreeMap ? new MapRetrievalCache<N, NetworkConnections<N, E>>(nodeConnections) : new MapIteratorCache<N, NetworkConnections<N, E>>(nodeConnections);
        this.edgeToReferenceNode = new MapIteratorCache<E, N>(edgeToReferenceNode);
    }

    @Override
    public Set<N> nodes() {
        return this.nodeConnections.unmodifiableKeySet();
    }

    @Override
    public Set<E> edges() {
        return this.edgeToReferenceNode.unmodifiableKeySet();
    }

    @Override
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override
    public boolean allowsParallelEdges() {
        return this.allowsParallelEdges;
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
    public ElementOrder<E> edgeOrder() {
        return this.edgeOrder;
    }

    @Override
    public Set<E> incidentEdges(N node) {
        return this.checkedConnections(node).incidentEdges();
    }

    @Override
    public EndpointPair<N> incidentNodes(E edge) {
        N nodeU = this.checkedReferenceNode(edge);
        N nodeV = this.nodeConnections.get(nodeU).adjacentNode(edge);
        return EndpointPair.of(this, nodeU, nodeV);
    }

    @Override
    public Set<N> adjacentNodes(N node) {
        return this.checkedConnections(node).adjacentNodes();
    }

    @Override
    public Set<E> edgesConnecting(N nodeU, N nodeV) {
        NetworkConnections<N, E> connectionsU = this.checkedConnections(nodeU);
        if (!this.allowsSelfLoops && nodeU == nodeV) {
            return ImmutableSet.of();
        }
        Preconditions.checkArgument(this.containsNode(nodeV), "Node %s is not an element of this graph.", nodeV);
        return connectionsU.edgesConnecting(nodeV);
    }

    @Override
    public Set<E> inEdges(N node) {
        return this.checkedConnections(node).inEdges();
    }

    @Override
    public Set<E> outEdges(N node) {
        return this.checkedConnections(node).outEdges();
    }

    @Override
    public Set<N> predecessors(N node) {
        return this.checkedConnections(node).predecessors();
    }

    @Override
    public Set<N> successors(N node) {
        return this.checkedConnections(node).successors();
    }

    protected final NetworkConnections<N, E> checkedConnections(N node) {
        NetworkConnections<N, E> connections = this.nodeConnections.get(node);
        if (connections == null) {
            Preconditions.checkNotNull(node);
            throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", node));
        }
        return connections;
    }

    protected final N checkedReferenceNode(E edge) {
        N referenceNode = this.edgeToReferenceNode.get(edge);
        if (referenceNode == null) {
            Preconditions.checkNotNull(edge);
            throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", edge));
        }
        return referenceNode;
    }

    protected final boolean containsNode(@Nullable N node) {
        return this.nodeConnections.containsKey(node);
    }

    protected final boolean containsEdge(@Nullable E edge) {
        return this.edgeToReferenceNode.containsKey(edge);
    }
}

