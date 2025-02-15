/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;

@Beta
public interface SuccessorsFunction<N> {
    public Iterable<? extends N> successors(N var1);
}

