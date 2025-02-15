/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations.chunks;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.VisitorValidator;

public class UnderscoreKeywordValidator
extends VisitorValidator {
    @Override
    public void visit(Name n, ProblemReporter arg) {
        UnderscoreKeywordValidator.validateIdentifier(n, n.getIdentifier(), arg);
        super.visit(n, arg);
    }

    @Override
    public void visit(SimpleName n, ProblemReporter arg) {
        UnderscoreKeywordValidator.validateIdentifier(n, n.getIdentifier(), arg);
        super.visit(n, arg);
    }

    private static void validateIdentifier(Node n, String id, ProblemReporter arg) {
        if (id.equals("_")) {
            arg.report(n, "'_' is a reserved keyword.", new Object[0]);
        }
    }
}

