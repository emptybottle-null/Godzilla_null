/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast.observer;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.ObservableProperty;

public interface AstObserver {
    public void propertyChange(Node var1, ObservableProperty var2, Object var3, Object var4);

    public void parentChange(Node var1, Node var2, Node var3);

    public void listChange(NodeList<?> var1, ListChangeType var2, int var3, Node var4);

    public void listReplacement(NodeList<?> var1, int var2, Node var3, Node var4);

    public static enum ListChangeType {
        ADDITION,
        REMOVAL;

    }
}

