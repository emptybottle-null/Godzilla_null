/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java9Validator;
import com.github.javaparser.ast.validator.language_level_validations.chunks.VarValidator;

public class Java10Validator
extends Java9Validator {
    final Validator varOnlyOnLocalVariableDefinitionAndForAndTry = new SingleNodeTypeValidator<VarType>(VarType.class, new VarValidator(false));

    public Java10Validator() {
        this.add(this.varOnlyOnLocalVariableDefinitionAndForAndTry);
    }
}

