/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import org.springframework.util.Assert;

public class NamedInheritableThreadLocal<T>
extends InheritableThreadLocal<T> {
    private final String name;

    public NamedInheritableThreadLocal(String name) {
        Assert.hasText(name, "Name must not be empty");
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}

