/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.TypedValidator;
import com.github.javaparser.ast.validator.Validator;

public class SingleNodeTypeValidator<N extends Node>
implements Validator {
    private final Class<N> type;
    private final TypedValidator<N> validator;

    public SingleNodeTypeValidator(Class<N> type, TypedValidator<N> validator) {
        this.type = type;
        this.validator = validator;
    }

    @Override
    public void accept(Node node, ProblemReporter problemReporter) {
        if (this.type.isInstance(node)) {
            this.validator.accept((Node)this.type.cast(node), problemReporter);
        }
        node.findAll(this.type).forEach(n -> this.validator.accept((Node)n, problemReporter));
    }
}

