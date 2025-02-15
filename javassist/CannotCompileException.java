/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import javassist.NotFoundException;
import javassist.compiler.CompileError;

public class CannotCompileException
extends Exception {
    private static final long serialVersionUID = 1L;
    private Throwable myCause;
    private String message;

    @Override
    public synchronized Throwable getCause() {
        return this.myCause == this ? null : this.myCause;
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        this.myCause = cause;
        return this;
    }

    public String getReason() {
        if (this.message != null) {
            return this.message;
        }
        return this.toString();
    }

    public CannotCompileException(String msg) {
        super(msg);
        this.message = msg;
        this.initCause(null);
    }

    public CannotCompileException(Throwable e) {
        super("by " + e.toString());
        this.message = null;
        this.initCause(e);
    }

    public CannotCompileException(String msg, Throwable e) {
        this(msg);
        this.initCause(e);
    }

    public CannotCompileException(NotFoundException e) {
        this("cannot find " + e.getMessage(), e);
    }

    public CannotCompileException(CompileError e) {
        this("[source error] " + e.getMessage(), e);
    }

    public CannotCompileException(ClassNotFoundException e, String name) {
        this("cannot find " + name, e);
    }

    public CannotCompileException(ClassFormatError e, String name) {
        this("invalid class format: " + name, e);
    }
}

