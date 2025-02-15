/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.graph.ConfigurableNetwork;
import com.google.common.graph.DirectedMultiNetworkConnections;
import com.google.common.graph.DirectedNetworkConnections;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.Network;
import com.google.common.graph.NetworkBuilder;
import com.google.common.graph.NetworkConnections;
import com.google.common.graph.UndirectedMultiNetworkConnections;
import com.google.common.graph.UndirectedNetworkConnections;
import com.google.errorprone.annotations.Immutable;
import java.util.Map;

@Immutable(containerOf={"N", "E"})
@Beta
public final class ImmutableNetwork<N, E>
extends ConfigurableNetwork<N, E> {
    private ImmutableNetwork(Network<N, E> network) {
        super(NetworkBuilder.from(network), ImmutableNetwork.getNodeConnections(network), ImmutableNetwork.getEdgeToReferenceNode(network));
    }

    public static <N, E> ImmutableNetwork<N, E> copyOf(Network<N, E> network) {
        return network instanceof ImmutableNetwork ? (ImmutableNetwork<N, E>)network : new ImmutableNetwork<N, E>(network);
    }

    @Deprecated
    public static <N, E> ImmutableNetwork<N, E> copyOf(ImmutableNetwork<N, E> network) {
        return Preconditions.checkNotNull(network);
    }

    @Override
    public ImmutableGraph<N> asGraph() {
        return new ImmutableGraph(super.asGraph());
    }

    private static <N, E> Map<N, NetworkConnections<N, E>> getNodeConnections(Network<N, E> network) {
        ImmutableMap.Builder<N, NetworkConnections<N, E>> nodeConnections = ImmutableMap.builder();
        for (N node : network.nodes()) {
            nodeConnections.put(node, ImmutableNetwork.connectionsOf(network, node));
        }
        return nodeConnections.build();
    }

    private static <N, E> Map<E, N> getEdgeToReferenceNode(Network<N, E> network) {
        ImmutableMap.Builder<E, N> edgeToReferenceNode = ImmutableMap.builder();
        for (E edge : network.edges()) {
            edgeToReferenceNode.put(edge, network.incidentNodes(edge).nodeU());
        }
        return edgeToReferenceNode.build();
    }

    private static <N, E> NetworkConnections<N, E> connectionsOf(Network<N, E> network, N node) {
        if (network.isDirected()) {
            Map<E, N> inEdgeMap = Maps.asMap(network.inEdges(node), ImmutableNetwork.sourceNodeFn(network));
            Map<E, N> outEdgeMap = Maps.asMap(network.outEdges(node), ImmutableNetwork.targetNodeFn(network));
            int selfLoopCount = network.edgesConnecting(node, node).size();
            return network.allowsParallelEdges() ? DirectedMultiNetworkConnections.ofImmutable(inEdgeMap, outEdgeMap, selfLoopCount) : DirectedNetworkConnections.ofImmutable(inEdgeMap, outEdgeMap, selfLoopCount);
        }
        Map<E, N> incidentEdgeMap = Maps.asMap(network.incidentEdges(node), ImmutableNetwork.adjacentNodeFn(network, node));
        return network.allowsParallelEdges() ? UndirectedMultiNetworkConnections.ofImmutable(incidentEdgeMap) : UndirectedNetworkConnections.ofImmutable(incidentEdgeMap);
    }

    private static <N, E> Function<E, N> sourceNodeFn(final Network<N, E> network) {
        return new Function<E, N>(){

            @Override
            public N apply(E edge) {
                return network.incidentNodes(edge).source();
            }
        };
    }

    private static <N, E> Function<E, N> targetNodeFn(final Network<N, E> network) {
        return new Function<E, N>(){

            @Override
            public N apply(E edge) {
                return network.incidentNodes(edge).target();
            }
        };
    }

    private static <N, E> Function<E, N> adjacentNodeFn(final Network<N, E> network, final N node) {
        return new Function<E, N>(){

            @Override
            public N apply(E edge) {
                return network.incidentNodes(edge).adjacentNode(node);
            }
        };
    }
}

