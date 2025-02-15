/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.lang.Nullable;

public abstract class CompiledExpression {
    public abstract Object getValue(@Nullable Object var1, @Nullable EvaluationContext var2) throws EvaluationException;
}

