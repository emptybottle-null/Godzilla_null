/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution.declarations;

import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;

public interface ResolvedAnnotationMemberDeclaration
extends ResolvedValueDeclaration {
    public Expression getDefaultValue();
}

