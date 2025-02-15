/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.validator.language_level_validations.Java13Validator;

public class Java14Validator
extends Java13Validator {
    public Java14Validator() {
        this.remove(this.noSwitchExpressions);
        this.remove(this.onlyOneLabelInSwitchCase);
        this.remove(this.noYield);
    }
}

