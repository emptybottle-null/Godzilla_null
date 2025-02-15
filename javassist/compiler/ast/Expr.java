/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.TokenId;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Visitor;

public class Expr
extends ASTList
implements TokenId {
    private static final long serialVersionUID = 1L;
    protected int operatorId;

    Expr(int op, ASTree _head, ASTList _tail) {
        super(_head, _tail);
        this.operatorId = op;
    }

    Expr(int op, ASTree _head) {
        super(_head);
        this.operatorId = op;
    }

    public static Expr make(int op, ASTree oprand1, ASTree oprand2) {
        return new Expr(op, oprand1, new ASTList(oprand2));
    }

    public static Expr make(int op, ASTree oprand1) {
        return new Expr(op, oprand1);
    }

    public int getOperator() {
        return this.operatorId;
    }

    public void setOperator(int op) {
        this.operatorId = op;
    }

    public ASTree oprand1() {
        return this.getLeft();
    }

    public void setOprand1(ASTree expr) {
        this.setLeft(expr);
    }

    public ASTree oprand2() {
        return this.getRight().getLeft();
    }

    public void setOprand2(ASTree expr) {
        this.getRight().setLeft(expr);
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atExpr(this);
    }

    public String getName() {
        int id = this.operatorId;
        if (id < 128) {
            return String.valueOf((char)id);
        }
        if (350 <= id && id <= 371) {
            return opNames[id - 350];
        }
        if (id == 323) {
            return "instanceof";
        }
        return String.valueOf(id);
    }

    @Override
    protected String getTag() {
        return "op:" + this.getName();
    }
}

