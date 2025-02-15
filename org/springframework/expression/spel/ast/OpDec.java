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

public class OpDec
extends Operator {
    private final boolean postfix;

    public OpDec(int startPos, int endPos, boolean postfix, SpelNodeImpl ... operands) {
        super("--", startPos, endPos, operands);
        this.postfix = postfix;
        Assert.notEmpty((Object[])operands, "Operands must not be empty");
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        SpelNodeImpl operand = this.getLeftOperand();
        ValueRef lvalue = operand.getValueRef(state);
        TypedValue operandTypedValue = lvalue.getValue();
        Object operandValue = operandTypedValue.getValue();
        TypedValue returnValue = operandTypedValue;
        TypedValue newValue = null;
        if (operandValue instanceof Number) {
            Number op1 = (Number)operandValue;
            newValue = op1 instanceof BigDecimal ? new TypedValue(((BigDecimal)op1).subtract(BigDecimal.ONE), operandTypedValue.getTypeDescriptor()) : (op1 instanceof Double ? new TypedValue(op1.doubleValue() - 1.0, operandTypedValue.getTypeDescriptor()) : (op1 instanceof Float ? new TypedValue(Float.valueOf(op1.floatValue() - 1.0f), operandTypedValue.getTypeDescriptor()) : (op1 instanceof BigInteger ? new TypedValue(((BigInteger)op1).subtract(BigInteger.ONE), operandTypedValue.getTypeDescriptor()) : (op1 instanceof Long ? new TypedValue(op1.longValue() - 1L, operandTypedValue.getTypeDescriptor()) : (op1 instanceof Integer ? new TypedValue(op1.intValue() - 1, operandTypedValue.getTypeDescriptor()) : (op1 instanceof Short ? new TypedValue(op1.shortValue() - 1, operandTypedValue.getTypeDescriptor()) : (op1 instanceof Byte ? new TypedValue(op1.byteValue() - 1, operandTypedValue.getTypeDescriptor()) : new TypedValue(op1.doubleValue() - 1.0, operandTypedValue.getTypeDescriptor()))))))));
        }
        if (newValue == null) {
            try {
                newValue = state.operate(Operation.SUBTRACT, returnValue.getValue(), 1);
            } catch (SpelEvaluationException ex) {
                if (ex.getMessageCode() == SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES) {
                    throw new SpelEvaluationException(operand.getStartPosition(), SpelMessage.OPERAND_NOT_DECREMENTABLE, operand.toStringAST());
                }
                throw ex;
            }
        }
        try {
            lvalue.setValue(newValue.getValue());
        } catch (SpelEvaluationException see) {
            if (see.getMessageCode() == SpelMessage.SETVALUE_NOT_SUPPORTED) {
                throw new SpelEvaluationException(operand.getStartPosition(), SpelMessage.OPERAND_NOT_DECREMENTABLE, new Object[0]);
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
        return this.getLeftOperand().toStringAST() + "--";
    }

    @Override
    public SpelNodeImpl getRightOperand() {
        throw new IllegalStateException("No right operand");
    }
}

