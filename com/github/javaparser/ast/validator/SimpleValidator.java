/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class SimpleValidator<N extends Node>
extends SingleNodeTypeValidator<N> {
    public SimpleValidator(Class<N> type, Predicate<N> condition, BiConsumer<N, ProblemReporter> problemSupplier) {
        super(type, (node, problemReporter) -> {
            if (condition.test(node)) {
                problemSupplier.accept(node, problemReporter);
            }
        });
    }
}

