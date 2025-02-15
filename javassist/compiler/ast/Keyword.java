/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Visitor;

public class Keyword
extends ASTree {
    private static final long serialVersionUID = 1L;
    protected int tokenId;

    public Keyword(int token) {
        this.tokenId = token;
    }

    public int get() {
        return this.tokenId;
    }

    @Override
    public String toString() {
        return "id:" + this.tokenId;
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atKeyword(this);
    }
}

