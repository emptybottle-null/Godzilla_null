/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.util.AnimatedIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class FlatAnimatedIcon
extends FlatAbstractIcon
implements AnimatedIcon {
    public FlatAnimatedIcon(int width, int height, Color color) {
        super(width, height, color);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        super.paintIcon(c, g, x, y);
        AnimatedIcon.AnimationSupport.saveIconLocation(this, c, x, y);
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        AnimatedIcon.AnimationSupport.paintIcon(this, c, g, 0, 0);
    }
}

