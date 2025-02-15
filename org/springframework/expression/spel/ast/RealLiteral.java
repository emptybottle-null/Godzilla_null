/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import org.springframework.asm.MethodVisitor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ast.Literal;

public class RealLiteral
extends Literal {
    private final TypedValue value;

    public RealLiteral(String payload, int startPos, int endPos, double value) {
        super(payload, startPos, endPos);
        this.value = new TypedValue(value);
        this.exitTypeDescriptor = "D";
    }

    @Override
    public TypedValue getLiteralValue() {
        return this.value;
    }

    @Override
    public boolean isCompilable() {
        return true;
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        mv.visitLdcInsn(this.value.getValue());
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}

