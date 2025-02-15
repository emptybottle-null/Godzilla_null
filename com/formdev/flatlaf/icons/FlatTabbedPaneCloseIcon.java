/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public class FlatTabbedPaneCloseIcon
extends FlatAbstractIcon {
    protected final Dimension size = UIManager.getDimension("TabbedPane.closeSize");
    protected final int arc = UIManager.getInt("TabbedPane.closeArc");
    protected final float crossPlainSize = FlatUIUtils.getUIFloat("TabbedPane.closeCrossPlainSize", 7.5f);
    protected final float crossFilledSize = FlatUIUtils.getUIFloat("TabbedPane.closeCrossFilledSize", this.crossPlainSize);
    protected final float closeCrossLineWidth = FlatUIUtils.getUIFloat("TabbedPane.closeCrossLineWidth", 1.0f);
    protected final Color background = UIManager.getColor("TabbedPane.closeBackground");
    protected final Color foreground = UIManager.getColor("TabbedPane.closeForeground");
    protected final Color hoverBackground = UIManager.getColor("TabbedPane.closeHoverBackground");
    protected final Color hoverForeground = UIManager.getColor("TabbedPane.closeHoverForeground");
    protected final Color pressedBackground = UIManager.getColor("TabbedPane.closePressedBackground");
    protected final Color pressedForeground = UIManager.getColor("TabbedPane.closePressedForeground");

    public FlatTabbedPaneCloseIcon() {
        super(16, 16, null);
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        Color bg = FlatButtonUI.buttonStateColor(c, this.background, null, null, this.hoverBackground, this.pressedBackground);
        if (bg != null) {
            g.setColor(FlatUIUtils.deriveColor(bg, c.getBackground()));
            g.fillRoundRect((this.width - this.size.width) / 2, (this.height - this.size.height) / 2, this.size.width, this.size.height, this.arc, this.arc);
        }
        Color fg = FlatButtonUI.buttonStateColor(c, this.foreground, null, null, this.hoverForeground, this.pressedForeground);
        g.setColor(FlatUIUtils.deriveColor(fg, c.getForeground()));
        float mx = this.width / 2;
        float my = this.height / 2;
        float r = (bg != null ? this.crossFilledSize : this.crossPlainSize) / 2.0f;
        Path2D.Float path = new Path2D.Float(0);
        path.append(new Line2D.Float(mx - r, my - r, mx + r, my + r), false);
        path.append(new Line2D.Float(mx - r, my + r, mx + r, my - r), false);
        g.setStroke(new BasicStroke(this.closeCrossLineWidth));
        g.draw(path);
    }
}

