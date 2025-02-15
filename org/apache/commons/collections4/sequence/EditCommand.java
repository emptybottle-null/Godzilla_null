/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.sequence;

import org.apache.commons.collections4.sequence.CommandVisitor;

public abstract class EditCommand<T> {
    private final T object;

    protected EditCommand(T object) {
        this.object = object;
    }

    protected T getObject() {
        return this.object;
    }

    public abstract void accept(CommandVisitor<T> var1);
}

