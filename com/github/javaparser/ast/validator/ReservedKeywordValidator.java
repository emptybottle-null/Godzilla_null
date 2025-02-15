/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator;

import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.validator.ProblemReporter;
import com.github.javaparser.ast.validator.VisitorValidator;
import com.github.javaparser.utils.CodeGenerationUtils;

public class ReservedKeywordValidator
extends VisitorValidator {
    private final String keyword;
    private final String error;

    public ReservedKeywordValidator(String keyword) {
        this.keyword = keyword;
        this.error = CodeGenerationUtils.f("'%s' cannot be used as an identifier as it is a keyword.", keyword);
    }

    @Override
    public void visit(Name n, ProblemReporter arg) {
        if (n.getIdentifier().equals(this.keyword)) {
            arg.report(n, this.error, new Object[0]);
        }
        super.visit(n, arg);
    }

    @Override
    public void visit(SimpleName n, ProblemReporter arg) {
        if (n.getIdentifier().equals(this.keyword)) {
            arg.report(n, this.error, new Object[0]);
        }
        super.visit(n, arg);
    }
}

