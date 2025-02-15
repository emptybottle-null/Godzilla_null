/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.tools.reflect;

import java.lang.reflect.InvocationTargetException;

public class CannotInvokeException
extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Throwable err = null;

    public Throwable getReason() {
        return this.err;
    }

    public CannotInvokeException(String reason) {
        super(reason);
    }

    public CannotInvokeException(InvocationTargetException e) {
        super("by " + e.getTargetException().toString());
        this.err = e.getTargetException();
    }

    public CannotInvokeException(IllegalAccessException e) {
        super("by " + e.toString());
        this.err = e;
    }

    public CannotInvokeException(ClassNotFoundException e) {
        super("by " + e.toString());
        this.err = e;
    }
}

