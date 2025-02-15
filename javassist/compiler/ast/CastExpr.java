/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.TokenId;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Visitor;

public class CastExpr
extends ASTList
implements TokenId {
    private static final long serialVersionUID = 1L;
    protected int castType;
    protected int arrayDim;

    public CastExpr(ASTList className, int dim, ASTree expr) {
        super(className, new ASTList(expr));
        this.castType = 307;
        this.arrayDim = dim;
    }

    public CastExpr(int type, int dim, ASTree expr) {
        super(null, new ASTList(expr));
        this.castType = type;
        this.arrayDim = dim;
    }

    public int getType() {
        return this.castType;
    }

    public int getArrayDim() {
        return this.arrayDim;
    }

    public ASTList getClassName() {
        return (ASTList)this.getLeft();
    }

    public ASTree getOprand() {
        return this.getRight().getLeft();
    }

    public void setOprand(ASTree t) {
        this.getRight().setLeft(t);
    }

    @Override
    public String getTag() {
        return "cast:" + this.castType + ":" + this.arrayDim;
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atCastExpr(this);
    }
}

