/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.common;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.TypedValue;
import org.springframework.expression.common.ExpressionUtils;
import org.springframework.lang.Nullable;

public class LiteralExpression
implements Expression {
    private final String literalValue;

    public LiteralExpression(String literalValue) {
        this.literalValue = literalValue;
    }

    @Override
    public final String getExpressionString() {
        return this.literalValue;
    }

    @Override
    public Class<?> getValueType(EvaluationContext context) {
        return String.class;
    }

    @Override
    public String getValue() {
        return this.literalValue;
    }

    @Override
    @Nullable
    public <T> T getValue(@Nullable Class<T> expectedResultType) throws EvaluationException {
        String value = this.getValue();
        return ExpressionUtils.convertTypedValue(null, new TypedValue(value), expectedResultType);
    }

    @Override
    public String getValue(@Nullable Object rootObject) {
        return this.literalValue;
    }

    @Override
    @Nullable
    public <T> T getValue(@Nullable Object rootObject, @Nullable Class<T> desiredResultType) throws EvaluationException {
        String value = this.getValue(rootObject);
        return ExpressionUtils.convertTypedValue(null, new TypedValue(value), desiredResultType);
    }

    @Override
    public String getValue(EvaluationContext context) {
        return this.literalValue;
    }

    @Override
    @Nullable
    public <T> T getValue(EvaluationContext context, @Nullable Class<T> expectedResultType) throws EvaluationException {
        String value = this.getValue(context);
        return ExpressionUtils.convertTypedValue(context, new TypedValue(value), expectedResultType);
    }

    @Override
    public String getValue(EvaluationContext context, @Nullable Object rootObject) throws EvaluationException {
        return this.literalValue;
    }

    @Override
    @Nullable
    public <T> T getValue(EvaluationContext context, @Nullable Object rootObject, @Nullable Class<T> desiredResultType) throws EvaluationException {
        String value = this.getValue(context, rootObject);
        return ExpressionUtils.convertTypedValue(context, new TypedValue(value), desiredResultType);
    }

    @Override
    public Class<?> getValueType() {
        return String.class;
    }

    @Override
    public Class<?> getValueType(@Nullable Object rootObject) throws EvaluationException {
        return String.class;
    }

    @Override
    public Class<?> getValueType(EvaluationContext context, @Nullable Object rootObject) throws EvaluationException {
        return String.class;
    }

    @Override
    public TypeDescriptor getValueTypeDescriptor() {
        return TypeDescriptor.valueOf(String.class);
    }

    @Override
    public TypeDescriptor getValueTypeDescriptor(@Nullable Object rootObject) throws EvaluationException {
        return TypeDescriptor.valueOf(String.class);
    }

    @Override
    public TypeDescriptor getValueTypeDescriptor(EvaluationContext context) {
        return TypeDescriptor.valueOf(String.class);
    }

    @Override
    public TypeDescriptor getValueTypeDescriptor(EvaluationContext context, @Nullable Object rootObject) throws EvaluationException {
        return TypeDescriptor.valueOf(String.class);
    }

    @Override
    public boolean isWritable(@Nullable Object rootObject) throws EvaluationException {
        return false;
    }

    @Override
    public boolean isWritable(EvaluationContext context) {
        return false;
    }

    @Override
    public boolean isWritable(EvaluationContext context, @Nullable Object rootObject) throws EvaluationException {
        return false;
    }

    @Override
    public void setValue(@Nullable Object rootObject, @Nullable Object value) throws EvaluationException {
        throw new EvaluationException(this.literalValue, "Cannot call setValue() on a LiteralExpression");
    }

    @Override
    public void setValue(EvaluationContext context, @Nullable Object value) throws EvaluationException {
        throw new EvaluationException(this.literalValue, "Cannot call setValue() on a LiteralExpression");
    }

    @Override
    public void setValue(EvaluationContext context, @Nullable Object rootObject, @Nullable Object value) throws EvaluationException {
        throw new EvaluationException(this.literalValue, "Cannot call setValue() on a LiteralExpression");
    }
}

