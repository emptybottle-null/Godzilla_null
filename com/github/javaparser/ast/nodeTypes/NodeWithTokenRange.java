/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.TokenRange;
import java.util.Optional;

public interface NodeWithTokenRange<N> {
    public Optional<TokenRange> getTokenRange();

    public N setTokenRange(TokenRange var1);
}

