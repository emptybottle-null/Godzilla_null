/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.type.UnionType;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import com.github.javaparser.ast.validator.language_level_validations.Java6Validator;

public class Java7Validator
extends Java6Validator {
    final SingleNodeTypeValidator<TryStmt> tryWithLimitedResources = new SingleNodeTypeValidator<TryStmt>(TryStmt.class, (n, reporter) -> {
        if (n.getCatchClauses().isEmpty() && n.getResources().isEmpty() && !n.getFinallyBlock().isPresent()) {
            reporter.report(n, "Try has no finally, no catch, and no resources.", new Object[0]);
        }
        for (Expression resource : n.getResources()) {
            if (resource.isVariableDeclarationExpr()) continue;
            reporter.report(n, "Try with resources only supports variable declarations.", new Object[0]);
        }
    });
    private final SingleNodeTypeValidator<UnionType> multiCatch = new SingleNodeTypeValidator<UnionType>(UnionType.class, (n, reporter) -> {
        if (n.getElements().size() == 1) {
            reporter.report(n, "Union type (multi catch) must have at least two elements.", new Object[0]);
        }
    });

    public Java7Validator() {
        this.remove(this.genericsWithoutDiamondOperator);
        this.replace(this.tryWithoutResources, this.tryWithLimitedResources);
        this.remove(this.noBinaryIntegerLiterals);
        this.remove(this.noUnderscoresInIntegerLiterals);
        this.replace(this.noMultiCatch, this.multiCatch);
    }
}

