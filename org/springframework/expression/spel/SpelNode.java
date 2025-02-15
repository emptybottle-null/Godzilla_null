/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.lang.Nullable;

public interface SpelNode {
    @Nullable
    public Object getValue(ExpressionState var1) throws EvaluationException;

    public TypedValue getTypedValue(ExpressionState var1) throws EvaluationException;

    public boolean isWritable(ExpressionState var1) throws EvaluationException;

    public void setValue(ExpressionState var1, @Nullable Object var2) throws EvaluationException;

    public String toStringAST();

    public int getChildCount();

    public SpelNode getChild(int var1);

    @Nullable
    public Class<?> getObjectClass(@Nullable Object var1);

    public int getStartPosition();

    public int getEndPosition();
}

