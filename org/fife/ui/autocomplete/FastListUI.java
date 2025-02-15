/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JViewport;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicListUI;

class FastListUI
extends BasicListUI {
    private boolean overriddenBackground;
    private boolean overriddenForeground;
    private static final int ESTIMATION_THRESHOLD = 200;

    FastListUI() {
    }

    private Color determineSelectionBackground() {
        Color c = UIManager.getColor("List.selectionBackground");
        if (c == null && (c = UIManager.getColor("nimbusSelectionBackground")) == null && (c = UIManager.getColor("textHighlight")) == null) {
            c = SystemColor.textHighlight;
        }
        return new Color(c.getRGB());
    }

    private Color determineSelectionForeground() {
        Color c = UIManager.getColor("List.selectionForeground");
        if (c == null && (c = UIManager.getColor("nimbusSelectedText")) == null && (c = UIManager.getColor("textHighlightText")) == null) {
            c = SystemColor.textHighlightText;
        }
        return new Color(c.getRGB());
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        if (this.list.getSelectionBackground() == null) {
            this.list.setSelectionBackground(this.determineSelectionBackground());
            this.overriddenBackground = true;
        }
        if (this.list.getSelectionForeground() == null) {
            this.list.setSelectionForeground(this.determineSelectionForeground());
            this.overriddenForeground = true;
        }
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        if (this.overriddenBackground) {
            this.list.setSelectionBackground(null);
        }
        if (this.overriddenForeground) {
            this.list.setSelectionForeground(null);
        }
    }

    @Override
    protected void updateLayoutState() {
        ListModel model = this.list.getModel();
        int itemCount = model.getSize();
        if (itemCount < 200) {
            super.updateLayoutState();
            return;
        }
        ListCellRenderer renderer = this.list.getCellRenderer();
        this.cellWidth = this.list.getWidth();
        if (this.list.getParent() instanceof JViewport) {
            this.cellWidth = this.list.getParent().getWidth();
        }
        this.cellHeights = null;
        if (renderer != null) {
            Object value = model.getElementAt(0);
            Component c = renderer.getListCellRendererComponent(this.list, value, 0, false, false);
            this.rendererPane.add(c);
            Dimension cellSize = c.getPreferredSize();
            this.cellHeight = cellSize.height;
            this.cellWidth = Math.max(this.cellWidth, cellSize.width);
        } else {
            this.cellHeight = 20;
        }
    }
}

