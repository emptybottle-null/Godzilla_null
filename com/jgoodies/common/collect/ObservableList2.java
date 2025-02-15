/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.collect;

import com.jgoodies.common.collect.ObservableList;

public interface ObservableList2<E>
extends ObservableList<E> {
    public void fireContentsChanged(int var1);

    public void fireContentsChanged(int var1, int var2);
}

