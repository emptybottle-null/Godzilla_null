/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatMenuItemRenderer;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;

public class FlatMenuUI
extends BasicMenuUI {
    private Color hoverBackground;
    private FlatMenuItemRenderer renderer;

    public static ComponentUI createUI(JComponent c) {
        return new FlatMenuUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.menuItem, "iconTextGap", FlatUIUtils.getUIInt("MenuItem.iconTextGap", 4));
        this.menuItem.setRolloverEnabled(true);
        this.hoverBackground = UIManager.getColor("MenuBar.hoverBackground");
        this.renderer = this.createRenderer();
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.hoverBackground = null;
        this.renderer = null;
    }

    protected FlatMenuItemRenderer createRenderer() {
        return new FlatMenuRenderer(this.menuItem, this.checkIcon, this.arrowIcon, this.acceleratorFont, this.acceleratorDelimiter);
    }

    @Override
    protected MouseInputListener createMouseInputListener(JComponent c) {
        return new BasicMenuUI.MouseInputHandler(){

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                this.rollover(e, true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                this.rollover(e, false);
            }

            private void rollover(MouseEvent e, boolean rollover) {
                JMenu menu = (JMenu)e.getSource();
                if (menu.isTopLevelMenu() && menu.isRolloverEnabled()) {
                    menu.getModel().setRollover(rollover);
                    menu.repaint();
                }
            }
        };
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return ((JMenu)this.menuItem).isTopLevelMenu() ? c.getPreferredSize() : null;
    }

    @Override
    protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
        return this.renderer.getPreferredMenuItemSize();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        this.renderer.paintMenuItem(g, this.selectionBackground, this.selectionForeground, this.disabledForeground, this.acceleratorForeground, this.acceleratorSelectionForeground);
    }

    protected class FlatMenuRenderer
    extends FlatMenuItemRenderer {
        protected final Color menuBarUnderlineSelectionBackground;
        protected final Color menuBarUnderlineSelectionColor;
        protected final int menuBarUnderlineSelectionHeight;

        protected FlatMenuRenderer(JMenuItem menuItem, Icon checkIcon, Icon arrowIcon, Font acceleratorFont, String acceleratorDelimiter) {
            super(menuItem, checkIcon, arrowIcon, acceleratorFont, acceleratorDelimiter);
            this.menuBarUnderlineSelectionBackground = FlatUIUtils.getUIColor("MenuBar.underlineSelectionBackground", this.underlineSelectionBackground);
            this.menuBarUnderlineSelectionColor = FlatUIUtils.getUIColor("MenuBar.underlineSelectionColor", this.underlineSelectionColor);
            this.menuBarUnderlineSelectionHeight = FlatUIUtils.getUIInt("MenuBar.underlineSelectionHeight", this.underlineSelectionHeight);
        }

        @Override
        protected void paintBackground(Graphics g, Color selectionBackground) {
            ButtonModel model;
            if (this.isUnderlineSelection() && ((JMenu)this.menuItem).isTopLevelMenu()) {
                selectionBackground = this.menuBarUnderlineSelectionBackground;
            }
            if ((model = this.menuItem.getModel()).isRollover() && !model.isArmed() && !model.isSelected() && model.isEnabled() && ((JMenu)this.menuItem).isTopLevelMenu()) {
                g.setColor(this.deriveBackground(FlatMenuUI.this.hoverBackground));
                g.fillRect(0, 0, this.menuItem.getWidth(), this.menuItem.getHeight());
            } else {
                super.paintBackground(g, selectionBackground);
            }
        }

        @Override
        protected void paintUnderlineSelection(Graphics g, Color underlineSelectionColor, int underlineSelectionHeight) {
            if (((JMenu)this.menuItem).isTopLevelMenu()) {
                underlineSelectionColor = this.menuBarUnderlineSelectionColor;
                underlineSelectionHeight = this.menuBarUnderlineSelectionHeight;
            }
            super.paintUnderlineSelection(g, underlineSelectionColor, underlineSelectionHeight);
        }
    }
}

