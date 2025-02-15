/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatFileViewFileIcon
extends FlatAbstractIcon {
    public FlatFileViewFileIcon() {
        super(16, 16, UIManager.getColor("Objects.Grey"));
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        g.fill(FlatUIUtils.createPath(8.0, 6.0, 8.0, 1.0, 13.0, 1.0, 13.0, 15.0, 3.0, 15.0, 3.0, 6.0));
        g.fill(FlatUIUtils.createPath(3.0, 5.0, 7.0, 5.0, 7.0, 1.0));
    }
}

