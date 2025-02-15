/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler.ast;

import javassist.CtField;
import javassist.compiler.CompileError;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.Visitor;

public class Member
extends Symbol {
    private static final long serialVersionUID = 1L;
    private CtField field = null;

    public Member(String name) {
        super(name);
    }

    public void setField(CtField f) {
        this.field = f;
    }

    public CtField getField() {
        return this.field;
    }

    @Override
    public void accept(Visitor v) throws CompileError {
        v.atMember(this);
    }
}

