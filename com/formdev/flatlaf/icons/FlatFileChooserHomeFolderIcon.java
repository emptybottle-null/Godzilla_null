/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatFileChooserHomeFolderIcon
extends FlatAbstractIcon {
    public FlatFileChooserHomeFolderIcon() {
        super(16, 16, UIManager.getColor("Actions.Grey"));
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        g.fill(FlatUIUtils.createPath(2.0, 8.0, 8.0, 2.0, 14.0, 8.0, 12.0, 8.0, 12.0, 13.0, 9.0, 13.0, 9.0, 10.0, 7.0, 10.0, 7.0, 13.0, 4.0, 13.0, 4.0, 8.0));
    }
}

