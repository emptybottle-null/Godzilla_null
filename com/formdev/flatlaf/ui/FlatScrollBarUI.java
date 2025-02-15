/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatArrowButton;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class FlatScrollBarUI
extends BasicScrollBarUI {
    protected Insets trackInsets;
    protected Insets thumbInsets;
    protected int trackArc;
    protected int thumbArc;
    protected Color hoverTrackColor;
    protected Color hoverThumbColor;
    protected boolean hoverThumbWithTrack;
    protected Color pressedTrackColor;
    protected Color pressedThumbColor;
    protected boolean pressedThumbWithTrack;
    protected boolean showButtons;
    protected String arrowType;
    protected Color buttonArrowColor;
    protected Color buttonDisabledArrowColor;
    protected Color hoverButtonBackground;
    protected Color pressedButtonBackground;
    private MouseAdapter hoverListener;
    protected boolean hoverTrack;
    protected boolean hoverThumb;
    private static boolean isPressed;

    public static ComponentUI createUI(JComponent c) {
        return new FlatScrollBarUI();
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.hoverListener = new ScrollBarHoverListener();
        this.scrollbar.addMouseListener(this.hoverListener);
        this.scrollbar.addMouseMotionListener(this.hoverListener);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.scrollbar.removeMouseListener(this.hoverListener);
        this.scrollbar.removeMouseMotionListener(this.hoverListener);
        this.hoverListener = null;
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.trackInsets = UIManager.getInsets("ScrollBar.trackInsets");
        this.thumbInsets = UIManager.getInsets("ScrollBar.thumbInsets");
        this.trackArc = UIManager.getInt("ScrollBar.trackArc");
        this.thumbArc = UIManager.getInt("ScrollBar.thumbArc");
        this.hoverTrackColor = UIManager.getColor("ScrollBar.hoverTrackColor");
        this.hoverThumbColor = UIManager.getColor("ScrollBar.hoverThumbColor");
        this.hoverThumbWithTrack = UIManager.getBoolean("ScrollBar.hoverThumbWithTrack");
        this.pressedTrackColor = UIManager.getColor("ScrollBar.pressedTrackColor");
        this.pressedThumbColor = UIManager.getColor("ScrollBar.pressedThumbColor");
        this.pressedThumbWithTrack = UIManager.getBoolean("ScrollBar.pressedThumbWithTrack");
        this.showButtons = UIManager.getBoolean("ScrollBar.showButtons");
        this.arrowType = UIManager.getString("Component.arrowType");
        this.buttonArrowColor = UIManager.getColor("ScrollBar.buttonArrowColor");
        this.buttonDisabledArrowColor = UIManager.getColor("ScrollBar.buttonDisabledArrowColor");
        this.hoverButtonBackground = UIManager.getColor("ScrollBar.hoverButtonBackground");
        this.pressedButtonBackground = UIManager.getColor("ScrollBar.pressedButtonBackground");
        if (this.trackInsets == null) {
            this.trackInsets = new Insets(0, 0, 0, 0);
        }
        if (this.thumbInsets == null) {
            this.thumbInsets = new Insets(0, 0, 0, 0);
        }
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.trackInsets = null;
        this.thumbInsets = null;
        this.hoverTrackColor = null;
        this.hoverThumbColor = null;
        this.pressedTrackColor = null;
        this.pressedThumbColor = null;
        this.buttonArrowColor = null;
        this.buttonDisabledArrowColor = null;
        this.hoverButtonBackground = null;
        this.pressedButtonBackground = null;
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        return new BasicScrollBarUI.PropertyChangeHandler(){

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                super.propertyChange(e);
                switch (e.getPropertyName()) {
                    case "JScrollBar.showButtons": {
                        FlatScrollBarUI.this.scrollbar.revalidate();
                        FlatScrollBarUI.this.scrollbar.repaint();
                        break;
                    }
                    case "componentOrientation": {
                        InputMap rtlInputMap;
                        InputMap inputMap = (InputMap)UIManager.get("ScrollBar.ancestorInputMap");
                        if (!FlatScrollBarUI.this.scrollbar.getComponentOrientation().isLeftToRight() && (rtlInputMap = (InputMap)UIManager.get("ScrollBar.ancestorInputMap.RightToLeft")) != null) {
                            rtlInputMap.setParent(inputMap);
                            inputMap = rtlInputMap;
                        }
                        SwingUtilities.replaceUIInputMap(FlatScrollBarUI.this.scrollbar, 1, inputMap);
                    }
                }
            }
        };
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return UIScale.scale(super.getPreferredSize(c));
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new FlatScrollBarButton(orientation);
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new FlatScrollBarButton(orientation);
    }

    protected boolean isShowButtons() {
        Object showButtons = this.scrollbar.getClientProperty("JScrollBar.showButtons");
        if (showButtons == null && this.scrollbar.getParent() instanceof JScrollPane) {
            showButtons = ((JScrollPane)this.scrollbar.getParent()).getClientProperty("JScrollBar.showButtons");
        }
        return showButtons != null ? Objects.equals(showButtons, true) : this.showButtons;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
        super.paint(g, c);
        FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(this.getTrackColor(c, this.hoverTrack, isPressed && this.hoverTrack && !this.hoverThumb));
        this.paintTrackOrThumb(g, c, trackBounds, this.trackInsets, this.trackArc);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !this.scrollbar.isEnabled()) {
            return;
        }
        g.setColor(this.getThumbColor(c, this.hoverThumb || this.hoverThumbWithTrack && this.hoverTrack, isPressed && (this.hoverThumb || this.pressedThumbWithTrack && this.hoverTrack)));
        this.paintTrackOrThumb(g, c, thumbBounds, this.thumbInsets, this.thumbArc);
    }

    protected void paintTrackOrThumb(Graphics g, JComponent c, Rectangle bounds, Insets insets, int arc) {
        if (this.scrollbar.getOrientation() == 0) {
            insets = new Insets(insets.right, insets.top, insets.left, insets.bottom);
        }
        bounds = FlatUIUtils.subtractInsets(bounds, UIScale.scale(insets));
        if (arc <= 0) {
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            arc = Math.min(UIScale.scale(arc), Math.min(bounds.width, bounds.height));
            g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, arc, arc);
        }
    }

    @Override
    protected void paintDecreaseHighlight(Graphics g) {
    }

    @Override
    protected void paintIncreaseHighlight(Graphics g) {
    }

    protected Color getTrackColor(JComponent c, boolean hover, boolean pressed) {
        Color trackColor = FlatUIUtils.deriveColor(this.trackColor, c.getBackground());
        return pressed && this.pressedTrackColor != null ? FlatUIUtils.deriveColor(this.pressedTrackColor, trackColor) : (hover && this.hoverTrackColor != null ? FlatUIUtils.deriveColor(this.hoverTrackColor, trackColor) : trackColor);
    }

    protected Color getThumbColor(JComponent c, boolean hover, boolean pressed) {
        Color trackColor = FlatUIUtils.deriveColor(this.trackColor, c.getBackground());
        Color thumbColor = FlatUIUtils.deriveColor(this.thumbColor, trackColor);
        return pressed && this.pressedThumbColor != null ? FlatUIUtils.deriveColor(this.pressedThumbColor, thumbColor) : (hover && this.hoverThumbColor != null ? FlatUIUtils.deriveColor(this.hoverThumbColor, thumbColor) : thumbColor);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return UIScale.scale(FlatUIUtils.addInsets(super.getMinimumThumbSize(), this.thumbInsets));
    }

    @Override
    protected Dimension getMaximumThumbSize() {
        return UIScale.scale(FlatUIUtils.addInsets(super.getMaximumThumbSize(), this.thumbInsets));
    }

    protected class FlatScrollBarButton
    extends FlatArrowButton {
        protected FlatScrollBarButton(int direction) {
            this(direction, this$0.arrowType, this$0.buttonArrowColor, this$0.buttonDisabledArrowColor, null, this$0.hoverButtonBackground, null, this$0.pressedButtonBackground);
        }

        protected FlatScrollBarButton(int direction, String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
            super(direction, type, foreground, disabledForeground, hoverForeground, hoverBackground, pressedForeground, pressedBackground);
            this.setArrowWidth(6);
            this.setFocusable(false);
            this.setRequestFocusEnabled(false);
        }

        @Override
        protected Color deriveBackground(Color background) {
            return FlatUIUtils.deriveColor(background, FlatScrollBarUI.this.scrollbar.getBackground());
        }

        @Override
        public Dimension getPreferredSize() {
            if (FlatScrollBarUI.this.isShowButtons()) {
                int w = UIScale.scale(FlatScrollBarUI.this.scrollBarWidth);
                return new Dimension(w, w);
            }
            return new Dimension();
        }

        @Override
        public Dimension getMinimumSize() {
            return FlatScrollBarUI.this.isShowButtons() ? super.getMinimumSize() : new Dimension();
        }

        @Override
        public Dimension getMaximumSize() {
            return FlatScrollBarUI.this.isShowButtons() ? super.getMaximumSize() : new Dimension();
        }
    }

    private class ScrollBarHoverListener
    extends MouseAdapter {
        private ScrollBarHoverListener() {
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!isPressed) {
                FlatScrollBarUI.this.hoverThumb = false;
                FlatScrollBarUI.this.hoverTrack = false;
                this.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (!isPressed) {
                this.update(e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            isPressed = true;
            this.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            isPressed = false;
            this.repaint();
            this.update(e.getX(), e.getY());
        }

        private void update(int x, int y) {
            boolean inTrack = FlatScrollBarUI.this.getTrackBounds().contains(x, y);
            boolean inThumb = FlatScrollBarUI.this.getThumbBounds().contains(x, y);
            if (inTrack != FlatScrollBarUI.this.hoverTrack || inThumb != FlatScrollBarUI.this.hoverThumb) {
                FlatScrollBarUI.this.hoverTrack = inTrack;
                FlatScrollBarUI.this.hoverThumb = inThumb;
                this.repaint();
            }
        }

        private void repaint() {
            if (FlatScrollBarUI.this.scrollbar.isEnabled()) {
                FlatScrollBarUI.this.scrollbar.repaint();
            }
        }
    }
}

