/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatFileChooserListViewIcon
extends FlatAbstractIcon {
    public FlatFileChooserListViewIcon() {
        super(16, 16, UIManager.getColor("Actions.Grey"));
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        g.fillRect(3, 3, 4, 4);
        g.fillRect(3, 9, 4, 4);
        g.fillRect(9, 9, 4, 4);
        g.fillRect(9, 3, 4, 4);
    }
}

