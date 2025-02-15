/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.TypedValue;

public interface ConstructorExecutor {
    public TypedValue execute(EvaluationContext var1, Object ... var2) throws AccessException;
}

