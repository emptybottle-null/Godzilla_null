/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class FlatRadioButtonIcon
extends FlatCheckBoxIcon {
    protected final int centerDiameter;

    public FlatRadioButtonIcon() {
        this.centerDiameter = FlatRadioButtonIcon.getUIInt("RadioButton.icon.centerDiameter", 8, this.style);
    }

    @Override
    protected void paintFocusBorder(Component c, Graphics2D g) {
        int wh = 15 + this.focusWidth * 2;
        g.fillOval(-this.focusWidth, -this.focusWidth, wh, wh);
    }

    @Override
    protected void paintBorder(Component c, Graphics2D g) {
        g.fillOval(0, 0, 15, 15);
    }

    @Override
    protected void paintBackground(Component c, Graphics2D g) {
        g.fillOval(1, 1, 13, 13);
    }

    @Override
    protected void paintCheckmark(Component c, Graphics2D g) {
        float xy = (float)(15 - this.centerDiameter) / 2.0f;
        g.fill(new Ellipse2D.Float(xy, xy, this.centerDiameter, this.centerDiameter));
    }
}

