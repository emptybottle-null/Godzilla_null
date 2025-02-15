/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public abstract class FlatInternalFrameAbstractIcon
extends FlatAbstractIcon {
    private final Color hoverBackground;
    private final Color pressedBackground;

    public FlatInternalFrameAbstractIcon() {
        this(UIManager.getDimension("InternalFrame.buttonSize"), UIManager.getColor("InternalFrame.buttonHoverBackground"), UIManager.getColor("InternalFrame.buttonPressedBackground"));
    }

    public FlatInternalFrameAbstractIcon(Dimension size, Color hoverBackground, Color pressedBackground) {
        super(size.width, size.height, null);
        this.hoverBackground = hoverBackground;
        this.pressedBackground = pressedBackground;
    }

    protected void paintBackground(Component c, Graphics2D g) {
        Color background = FlatButtonUI.buttonStateColor(c, null, null, null, this.hoverBackground, this.pressedBackground);
        if (background != null) {
            g.setColor(FlatUIUtils.deriveColor(background, c.getBackground()));
            g.fillRect(0, 0, this.width, this.height);
        }
    }
}

