/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Type;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.ast.TypeReference;
import org.springframework.expression.spel.support.BooleanTypedValue;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class OperatorInstanceof
extends Operator {
    @Nullable
    private Class<?> type;

    public OperatorInstanceof(int startPos, int endPos, SpelNodeImpl ... operands) {
        super("instanceof", startPos, endPos, operands);
    }

    @Override
    public BooleanTypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        SpelNodeImpl rightOperand = this.getRightOperand();
        TypedValue left = this.getLeftOperand().getValueInternal(state);
        TypedValue right = rightOperand.getValueInternal(state);
        Object leftValue = left.getValue();
        Object rightValue = right.getValue();
        if (!(rightValue instanceof Class)) {
            throw new SpelEvaluationException(this.getRightOperand().getStartPosition(), SpelMessage.INSTANCEOF_OPERATOR_NEEDS_CLASS_OPERAND, rightValue == null ? "null" : rightValue.getClass().getName());
        }
        Class rightClass = (Class)rightValue;
        BooleanTypedValue result = leftValue == null ? BooleanTypedValue.FALSE : BooleanTypedValue.forValue(rightClass.isAssignableFrom(leftValue.getClass()));
        this.type = rightClass;
        if (rightOperand instanceof TypeReference) {
            this.exitTypeDescriptor = "Z";
        }
        return result;
    }

    @Override
    public boolean isCompilable() {
        return this.exitTypeDescriptor != null && this.getLeftOperand().isCompilable();
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        this.getLeftOperand().generateCode(mv, cf);
        CodeFlow.insertBoxIfNecessary(mv, cf.lastDescriptor());
        Assert.state(this.type != null, "No type available");
        if (this.type.isPrimitive()) {
            mv.visitInsn(87);
            mv.visitInsn(3);
        } else {
            mv.visitTypeInsn(193, Type.getInternalName(this.type));
        }
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}

