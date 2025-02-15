/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations.chunks;

import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LiteralStringValueExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.VisitorValidator;

public class NoUnderscoresInIntegerLiteralsValidator
extends VisitorValidator {
    @Override
    public void visit(IntegerLiteralExpr n, ProblemReporter arg) {
        NoUnderscoresInIntegerLiteralsValidator.validate(n, arg);
        super.visit(n, arg);
    }

    @Override
    public void visit(LongLiteralExpr n, ProblemReporter arg) {
        NoUnderscoresInIntegerLiteralsValidator.validate(n, arg);
        super.visit(n, arg);
    }

    private static void validate(LiteralStringValueExpr n, ProblemReporter arg) {
        if (n.getValue().contains("_")) {
            arg.report(n, "Underscores in literal values are not supported.", new Object[0]);
        }
    }
}

