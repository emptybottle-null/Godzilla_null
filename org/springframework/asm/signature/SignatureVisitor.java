/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.signature;

public interface SignatureVisitor {
    public static final char EXTENDS = '+';
    public static final char SUPER = '-';
    public static final char INSTANCEOF = '=';

    public void visitFormalTypeParameter(String var1);

    public SignatureVisitor visitClassBound();

    public SignatureVisitor visitInterfaceBound();

    public SignatureVisitor visitSuperclass();

    public SignatureVisitor visitInterface();

    public SignatureVisitor visitParameterType();

    public SignatureVisitor visitReturnType();

    public SignatureVisitor visitExceptionType();

    public void visitBaseType(char var1);

    public void visitTypeVariable(String var1);

    public SignatureVisitor visitArrayType();

    public void visitClassType(String var1);

    public void visitInnerClassType(String var1);

    public void visitTypeArgument();

    public SignatureVisitor visitTypeArgument(char var1);

    public void visitEnd();
}

