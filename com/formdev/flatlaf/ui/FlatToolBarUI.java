/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolBarUI;

public class FlatToolBarUI
extends BasicToolBarUI {
    public static ComponentUI createUI(JComponent c) {
        return new FlatToolBarUI();
    }

    @Override
    protected ContainerListener createToolBarContListener() {
        return new BasicToolBarUI.ToolBarContListener(){

            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
                Component c = e.getChild();
                if (c instanceof AbstractButton) {
                    c.setFocusable(false);
                }
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                super.componentRemoved(e);
                Component c = e.getChild();
                if (c instanceof AbstractButton) {
                    c.setFocusable(true);
                }
            }
        };
    }

    @Override
    protected void setBorderToRollover(Component c) {
    }

    @Override
    protected void setBorderToNonRollover(Component c) {
    }

    @Override
    protected void setBorderToNormal(Component c) {
    }

    @Override
    protected void installRolloverBorders(JComponent c) {
    }

    @Override
    protected void installNonRolloverBorders(JComponent c) {
    }

    @Override
    protected void installNormalBorders(JComponent c) {
    }

    @Override
    protected Border createRolloverBorder() {
        return null;
    }

    @Override
    protected Border createNonRolloverBorder() {
        return null;
    }

    @Override
    public void setOrientation(int orientation) {
        Insets margin;
        Insets newMargin;
        if (orientation != this.toolBar.getOrientation() && !(newMargin = new Insets(margin.left, margin.top, margin.right, margin.bottom)).equals(margin = this.toolBar.getMargin())) {
            this.toolBar.setMargin(newMargin);
        }
        super.setOrientation(orientation);
    }
}

