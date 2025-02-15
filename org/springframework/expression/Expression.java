/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.lang.Nullable;

public interface Expression {
    public String getExpressionString();

    @Nullable
    public Object getValue() throws EvaluationException;

    @Nullable
    public <T> T getValue(@Nullable Class<T> var1) throws EvaluationException;

    @Nullable
    public Object getValue(@Nullable Object var1) throws EvaluationException;

    @Nullable
    public <T> T getValue(@Nullable Object var1, @Nullable Class<T> var2) throws EvaluationException;

    @Nullable
    public Object getValue(EvaluationContext var1) throws EvaluationException;

    @Nullable
    public Object getValue(EvaluationContext var1, @Nullable Object var2) throws EvaluationException;

    @Nullable
    public <T> T getValue(EvaluationContext var1, @Nullable Class<T> var2) throws EvaluationException;

    @Nullable
    public <T> T getValue(EvaluationContext var1, @Nullable Object var2, @Nullable Class<T> var3) throws EvaluationException;

    @Nullable
    public Class<?> getValueType() throws EvaluationException;

    @Nullable
    public Class<?> getValueType(@Nullable Object var1) throws EvaluationException;

    @Nullable
    public Class<?> getValueType(EvaluationContext var1) throws EvaluationException;

    @Nullable
    public Class<?> getValueType(EvaluationContext var1, @Nullable Object var2) throws EvaluationException;

    @Nullable
    public TypeDescriptor getValueTypeDescriptor() throws EvaluationException;

    @Nullable
    public TypeDescriptor getValueTypeDescriptor(@Nullable Object var1) throws EvaluationException;

    @Nullable
    public TypeDescriptor getValueTypeDescriptor(EvaluationContext var1) throws EvaluationException;

    @Nullable
    public TypeDescriptor getValueTypeDescriptor(EvaluationContext var1, @Nullable Object var2) throws EvaluationException;

    public boolean isWritable(@Nullable Object var1) throws EvaluationException;

    public boolean isWritable(EvaluationContext var1) throws EvaluationException;

    public boolean isWritable(EvaluationContext var1, @Nullable Object var2) throws EvaluationException;

    public void setValue(@Nullable Object var1, @Nullable Object var2) throws EvaluationException;

    public void setValue(EvaluationContext var1, @Nullable Object var2) throws EvaluationException;

    public void setValue(EvaluationContext var1, @Nullable Object var2, @Nullable Object var3) throws EvaluationException;
}

