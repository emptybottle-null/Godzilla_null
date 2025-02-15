/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.lang.Nullable;

public interface ValueRef {
    public TypedValue getValue();

    public void setValue(@Nullable Object var1);

    public boolean isWritable();

    public static class TypedValueHolderValueRef
    implements ValueRef {
        private final TypedValue typedValue;
        private final SpelNodeImpl node;

        public TypedValueHolderValueRef(TypedValue typedValue, SpelNodeImpl node) {
            this.typedValue = typedValue;
            this.node = node;
        }

        @Override
        public TypedValue getValue() {
            return this.typedValue;
        }

        @Override
        public void setValue(@Nullable Object newValue) {
            throw new SpelEvaluationException(this.node.getStartPosition(), SpelMessage.NOT_ASSIGNABLE, this.node.toStringAST());
        }

        @Override
        public boolean isWritable() {
            return false;
        }
    }

    public static class NullValueRef
    implements ValueRef {
        static final NullValueRef INSTANCE = new NullValueRef();

        @Override
        public TypedValue getValue() {
            return TypedValue.NULL;
        }

        @Override
        public void setValue(@Nullable Object newValue) {
            throw new SpelEvaluationException(0, SpelMessage.NOT_ASSIGNABLE, "null");
        }

        @Override
        public boolean isWritable() {
            return false;
        }
    }
}

