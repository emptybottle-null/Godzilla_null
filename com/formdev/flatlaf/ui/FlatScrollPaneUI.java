/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatButtonBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.Scrollable;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;

public class FlatScrollPaneUI
extends BasicScrollPaneUI {
    private Handler handler;

    public static ComponentUI createUI(JComponent c) {
        return new FlatScrollPaneUI();
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        int focusWidth = UIManager.getInt("Component.focusWidth");
        LookAndFeel.installProperty(c, "opaque", focusWidth == 0);
        MigLayoutVisualPadding.install(this.scrollpane);
    }

    @Override
    public void uninstallUI(JComponent c) {
        MigLayoutVisualPadding.uninstall(this.scrollpane);
        super.uninstallUI(c);
    }

    @Override
    protected void installListeners(JScrollPane c) {
        super.installListeners(c);
        this.addViewportListeners(this.scrollpane.getViewport());
    }

    @Override
    protected void uninstallListeners(JComponent c) {
        super.uninstallListeners(c);
        this.removeViewportListeners(this.scrollpane.getViewport());
        this.handler = null;
    }

    @Override
    protected MouseWheelListener createMouseWheelListener() {
        return new BasicScrollPaneUI.MouseWheelHandler(){

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (FlatScrollPaneUI.this.isSmoothScrollingEnabled() && FlatScrollPaneUI.this.scrollpane.isWheelScrollingEnabled() && e.getScrollType() == 0 && e.getPreciseWheelRotation() != 0.0 && e.getPreciseWheelRotation() != (double)e.getWheelRotation()) {
                    FlatScrollPaneUI.this.mouseWheelMovedSmooth(e);
                } else {
                    super.mouseWheelMoved(e);
                }
            }
        };
    }

    protected boolean isSmoothScrollingEnabled() {
        Object smoothScrolling = this.scrollpane.getClientProperty("JScrollPane.smoothScrolling");
        if (smoothScrolling instanceof Boolean) {
            return (Boolean)smoothScrolling;
        }
        return UIManager.getBoolean("ScrollPane.smoothScrolling");
    }

    private void mouseWheelMovedSmooth(MouseWheelEvent e) {
        int maxValue;
        int unitIncrement;
        JViewport viewport = this.scrollpane.getViewport();
        if (viewport == null) {
            return;
        }
        JScrollBar scrollbar = this.scrollpane.getVerticalScrollBar();
        if (!(scrollbar != null && scrollbar.isVisible() && !e.isShiftDown() || (scrollbar = this.scrollpane.getHorizontalScrollBar()) != null && scrollbar.isVisible())) {
            return;
        }
        e.consume();
        double rotation = e.getPreciseWheelRotation();
        int orientation = scrollbar.getOrientation();
        Component view = viewport.getView();
        if (view instanceof Scrollable) {
            Scrollable scrollable = (Scrollable)((Object)view);
            Rectangle visibleRect = new Rectangle(viewport.getViewSize());
            unitIncrement = scrollable.getScrollableUnitIncrement(visibleRect, orientation, 1);
            if (unitIncrement > 0) {
                if (orientation == 1) {
                    visibleRect.y += unitIncrement;
                    visibleRect.height -= unitIncrement;
                } else {
                    visibleRect.x += unitIncrement;
                    visibleRect.width -= unitIncrement;
                }
                int unitIncrement2 = scrollable.getScrollableUnitIncrement(visibleRect, orientation, 1);
                if (unitIncrement2 > 0) {
                    unitIncrement = Math.min(unitIncrement, unitIncrement2);
                }
            }
        } else {
            int direction = rotation < 0.0 ? -1 : 1;
            unitIncrement = scrollbar.getUnitIncrement(direction);
        }
        int viewportWH = orientation == 1 ? viewport.getHeight() : viewport.getWidth();
        int scrollIncrement = Math.min(unitIncrement * e.getScrollAmount(), viewportWH);
        double delta = rotation * (double)scrollIncrement;
        int idelta = (int)Math.round(delta);
        if (idelta == 0) {
            if (rotation > 0.0) {
                idelta = 1;
            } else if (rotation < 0.0) {
                idelta = -1;
            }
        }
        int value = scrollbar.getValue();
        int minValue = scrollbar.getMinimum();
        int newValue = Math.max(minValue, Math.min(value + idelta, maxValue = scrollbar.getMaximum() - scrollbar.getModel().getExtent()));
        if (newValue != value) {
            scrollbar.setValue(newValue);
        }
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        return new BasicScrollPaneUI.PropertyChangeHandler(){

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                super.propertyChange(e);
                switch (e.getPropertyName()) {
                    case "JScrollBar.showButtons": {
                        JScrollBar vsb = FlatScrollPaneUI.this.scrollpane.getVerticalScrollBar();
                        JScrollBar hsb = FlatScrollPaneUI.this.scrollpane.getHorizontalScrollBar();
                        if (vsb != null) {
                            vsb.revalidate();
                            vsb.repaint();
                        }
                        if (hsb == null) break;
                        hsb.revalidate();
                        hsb.repaint();
                        break;
                    }
                    case "LOWER_LEFT_CORNER": 
                    case "LOWER_RIGHT_CORNER": 
                    case "UPPER_LEFT_CORNER": 
                    case "UPPER_RIGHT_CORNER": {
                        Object corner = e.getNewValue();
                        if (!(corner instanceof JButton) || !(((JButton)corner).getBorder() instanceof FlatButtonBorder) || FlatScrollPaneUI.this.scrollpane.getViewport() == null || !(FlatScrollPaneUI.this.scrollpane.getViewport().getView() instanceof JTable)) break;
                        ((JButton)corner).setBorder(BorderFactory.createEmptyBorder());
                        ((JButton)corner).setFocusable(false);
                    }
                }
            }
        };
    }

    private Handler getHandler() {
        if (this.handler == null) {
            this.handler = new Handler();
        }
        return this.handler;
    }

    @Override
    protected void updateViewport(PropertyChangeEvent e) {
        super.updateViewport(e);
        JViewport oldViewport = (JViewport)e.getOldValue();
        JViewport newViewport = (JViewport)e.getNewValue();
        this.removeViewportListeners(oldViewport);
        this.addViewportListeners(newViewport);
    }

    private void addViewportListeners(JViewport viewport) {
        if (viewport == null) {
            return;
        }
        viewport.addContainerListener(this.getHandler());
        Component view = viewport.getView();
        if (view != null) {
            view.addFocusListener(this.getHandler());
        }
    }

    private void removeViewportListeners(JViewport viewport) {
        if (viewport == null) {
            return;
        }
        viewport.removeContainerListener(this.getHandler());
        Component view = viewport.getView();
        if (view != null) {
            view.removeFocusListener(this.getHandler());
        }
    }

    @Override
    public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            FlatUIUtils.paintParentBackground(g, c);
            Insets insets = c.getInsets();
            g.setColor(c.getBackground());
            g.fillRect(insets.left, insets.top, c.getWidth() - insets.left - insets.right, c.getHeight() - insets.top - insets.bottom);
        }
        this.paint(g, c);
    }

    private class Handler
    implements ContainerListener,
    FocusListener {
        private Handler() {
        }

        @Override
        public void componentAdded(ContainerEvent e) {
            e.getChild().addFocusListener(this);
        }

        @Override
        public void componentRemoved(ContainerEvent e) {
            e.getChild().removeFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            FlatScrollPaneUI.this.scrollpane.repaint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            FlatScrollPaneUI.this.scrollpane.repaint();
        }
    }
}

