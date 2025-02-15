/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatButtonBorder;
import com.formdev.flatlaf.ui.FlatLabelUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.plaf.basic.BasicButtonUI;

public class FlatButtonUI
extends BasicButtonUI {
    protected int minimumWidth;
    protected int iconTextGap;
    protected Color background;
    protected Color foreground;
    protected Color startBackground;
    protected Color endBackground;
    protected Color focusedBackground;
    protected Color hoverBackground;
    protected Color pressedBackground;
    protected Color selectedBackground;
    protected Color selectedForeground;
    protected Color disabledBackground;
    protected Color disabledText;
    protected Color disabledSelectedBackground;
    protected Color defaultBackground;
    protected Color defaultEndBackground;
    protected Color defaultForeground;
    protected Color defaultFocusedBackground;
    protected Color defaultHoverBackground;
    protected Color defaultPressedBackground;
    protected boolean defaultBoldText;
    protected int shadowWidth;
    protected Color shadowColor;
    protected Color defaultShadowColor;
    protected Insets toolbarSpacingInsets;
    protected Color toolbarHoverBackground;
    protected Color toolbarPressedBackground;
    protected Color toolbarSelectedBackground;
    private Icon helpButtonIcon;
    private boolean defaults_initialized = false;
    static final int TYPE_OTHER = -1;
    static final int TYPE_SQUARE = 0;
    static final int TYPE_ROUND_RECT = 1;

    public static ComponentUI createUI(JComponent c) {
        return FlatUIUtils.createSharedUI(FlatButtonUI.class, FlatButtonUI::new);
    }

    @Override
    protected void installDefaults(AbstractButton b) {
        Color bg;
        super.installDefaults(b);
        if (!this.defaults_initialized) {
            String prefix = this.getPropertyPrefix();
            this.minimumWidth = UIManager.getInt(prefix + "minimumWidth");
            this.iconTextGap = FlatUIUtils.getUIInt(prefix + "iconTextGap", 4);
            this.background = UIManager.getColor(prefix + "background");
            this.foreground = UIManager.getColor(prefix + "foreground");
            this.startBackground = UIManager.getColor(prefix + "startBackground");
            this.endBackground = UIManager.getColor(prefix + "endBackground");
            this.focusedBackground = UIManager.getColor(prefix + "focusedBackground");
            this.hoverBackground = UIManager.getColor(prefix + "hoverBackground");
            this.pressedBackground = UIManager.getColor(prefix + "pressedBackground");
            this.selectedBackground = UIManager.getColor(prefix + "selectedBackground");
            this.selectedForeground = UIManager.getColor(prefix + "selectedForeground");
            this.disabledBackground = UIManager.getColor(prefix + "disabledBackground");
            this.disabledText = UIManager.getColor(prefix + "disabledText");
            this.disabledSelectedBackground = UIManager.getColor(prefix + "disabledSelectedBackground");
            if (UIManager.getBoolean("Button.paintShadow")) {
                this.shadowWidth = FlatUIUtils.getUIInt("Button.shadowWidth", 2);
                this.shadowColor = UIManager.getColor("Button.shadowColor");
                this.defaultShadowColor = UIManager.getColor("Button.default.shadowColor");
            } else {
                this.shadowWidth = 0;
                this.shadowColor = null;
                this.defaultShadowColor = null;
            }
            this.defaultBackground = FlatUIUtils.getUIColor("Button.default.startBackground", "Button.default.background");
            this.defaultEndBackground = UIManager.getColor("Button.default.endBackground");
            this.defaultForeground = UIManager.getColor("Button.default.foreground");
            this.defaultFocusedBackground = UIManager.getColor("Button.default.focusedBackground");
            this.defaultHoverBackground = UIManager.getColor("Button.default.hoverBackground");
            this.defaultPressedBackground = UIManager.getColor("Button.default.pressedBackground");
            this.defaultBoldText = UIManager.getBoolean("Button.default.boldText");
            this.toolbarSpacingInsets = UIManager.getInsets("Button.toolbar.spacingInsets");
            this.toolbarHoverBackground = UIManager.getColor(prefix + "toolbar.hoverBackground");
            this.toolbarPressedBackground = UIManager.getColor(prefix + "toolbar.pressedBackground");
            this.toolbarSelectedBackground = UIManager.getColor(prefix + "toolbar.selectedBackground");
            this.helpButtonIcon = UIManager.getIcon("HelpButton.icon");
            this.defaults_initialized = true;
        }
        if (this.startBackground != null && ((bg = b.getBackground()) == null || bg instanceof UIResource)) {
            b.setBackground(this.startBackground);
        }
        LookAndFeel.installProperty(b, "opaque", false);
        LookAndFeel.installProperty(b, "iconTextGap", UIScale.scale(this.iconTextGap));
        MigLayoutVisualPadding.install(b);
    }

    @Override
    protected void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
        MigLayoutVisualPadding.uninstall(b);
        this.defaults_initialized = false;
    }

    @Override
    protected BasicButtonListener createButtonListener(AbstractButton b) {
        return new FlatButtonListener(b);
    }

    protected void propertyChange(AbstractButton b, PropertyChangeEvent e) {
        switch (e.getPropertyName()) {
            case "JButton.squareSize": 
            case "JComponent.minimumWidth": 
            case "JComponent.minimumHeight": {
                b.revalidate();
                break;
            }
            case "JButton.buttonType": {
                b.revalidate();
                b.repaint();
            }
        }
    }

    static boolean isContentAreaFilled(Component c) {
        return !(c instanceof AbstractButton) || ((AbstractButton)c).isContentAreaFilled();
    }

    public static boolean isFocusPainted(Component c) {
        return !(c instanceof AbstractButton) || ((AbstractButton)c).isFocusPainted();
    }

    static boolean isDefaultButton(Component c) {
        return c instanceof JButton && ((JButton)c).isDefaultButton();
    }

    static boolean isIconOnlyOrSingleCharacterButton(Component c) {
        if (!(c instanceof JButton) && !(c instanceof JToggleButton)) {
            return false;
        }
        Icon icon = ((AbstractButton)c).getIcon();
        String text = ((AbstractButton)c).getText();
        return icon != null && (text == null || text.isEmpty()) || icon == null && text != null && ("...".equals(text) || text.length() == 1 || text.length() == 2 && Character.isSurrogatePair(text.charAt(0), text.charAt(1)));
    }

    static int getButtonType(Component c) {
        if (!(c instanceof AbstractButton)) {
            return -1;
        }
        Object value = ((AbstractButton)c).getClientProperty("JButton.buttonType");
        if (!(value instanceof String)) {
            return -1;
        }
        switch ((String)value) {
            case "square": {
                return 0;
            }
            case "roundRect": {
                return 1;
            }
        }
        return -1;
    }

    static boolean isHelpButton(Component c) {
        return c instanceof JButton && FlatClientProperties.clientPropertyEquals((JButton)c, "JButton.buttonType", "help");
    }

    static boolean isToolBarButton(Component c) {
        return c.getParent() instanceof JToolBar || c instanceof AbstractButton && FlatClientProperties.clientPropertyEquals((AbstractButton)c, "JButton.buttonType", "toolBarButton");
    }

    @Override
    public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            FlatUIUtils.paintParentBackground(g, c);
        }
        if (FlatButtonUI.isHelpButton(c)) {
            this.helpButtonIcon.paintIcon(c, g, 0, 0);
            return;
        }
        if (FlatButtonUI.isContentAreaFilled(c)) {
            this.paintBackground(g, c);
        }
        this.paint(g, c);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void paintBackground(Graphics g, JComponent c) {
        Color background = this.getBackground(c);
        if (background == null) {
            return;
        }
        Graphics2D g2 = (Graphics2D)g.create();
        try {
            Color endBg;
            Color shadowColor;
            FlatUIUtils.setRenderingHints(g2);
            boolean isToolBarButton = FlatButtonUI.isToolBarButton(c);
            float focusWidth = isToolBarButton ? 0.0f : FlatUIUtils.getBorderFocusWidth(c);
            float arc = FlatUIUtils.getBorderArc(c);
            boolean def = FlatButtonUI.isDefaultButton(c);
            int x = 0;
            int y = 0;
            int width = c.getWidth();
            int height = c.getHeight();
            if (isToolBarButton) {
                Insets spacing = UIScale.scale(this.toolbarSpacingInsets);
                x += spacing.left;
                y += spacing.top;
                width -= spacing.left + spacing.right;
                height -= spacing.top + spacing.bottom;
            }
            Color color = shadowColor = def ? this.defaultShadowColor : this.shadowColor;
            if (!(isToolBarButton || shadowColor == null || this.shadowWidth <= 0 || !(focusWidth > 0.0f) || FlatButtonUI.isFocusPainted(c) && FlatUIUtils.isPermanentFocusOwner(c) || !c.isEnabled())) {
                g2.setColor(shadowColor);
                g2.fill(new RoundRectangle2D.Float(focusWidth, focusWidth + UIScale.scale((float)this.shadowWidth), (float)width - focusWidth * 2.0f, (float)height - focusWidth * 2.0f, arc, arc));
            }
            Color startBg = def ? this.defaultBackground : this.startBackground;
            Color color2 = endBg = def ? this.defaultEndBackground : this.endBackground;
            if (background == startBg && endBg != null && !startBg.equals(endBg)) {
                g2.setPaint(new GradientPaint(0.0f, 0.0f, startBg, 0.0f, height, endBg));
            } else {
                g2.setColor(FlatUIUtils.deriveColor(background, this.getBackgroundBase(c, def)));
            }
            FlatUIUtils.paintComponentBackground(g2, x, y, width, height, focusWidth, arc);
        } finally {
            g2.dispose();
        }
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(FlatLabelUI.createGraphicsHTMLTextYCorrection(g, c), c);
    }

    @Override
    protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
        if (FlatButtonUI.isHelpButton(b)) {
            return;
        }
        if (this.defaultBoldText && FlatButtonUI.isDefaultButton(b) && b.getFont() instanceof UIResource) {
            Font boldFont = g.getFont().deriveFont(1);
            g.setFont(boldFont);
            int boldWidth = b.getFontMetrics(boldFont).stringWidth(text);
            if (boldWidth > textRect.width) {
                textRect.x -= (boldWidth - textRect.width) / 2;
                textRect.width = boldWidth;
            }
        }
        FlatButtonUI.paintText(g, b, textRect, text, this.getForeground(b));
    }

    public static void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text, Color foreground) {
        FontMetrics fm = b.getFontMetrics(b.getFont());
        int mnemonicIndex = FlatLaf.isShowMnemonics() ? b.getDisplayedMnemonicIndex() : -1;
        g.setColor(foreground);
        FlatUIUtils.drawStringUnderlineCharAt(b, g, text, mnemonicIndex, textRect.x, textRect.y + fm.getAscent());
    }

    protected Color getBackground(JComponent c) {
        if (((AbstractButton)c).isSelected()) {
            boolean toolBarButton = FlatButtonUI.isToolBarButton(c);
            return FlatButtonUI.buttonStateColor(c, toolBarButton ? this.toolbarSelectedBackground : this.selectedBackground, toolBarButton ? this.toolbarSelectedBackground : this.disabledSelectedBackground, null, null, toolBarButton ? this.toolbarPressedBackground : this.pressedBackground);
        }
        if (!c.isEnabled()) {
            return this.disabledBackground;
        }
        if (FlatButtonUI.isToolBarButton(c)) {
            ButtonModel model = ((AbstractButton)c).getModel();
            if (model.isPressed()) {
                return this.toolbarPressedBackground;
            }
            if (model.isRollover()) {
                return this.toolbarHoverBackground;
            }
            Color bg = c.getBackground();
            if (this.isCustomBackground(bg)) {
                return bg;
            }
            return null;
        }
        boolean def = FlatButtonUI.isDefaultButton(c);
        return FlatButtonUI.buttonStateColor(c, this.getBackgroundBase(c, def), null, this.isCustomBackground(c.getBackground()) ? null : (def ? this.defaultFocusedBackground : this.focusedBackground), def ? this.defaultHoverBackground : this.hoverBackground, def ? this.defaultPressedBackground : this.pressedBackground);
    }

    protected Color getBackgroundBase(JComponent c, boolean def) {
        Color bg = c.getBackground();
        if (this.isCustomBackground(bg)) {
            return bg;
        }
        return def ? this.defaultBackground : bg;
    }

    protected boolean isCustomBackground(Color bg) {
        return bg != this.background && (this.startBackground == null || bg != this.startBackground);
    }

    public static Color buttonStateColor(Component c, Color enabledColor, Color disabledColor, Color focusedColor, Color hoverColor, Color pressedColor) {
        AbstractButton b;
        AbstractButton abstractButton = b = c instanceof AbstractButton ? (AbstractButton)c : null;
        if (!c.isEnabled()) {
            return disabledColor;
        }
        if (pressedColor != null && b != null && b.getModel().isPressed()) {
            return pressedColor;
        }
        if (hoverColor != null && b != null && b.getModel().isRollover()) {
            return hoverColor;
        }
        if (focusedColor != null && FlatButtonUI.isFocusPainted(c) && FlatUIUtils.isPermanentFocusOwner(c)) {
            return focusedColor;
        }
        return enabledColor;
    }

    protected Color getForeground(JComponent c) {
        if (!c.isEnabled()) {
            return this.disabledText;
        }
        if (((AbstractButton)c).isSelected() && !FlatButtonUI.isToolBarButton(c)) {
            return this.selectedForeground;
        }
        Color fg = c.getForeground();
        if (this.isCustomForeground(fg)) {
            return fg;
        }
        boolean def = FlatButtonUI.isDefaultButton(c);
        return def ? this.defaultForeground : fg;
    }

    protected boolean isCustomForeground(Color fg) {
        return fg != this.foreground;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        if (FlatButtonUI.isHelpButton(c)) {
            return new Dimension(this.helpButtonIcon.getIconWidth(), this.helpButtonIcon.getIconHeight());
        }
        Dimension prefSize = super.getPreferredSize(c);
        if (prefSize == null) {
            return null;
        }
        boolean isIconOnlyOrSingleCharacter = FlatButtonUI.isIconOnlyOrSingleCharacterButton(c);
        if (FlatClientProperties.clientPropertyBoolean(c, "JButton.squareSize", false)) {
            prefSize.width = prefSize.height = Math.max(prefSize.width, prefSize.height);
        } else if (isIconOnlyOrSingleCharacter && ((AbstractButton)c).getIcon() == null) {
            prefSize.width = Math.max(prefSize.width, prefSize.height);
        } else if (!isIconOnlyOrSingleCharacter && !FlatButtonUI.isToolBarButton(c) && c.getBorder() instanceof FlatButtonBorder) {
            float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
            prefSize.width = Math.max(prefSize.width, UIScale.scale(FlatUIUtils.minimumWidth(c, this.minimumWidth)) + Math.round(focusWidth * 2.0f));
            prefSize.height = Math.max(prefSize.height, UIScale.scale(FlatUIUtils.minimumHeight(c, 0)) + Math.round(focusWidth * 2.0f));
        }
        return prefSize;
    }

    protected class FlatButtonListener
    extends BasicButtonListener {
        private final AbstractButton b;

        protected FlatButtonListener(AbstractButton b) {
            super(b);
            this.b = b;
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            super.propertyChange(e);
            FlatButtonUI.this.propertyChange(this.b, e);
        }
    }
}

