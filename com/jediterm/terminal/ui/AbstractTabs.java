/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import java.awt.Component;
import javax.swing.JComponent;

public interface AbstractTabs<T extends Component> {
    public int getTabCount();

    public void addTab(String var1, T var2);

    public String getTitleAt(int var1);

    public int getSelectedIndex();

    public void setSelectedIndex(int var1);

    public void setTabComponentAt(int var1, Component var2);

    public int indexOfComponent(Component var1);

    public int indexOfTabComponent(Component var1);

    public void removeAll();

    public void remove(T var1);

    public void setTitleAt(int var1, String var2);

    public void setSelectedComponent(T var1);

    public JComponent getComponent();

    public T getComponentAt(int var1);

    public void addChangeListener(TabChangeListener var1);

    public static interface TabChangeListener {
        public void tabRemoved();

        public void selectionChanged();
    }
}

