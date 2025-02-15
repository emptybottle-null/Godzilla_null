/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import org.springframework.expression.EvaluationException;
import org.springframework.lang.Nullable;

public interface TypeComparator {
    public boolean canCompare(@Nullable Object var1, @Nullable Object var2);

    public int compare(@Nullable Object var1, @Nullable Object var2) throws EvaluationException;
}

