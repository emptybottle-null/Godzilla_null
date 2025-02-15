/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.sequence;

public interface CommandVisitor<T> {
    public void visitInsertCommand(T var1);

    public void visitKeepCommand(T var1);

    public void visitDeleteCommand(T var1);
}

