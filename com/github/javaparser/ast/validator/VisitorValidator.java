/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public abstract class VisitorValidator
extends VoidVisitorAdapter<ProblemReporter>
implements Validator {
    @Override
    public void accept(Node node, ProblemReporter problemReporter) {
        node.accept(this, problemReporter);
    }
}

