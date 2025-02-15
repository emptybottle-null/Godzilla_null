/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

public class FlatToggleButtonUI
extends FlatButtonUI {
    protected int tabUnderlineHeight;
    protected Color tabUnderlineColor;
    protected Color tabDisabledUnderlineColor;
    protected Color tabSelectedBackground;
    protected Color tabHoverBackground;
    protected Color tabFocusBackground;
    private boolean defaults_initialized = false;

    public static ComponentUI createUI(JComponent c) {
        return FlatUIUtils.createSharedUI(FlatToggleButtonUI.class, FlatToggleButtonUI::new);
    }

    @Override
    protected String getPropertyPrefix() {
        return "ToggleButton.";
    }

    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        if (!this.defaults_initialized) {
            this.tabUnderlineHeight = UIManager.getInt("ToggleButton.tab.underlineHeight");
            this.tabUnderlineColor = UIManager.getColor("ToggleButton.tab.underlineColor");
            this.tabDisabledUnderlineColor = UIManager.getColor("ToggleButton.tab.disabledUnderlineColor");
            this.tabSelectedBackground = UIManager.getColor("ToggleButton.tab.selectedBackground");
            this.tabHoverBackground = UIManager.getColor("ToggleButton.tab.hoverBackground");
            this.tabFocusBackground = UIManager.getColor("ToggleButton.tab.focusBackground");
            this.defaults_initialized = true;
        }
    }

    @Override
    protected void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
        this.defaults_initialized = false;
    }

    @Override
    protected void propertyChange(AbstractButton b, PropertyChangeEvent e) {
        super.propertyChange(b, e);
        switch (e.getPropertyName()) {
            case "JButton.buttonType": {
                if ("tab".equals(e.getOldValue()) || "tab".equals(e.getNewValue())) {
                    MigLayoutVisualPadding.uninstall(b);
                    MigLayoutVisualPadding.install(b);
                    b.revalidate();
                }
                b.repaint();
                break;
            }
            case "JToggleButton.tab.underlineHeight": 
            case "JToggleButton.tab.underlineColor": 
            case "JToggleButton.tab.selectedBackground": {
                b.repaint();
            }
        }
    }

    static boolean isTabButton(Component c) {
        return c instanceof JToggleButton && FlatClientProperties.clientPropertyEquals((JToggleButton)c, "JButton.buttonType", "tab");
    }

    @Override
    protected void paintBackground(Graphics g, JComponent c) {
        if (FlatToggleButtonUI.isTabButton(c)) {
            Color background;
            Color bg;
            Color enabledColor;
            int height = c.getHeight();
            int width = c.getWidth();
            boolean selected = ((AbstractButton)c).isSelected();
            Color color = enabledColor = selected ? FlatClientProperties.clientPropertyColor(c, "JToggleButton.tab.selectedBackground", this.tabSelectedBackground) : null;
            if (enabledColor == null && this.isCustomBackground(bg = c.getBackground())) {
                enabledColor = bg;
            }
            if ((background = FlatToggleButtonUI.buttonStateColor(c, enabledColor, null, this.tabFocusBackground, this.tabHoverBackground, null)) != null) {
                g.setColor(background);
                g.fillRect(0, 0, width, height);
            }
            if (selected) {
                int underlineHeight = UIScale.scale(FlatClientProperties.clientPropertyInt(c, "JToggleButton.tab.underlineHeight", this.tabUnderlineHeight));
                g.setColor(c.isEnabled() ? FlatClientProperties.clientPropertyColor(c, "JToggleButton.tab.underlineColor", this.tabUnderlineColor) : this.tabDisabledUnderlineColor);
                g.fillRect(0, height - underlineHeight, width, underlineHeight);
            }
        } else {
            super.paintBackground(g, c);
        }
    }
}

