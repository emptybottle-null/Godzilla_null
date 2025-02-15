/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.nodeTypes;

public interface NodeWithDeclaration {
    default public String getDeclarationAsString() {
        return this.getDeclarationAsString(true, true, true);
    }

    default public String getDeclarationAsString(boolean includingModifiers, boolean includingThrows) {
        return this.getDeclarationAsString(includingModifiers, includingThrows, true);
    }

    public String getDeclarationAsString(boolean var1, boolean var2, boolean var3);
}

