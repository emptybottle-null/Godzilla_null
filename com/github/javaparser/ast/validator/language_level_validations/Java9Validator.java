/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java8Validator;
import com.github.javaparser.ast.validator.language_level_validations.chunks.ModifierValidator;
import com.github.javaparser.ast.validator.language_level_validations.chunks.UnderscoreKeywordValidator;

public class Java9Validator
extends Java8Validator {
    final Validator underscoreKeywordValidator = new UnderscoreKeywordValidator();
    final Validator modifiers = new ModifierValidator(true, true, true);
    final SingleNodeTypeValidator<TryStmt> tryWithResources = new SingleNodeTypeValidator<TryStmt>(TryStmt.class, (n, reporter) -> {
        if (n.getCatchClauses().isEmpty() && n.getResources().isEmpty() && !n.getFinallyBlock().isPresent()) {
            reporter.report(n, "Try has no finally, no catch, and no resources.", new Object[0]);
        }
    });

    public Java9Validator() {
        this.add(this.underscoreKeywordValidator);
        this.remove(this.noModules);
        this.replace(this.modifiersWithoutPrivateInterfaceMethods, this.modifiers);
        this.replace(this.tryWithLimitedResources, this.tryWithResources);
    }
}

