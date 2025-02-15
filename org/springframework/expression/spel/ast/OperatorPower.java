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
import org.springframework.expression.spel.ast.Operator;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.util.NumberUtils;

public class OperatorPower
extends Operator {
    public OperatorPower(int startPos, int endPos, SpelNodeImpl ... operands) {
        super("^", startPos, endPos, operands);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        SpelNodeImpl leftOp = this.getLeftOperand();
        SpelNodeImpl rightOp = this.getRightOperand();
        Object leftOperand = leftOp.getValueInternal(state).getValue();
        Object rightOperand = rightOp.getValueInternal(state).getValue();
        if (leftOperand instanceof Number && rightOperand instanceof Number) {
            Number leftNumber = (Number)leftOperand;
            Number rightNumber = (Number)rightOperand;
            if (leftNumber instanceof BigDecimal) {
                BigDecimal leftBigDecimal = NumberUtils.convertNumberToTargetClass(leftNumber, BigDecimal.class);
                return new TypedValue(leftBigDecimal.pow(rightNumber.intValue()));
            }
            if (leftNumber instanceof BigInteger) {
                BigInteger leftBigInteger = NumberUtils.convertNumberToTargetClass(leftNumber, BigInteger.class);
                return new TypedValue(leftBigInteger.pow(rightNumber.intValue()));
            }
            if (leftNumber instanceof Double || rightNumber instanceof Double) {
                return new TypedValue(Math.pow(leftNumber.doubleValue(), rightNumber.doubleValue()));
            }
            if (leftNumber instanceof Float || rightNumber instanceof Float) {
                return new TypedValue(Math.pow(leftNumber.floatValue(), rightNumber.floatValue()));
            }
            double d = Math.pow(leftNumber.doubleValue(), rightNumber.doubleValue());
            if (d > 2.147483647E9 || leftNumber instanceof Long || rightNumber instanceof Long) {
                return new TypedValue((long)d);
            }
            return new TypedValue((int)d);
        }
        return state.operate(Operation.POWER, leftOperand, rightOperand);
    }
}

