/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.graph.AbstractNetwork;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Network;
import java.util.Optional;
import java.util.Set;

abstract class ForwardingNetwork<N, E>
extends AbstractNetwork<N, E> {
    ForwardingNetwork() {
    }

    protected abstract Network<N, E> delegate();

    @Override
    public Set<N> nodes() {
        return this.delegate().nodes();
    }

    @Override
    public Set<E> edges() {
        return this.delegate().edges();
    }

    @Override
    public boolean isDirected() {
        return this.delegate().isDirected();
    }

    @Override
    public boolean allowsParallelEdges() {
        return this.delegate().allowsParallelEdges();
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
    public ElementOrder<E> edgeOrder() {
        return this.delegate().edgeOrder();
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
    public Set<E> incidentEdges(N node) {
        return this.delegate().incidentEdges(node);
    }

    @Override
    public Set<E> inEdges(N node) {
        return this.delegate().inEdges(node);
    }

    @Override
    public Set<E> outEdges(N node) {
        return this.delegate().outEdges(node);
    }

    @Override
    public EndpointPair<N> incidentNodes(E edge) {
        return this.delegate().incidentNodes(edge);
    }

    @Override
    public Set<E> adjacentEdges(E edge) {
        return this.delegate().adjacentEdges(edge);
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
    public Set<E> edgesConnecting(N nodeU, N nodeV) {
        return this.delegate().edgesConnecting(nodeU, nodeV);
    }

    @Override
    public Set<E> edgesConnecting(EndpointPair<N> endpoints) {
        return this.delegate().edgesConnecting(endpoints);
    }

    @Override
    public Optional<E> edgeConnecting(N nodeU, N nodeV) {
        return this.delegate().edgeConnecting(nodeU, nodeV);
    }

    @Override
    public Optional<E> edgeConnecting(EndpointPair<N> endpoints) {
        return this.delegate().edgeConnecting(endpoints);
    }

    @Override
    public E edgeConnectingOrNull(N nodeU, N nodeV) {
        return this.delegate().edgeConnectingOrNull(nodeU, nodeV);
    }

    @Override
    public E edgeConnectingOrNull(EndpointPair<N> endpoints) {
        return this.delegate().edgeConnectingOrNull(endpoints);
    }

    @Override
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        return this.delegate().hasEdgeConnecting(nodeU, nodeV);
    }

    @Override
    public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
        return this.delegate().hasEdgeConnecting(endpoints);
    }
}

