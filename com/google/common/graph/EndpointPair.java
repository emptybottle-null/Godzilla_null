/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.Graph;
import com.google.common.graph.Network;
import com.google.errorprone.annotations.Immutable;
import org.checkerframework.checker.nullness.qual.Nullable;

@Immutable(containerOf={"N"})
@Beta
public abstract class EndpointPair<N>
implements Iterable<N> {
    private final N nodeU;
    private final N nodeV;

    private EndpointPair(N nodeU, N nodeV) {
        this.nodeU = Preconditions.checkNotNull(nodeU);
        this.nodeV = Preconditions.checkNotNull(nodeV);
    }

    public static <N> EndpointPair<N> ordered(N source, N target) {
        return new Ordered(source, target);
    }

    public static <N> EndpointPair<N> unordered(N nodeU, N nodeV) {
        return new Unordered(nodeV, nodeU);
    }

    static <N> EndpointPair<N> of(Graph<?> graph, N nodeU, N nodeV) {
        return graph.isDirected() ? EndpointPair.ordered(nodeU, nodeV) : EndpointPair.unordered(nodeU, nodeV);
    }

    static <N> EndpointPair<N> of(Network<?, ?> network, N nodeU, N nodeV) {
        return network.isDirected() ? EndpointPair.ordered(nodeU, nodeV) : EndpointPair.unordered(nodeU, nodeV);
    }

    public abstract N source();

    public abstract N target();

    public final N nodeU() {
        return this.nodeU;
    }

    public final N nodeV() {
        return this.nodeV;
    }

    public final N adjacentNode(Object node) {
        if (node.equals(this.nodeU)) {
            return this.nodeV;
        }
        if (node.equals(this.nodeV)) {
            return this.nodeU;
        }
        throw new IllegalArgumentException("EndpointPair " + this + " does not contain node " + node);
    }

    public abstract boolean isOrdered();

    @Override
    public final UnmodifiableIterator<N> iterator() {
        return Iterators.forArray(this.nodeU, this.nodeV);
    }

    public abstract boolean equals(@Nullable Object var1);

    public abstract int hashCode();

    private static final class Unordered<N>
    extends EndpointPair<N> {
        private Unordered(N nodeU, N nodeV) {
            super(nodeU, nodeV);
        }

        @Override
        public N source() {
            throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
        }

        @Override
        public N target() {
            throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
        }

        @Override
        public boolean isOrdered() {
            return false;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair other = (EndpointPair)obj;
            if (this.isOrdered() != other.isOrdered()) {
                return false;
            }
            if (this.nodeU().equals(other.nodeU())) {
                return this.nodeV().equals(other.nodeV());
            }
            return this.nodeU().equals(other.nodeV()) && this.nodeV().equals(other.nodeU());
        }

        @Override
        public int hashCode() {
            return this.nodeU().hashCode() + this.nodeV().hashCode();
        }

        public String toString() {
            return "[" + this.nodeU() + ", " + this.nodeV() + "]";
        }
    }

    private static final class Ordered<N>
    extends EndpointPair<N> {
        private Ordered(N source, N target) {
            super(source, target);
        }

        @Override
        public N source() {
            return this.nodeU();
        }

        @Override
        public N target() {
            return this.nodeV();
        }

        @Override
        public boolean isOrdered() {
            return true;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EndpointPair)) {
                return false;
            }
            EndpointPair other = (EndpointPair)obj;
            if (this.isOrdered() != other.isOrdered()) {
                return false;
            }
            return this.source().equals(other.source()) && this.target().equals(other.target());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.source(), this.target());
        }

        public String toString() {
            return "<" + this.source() + " -> " + this.target() + ">";
        }
    }
}

