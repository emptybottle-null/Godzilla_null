/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Stmnt;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.Visitor;

public class MethodDecl
extends ASTList {
    private static final long serialVersionUID = 1L;
    public static final String initName = "<init>";

    public MethodDecl(ASTree _head, ASTList _tail) {
        super(_head, _tail);
    }

    public boolean isConstructor() {
        Symbol sym = this.getReturn().getVariable();
        return sym != null && initName.equals(sym.get());
    }

    public ASTList getModifiers() {
        return (ASTList)this.getLeft();
    }

    public Declarator getReturn() {
        return (Declarator)this.tail().head();
    }

    public ASTList getParams() {
        return (ASTList)this.sublist(2).head();
    }

    public ASTList getThrows() {
        return (ASTList)this.sublist(3).head();
    }

    public Stmnt getBody() {
        return (Stmnt)this.sublist(4).head();
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atMethodDecl(this);
    }
}

