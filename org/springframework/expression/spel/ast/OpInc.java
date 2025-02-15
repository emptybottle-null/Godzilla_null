/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.ast.ValueRef;
import org.springframework.util.Assert;

public class OpInc
extends Operator {
    private final boolean postfix;

    public OpInc(int startPos, int endPos, boolean postfix, SpelNodeImpl ... operands) {
        super("++", startPos, endPos, operands);
        this.postfix = postfix;
        Assert.notEmpty((Object[])operands, "Operands must not be empty");
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        SpelNodeImpl operand = this.getLeftOperand();
        ValueRef valueRef = operand.getValueRef(state);
        TypedValue typedValue = valueRef.getValue();
        Object value = typedValue.getValue();
        TypedValue returnValue = typedValue;
        TypedValue newValue = null;
        if (value instanceof Number) {
            Number op1 = (Number)value;
            newValue = op1 instanceof BigDecimal ? new TypedValue(((BigDecimal)op1).add(BigDecimal.ONE), typedValue.getTypeDescriptor()) : (op1 instanceof Double ? new TypedValue(op1.doubleValue() + 1.0, typedValue.getTypeDescriptor()) : (op1 instanceof Float ? new TypedValue(Float.valueOf(op1.floatValue() + 1.0f), typedValue.getTypeDescriptor()) : (op1 instanceof BigInteger ? new TypedValue(((BigInteger)op1).add(BigInteger.ONE), typedValue.getTypeDescriptor()) : (op1 instanceof Long ? new TypedValue(op1.longValue() + 1L, typedValue.getTypeDescriptor()) : (op1 instanceof Integer ? new TypedValue(op1.intValue() + 1, typedValue.getTypeDescriptor()) : (op1 instanceof Short ? new TypedValue(op1.shortValue() + 1, typedValue.getTypeDescriptor()) : (op1 instanceof Byte ? new TypedValue(op1.byteValue() + 1, typedValue.getTypeDescriptor()) : new TypedValue(op1.doubleValue() + 1.0, typedValue.getTypeDescriptor()))))))));
        }
        if (newValue == null) {
            try {
                newValue = state.operate(Operation.ADD, returnValue.getValue(), 1);
            } catch (SpelEvaluationException ex) {
                if (ex.getMessageCode() == SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES) {
                    throw new SpelEvaluationException(operand.getStartPosition(), SpelMessage.OPERAND_NOT_INCREMENTABLE, operand.toStringAST());
                }
                throw ex;
            }
        }
        try {
            valueRef.setValue(newValue.getValue());
        } catch (SpelEvaluationException see) {
            if (see.getMessageCode() == SpelMessage.SETVALUE_NOT_SUPPORTED) {
                throw new SpelEvaluationException(operand.getStartPosition(), SpelMessage.OPERAND_NOT_INCREMENTABLE, new Object[0]);
            }
            throw see;
        }
        if (!this.postfix) {
            returnValue = newValue;
        }
        return returnValue;
    }

    @Override
    public String toStringAST() {
        return this.getLeftOperand().toStringAST() + "++";
    }

    @Override
    public SpelNodeImpl getRightOperand() {
        throw new IllegalStateException("No right operand");
    }
}

