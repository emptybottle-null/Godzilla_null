/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.ast;

import org.springframework.asm.MethodVisitor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;
import org.springframework.expression.spel.ast.Literal;

public class NullLiteral
extends Literal {
    public NullLiteral(int startPos, int endPos) {
        super(null, startPos, endPos);
        this.exitTypeDescriptor = "Ljava/lang/Object";
    }

    @Override
    public TypedValue getLiteralValue() {
        return TypedValue.NULL;
    }

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public boolean isCompilable() {
        return true;
    }

    @Override
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        mv.visitInsn(1);
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}

