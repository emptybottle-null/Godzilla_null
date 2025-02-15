/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.type.VarType;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java10Validator;
import com.github.javaparser.ast.validator.language_level_validations.chunks.VarValidator;

public class Java11Validator
extends Java10Validator {
    final Validator varAlsoInLambdaParameters = new SingleNodeTypeValidator<VarType>(VarType.class, new VarValidator(true));

    public Java11Validator() {
        this.replace(this.varOnlyOnLocalVariableDefinitionAndForAndTry, this.varAlsoInLambdaParameters);
    }
}

