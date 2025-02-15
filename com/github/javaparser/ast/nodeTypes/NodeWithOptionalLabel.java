/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.utils.Utils;
import java.util.Optional;

public interface NodeWithOptionalLabel<T extends Node> {
    public Optional<SimpleName> getLabel();

    public T setLabel(SimpleName var1);

    public T removeLabel();

    default public T setLabel(String label) {
        Utils.assertNonEmpty(label);
        return this.setLabel(new SimpleName(label));
    }

    default public Optional<String> getLabelAsString() {
        return this.getLabel().flatMap(l -> Optional.of(l.getIdentifier()));
    }
}

