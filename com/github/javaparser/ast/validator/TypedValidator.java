/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator;

import com.github.javaparser.ParseResult;
import com.github.javaparser.Problem;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.validator.ProblemReporter;
import java.util.function.BiConsumer;

public interface TypedValidator<N extends Node>
extends BiConsumer<N, ProblemReporter> {
    @Override
    public void accept(N var1, ProblemReporter var2);

    default public ParseResult.PostProcessor postProcessor() {
        return (result, configuration) -> result.getResult().ifPresent(node -> this.accept((N)node, new ProblemReporter(problem -> result.getProblems().add((Problem)problem))));
    }
}

