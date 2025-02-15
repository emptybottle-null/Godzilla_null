/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.math.BigDecimal;
import java.math.BigInteger;
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

public class OpMinus
extends Operator {
    public OpMinus(int startPos, int endPos, SpelNodeImpl ... operands) {
        super("-", startPos, endPos, operands);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        SpelNodeImpl leftOp = this.getLeftOperand();
        if (this.children.length < 2) {
            Object operand = leftOp.getValueInternal(state).getValue();
            if (operand instanceof Number) {
                if (operand instanceof BigDecimal) {
                    return new TypedValue(((BigDecimal)operand).negate());
                }
                if (operand instanceof Double) {
                    this.exitTypeDescriptor = "D";
                    return new TypedValue(0.0 - ((Number)operand).doubleValue());
                }
                if (operand instanceof Float) {
                    this.exitTypeDescriptor = "F";
                    return new TypedValue(Float.valueOf(0.0f - ((Number)operand).floatValue()));
                }
                if (operand instanceof BigInteger) {
                    return new TypedValue(((BigInteger)operand).negate());
                }
                if (operand instanceof Long) {
                    this.exitTypeDescriptor = "J";
                    return new TypedValue(0L - ((Number)operand).longValue());
                }
                if (operand instanceof Integer) {
                    this.exitTypeDescriptor = "I";
                    return new TypedValue(0 - ((Number)operand).intValue());
                }
                if (operand instanceof Short) {
                    return new TypedValue(0 - ((Number)operand).shortValue());
                }
                if (operand instanceof Byte) {
                    return new TypedValue(0 - ((Number)operand).byteValue());
                }
                return new TypedValue(0.0 - ((Number)operand).doubleValue());
            }
            return state.operate(Operation.SUBTRACT, operand, null);
        }
        Object left = leftOp.getValueInternal(state).getValue();
        Object right = this.getRightOperand().getValueInternal(state).getValue();
        if (left instanceof Number && right instanceof Number) {
            Number leftNumber = (Number)left;
            Number rightNumber = (Number)right;
            if (leftNumber instanceof BigDecimal || rightNumber instanceof BigDecimal) {
                BigDecimal leftBigDecimal = NumberUtils.convertNumberToTargetClass(leftNumber, BigDecimal.class);
                BigDecimal rightBigDecimal = NumberUtils.convertNumberToTargetClass(rightNumber, BigDecimal.class);
                return new TypedValue(leftBigDecimal.subtract(rightBigDecimal));
            }
            if (leftNumber instanceof Double || rightNumber instanceof Double) {
                this.exitTypeDescriptor = "D";
                return new TypedValue(leftNumber.doubleValue() - rightNumber.doubleValue());
            }
            if (leftNumber instanceof Float || rightNumber instanceof Float) {
                this.exitTypeDescriptor = "F";
                return new TypedValue(Float.valueOf(leftNumber.floatValue() - rightNumber.floatValue()));
            }
            if (leftNumber instanceof BigInteger || rightNumber instanceof BigInteger) {
                BigInteger leftBigInteger = NumberUtils.convertNumberToTargetClass(leftNumber, BigInteger.class);
                BigInteger rightBigInteger = NumberUtils.convertNumberToTargetClass(rightNumber, BigInteger.class);
                return new TypedValue(leftBigInteger.subtract(rightBigInteger));
            }
            if (leftNumber instanceof Long || rightNumber instanceof Long) {
                this.exitTypeDescriptor = "J";
                return new TypedValue(leftNumber.longValue() - rightNumber.longValue());
            }
            if (CodeFlow.isIntegerForNumericOp(leftNumber) || CodeFlow.isIntegerForNumericOp(rightNumber)) {
                this.exitTypeDescriptor = "I";
                return new TypedValue(leftNumber.intValue() - rightNumber.intValue());
            }
            return new TypedValue(leftNumber.doubleValue() - rightNumber.doubleValue());
        }
        if (left instanceof String && right instanceof Integer && ((String)left).length() == 1) {
            String theString = (String)left;
            Integer theInteger = (Integer)right;
            return new TypedValue(Character.toString((char)(theString.charAt(0) - theInteger)));
        }
        return state.operate(Operation.SUBTRACT, left, right);
    }

    @Override
    public String toStringAST() {
        if (this.children.length < 2) {
            return "-" + this.getLeftOperand().toStringAST();
        }
        return super.toStringAST();
    }

    @Override
    public SpelNodeImpl getRightOperand() {
        if (this.children.length < 2) {
            throw new IllegalStateException("No right operand");
        }
        return this.children[1];
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
                    mv.visitInsn(100);
                    break;
                }
                case 'J': {
                    mv.visitInsn(101);
                    break;
                }
                case 'F': {
                    mv.visitInsn(102);
                    break;
                }
                case 'D': {
                    mv.visitInsn(103);
                    break;
                }
                default: {
                    throw new IllegalStateException("Unrecognized exit type descriptor: '" + this.exitTypeDescriptor + "'");
                }
            }
        } else {
            switch (targetDesc) {
                case 'I': {
                    mv.visitInsn(116);
                    break;
                }
                case 'J': {
                    mv.visitInsn(117);
                    break;
                }
                case 'F': {
                    mv.visitInsn(118);
                    break;
                }
                case 'D': {
                    mv.visitInsn(119);
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

