/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.validator.language_level_validations.Java13Validator;

public class Java13PreviewValidator
extends Java13Validator {
    public Java13PreviewValidator() {
        this.remove(this.noTextBlockLiteral);
        this.remove(this.noSwitchExpressions);
        this.remove(this.onlyOneLabelInSwitchCase);
        this.remove(this.noYield);
    }
}

