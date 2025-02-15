/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import org.springframework.asm.Label;
import org.springframework.asm.MethodVisitor;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

public class Ternary
extends SpelNodeImpl {
    public Ternary(int startPos, int endPos, SpelNodeImpl ... args) {
        super(startPos, endPos, args);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        Boolean value = this.children[0].getValue(state, Boolean.class);
        if (value == null) {
            throw new SpelEvaluationException(this.getChild(0).getStartPosition(), SpelMessage.TYPE_CONVERSION_ERROR, "null", "boolean");
        }
        TypedValue result = this.children[value != false ? 1 : 2].getValueInternal(state);
        this.computeExitTypeDescriptor();
        return result;
    }

    @Override
    public String toStringAST() {
        return this.getChild(0).toStringAST() + " ? " + this.getChild(1).toStringAST() + " : " + this.getChild(2).toStringAST();
    }

    private void computeExitTypeDescriptor() {
        if (this.exitTypeDescriptor == null && this.children[1].exitTypeDescriptor != null && this.children[2].exitTypeDescriptor != null) {
            String leftDescriptor = this.children[1].exitTypeDescriptor;
            String rightDescriptor = this.children[2].exitTypeDescriptor;
            this.exitTypeDescriptor = ObjectUtils.nullSafeEquals(leftDescriptor, rightDescriptor) ? leftDescriptor : "Ljava/lang/Object";
        }
    }

    @Override
    public boolean isCompilable() {
        SpelNodeImpl condition = this.children[0];
        SpelNodeImpl left = this.children[1];
        SpelNodeImpl right = this.children[2];
        return condition.isCompilable() && left.isCompilable() && right.isCompilable() && CodeFlow.isBooleanCompatible(condition.exitTypeDescriptor) && left.exitTypeDescriptor != null && right.exitTypeDescriptor != null;
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        this.computeExitTypeDescriptor();
        cf.enterCompilationScope();
        this.children[0].generateCode(mv, cf);
        String lastDesc = cf.lastDescriptor();
        Assert.state(lastDesc != null, "No last descriptor");
        if (!CodeFlow.isPrimitive(lastDesc)) {
            CodeFlow.insertUnboxInsns(mv, 'Z', lastDesc);
        }
        cf.exitCompilationScope();
        Label elseTarget = new Label();
        Label endOfIf = new Label();
        mv.visitJumpInsn(153, elseTarget);
        cf.enterCompilationScope();
        this.children[1].generateCode(mv, cf);
        if (!CodeFlow.isPrimitive(this.exitTypeDescriptor)) {
            lastDesc = cf.lastDescriptor();
            Assert.state(lastDesc != null, "No last descriptor");
            CodeFlow.insertBoxIfNecessary(mv, lastDesc.charAt(0));
        }
        cf.exitCompilationScope();
        mv.visitJumpInsn(167, endOfIf);
        mv.visitLabel(elseTarget);
        cf.enterCompilationScope();
        this.children[2].generateCode(mv, cf);
        if (!CodeFlow.isPrimitive(this.exitTypeDescriptor)) {
            lastDesc = cf.lastDescriptor();
            Assert.state(lastDesc != null, "No last descriptor");
            CodeFlow.insertBoxIfNecessary(mv, lastDesc.charAt(0));
        }
        cf.exitCompilationScope();
        mv.visitLabel(endOfIf);
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}

