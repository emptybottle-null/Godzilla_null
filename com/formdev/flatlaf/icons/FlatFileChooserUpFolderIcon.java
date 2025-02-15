/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatFileChooserUpFolderIcon
extends FlatAbstractIcon {
    private final Color blueColor = UIManager.getColor("Actions.Blue");

    public FlatFileChooserUpFolderIcon() {
        super(16, 16, UIManager.getColor("Actions.Grey"));
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        g.fill(FlatUIUtils.createPath(2.0, 3.0, 5.5, 3.0, 7.0, 5.0, 9.0, 5.0, 9.0, 9.0, 13.0, 9.0, 13.0, 5.0, 14.0, 5.0, 14.0, 13.0, 2.0, 13.0));
        g.setColor(this.blueColor);
        g.fill(FlatUIUtils.createPath(12.0, 4.0, 12.0, 8.0, 10.0, 8.0, 10.0, 4.0, 8.0, 4.0, 11.0, 1.0, 14.0, 4.0, 12.0, 4.0));
    }
}

