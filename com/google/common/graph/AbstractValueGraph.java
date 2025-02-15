/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.graph.AbstractBaseGraph;
import com.google.common.graph.AbstractGraph;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.google.common.graph.ValueGraph;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public abstract class AbstractValueGraph<N, V>
extends AbstractBaseGraph<N>
implements ValueGraph<N, V> {
    @Override
    public Graph<N> asGraph() {
        return new AbstractGraph<N>(){

            @Override
            public Set<N> nodes() {
                return AbstractValueGraph.this.nodes();
            }

            @Override
            public Set<EndpointPair<N>> edges() {
                return AbstractValueGraph.this.edges();
            }

            @Override
            public boolean isDirected() {
                return AbstractValueGraph.this.isDirected();
            }

            @Override
            public boolean allowsSelfLoops() {
                return AbstractValueGraph.this.allowsSelfLoops();
            }

            @Override
            public ElementOrder<N> nodeOrder() {
                return AbstractValueGraph.this.nodeOrder();
            }

            @Override
            public Set<N> adjacentNodes(N node) {
                return AbstractValueGraph.this.adjacentNodes(node);
            }

            @Override
            public Set<N> predecessors(N node) {
                return AbstractValueGraph.this.predecessors(node);
            }

            @Override
            public Set<N> successors(N node) {
                return AbstractValueGraph.this.successors(node);
            }

            @Override
            public int degree(N node) {
                return AbstractValueGraph.this.degree(node);
            }

            @Override
            public int inDegree(N node) {
                return AbstractValueGraph.this.inDegree(node);
            }

            @Override
            public int outDegree(N node) {
                return AbstractValueGraph.this.outDegree(node);
            }
        };
    }

    @Override
    public Optional<V> edgeValue(N nodeU, N nodeV) {
        return Optional.ofNullable(this.edgeValueOrDefault(nodeU, nodeV, null));
    }

    @Override
    public Optional<V> edgeValue(EndpointPair<N> endpoints) {
        return Optional.ofNullable(this.edgeValueOrDefault(endpoints, null));
    }

    @Override
    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValueGraph)) {
            return false;
        }
        ValueGraph other = (ValueGraph)obj;
        return this.isDirected() == other.isDirected() && this.nodes().equals(other.nodes()) && AbstractValueGraph.edgeValueMap(this).equals(AbstractValueGraph.edgeValueMap(other));
    }

    @Override
    public final int hashCode() {
        return AbstractValueGraph.edgeValueMap(this).hashCode();
    }

    public String toString() {
        return "isDirected: " + this.isDirected() + ", allowsSelfLoops: " + this.allowsSelfLoops() + ", nodes: " + this.nodes() + ", edges: " + AbstractValueGraph.edgeValueMap(this);
    }

    private static <N, V> Map<EndpointPair<N>, V> edgeValueMap(final ValueGraph<N, V> graph) {
        Function edgeToValueFn = new Function<EndpointPair<N>, V>(){

            @Override
            public V apply(EndpointPair<N> edge) {
                return graph.edgeValueOrDefault(edge.nodeU(), edge.nodeV(), null);
            }
        };
        return Maps.asMap(graph.edges(), edgeToValueFn);
    }
}

