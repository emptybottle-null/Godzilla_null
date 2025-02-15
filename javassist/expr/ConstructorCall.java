/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.expr;

import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;
import javassist.expr.MethodCall;

public class ConstructorCall
extends MethodCall {
    protected ConstructorCall(int pos, CodeIterator i, CtClass decl, MethodInfo m) {
        super(pos, i, decl, m);
    }

    @Override
    public String getMethodName() {
        return this.isSuper() ? "super" : "this";
    }

    @Override
    public CtMethod getMethod() throws NotFoundException {
        throw new NotFoundException("this is a constructor call.  Call getConstructor().");
    }

    public CtConstructor getConstructor() throws NotFoundException {
        return this.getCtClass().getConstructor(this.getSignature());
    }

    @Override
    public boolean isSuper() {
        return super.isSuper();
    }
}

