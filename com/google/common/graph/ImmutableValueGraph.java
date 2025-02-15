/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.graph.ConfigurableValueGraph;
import com.google.common.graph.DirectedGraphConnections;
import com.google.common.graph.GraphConnections;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.UndirectedGraphConnections;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.google.errorprone.annotations.Immutable;

@Immutable(containerOf={"N", "V"})
@Beta
public final class ImmutableValueGraph<N, V>
extends ConfigurableValueGraph<N, V> {
    private ImmutableValueGraph(ValueGraph<N, V> graph) {
        super(ValueGraphBuilder.from(graph), ImmutableValueGraph.getNodeConnections(graph), graph.edges().size());
    }

    public static <N, V> ImmutableValueGraph<N, V> copyOf(ValueGraph<N, V> graph) {
        return graph instanceof ImmutableValueGraph ? (ImmutableValueGraph<N, V>)graph : new ImmutableValueGraph<N, V>(graph);
    }

    @Deprecated
    public static <N, V> ImmutableValueGraph<N, V> copyOf(ImmutableValueGraph<N, V> graph) {
        return Preconditions.checkNotNull(graph);
    }

    @Override
    public ImmutableGraph<N> asGraph() {
        return new ImmutableGraph(this);
    }

    private static <N, V> ImmutableMap<N, GraphConnections<N, V>> getNodeConnections(ValueGraph<N, V> graph) {
        ImmutableMap.Builder<N, GraphConnections<N, V>> nodeConnections = ImmutableMap.builder();
        for (N node : graph.nodes()) {
            nodeConnections.put(node, ImmutableValueGraph.connectionsOf(graph, node));
        }
        return nodeConnections.build();
    }

    private static <N, V> GraphConnections<N, V> connectionsOf(final ValueGraph<N, V> graph, final N node) {
        Function successorNodeToValueFn = new Function<N, V>(){

            @Override
            public V apply(N successorNode) {
                return graph.edgeValueOrDefault(node, successorNode, null);
            }
        };
        return graph.isDirected() ? DirectedGraphConnections.ofImmutable(graph.predecessors((Object)node), Maps.asMap(graph.successors((Object)node), successorNodeToValueFn)) : UndirectedGraphConnections.ofImmutable(Maps.asMap(graph.adjacentNodes(node), successorNodeToValueFn));
    }
}

