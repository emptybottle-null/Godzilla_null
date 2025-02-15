/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.UIManager;

public class FlatCheckBoxIcon
extends FlatAbstractIcon {
    protected final String style = UIManager.getString("CheckBox.icon.style");
    public final int focusWidth = FlatCheckBoxIcon.getUIInt("CheckBox.icon.focusWidth", UIManager.getInt("Component.focusWidth"), this.style);
    protected final Color focusColor = FlatUIUtils.getUIColor("CheckBox.icon.focusColor", UIManager.getColor("Component.focusColor"));
    protected final int arc = FlatUIUtils.getUIInt("CheckBox.arc", 2);
    protected final Color borderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.borderColor", this.style);
    protected final Color background = FlatCheckBoxIcon.getUIColor("CheckBox.icon.background", this.style);
    protected final Color selectedBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedBorderColor", this.style);
    protected final Color selectedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedBackground", this.style);
    protected final Color checkmarkColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.checkmarkColor", this.style);
    protected final Color disabledBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.disabledBorderColor", this.style);
    protected final Color disabledBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.disabledBackground", this.style);
    protected final Color disabledCheckmarkColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.disabledCheckmarkColor", this.style);
    protected final Color focusedBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.focusedBorderColor", this.style);
    protected final Color focusedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.focusedBackground", this.style);
    protected final Color selectedFocusedBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedFocusedBorderColor", this.style);
    protected final Color selectedFocusedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedFocusedBackground", this.style);
    protected final Color selectedFocusedCheckmarkColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedFocusedCheckmarkColor", this.style);
    protected final Color hoverBorderColor = FlatCheckBoxIcon.getUIColor("CheckBox.icon.hoverBorderColor", this.style);
    protected final Color hoverBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.hoverBackground", this.style);
    protected final Color selectedHoverBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedHoverBackground", this.style);
    protected final Color pressedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.pressedBackground", this.style);
    protected final Color selectedPressedBackground = FlatCheckBoxIcon.getUIColor("CheckBox.icon.selectedPressedBackground", this.style);
    static final int ICON_SIZE = 15;

    protected static Color getUIColor(String key, String style) {
        Color color;
        if (style != null && (color = UIManager.getColor(FlatCheckBoxIcon.styleKey(key, style))) != null) {
            return color;
        }
        return UIManager.getColor(key);
    }

    protected static int getUIInt(String key, int defaultValue, String style) {
        Object value;
        if (style != null && (value = UIManager.get(FlatCheckBoxIcon.styleKey(key, style))) instanceof Integer) {
            return (Integer)value;
        }
        return FlatUIUtils.getUIInt(key, defaultValue);
    }

    private static String styleKey(String key, String style) {
        return key.replace(".icon.", ".icon[" + style + "].");
    }

    public FlatCheckBoxIcon() {
        super(15, 15, null);
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        boolean indeterminate = this.isIndeterminate(c);
        boolean selected = indeterminate || this.isSelected(c);
        boolean isFocused = FlatUIUtils.isPermanentFocusOwner(c);
        if (isFocused && this.focusWidth > 0 && FlatButtonUI.isFocusPainted(c)) {
            g.setColor(this.getFocusColor(c));
            this.paintFocusBorder(c, g);
        }
        g.setColor(this.getBorderColor(c, selected));
        this.paintBorder(c, g);
        g.setColor(FlatUIUtils.deriveColor(this.getBackground(c, selected), selected ? this.selectedBackground : this.background));
        this.paintBackground(c, g);
        if (selected || indeterminate) {
            g.setColor(this.getCheckmarkColor(c, selected, isFocused));
            if (indeterminate) {
                this.paintIndeterminate(c, g);
            } else {
                this.paintCheckmark(c, g);
            }
        }
    }

    protected void paintFocusBorder(Component c, Graphics2D g) {
        int wh = 14 + this.focusWidth * 2;
        int arcwh = this.arc + this.focusWidth * 2;
        g.fillRoundRect(-this.focusWidth + 1, -this.focusWidth, wh, wh, arcwh, arcwh);
    }

    protected void paintBorder(Component c, Graphics2D g) {
        int arcwh = this.arc;
        g.fillRoundRect(1, 0, 14, 14, arcwh, arcwh);
    }

    protected void paintBackground(Component c, Graphics2D g) {
        int arcwh = this.arc - 1;
        g.fillRoundRect(2, 1, 12, 12, arcwh, arcwh);
    }

    protected void paintCheckmark(Component c, Graphics2D g) {
        Path2D.Float path = new Path2D.Float();
        path.moveTo(4.5f, 7.5f);
        path.lineTo(6.6f, 10.0f);
        path.lineTo(11.25f, 3.5f);
        g.setStroke(new BasicStroke(1.9f, 1, 1));
        g.draw(path);
    }

    protected void paintIndeterminate(Component c, Graphics2D g) {
        g.fill(new RoundRectangle2D.Float(3.75f, 5.75f, 8.5f, 2.5f, 2.0f, 2.0f));
    }

    protected boolean isIndeterminate(Component c) {
        return c instanceof JComponent && FlatClientProperties.clientPropertyEquals((JComponent)c, "JButton.selectedState", "indeterminate");
    }

    protected boolean isSelected(Component c) {
        return c instanceof AbstractButton && ((AbstractButton)c).isSelected();
    }

    protected Color getFocusColor(Component c) {
        return this.focusColor;
    }

    protected Color getBorderColor(Component c, boolean selected) {
        return FlatButtonUI.buttonStateColor(c, selected ? this.selectedBorderColor : this.borderColor, this.disabledBorderColor, selected && this.selectedFocusedBorderColor != null ? this.selectedFocusedBorderColor : this.focusedBorderColor, this.hoverBorderColor, null);
    }

    protected Color getBackground(Component c, boolean selected) {
        return FlatButtonUI.buttonStateColor(c, selected ? this.selectedBackground : this.background, this.disabledBackground, selected && this.selectedFocusedBackground != null ? this.selectedFocusedBackground : this.focusedBackground, selected && this.selectedHoverBackground != null ? this.selectedHoverBackground : this.hoverBackground, selected && this.selectedPressedBackground != null ? this.selectedPressedBackground : this.pressedBackground);
    }

    protected Color getCheckmarkColor(Component c, boolean selected, boolean isFocused) {
        return c.isEnabled() ? (selected && isFocused && this.selectedFocusedCheckmarkColor != null ? this.selectedFocusedCheckmarkColor : this.checkmarkColor) : this.disabledCheckmarkColor;
    }
}

