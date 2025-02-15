/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

public final class ClassTooLargeException
extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 160715609518896765L;
    private final String className;
    private final int constantPoolCount;

    public ClassTooLargeException(String className, int constantPoolCount) {
        super("Class too large: " + className);
        this.className = className;
        this.constantPoolCount = constantPoolCount;
    }

    public String getClassName() {
        return this.className;
    }

    public int getConstantPoolCount() {
        return this.constantPoolCount;
    }
}

