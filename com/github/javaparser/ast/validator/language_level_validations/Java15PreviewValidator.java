/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.validator.language_level_validations.Java15Validator;

public class Java15PreviewValidator
extends Java15Validator {
    public Java15PreviewValidator() {
        this.remove(this.noPatternMatchingInstanceOf);
    }
}

