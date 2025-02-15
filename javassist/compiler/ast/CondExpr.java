/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Visitor;

public class CondExpr
extends ASTList {
    private static final long serialVersionUID = 1L;

    public CondExpr(ASTree cond, ASTree thenp, ASTree elsep) {
        super(cond, new ASTList(thenp, new ASTList(elsep)));
    }

    public ASTree condExpr() {
        return this.head();
    }

    public void setCond(ASTree t) {
        this.setHead(t);
    }

    public ASTree thenExpr() {
        return this.tail().head();
    }

    public void setThen(ASTree t) {
        this.tail().setHead(t);
    }

    public ASTree elseExpr() {
        return this.tail().tail().head();
    }

    public void setElse(ASTree t) {
        this.tail().tail().setHead(t);
    }

    @Override
    public String getTag() {
        return "?:";
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atCondExpr(this);
    }
}

