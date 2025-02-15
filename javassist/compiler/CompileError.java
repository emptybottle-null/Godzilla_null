/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.compiler.Lex;

public class CompileError
extends Exception {
    private static final long serialVersionUID = 1L;
    private Lex lex;
    private String reason;

    public CompileError(String s, Lex l) {
        this.reason = s;
        this.lex = l;
    }

    public CompileError(String s) {
        this.reason = s;
        this.lex = null;
    }

    public CompileError(CannotCompileException e) {
        this(e.getReason());
    }

    public CompileError(NotFoundException e) {
        this("cannot find " + e.getMessage());
    }

    public Lex getLex() {
        return this.lex;
    }

    @Override
    public String getMessage() {
        return this.reason;
    }

    @Override
    public String toString() {
        return "compile error: " + this.reason;
    }
}

