/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.TokenId;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.Visitor;

public class Declarator
extends ASTList
implements TokenId {
    private static final long serialVersionUID = 1L;
    protected int varType;
    protected int arrayDim;
    protected int localVar;
    protected String qualifiedClass;

    public Declarator(int type, int dim) {
        super(null);
        this.varType = type;
        this.arrayDim = dim;
        this.localVar = -1;
        this.qualifiedClass = null;
    }

    public Declarator(ASTList className, int dim) {
        super(null);
        this.varType = 307;
        this.arrayDim = dim;
        this.localVar = -1;
        this.qualifiedClass = Declarator.astToClassName(className, '/');
    }

    public Declarator(int type, String jvmClassName, int dim, int var, Symbol sym) {
        super(null);
        this.varType = type;
        this.arrayDim = dim;
        this.localVar = var;
        this.qualifiedClass = jvmClassName;
        this.setLeft(sym);
        Declarator.append(this, null);
    }

    public Declarator make(Symbol sym, int dim, ASTree init) {
        Declarator d = new Declarator(this.varType, this.arrayDim + dim);
        d.qualifiedClass = this.qualifiedClass;
        d.setLeft(sym);
        Declarator.append(d, init);
        return d;
    }

    public int getType() {
        return this.varType;
    }

    public int getArrayDim() {
        return this.arrayDim;
    }

    public void addArrayDim(int d) {
        this.arrayDim += d;
    }

    public String getClassName() {
        return this.qualifiedClass;
    }

    public void setClassName(String s) {
        this.qualifiedClass = s;
    }

    public Symbol getVariable() {
        return (Symbol)this.getLeft();
    }

    public void setVariable(Symbol sym) {
        this.setLeft(sym);
    }

    public ASTree getInitializer() {
        ASTList t = this.tail();
        if (t != null) {
            return t.head();
        }
        return null;
    }

    public void setLocalVar(int n) {
        this.localVar = n;
    }

    public int getLocalVar() {
        return this.localVar;
    }

    @Override
    public String getTag() {
        return "decl";
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atDeclarator(this);
    }

    public static String astToClassName(ASTList name, char sep) {
        if (name == null) {
            return null;
        }
        StringBuffer sbuf = new StringBuffer();
        Declarator.astToClassName(sbuf, name, sep);
        return sbuf.toString();
    }

    private static void astToClassName(StringBuffer sbuf, ASTList name, char sep) {
        while (true) {
            ASTree h;
            if ((h = name.head()) instanceof Symbol) {
                sbuf.append(((Symbol)h).get());
            } else if (h instanceof ASTList) {
                Declarator.astToClassName(sbuf, (ASTList)h, sep);
            }
            name = name.tail();
            if (name == null) break;
            sbuf.append(sep);
        }
    }
}

