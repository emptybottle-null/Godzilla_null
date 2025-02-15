/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.graph.BaseGraph;
import com.google.common.graph.EndpointPair;
import java.util.Iterator;
import java.util.Set;

abstract class EndpointPairIterator<N>
extends AbstractIterator<EndpointPair<N>> {
    private final BaseGraph<N> graph;
    private final Iterator<N> nodeIterator;
    protected N node = null;
    protected Iterator<N> successorIterator = ImmutableSet.of().iterator();

    static <N> EndpointPairIterator<N> of(BaseGraph<N> graph) {
        return graph.isDirected() ? new Directed(graph) : new Undirected(graph);
    }

    private EndpointPairIterator(BaseGraph<N> graph) {
        this.graph = graph;
        this.nodeIterator = graph.nodes().iterator();
    }

    protected final boolean advance() {
        Preconditions.checkState(!this.successorIterator.hasNext());
        if (!this.nodeIterator.hasNext()) {
            return false;
        }
        this.node = this.nodeIterator.next();
        this.successorIterator = this.graph.successors((Object)this.node).iterator();
        return true;
    }

    private static final class Undirected<N>
    extends EndpointPairIterator<N> {
        private Set<N> visitedNodes;

        private Undirected(BaseGraph<N> graph) {
            super(graph);
            this.visitedNodes = Sets.newHashSetWithExpectedSize(graph.nodes().size());
        }

        @Override
        protected EndpointPair<N> computeNext() {
            while (true) {
                if (this.successorIterator.hasNext()) {
                    Object otherNode = this.successorIterator.next();
                    if (this.visitedNodes.contains(otherNode)) continue;
                    return EndpointPair.unordered(this.node, otherNode);
                }
                this.visitedNodes.add(this.node);
                if (!this.advance()) break;
            }
            this.visitedNodes = null;
            return (EndpointPair)this.endOfData();
        }
    }

    private static final class Directed<N>
    extends EndpointPairIterator<N> {
        private Directed(BaseGraph<N> graph) {
            super(graph);
        }

        @Override
        protected EndpointPair<N> computeNext() {
            do {
                if (!this.successorIterator.hasNext()) continue;
                return EndpointPair.ordered(this.node, this.successorIterator.next());
            } while (this.advance());
            return (EndpointPair)this.endOfData();
        }
    }
}

