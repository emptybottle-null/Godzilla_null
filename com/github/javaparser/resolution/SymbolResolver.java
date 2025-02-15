/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.resolution;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.types.ResolvedType;

public interface SymbolResolver {
    public <T> T resolveDeclaration(Node var1, Class<T> var2);

    public <T> T toResolvedType(Type var1, Class<T> var2);

    public ResolvedType calculateType(Expression var1);
}

