/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.util.StringJoiner;
import org.springframework.asm.MethodVisitor;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.ast.ValueRef;
import org.springframework.lang.Nullable;

public class CompoundExpression
extends SpelNodeImpl {
    public CompoundExpression(int startPos, int endPos, SpelNodeImpl ... expressionComponents) {
        super(startPos, endPos, expressionComponents);
        if (expressionComponents.length < 2) {
            throw new IllegalStateException("Do not build compound expressions with less than two entries: " + expressionComponents.length);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected ValueRef getValueRef(ExpressionState state) throws EvaluationException {
        if (this.getChildCount() == 1) {
            return this.children[0].getValueRef(state);
        }
        SpelNodeImpl nextNode = this.children[0];
        TypedValue result = nextNode.getValueInternal(state);
        int cc = this.getChildCount();
        for (int i = 1; i < cc - 1; ++i) {
            try {
                state.pushActiveContextObject(result);
                nextNode = this.children[i];
                result = nextNode.getValueInternal(state);
                continue;
            } finally {
                state.popActiveContextObject();
            }
        }
        try {
            state.pushActiveContextObject(result);
            nextNode = this.children[cc - 1];
            ValueRef valueRef = nextNode.getValueRef(state);
            state.popActiveContextObject();
            return valueRef;
        } catch (Throwable throwable) {
            try {
                state.popActiveContextObject();
                throw throwable;
            } catch (SpelEvaluationException ex) {
                ex.setPosition(nextNode.getStartPosition());
                throw ex;
            }
        }
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        ValueRef ref = this.getValueRef(state);
        TypedValue result = ref.getValue();
        this.exitTypeDescriptor = this.children[this.children.length - 1].exitTypeDescriptor;
        return result;
    }

    @Override
    public void setValue(ExpressionState state, @Nullable Object value) throws EvaluationException {
        this.getValueRef(state).setValue(value);
    }

    @Override
    public boolean isWritable(ExpressionState state) throws EvaluationException {
        return this.getValueRef(state).isWritable();
    }

    @Override
    public String toStringAST() {
        StringJoiner sj = new StringJoiner(".");
        for (int i = 0; i < this.getChildCount(); ++i) {
            sj.add(this.getChild(i).toStringAST());
        }
        return sj.toString();
    }

    @Override
    public boolean isCompilable() {
        for (SpelNodeImpl child : this.children) {
            if (child.isCompilable()) continue;
            return false;
        }
        return true;
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        for (SpelNodeImpl child : this.children) {
            child.generateCode(mv, cf);
        }
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}

