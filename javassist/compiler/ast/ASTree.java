/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import java.io.Serializable;
import javassist.compiler.CompileError;
import javassist.compiler.ast.Visitor;

public abstract class ASTree
implements Serializable {
    private static final long serialVersionUID = 1L;

    public ASTree getLeft() {
        return null;
    }

    public ASTree getRight() {
        return null;
    }

    public void setLeft(ASTree _left) {
    }

    public void setRight(ASTree _right) {
    }

    public abstract void accept(Visitor var1) throws CompileError;

    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        sbuf.append('<');
        sbuf.append(this.getTag());
        sbuf.append('>');
        return sbuf.toString();
    }

    protected String getTag() {
        String name = this.getClass().getName();
        return name.substring(name.lastIndexOf(46) + 1);
    }
}

