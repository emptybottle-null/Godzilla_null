/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.observer;

import com.github.javaparser.ast.observer.AstObserver;

public interface Observable {
    public void register(AstObserver var1);

    public void unregister(AstObserver var1);

    public boolean isRegistered(AstObserver var1);
}

