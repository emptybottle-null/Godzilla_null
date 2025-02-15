/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.graph.BaseGraph;
import com.google.common.graph.ConfigurableValueGraph;
import com.google.common.graph.DirectedGraphConnections;
import com.google.common.graph.ForwardingGraph;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.GraphConnections;
import com.google.common.graph.GraphConstants;
import com.google.common.graph.UndirectedGraphConnections;
import com.google.errorprone.annotations.Immutable;

@Immutable(containerOf={"N"})
@Beta
public class ImmutableGraph<N>
extends ForwardingGraph<N> {
    private final BaseGraph<N> backingGraph;

    ImmutableGraph(BaseGraph<N> backingGraph) {
        this.backingGraph = backingGraph;
    }

    public static <N> ImmutableGraph<N> copyOf(Graph<N> graph) {
        return graph instanceof ImmutableGraph ? (ImmutableGraph<N>)graph : new ImmutableGraph<N>(new ConfigurableValueGraph<N, GraphConstants.Presence>(GraphBuilder.from(graph), ImmutableGraph.getNodeConnections(graph), graph.edges().size()));
    }

    @Deprecated
    public static <N> ImmutableGraph<N> copyOf(ImmutableGraph<N> graph) {
        return Preconditions.checkNotNull(graph);
    }

    private static <N> ImmutableMap<N, GraphConnections<N, GraphConstants.Presence>> getNodeConnections(Graph<N> graph) {
        ImmutableMap.Builder<N, GraphConnections<N, GraphConstants.Presence>> nodeConnections = ImmutableMap.builder();
        for (N node : graph.nodes()) {
            nodeConnections.put(node, ImmutableGraph.connectionsOf(graph, node));
        }
        return nodeConnections.build();
    }

    private static <N> GraphConnections<N, GraphConstants.Presence> connectionsOf(Graph<N> graph, N node) {
        Function<Object, GraphConstants.Presence> edgeValueFn = Functions.constant(GraphConstants.Presence.EDGE_EXISTS);
        return graph.isDirected() ? DirectedGraphConnections.ofImmutable(graph.predecessors((Object)node), Maps.asMap(graph.successors((Object)node), edgeValueFn)) : UndirectedGraphConnections.ofImmutable(Maps.asMap(graph.adjacentNodes(node), edgeValueFn));
    }

    @Override
    protected BaseGraph<N> delegate() {
        return this.backingGraph;
    }
}

