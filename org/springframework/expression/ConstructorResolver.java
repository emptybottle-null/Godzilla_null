/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import java.util.List;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.AccessException;
import org.springframework.expression.ConstructorExecutor;
import org.springframework.expression.EvaluationContext;
import org.springframework.lang.Nullable;

@FunctionalInterface
public interface ConstructorResolver {
    @Nullable
    public ConstructorExecutor resolve(EvaluationContext var1, String var2, List<TypeDescriptor> var3) throws AccessException;
}

