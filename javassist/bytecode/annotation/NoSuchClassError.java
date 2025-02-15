/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.annotation;

public class NoSuchClassError
extends Error {
    private static final long serialVersionUID = 1L;
    private String className;

    public NoSuchClassError(String className, Error cause) {
        super(cause.toString(), cause);
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }
}

