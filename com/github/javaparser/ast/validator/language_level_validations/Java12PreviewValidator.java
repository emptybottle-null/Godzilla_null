/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.validator.language_level_validations.Java12Validator;

public class Java12PreviewValidator
extends Java12Validator {
    public Java12PreviewValidator() {
        this.remove(this.noSwitchExpressions);
        this.remove(this.onlyOneLabelInSwitchCase);
    }
}

