/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.SpelNodeImpl;

public class Identifier
extends SpelNodeImpl {
    private final TypedValue id;

    public Identifier(String payload, int startPos, int endPos) {
        super(startPos, endPos, new SpelNodeImpl[0]);
        this.id = new TypedValue(payload);
    }

    @Override
    public String toStringAST() {
        return String.valueOf(this.id.getValue());
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) {
        return this.id;
    }
}

