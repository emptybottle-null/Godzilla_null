/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.ast.ValueRef;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

public class Projection
extends SpelNodeImpl {
    private final boolean nullSafe;

    public Projection(boolean nullSafe, int startPos, int endPos, SpelNodeImpl expression) {
        super(startPos, endPos, expression);
        this.nullSafe = nullSafe;
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        return this.getValueRef(state).getValue();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected ValueRef getValueRef(ExpressionState state) throws EvaluationException {
        TypedValue op = state.getActiveContextObject();
        Object operand = op.getValue();
        boolean operandIsArray = ObjectUtils.isArray(operand);
        if (operand instanceof Map) {
            Map mapData = (Map)operand;
            ArrayList<Object> result = new ArrayList<Object>();
            for (Map.Entry entry : mapData.entrySet()) {
                try {
                    state.pushActiveContextObject(new TypedValue(entry));
                    state.enterScope();
                    result.add(this.children[0].getValueInternal(state).getValue());
                } finally {
                    state.popActiveContextObject();
                    state.exitScope();
                }
            }
            return new ValueRef.TypedValueHolderValueRef(new TypedValue(result), this);
        }
        if (operand instanceof Iterable || operandIsArray) {
            List<Object> data = operand instanceof Iterable ? (List<Object>)operand : Arrays.asList(ObjectUtils.toObjectArray(operand));
            ArrayList<Object> result = new ArrayList<Object>();
            Class arrayElementType = null;
            for (Object t : data) {
                try {
                    state.pushActiveContextObject(new TypedValue(t));
                    state.enterScope("index", result.size());
                    Object value = this.children[0].getValueInternal(state).getValue();
                    if (value != null && operandIsArray) {
                        arrayElementType = this.determineCommonType(arrayElementType, value.getClass());
                    }
                    result.add(value);
                } finally {
                    state.exitScope();
                    state.popActiveContextObject();
                }
            }
            if (operandIsArray) {
                if (arrayElementType == null) {
                    arrayElementType = Object.class;
                }
                Object resultArray = Array.newInstance(arrayElementType, result.size());
                System.arraycopy(result.toArray(), 0, resultArray, 0, result.size());
                return new ValueRef.TypedValueHolderValueRef(new TypedValue(resultArray), this);
            }
            return new ValueRef.TypedValueHolderValueRef(new TypedValue(result), this);
        }
        if (operand == null) {
            if (this.nullSafe) {
                return ValueRef.NullValueRef.INSTANCE;
            }
            throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.PROJECTION_NOT_SUPPORTED_ON_TYPE, "null");
        }
        throw new SpelEvaluationException(this.getStartPosition(), SpelMessage.PROJECTION_NOT_SUPPORTED_ON_TYPE, operand.getClass().getName());
    }

    @Override
    public String toStringAST() {
        return "![" + this.getChild(0).toStringAST() + "]";
    }

    private Class<?> determineCommonType(@Nullable Class<?> oldType, Class<?> newType) {
        if (oldType == null) {
            return newType;
        }
        if (oldType.isAssignableFrom(newType)) {
            return oldType;
        }
        for (Class<?> nextType = newType; nextType != Object.class; nextType = nextType.getSuperclass()) {
            if (!nextType.isAssignableFrom(oldType)) continue;
            return nextType;
        }
        for (Class<?> nextInterface : ClassUtils.getAllInterfacesForClassAsSet(newType)) {
            if (!nextInterface.isAssignableFrom(oldType)) continue;
            return nextInterface;
        }
        return Object.class;
    }
}

