/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;

public interface BeanResolver {
    public Object resolve(EvaluationContext var1, String var2) throws AccessException;
}

