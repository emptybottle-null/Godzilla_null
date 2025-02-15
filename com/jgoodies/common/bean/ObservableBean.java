/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.bean;

import java.beans.PropertyChangeListener;

public interface ObservableBean {
    public void addPropertyChangeListener(PropertyChangeListener var1);

    public void removePropertyChangeListener(PropertyChangeListener var1);
}

