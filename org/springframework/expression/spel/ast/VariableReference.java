/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import java.lang.reflect.Modifier;
import org.springframework.asm.MethodVisitor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.expression.spel.ast.ValueRef;
import org.springframework.lang.Nullable;

public class VariableReference
extends SpelNodeImpl {
    private static final String THIS = "this";
    private static final String ROOT = "root";
    private final String name;

    public VariableReference(String variableName, int startPos, int endPos) {
        super(startPos, endPos, new SpelNodeImpl[0]);
        this.name = variableName;
    }

    @Override
    public ValueRef getValueRef(ExpressionState state) throws SpelEvaluationException {
        if (this.name.equals(THIS)) {
            return new ValueRef.TypedValueHolderValueRef(state.getActiveContextObject(), this);
        }
        if (this.name.equals(ROOT)) {
            return new ValueRef.TypedValueHolderValueRef(state.getRootContextObject(), this);
        }
        TypedValue result = state.lookupVariable(this.name);
        return new VariableRef(this.name, result, state.getEvaluationContext());
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws SpelEvaluationException {
        if (this.name.equals(THIS)) {
            return state.getActiveContextObject();
        }
        if (this.name.equals(ROOT)) {
            TypedValue result = state.getRootContextObject();
            this.exitTypeDescriptor = CodeFlow.toDescriptorFromObject(result.getValue());
            return result;
        }
        TypedValue result = state.lookupVariable(this.name);
        Object value = result.getValue();
        this.exitTypeDescriptor = value == null || !Modifier.isPublic(value.getClass().getModifiers()) ? "Ljava/lang/Object" : CodeFlow.toDescriptorFromObject(value);
        return result;
    }

    @Override
    public void setValue(ExpressionState state, @Nullable Object value) throws SpelEvaluationException {
        state.setVariable(this.name, value);
    }

    @Override
    public String toStringAST() {
        return "#" + this.name;
    }

    @Override
    public boolean isWritable(ExpressionState expressionState) throws SpelEvaluationException {
        return !this.name.equals(THIS) && !this.name.equals(ROOT);
    }

    @Override
    public boolean isCompilable() {
        return this.exitTypeDescriptor != null;
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        if (this.name.equals(ROOT)) {
            mv.visitVarInsn(25, 1);
        } else {
            mv.visitVarInsn(25, 2);
            mv.visitLdcInsn(this.name);
            mv.visitMethodInsn(185, "org/springframework/expression/EvaluationContext", "lookupVariable", "(Ljava/lang/String;)Ljava/lang/Object;", true);
        }
        CodeFlow.insertCheckCast(mv, this.exitTypeDescriptor);
        cf.pushDescriptor(this.exitTypeDescriptor);
    }

    private static class VariableRef
    implements ValueRef {
        private final String name;
        private final TypedValue value;
        private final EvaluationContext evaluationContext;

        public VariableRef(String name, TypedValue value, EvaluationContext evaluationContext) {
            this.name = name;
            this.value = value;
            this.evaluationContext = evaluationContext;
        }

        @Override
        public TypedValue getValue() {
            return this.value;
        }

        @Override
        public void setValue(@Nullable Object newValue) {
            this.evaluationContext.setVariable(this.name, newValue);
        }

        @Override
        public boolean isWritable() {
            return true;
        }
    }
}

