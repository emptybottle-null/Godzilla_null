/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class FlatPopupMenuBorder
extends FlatLineBorder {
    public FlatPopupMenuBorder() {
        super(UIManager.getInsets("PopupMenu.borderInsets"), UIManager.getColor("PopupMenu.borderColor"));
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        if (c instanceof Container && ((Container)c).getComponentCount() > 0 && ((Container)c).getComponent(0) instanceof JScrollPane) {
            insets.right = insets.bottom = UIScale.scale(1);
            insets.top = insets.bottom;
            insets.left = insets.bottom;
            return insets;
        }
        return super.getBorderInsets(c, insets);
    }
}

