/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.common;

public class VariableDeclaration {
    private String type;
    private String name;
    private int offset;

    public VariableDeclaration(String name, int offset) {
        this(null, name, offset);
    }

    public VariableDeclaration(String type, String name, int offset) {
        this.type = type;
        this.name = name;
        this.offset = offset;
    }

    public String getName() {
        return this.name;
    }

    public int getOffset() {
        return this.offset;
    }

    public String getType() {
        return this.type;
    }
}

