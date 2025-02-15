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

public class CompositeStringExpression
implements Expression {
    private final String expressionString;
    private final Expression[] expressions;

    public CompositeStringExpression(String expressionString, Expression[] expressions) {
        this.expressionString = expressionString;
        this.expressions = expressions;
    }

    @Override
    public final String getExpressionString() {
        return this.expressionString;
    }

    public final Expression[] getExpressions() {
        return this.expressions;
    }

    @Override
    public String getValue() throws EvaluationException {
        StringBuilder sb = new StringBuilder();
        for (Expression expression : this.expressions) {
            String value = expression.getValue(String.class);
            if (value == null) continue;
            sb.append(value);
        }
        return sb.toString();
    }

    @Override
    @Nullable
    public <T> T getValue(@Nullable Class<T> expectedResultType) throws EvaluationException {
        String value = this.getValue();
        return ExpressionUtils.convertTypedValue(null, new TypedValue(value), expectedResultType);
    }

    @Override
    public String getValue(@Nullable Object rootObject) throws EvaluationException {
        StringBuilder sb = new StringBuilder();
        for (Expression expression : this.expressions) {
            String value = expression.getValue(rootObject, String.class);
            if (value == null) continue;
            sb.append(value);
        }
        return sb.toString();
    }

    @Override
    @Nullable
    public <T> T getValue(@Nullable Object rootObject, @Nullable Class<T> desiredResultType) throws EvaluationException {
        String value = this.getValue(rootObject);
        return ExpressionUtils.convertTypedValue(null, new TypedValue(value), desiredResultType);
    }

    @Override
    public String getValue(EvaluationContext context) throws EvaluationException {
        StringBuilder sb = new StringBuilder();
        for (Expression expression : this.expressions) {
            String value = expression.getValue(context, String.class);
            if (value == null) continue;
            sb.append(value);
        }
        return sb.toString();
    }

    @Override
    @Nullable
    public <T> T getValue(EvaluationContext context, @Nullable Class<T> expectedResultType) throws EvaluationException {
        String value = this.getValue(context);
        return ExpressionUtils.convertTypedValue(context, new TypedValue(value), expectedResultType);
    }

    @Override
    public String getValue(EvaluationContext context, @Nullable Object rootObject) throws EvaluationException {
        StringBuilder sb = new StringBuilder();
        for (Expression expression : this.expressions) {
            String value = expression.getValue(context, rootObject, String.class);
            if (value == null) continue;
            sb.append(value);
        }
        return sb.toString();
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
    public Class<?> getValueType(EvaluationContext context) {
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
        throw new EvaluationException(this.expressionString, "Cannot call setValue on a composite expression");
    }

    @Override
    public void setValue(EvaluationContext context, @Nullable Object value) throws EvaluationException {
        throw new EvaluationException(this.expressionString, "Cannot call setValue on a composite expression");
    }

    @Override
    public void setValue(EvaluationContext context, @Nullable Object rootObject, @Nullable Object value) throws EvaluationException {
        throw new EvaluationException(this.expressionString, "Cannot call setValue on a composite expression");
    }
}

