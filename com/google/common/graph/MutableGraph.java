/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
public interface MutableGraph<N>
extends Graph<N> {
    @CanIgnoreReturnValue
    public boolean addNode(N var1);

    @CanIgnoreReturnValue
    public boolean putEdge(N var1, N var2);

    @CanIgnoreReturnValue
    public boolean putEdge(EndpointPair<N> var1);

    @CanIgnoreReturnValue
    public boolean removeNode(N var1);

    @CanIgnoreReturnValue
    public boolean removeEdge(N var1, N var2);

    @CanIgnoreReturnValue
    public boolean removeEdge(EndpointPair<N> var1);
}

