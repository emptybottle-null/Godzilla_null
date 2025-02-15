/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.ui.AbstractTabs;
import com.jediterm.terminal.ui.JediTermWidget;
import java.awt.Component;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TerminalTabsImpl
implements AbstractTabs<JediTermWidget> {
    protected JTabbedPane myTabbedPane = new JTabbedPane();

    @Override
    public int getTabCount() {
        return this.myTabbedPane.getTabCount();
    }

    @Override
    public void addTab(String name, JediTermWidget terminal) {
        this.myTabbedPane.addTab(name, terminal);
    }

    @Override
    public String getTitleAt(int index) {
        return this.myTabbedPane.getTitleAt(index);
    }

    @Override
    public int getSelectedIndex() {
        return this.myTabbedPane.getSelectedIndex();
    }

    @Override
    public void setSelectedIndex(int index) {
        this.myTabbedPane.setSelectedIndex(index);
    }

    @Override
    public void setTabComponentAt(int index, Component component) {
        this.myTabbedPane.setTabComponentAt(index, component);
    }

    @Override
    public int indexOfComponent(Component component) {
        return this.myTabbedPane.indexOfComponent(component);
    }

    @Override
    public int indexOfTabComponent(Component component) {
        return this.myTabbedPane.indexOfTabComponent(component);
    }

    @Override
    public void removeAll() {
        this.myTabbedPane.removeAll();
    }

    @Override
    public void remove(JediTermWidget terminal) {
        this.myTabbedPane.remove(terminal);
    }

    @Override
    public void setTitleAt(int index, String name) {
        this.myTabbedPane.setTitleAt(index, name);
    }

    @Override
    public void setSelectedComponent(JediTermWidget terminal) {
        this.myTabbedPane.setSelectedComponent(terminal);
    }

    @Override
    public JComponent getComponent() {
        return this.myTabbedPane;
    }

    @Override
    public JediTermWidget getComponentAt(int index) {
        return (JediTermWidget)this.myTabbedPane.getComponentAt(index);
    }

    @Override
    public void addChangeListener(final AbstractTabs.TabChangeListener listener) {
        this.myTabbedPane.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                listener.selectionChanged();
            }
        });
        this.myTabbedPane.addContainerListener(new ContainerListener(){

            @Override
            public void componentAdded(ContainerEvent e) {
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                if (e.getSource() == TerminalTabsImpl.this.myTabbedPane) {
                    listener.tabRemoved();
                }
            }
        });
    }
}

