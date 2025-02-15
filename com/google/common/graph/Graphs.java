/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.ForwardingGraph;
import com.google.common.graph.ForwardingNetwork;
import com.google.common.graph.ForwardingValueGraph;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.Network;
import com.google.common.graph.NetworkBuilder;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public final class Graphs {
    private Graphs() {
    }

    public static <N> boolean hasCycle(Graph<N> graph) {
        int numEdges = graph.edges().size();
        if (numEdges == 0) {
            return false;
        }
        if (!graph.isDirected() && numEdges >= graph.nodes().size()) {
            return true;
        }
        HashMap<Object, NodeVisitState> visitedNodes = Maps.newHashMapWithExpectedSize(graph.nodes().size());
        for (N node : graph.nodes()) {
            if (!Graphs.subgraphHasCycle(graph, visitedNodes, node, null)) continue;
            return true;
        }
        return false;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (!network.isDirected() && network.allowsParallelEdges() && network.edges().size() > network.asGraph().edges().size()) {
            return true;
        }
        return Graphs.hasCycle(network.asGraph());
    }

    private static <N> boolean subgraphHasCycle(Graph<N> graph, Map<Object, NodeVisitState> visitedNodes, N node, @Nullable N previousNode) {
        NodeVisitState state = visitedNodes.get(node);
        if (state == NodeVisitState.COMPLETE) {
            return false;
        }
        if (state == NodeVisitState.PENDING) {
            return true;
        }
        visitedNodes.put(node, NodeVisitState.PENDING);
        for (Object nextNode : graph.successors((Object)node)) {
            if (!Graphs.canTraverseWithoutReusingEdge(graph, nextNode, previousNode) || !Graphs.subgraphHasCycle(graph, visitedNodes, nextNode, node)) continue;
            return true;
        }
        visitedNodes.put(node, NodeVisitState.COMPLETE);
        return false;
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object nextNode, @Nullable Object previousNode) {
        return graph.isDirected() || !Objects.equal(previousNode, nextNode);
    }

    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        MutableGraph transitiveClosure = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N node : graph.nodes()) {
                for (N reachableNode : Graphs.reachableNodes(graph, node)) {
                    transitiveClosure.putEdge(node, reachableNode);
                }
            }
        } else {
            HashSet<N> visitedNodes = new HashSet<N>();
            for (N node : graph.nodes()) {
                if (visitedNodes.contains(node)) continue;
                Set<N> reachableNodes = Graphs.reachableNodes(graph, node);
                visitedNodes.addAll(reachableNodes);
                int pairwiseMatch = 1;
                for (N nodeU : reachableNodes) {
                    for (N nodeV : Iterables.limit(reachableNodes, pairwiseMatch++)) {
                        transitiveClosure.putEdge(nodeU, nodeV);
                    }
                }
            }
        }
        return transitiveClosure;
    }

    public static <N> Set<N> reachableNodes(Graph<N> graph, N node) {
        Preconditions.checkArgument(graph.nodes().contains(node), "Node %s is not an element of this graph.", node);
        LinkedHashSet<Object> visitedNodes = new LinkedHashSet<Object>();
        ArrayDeque<Object> queuedNodes = new ArrayDeque<Object>();
        visitedNodes.add(node);
        queuedNodes.add(node);
        while (!queuedNodes.isEmpty()) {
            Object currentNode = queuedNodes.remove();
            for (Object successor : graph.successors(currentNode)) {
                if (!visitedNodes.add(successor)) continue;
                queuedNodes.add(successor);
            }
        }
        return Collections.unmodifiableSet(visitedNodes);
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        if (!graph.isDirected()) {
            return graph;
        }
        if (graph instanceof TransposedGraph) {
            return ((TransposedGraph)graph).graph;
        }
        return new TransposedGraph<N>(graph);
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> graph) {
        if (!graph.isDirected()) {
            return graph;
        }
        if (graph instanceof TransposedValueGraph) {
            return ((TransposedValueGraph)graph).graph;
        }
        return new TransposedValueGraph<N, V>(graph);
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        if (!network.isDirected()) {
            return network;
        }
        if (network instanceof TransposedNetwork) {
            return ((TransposedNetwork)network).network;
        }
        return new TransposedNetwork<N, E>(network);
    }

    static <N> EndpointPair<N> transpose(EndpointPair<N> endpoints) {
        if (endpoints.isOrdered()) {
            return EndpointPair.ordered(endpoints.target(), endpoints.source());
        }
        return endpoints;
    }

    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> nodes) {
        MutableGraph subgraph = nodes instanceof Collection ? GraphBuilder.from(graph).expectedNodeCount(((Collection)nodes).size()).build() : GraphBuilder.from(graph).build();
        for (Object node : nodes) {
            subgraph.addNode(node);
        }
        for (Object node : subgraph.nodes()) {
            for (Object successorNode : graph.successors(node)) {
                if (!subgraph.nodes().contains(successorNode)) continue;
                subgraph.putEdge(node, successorNode);
            }
        }
        return subgraph;
    }

    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> graph, Iterable<? extends N> nodes) {
        MutableValueGraph subgraph = nodes instanceof Collection ? ValueGraphBuilder.from(graph).expectedNodeCount(((Collection)nodes).size()).build() : ValueGraphBuilder.from(graph).build();
        for (Object node : nodes) {
            subgraph.addNode(node);
        }
        for (Object node : subgraph.nodes()) {
            for (Object successorNode : graph.successors(node)) {
                if (!subgraph.nodes().contains(successorNode)) continue;
                subgraph.putEdgeValue(node, successorNode, graph.edgeValueOrDefault(node, successorNode, null));
            }
        }
        return subgraph;
    }

    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> nodes) {
        MutableNetwork subgraph = nodes instanceof Collection ? NetworkBuilder.from(network).expectedNodeCount(((Collection)nodes).size()).build() : NetworkBuilder.from(network).build();
        for (Object node : nodes) {
            subgraph.addNode(node);
        }
        for (Object node : subgraph.nodes()) {
            for (E edge : network.outEdges(node)) {
                N successorNode = network.incidentNodes(edge).adjacentNode(node);
                if (!subgraph.nodes().contains(successorNode)) continue;
                subgraph.addEdge(node, successorNode, edge);
            }
        }
        return subgraph;
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph copy = GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N n : graph.nodes()) {
            copy.addNode(n);
        }
        for (EndpointPair endpointPair : graph.edges()) {
            copy.putEdge(endpointPair.nodeU(), endpointPair.nodeV());
        }
        return copy;
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> graph) {
        MutableValueGraph copy = ValueGraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (Object n : graph.nodes()) {
            copy.addNode(n);
        }
        for (EndpointPair endpointPair : graph.edges()) {
            copy.putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), graph.edgeValueOrDefault(endpointPair.nodeU(), endpointPair.nodeV(), null));
        }
        return copy;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork copy = NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        for (N node : network.nodes()) {
            copy.addNode(node);
        }
        for (Object edge : network.edges()) {
            EndpointPair<N> endpointPair = network.incidentNodes(edge);
            copy.addEdge(endpointPair.nodeU(), endpointPair.nodeV(), edge);
        }
        return copy;
    }

    @CanIgnoreReturnValue
    static int checkNonNegative(int value) {
        Preconditions.checkArgument(value >= 0, "Not true that %s is non-negative.", value);
        return value;
    }

    @CanIgnoreReturnValue
    static long checkNonNegative(long value) {
        Preconditions.checkArgument(value >= 0L, "Not true that %s is non-negative.", value);
        return value;
    }

    @CanIgnoreReturnValue
    static int checkPositive(int value) {
        Preconditions.checkArgument(value > 0, "Not true that %s is positive.", value);
        return value;
    }

    @CanIgnoreReturnValue
    static long checkPositive(long value) {
        Preconditions.checkArgument(value > 0L, "Not true that %s is positive.", value);
        return value;
    }

    private static enum NodeVisitState {
        PENDING,
        COMPLETE;

    }

    private static class TransposedNetwork<N, E>
    extends ForwardingNetwork<N, E> {
        private final Network<N, E> network;

        TransposedNetwork(Network<N, E> network) {
            this.network = network;
        }

        @Override
        protected Network<N, E> delegate() {
            return this.network;
        }

        @Override
        public Set<N> predecessors(N node) {
            return this.delegate().successors((Object)node);
        }

        @Override
        public Set<N> successors(N node) {
            return this.delegate().predecessors((Object)node);
        }

        @Override
        public int inDegree(N node) {
            return this.delegate().outDegree(node);
        }

        @Override
        public int outDegree(N node) {
            return this.delegate().inDegree(node);
        }

        @Override
        public Set<E> inEdges(N node) {
            return this.delegate().outEdges(node);
        }

        @Override
        public Set<E> outEdges(N node) {
            return this.delegate().inEdges(node);
        }

        @Override
        public EndpointPair<N> incidentNodes(E edge) {
            EndpointPair<N> endpointPair = this.delegate().incidentNodes(edge);
            return EndpointPair.of(this.network, endpointPair.nodeV(), endpointPair.nodeU());
        }

        @Override
        public Set<E> edgesConnecting(N nodeU, N nodeV) {
            return this.delegate().edgesConnecting(nodeV, nodeU);
        }

        @Override
        public Set<E> edgesConnecting(EndpointPair<N> endpoints) {
            return this.delegate().edgesConnecting(Graphs.transpose(endpoints));
        }

        @Override
        public Optional<E> edgeConnecting(N nodeU, N nodeV) {
            return this.delegate().edgeConnecting(nodeV, nodeU);
        }

        @Override
        public Optional<E> edgeConnecting(EndpointPair<N> endpoints) {
            return this.delegate().edgeConnecting(Graphs.transpose(endpoints));
        }

        @Override
        public E edgeConnectingOrNull(N nodeU, N nodeV) {
            return this.delegate().edgeConnectingOrNull(nodeV, nodeU);
        }

        @Override
        public E edgeConnectingOrNull(EndpointPair<N> endpoints) {
            return this.delegate().edgeConnectingOrNull(Graphs.transpose(endpoints));
        }

        @Override
        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return this.delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        @Override
        public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
            return this.delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
        }
    }

    private static class TransposedValueGraph<N, V>
    extends ForwardingValueGraph<N, V> {
        private final ValueGraph<N, V> graph;

        TransposedValueGraph(ValueGraph<N, V> graph) {
            this.graph = graph;
        }

        @Override
        protected ValueGraph<N, V> delegate() {
            return this.graph;
        }

        @Override
        public Set<N> predecessors(N node) {
            return this.delegate().successors((Object)node);
        }

        @Override
        public Set<N> successors(N node) {
            return this.delegate().predecessors((Object)node);
        }

        @Override
        public int inDegree(N node) {
            return this.delegate().outDegree(node);
        }

        @Override
        public int outDegree(N node) {
            return this.delegate().inDegree(node);
        }

        @Override
        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return this.delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        @Override
        public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
            return this.delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
        }

        @Override
        public Optional<V> edgeValue(N nodeU, N nodeV) {
            return this.delegate().edgeValue(nodeV, nodeU);
        }

        @Override
        public Optional<V> edgeValue(EndpointPair<N> endpoints) {
            return this.delegate().edgeValue(Graphs.transpose(endpoints));
        }

        @Override
        public @Nullable V edgeValueOrDefault(N nodeU, N nodeV, @Nullable V defaultValue) {
            return this.delegate().edgeValueOrDefault(nodeV, nodeU, defaultValue);
        }

        @Override
        public @Nullable V edgeValueOrDefault(EndpointPair<N> endpoints, @Nullable V defaultValue) {
            return this.delegate().edgeValueOrDefault(Graphs.transpose(endpoints), defaultValue);
        }
    }

    private static class TransposedGraph<N>
    extends ForwardingGraph<N> {
        private final Graph<N> graph;

        TransposedGraph(Graph<N> graph) {
            this.graph = graph;
        }

        @Override
        protected Graph<N> delegate() {
            return this.graph;
        }

        @Override
        public Set<N> predecessors(N node) {
            return this.delegate().successors((Object)node);
        }

        @Override
        public Set<N> successors(N node) {
            return this.delegate().predecessors((Object)node);
        }

        @Override
        public int inDegree(N node) {
            return this.delegate().outDegree(node);
        }

        @Override
        public int outDegree(N node) {
            return this.delegate().inDegree(node);
        }

        @Override
        public boolean hasEdgeConnecting(N nodeU, N nodeV) {
            return this.delegate().hasEdgeConnecting(nodeV, nodeU);
        }

        @Override
        public boolean hasEdgeConnecting(EndpointPair<N> endpoints) {
            return this.delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
        }
    }
}

