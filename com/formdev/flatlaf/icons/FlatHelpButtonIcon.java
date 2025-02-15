/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public class FlatHelpButtonIcon
extends FlatAbstractIcon {
    protected final int focusWidth = UIManager.getInt("Component.focusWidth");
    protected final Color focusColor = UIManager.getColor("Component.focusColor");
    protected final Color borderColor = UIManager.getColor("HelpButton.borderColor");
    protected final Color disabledBorderColor = UIManager.getColor("HelpButton.disabledBorderColor");
    protected final Color focusedBorderColor = UIManager.getColor("HelpButton.focusedBorderColor");
    protected final Color hoverBorderColor = UIManager.getColor("HelpButton.hoverBorderColor");
    protected final Color background = UIManager.getColor("HelpButton.background");
    protected final Color disabledBackground = UIManager.getColor("HelpButton.disabledBackground");
    protected final Color focusedBackground = UIManager.getColor("HelpButton.focusedBackground");
    protected final Color hoverBackground = UIManager.getColor("HelpButton.hoverBackground");
    protected final Color pressedBackground = UIManager.getColor("HelpButton.pressedBackground");
    protected final Color questionMarkColor = UIManager.getColor("HelpButton.questionMarkColor");
    protected final Color disabledQuestionMarkColor = UIManager.getColor("HelpButton.disabledQuestionMarkColor");
    protected final int iconSize = 22 + this.focusWidth * 2;

    public FlatHelpButtonIcon() {
        super(0, 0, null);
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g2) {
        boolean enabled = c.isEnabled();
        boolean focused = FlatUIUtils.isPermanentFocusOwner(c);
        if (focused && FlatButtonUI.isFocusPainted(c)) {
            g2.setColor(this.focusColor);
            g2.fill(new Ellipse2D.Float(0.5f, 0.5f, this.iconSize - 1, this.iconSize - 1));
        }
        g2.setColor(FlatButtonUI.buttonStateColor(c, this.borderColor, this.disabledBorderColor, this.focusedBorderColor, this.hoverBorderColor, null));
        g2.fill(new Ellipse2D.Float((float)this.focusWidth + 0.5f, (float)this.focusWidth + 0.5f, 21.0f, 21.0f));
        g2.setColor(FlatUIUtils.deriveColor(FlatButtonUI.buttonStateColor(c, this.background, this.disabledBackground, this.focusedBackground, this.hoverBackground, this.pressedBackground), this.background));
        g2.fill(new Ellipse2D.Float((float)this.focusWidth + 1.5f, (float)this.focusWidth + 1.5f, 19.0f, 19.0f));
        Path2D.Float q = new Path2D.Float();
        ((Path2D)q).moveTo(11.0, 5.0);
        ((Path2D)q).curveTo(8.8, 5.0, 7.0, 6.8, 7.0, 9.0);
        ((Path2D)q).lineTo(9.0, 9.0);
        ((Path2D)q).curveTo(9.0, 7.9, 9.9, 7.0, 11.0, 7.0);
        ((Path2D)q).curveTo(12.1, 7.0, 13.0, 7.9, 13.0, 9.0);
        ((Path2D)q).curveTo(13.0, 11.0, 10.0, 10.75, 10.0, 14.0);
        ((Path2D)q).lineTo(12.0, 14.0);
        ((Path2D)q).curveTo(12.0, 11.75, 15.0, 11.5, 15.0, 9.0);
        ((Path2D)q).curveTo(15.0, 6.8, 13.2, 5.0, 11.0, 5.0);
        q.closePath();
        g2.translate(this.focusWidth, this.focusWidth);
        g2.setColor(enabled ? this.questionMarkColor : this.disabledQuestionMarkColor);
        g2.fill(q);
        g2.fillRect(10, 15, 2, 2);
    }

    @Override
    public int getIconWidth() {
        return UIScale.scale(this.iconSize);
    }

    @Override
    public int getIconHeight() {
        return UIScale.scale(this.iconSize);
    }
}

