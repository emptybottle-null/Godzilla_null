/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.Validator;

public class TreeVisitorValidator
implements Validator {
    private final Validator validator;

    public TreeVisitorValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public final void accept(Node node, ProblemReporter reporter) {
        this.validator.accept(node, reporter);
        for (Node child : node.getChildNodes()) {
            this.accept(child, reporter);
        }
    }
}

