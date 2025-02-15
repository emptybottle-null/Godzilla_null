/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java1_0Validator;

public class Java1_1Validator
extends Java1_0Validator {
    final Validator innerClasses = new SingleNodeTypeValidator<ClassOrInterfaceDeclaration>(ClassOrInterfaceDeclaration.class, (n, reporter) -> n.getParentNode().ifPresent(p -> {
        if (p instanceof LocalClassDeclarationStmt && n.isInterface()) {
            reporter.report(n, "There is no such thing as a local interface.", new Object[0]);
        }
    }));

    public Java1_1Validator() {
        this.replace(this.noInnerClasses, this.innerClasses);
        this.remove(this.noReflection);
    }
}

