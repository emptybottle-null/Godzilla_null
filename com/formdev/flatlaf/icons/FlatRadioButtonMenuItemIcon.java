/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatCheckBoxMenuItemIcon;
import java.awt.Graphics2D;

public class FlatRadioButtonMenuItemIcon
extends FlatCheckBoxMenuItemIcon {
    @Override
    protected void paintCheckmark(Graphics2D g2) {
        g2.fillOval(4, 4, 7, 7);
    }
}

