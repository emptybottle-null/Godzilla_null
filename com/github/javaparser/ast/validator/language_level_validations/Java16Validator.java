/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.validator.language_level_validations.Java15Validator;

public class Java16Validator
extends Java15Validator {
    public Java16Validator() {
        this.remove(this.noPatternMatchingInstanceOf);
    }
}

