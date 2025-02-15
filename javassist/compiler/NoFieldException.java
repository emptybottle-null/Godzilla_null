/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

import javassist.compiler.CompileError;
import javassist.compiler.ast.ASTree;

public class NoFieldException
extends CompileError {
    private static final long serialVersionUID = 1L;
    private String fieldName;
    private ASTree expr;

    public NoFieldException(String name, ASTree e) {
        super("no such field: " + name);
        this.fieldName = name;
        this.expr = e;
    }

    public String getField() {
        return this.fieldName;
    }

    public ASTree getExpr() {
        return this.expr;
    }
}

