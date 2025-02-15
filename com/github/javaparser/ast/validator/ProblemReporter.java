/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator;

import com.github.javaparser.Problem;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.nodeTypes.NodeWithTokenRange;
import com.github.javaparser.utils.CodeGenerationUtils;
import java.util.function.Consumer;

public class ProblemReporter {
    private final Consumer<Problem> problemConsumer;

    public ProblemReporter(Consumer<Problem> problemConsumer) {
        this.problemConsumer = problemConsumer;
    }

    public void report(NodeWithTokenRange<?> node, String message, Object ... args) {
        this.report((TokenRange)node.getTokenRange().orElse(null), message, args);
    }

    public void report(TokenRange range, String message, Object ... args) {
        this.problemConsumer.accept(new Problem(CodeGenerationUtils.f(message, args), range, null));
    }
}

