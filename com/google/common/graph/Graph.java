/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.graph.BaseGraph;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.EndpointPair;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public interface Graph<N>
extends BaseGraph<N> {
    @Override
    public Set<N> nodes();

    @Override
    public Set<EndpointPair<N>> edges();

    @Override
    public boolean isDirected();

    @Override
    public boolean allowsSelfLoops();

    @Override
    public ElementOrder<N> nodeOrder();

    @Override
    public Set<N> adjacentNodes(N var1);

    @Override
    public Set<N> predecessors(N var1);

    @Override
    public Set<N> successors(N var1);

    @Override
    public Set<EndpointPair<N>> incidentEdges(N var1);

    @Override
    public int degree(N var1);

    @Override
    public int inDegree(N var1);

    @Override
    public int outDegree(N var1);

    @Override
    public boolean hasEdgeConnecting(N var1, N var2);

    @Override
    public boolean hasEdgeConnecting(EndpointPair<N> var1);

    public boolean equals(@Nullable Object var1);

    public int hashCode();
}

