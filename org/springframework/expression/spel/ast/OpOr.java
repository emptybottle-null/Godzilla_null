/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import org.springframework.asm.Label;
import org.springframework.asm.MethodVisitor;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.support.BooleanTypedValue;
import org.springframework.lang.Nullable;

public class OpOr
extends Operator {
    public OpOr(int startPos, int endPos, SpelNodeImpl ... operands) {
        super("or", startPos, endPos, operands);
        this.exitTypeDescriptor = "Z";
    }

    @Override
    public BooleanTypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        if (this.getBooleanValue(state, this.getLeftOperand())) {
            return BooleanTypedValue.TRUE;
        }
        return BooleanTypedValue.forValue(this.getBooleanValue(state, this.getRightOperand()));
    }

    private boolean getBooleanValue(ExpressionState state, SpelNodeImpl operand) {
        try {
            Boolean value = operand.getValue(state, Boolean.class);
            this.assertValueNotNull(value);
            return value;
        } catch (SpelEvaluationException ee) {
            ee.setPosition(operand.getStartPosition());
            throw ee;
        }
    }

    private void assertValueNotNull(@Nullable Boolean value) {
        if (value == null) {
            throw new SpelEvaluationException(SpelMessage.TYPE_CONVERSION_ERROR, "null", "boolean");
        }
    }

    @Override
    public boolean isCompilable() {
        SpelNodeImpl left = this.getLeftOperand();
        SpelNodeImpl right = this.getRightOperand();
        return left.isCompilable() && right.isCompilable() && CodeFlow.isBooleanCompatible(left.exitTypeDescriptor) && CodeFlow.isBooleanCompatible(right.exitTypeDescriptor);
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        Label elseTarget = new Label();
        Label endOfIf = new Label();
        cf.enterCompilationScope();
        this.getLeftOperand().generateCode(mv, cf);
        cf.unboxBooleanIfNecessary(mv);
        cf.exitCompilationScope();
        mv.visitJumpInsn(153, elseTarget);
        mv.visitLdcInsn(1);
        mv.visitJumpInsn(167, endOfIf);
        mv.visitLabel(elseTarget);
        cf.enterCompilationScope();
        this.getRightOperand().generateCode(mv, cf);
        cf.unboxBooleanIfNecessary(mv);
        cf.exitCompilationScope();
        mv.visitLabel(endOfIf);
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}

