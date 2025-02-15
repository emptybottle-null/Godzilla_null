/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.signature;

import org.springframework.asm.signature.SignatureVisitor;

public class SignatureWriter
implements SignatureVisitor {
    private final StringBuffer a = new StringBuffer();
    private boolean b;
    private boolean c;
    private int d;

    public void visitFormalTypeParameter(String string) {
        if (!this.b) {
            this.b = true;
            this.a.append('<');
        }
        this.a.append(string);
        this.a.append(':');
    }

    public SignatureVisitor visitClassBound() {
        return this;
    }

    public SignatureVisitor visitInterfaceBound() {
        this.a.append(':');
        return this;
    }

    public SignatureVisitor visitSuperclass() {
        this.a();
        return this;
    }

    public SignatureVisitor visitInterface() {
        return this;
    }

    public SignatureVisitor visitParameterType() {
        this.a();
        if (!this.c) {
            this.c = true;
            this.a.append('(');
        }
        return this;
    }

    public SignatureVisitor visitReturnType() {
        this.a();
        if (!this.c) {
            this.a.append('(');
        }
        this.a.append(')');
        return this;
    }

    public SignatureVisitor visitExceptionType() {
        this.a.append('^');
        return this;
    }

    public void visitBaseType(char c) {
        this.a.append(c);
    }

    public void visitTypeVariable(String string) {
        this.a.append('T');
        this.a.append(string);
        this.a.append(';');
    }

    public SignatureVisitor visitArrayType() {
        this.a.append('[');
        return this;
    }

    public void visitClassType(String string) {
        this.a.append('L');
        this.a.append(string);
        this.d *= 2;
    }

    public void visitInnerClassType(String string) {
        this.b();
        this.a.append('.');
        this.a.append(string);
        this.d *= 2;
    }

    public void visitTypeArgument() {
        if (this.d % 2 == 0) {
            ++this.d;
            this.a.append('<');
        }
        this.a.append('*');
    }

    public SignatureVisitor visitTypeArgument(char c) {
        if (this.d % 2 == 0) {
            ++this.d;
            this.a.append('<');
        }
        if (c != '=') {
            this.a.append(c);
        }
        return this;
    }

    public void visitEnd() {
        this.b();
        this.a.append(';');
    }

    public String toString() {
        return this.a.toString();
    }

    private void a() {
        if (this.b) {
            this.b = false;
            this.a.append('>');
        }
    }

    private void b() {
        if (this.d % 2 == 1) {
            this.a.append('>');
        }
        this.d /= 2;
    }
}

