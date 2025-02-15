/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.validator.ReservedKeywordValidator;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java1_1Validator;
import com.github.javaparser.ast.validator.language_level_validations.chunks.ModifierValidator;

public class Java1_2Validator
extends Java1_1Validator {
    final Validator modifiersWithoutDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods = new ModifierValidator(true, false, false);
    final Validator strictfpNotAllowed = new ReservedKeywordValidator("strictfp");

    public Java1_2Validator() {
        this.replace(this.modifiersWithoutStrictfpAndDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods, this.modifiersWithoutDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods);
        this.add(this.strictfpNotAllowed);
    }
}

