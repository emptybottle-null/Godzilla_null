/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Visitor;

public class StringL
extends ASTree {
    private static final long serialVersionUID = 1L;
    protected String text;

    public StringL(String t) {
        this.text = t;
    }

    public String get() {
        return this.text;
    }

    @Override
    public String toString() {
        return "\"" + this.text + "\"";
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atStringL(this);
    }
}

