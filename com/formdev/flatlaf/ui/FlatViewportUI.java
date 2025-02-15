/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicViewportUI;

public class FlatViewportUI
extends BasicViewportUI {
    public static ComponentUI createUI(JComponent c) {
        return FlatUIUtils.createSharedUI(FlatViewportUI.class, FlatViewportUI::new);
    }

    @Override
    public void update(Graphics g, JComponent c) {
        Component view = ((JViewport)c).getView();
        if (c.isOpaque() && view instanceof JTable) {
            g.setColor(view.getBackground());
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
            this.paint(g, c);
        } else {
            super.update(g, c);
        }
    }
}

