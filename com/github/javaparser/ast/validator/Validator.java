/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.TypedValidator;

public interface Validator
extends TypedValidator<Node> {
    @Override
    public void accept(Node var1, ProblemReporter var2);
}

