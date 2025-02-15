/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.validator.language_level_validations;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithTokenRange;
import com.github.javaparser.ast.validator.SingleNodeTypeValidator;
import com.github.javaparser.ast.validator.Validator;
import com.github.javaparser.ast.validator.language_level_validations.Java7Validator;
import com.github.javaparser.ast.validator.language_level_validations.chunks.ModifierValidator;

public class Java8Validator
extends Java7Validator {
    final Validator modifiersWithoutPrivateInterfaceMethods = new ModifierValidator(true, true, false);
    final Validator defaultMethodsInInterface = new SingleNodeTypeValidator<ClassOrInterfaceDeclaration>(ClassOrInterfaceDeclaration.class, (n, reporter) -> {
        if (n.isInterface()) {
            n.getMethods().forEach(m -> {
                if (m.isDefault() && !m.getBody().isPresent()) {
                    reporter.report((NodeWithTokenRange<?>)m, "'default' methods must have a body.", new Object[0]);
                }
            });
        }
    });

    public Java8Validator() {
        this.replace(this.modifiersWithoutDefaultAndStaticInterfaceMethodsAndPrivateInterfaceMethods, this.modifiersWithoutPrivateInterfaceMethods);
        this.add(this.defaultMethodsInInterface);
        this.remove(this.noLambdas);
    }
}

