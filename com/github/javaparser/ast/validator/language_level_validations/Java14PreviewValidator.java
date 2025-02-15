/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.validator.language_level_validations.Java14Validator;

public class Java14PreviewValidator
extends Java14Validator {
    public Java14PreviewValidator() {
        this.remove(this.noPatternMatchingInstanceOf);
        this.remove(this.noTextBlockLiteral);
    }
}

