/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.validator.language_level_validations.Java1_3Validator;

public class Java1_4Validator
extends Java1_3Validator {
    public Java1_4Validator() {
        this.remove(this.noAssertKeyword);
    }
}

