/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.validator.language_level_validations.Java14Validator;

public class Java15Validator
extends Java14Validator {
    public Java15Validator() {
        this.remove(this.noTextBlockLiteral);
    }
}

