/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.Position;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import java.util.Optional;

public interface NodeWithRange<N> {
    public Optional<Range> getRange();

    public N setRange(Range var1);

    default public Optional<Position> getBegin() {
        return this.getRange().map(r -> r.begin);
    }

    default public Optional<Position> getEnd() {
        return this.getRange().map(r -> r.end);
    }

    @Deprecated
    default public boolean containsWithin(Node other) {
        return this.containsWithinRange(other);
    }

    default public boolean containsWithinRange(Node other) {
        if (this.hasRange() && other.hasRange()) {
            return this.getRange().get().contains(other.getRange().get());
        }
        return false;
    }

    default public boolean hasRange() {
        return this.getRange().isPresent();
    }
}

