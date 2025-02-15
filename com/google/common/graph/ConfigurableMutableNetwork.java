/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.graph.ConfigurableNetwork;
import com.google.common.graph.DirectedMultiNetworkConnections;
import com.google.common.graph.DirectedNetworkConnections;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;
import com.google.common.graph.NetworkConnections;
import com.google.common.graph.UndirectedMultiNetworkConnections;
import com.google.common.graph.UndirectedNetworkConnections;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

final class ConfigurableMutableNetwork<N, E>
extends ConfigurableNetwork<N, E>
implements MutableNetwork<N, E> {
    ConfigurableMutableNetwork(NetworkBuilder<? super N, ? super E> builder) {
        super(builder);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addNode(N node) {
        Preconditions.checkNotNull(node, "node");
        if (this.containsNode(node)) {
            return false;
        }
        this.addNodeInternal(node);
        return true;
    }

    @CanIgnoreReturnValue
    private NetworkConnections<N, E> addNodeInternal(N node) {
        NetworkConnections<N, E> connections = this.newConnections();
        Preconditions.checkState(this.nodeConnections.put(node, connections) == null);
        return connections;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addEdge(N nodeU, N nodeV, E edge) {
        Preconditions.checkNotNull(nodeU, "nodeU");
        Preconditions.checkNotNull(nodeV, "nodeV");
        Preconditions.checkNotNull(edge, "edge");
        if (this.containsEdge(edge)) {
            EndpointPair existingIncidentNodes = this.incidentNodes(edge);
            EndpointPair<N> newIncidentNodes = EndpointPair.of(this, nodeU, nodeV);
            Preconditions.checkArgument(existingIncidentNodes.equals(newIncidentNodes), "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.", edge, existingIncidentNodes, newIncidentNodes);
            return false;
        }
        NetworkConnections<N, E> connectionsU = (NetworkConnections<N, E>)this.nodeConnections.get(nodeU);
        if (!this.allowsParallelEdges()) {
            Preconditions.checkArgument(connectionsU == null || !connectionsU.successors().contains(nodeV), "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.", nodeU, nodeV);
        }
        boolean isSelfLoop = nodeU.equals(nodeV);
        if (!this.allowsSelfLoops()) {
            Preconditions.checkArgument(!isSelfLoop, "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", nodeU);
        }
        if (connectionsU == null) {
            connectionsU = this.addNodeInternal(nodeU);
        }
        connectionsU.addOutEdge(edge, nodeV);
        NetworkConnections<N, E> connectionsV = (NetworkConnections<N, E>)this.nodeConnections.get(nodeV);
        if (connectionsV == null) {
            connectionsV = this.addNodeInternal(nodeV);
        }
        connectionsV.addInEdge(edge, nodeU, isSelfLoop);
        this.edgeToReferenceNode.put(edge, nodeU);
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addEdge(EndpointPair<N> endpoints, E edge) {
        this.validateEndpoints(endpoints);
        return this.addEdge(endpoints.nodeU(), endpoints.nodeV(), edge);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean removeNode(N node) {
        Preconditions.checkNotNull(node, "node");
        NetworkConnections connections = (NetworkConnections)this.nodeConnections.get(node);
        if (connections == null) {
            return false;
        }
        for (Object edge : ImmutableList.copyOf(connections.incidentEdges())) {
            this.removeEdge(edge);
        }
        this.nodeConnections.remove(node);
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean removeEdge(E edge) {
        Preconditions.checkNotNull(edge, "edge");
        Object nodeU = this.edgeToReferenceNode.get(edge);
        if (nodeU == null) {
            return false;
        }
        NetworkConnections connectionsU = (NetworkConnections)this.nodeConnections.get(nodeU);
        Object nodeV = connectionsU.adjacentNode(edge);
        NetworkConnections connectionsV = (NetworkConnections)this.nodeConnections.get(nodeV);
        connectionsU.removeOutEdge(edge);
        connectionsV.removeInEdge(edge, this.allowsSelfLoops() && nodeU.equals(nodeV));
        this.edgeToReferenceNode.remove(edge);
        return true;
    }

    private NetworkConnections<N, E> newConnections() {
        return this.isDirected() ? (this.allowsParallelEdges() ? DirectedMultiNetworkConnections.of() : DirectedNetworkConnections.of()) : (this.allowsParallelEdges() ? UndirectedMultiNetworkConnections.of() : UndirectedNetworkConnections.of());
    }
}

