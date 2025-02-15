/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.Visitor;

public class Variable
extends Symbol {
    private static final long serialVersionUID = 1L;
    protected Declarator declarator;

    public Variable(String sym, Declarator d) {
        super(sym);
        this.declarator = d;
    }

    public Declarator getDeclarator() {
        return this.declarator;
    }

    @Override
    public String toString() {
        return this.identifier + ":" + this.declarator.getType();
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atVariable(this);
    }
}

