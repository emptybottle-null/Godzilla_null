/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import java.util.List;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.MethodExecutor;
import org.springframework.lang.Nullable;

public interface MethodResolver {
    @Nullable
    public MethodExecutor resolve(EvaluationContext var1, Object var2, String var3, List<TypeDescriptor> var4) throws AccessException;
}

