/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.springframework.asm.MethodVisitor;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;

public class OpDivide
extends Operator {
    public OpDivide(int startPos, int endPos, SpelNodeImpl ... operands) {
        super("/", startPos, endPos, operands);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        Object leftOperand = this.getLeftOperand().getValueInternal(state).getValue();
        Object rightOperand = this.getRightOperand().getValueInternal(state).getValue();
        if (leftOperand instanceof Number && rightOperand instanceof Number) {
            Number leftNumber = (Number)leftOperand;
            Number rightNumber = (Number)rightOperand;
            if (leftNumber instanceof BigDecimal || rightNumber instanceof BigDecimal) {
                BigDecimal leftBigDecimal = NumberUtils.convertNumberToTargetClass(leftNumber, BigDecimal.class);
                BigDecimal rightBigDecimal = NumberUtils.convertNumberToTargetClass(rightNumber, BigDecimal.class);
                int scale = Math.max(leftBigDecimal.scale(), rightBigDecimal.scale());
                return new TypedValue(leftBigDecimal.divide(rightBigDecimal, scale, RoundingMode.HALF_EVEN));
            }
            if (leftNumber instanceof Double || rightNumber instanceof Double) {
                this.exitTypeDescriptor = "D";
                return new TypedValue(leftNumber.doubleValue() / rightNumber.doubleValue());
            }
            if (leftNumber instanceof Float || rightNumber instanceof Float) {
                this.exitTypeDescriptor = "F";
                return new TypedValue(Float.valueOf(leftNumber.floatValue() / rightNumber.floatValue()));
            }
            if (leftNumber instanceof BigInteger || rightNumber instanceof BigInteger) {
                BigInteger leftBigInteger = NumberUtils.convertNumberToTargetClass(leftNumber, BigInteger.class);
                BigInteger rightBigInteger = NumberUtils.convertNumberToTargetClass(rightNumber, BigInteger.class);
                return new TypedValue(leftBigInteger.divide(rightBigInteger));
            }
            if (leftNumber instanceof Long || rightNumber instanceof Long) {
                this.exitTypeDescriptor = "J";
                return new TypedValue(leftNumber.longValue() / rightNumber.longValue());
            }
            if (CodeFlow.isIntegerForNumericOp(leftNumber) || CodeFlow.isIntegerForNumericOp(rightNumber)) {
                this.exitTypeDescriptor = "I";
                return new TypedValue(leftNumber.intValue() / rightNumber.intValue());
            }
            return new TypedValue(leftNumber.doubleValue() / rightNumber.doubleValue());
        }
        return state.operate(Operation.DIVIDE, leftOperand, rightOperand);
    }

    @Override
    public boolean isCompilable() {
        if (!this.getLeftOperand().isCompilable()) {
            return false;
        }
        if (this.children.length > 1 && !this.getRightOperand().isCompilable()) {
            return false;
        }
        return this.exitTypeDescriptor != null;
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        this.getLeftOperand().generateCode(mv, cf);
        String leftDesc = this.getLeftOperand().exitTypeDescriptor;
        String exitDesc = this.exitTypeDescriptor;
        Assert.state(exitDesc != null, "No exit type descriptor");
        char targetDesc = exitDesc.charAt(0);
        CodeFlow.insertNumericUnboxOrPrimitiveTypeCoercion(mv, leftDesc, targetDesc);
        if (this.children.length > 1) {
            cf.enterCompilationScope();
            this.getRightOperand().generateCode(mv, cf);
            String rightDesc = this.getRightOperand().exitTypeDescriptor;
            cf.exitCompilationScope();
            CodeFlow.insertNumericUnboxOrPrimitiveTypeCoercion(mv, rightDesc, targetDesc);
            switch (targetDesc) {
                case 'I': {
                    mv.visitInsn(108);
                    break;
                }
                case 'J': {
                    mv.visitInsn(109);
                    break;
                }
                case 'F': {
                    mv.visitInsn(110);
                    break;
                }
                case 'D': {
                    mv.visitInsn(111);
                    break;
                }
                default: {
                    throw new IllegalStateException("Unrecognized exit type descriptor: '" + this.exitTypeDescriptor + "'");
                }
            }
        }
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}

