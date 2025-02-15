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
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.support.BooleanTypedValue;

public class OperatorNot
extends SpelNodeImpl {
    public OperatorNot(int startPos, int endPos, SpelNodeImpl operand) {
        super(startPos, endPos, operand);
        this.exitTypeDescriptor = "Z";
    }

    @Override
    public BooleanTypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        try {
            Boolean value = this.children[0].getValue(state, Boolean.class);
            if (value == null) {
                throw new SpelEvaluationException(SpelMessage.TYPE_CONVERSION_ERROR, "null", "boolean");
            }
            return BooleanTypedValue.forValue(value == false);
        } catch (SpelEvaluationException ex) {
            ex.setPosition(this.getChild(0).getStartPosition());
            throw ex;
        }
    }

    @Override
    public String toStringAST() {
        return "!" + this.getChild(0).toStringAST();
    }

    @Override
    public boolean isCompilable() {
        SpelNodeImpl child = this.children[0];
        return child.isCompilable() && CodeFlow.isBooleanCompatible(child.exitTypeDescriptor);
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        this.children[0].generateCode(mv, cf);
        cf.unboxBooleanIfNecessary(mv);
        Label elseTarget = new Label();
        Label endOfIf = new Label();
        mv.visitJumpInsn(154, elseTarget);
        mv.visitInsn(4);
        mv.visitJumpInsn(167, endOfIf);
        mv.visitLabel(elseTarget);
        mv.visitInsn(3);
        mv.visitLabel(endOfIf);
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}

