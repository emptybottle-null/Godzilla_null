/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import org.springframework.expression.EvaluationException;

@FunctionalInterface
public interface TypeLocator {
    public Class<?> findType(String var1) throws EvaluationException;
}

