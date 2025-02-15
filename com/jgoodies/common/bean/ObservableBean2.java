/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.bean;

import com.jgoodies.common.bean.ObservableBean;
import java.beans.PropertyChangeListener;

public interface ObservableBean2
extends ObservableBean {
    public void addPropertyChangeListener(String var1, PropertyChangeListener var2);

    public void removePropertyChangeListener(String var1, PropertyChangeListener var2);

    public PropertyChangeListener[] getPropertyChangeListeners();

    public PropertyChangeListener[] getPropertyChangeListeners(String var1);
}

