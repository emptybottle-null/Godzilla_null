/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.tools.reflect;

public class CannotCreateException
extends Exception {
    private static final long serialVersionUID = 1L;

    public CannotCreateException(String s) {
        super(s);
    }

    public CannotCreateException(Exception e) {
        super("by " + e.toString());
    }
}

