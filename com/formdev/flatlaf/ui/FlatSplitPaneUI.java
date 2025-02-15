/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatArrowButton;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class FlatSplitPaneUI
extends BasicSplitPaneUI {
    protected String arrowType;
    private Boolean continuousLayout;
    protected Color oneTouchArrowColor;
    protected Color oneTouchHoverArrowColor;
    protected Color oneTouchPressedArrowColor;

    public static ComponentUI createUI(JComponent c) {
        return new FlatSplitPaneUI();
    }

    @Override
    protected void installDefaults() {
        this.arrowType = UIManager.getString("Component.arrowType");
        this.oneTouchArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchArrowColor");
        this.oneTouchHoverArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchHoverArrowColor");
        this.oneTouchPressedArrowColor = UIManager.getColor("SplitPaneDivider.oneTouchPressedArrowColor");
        super.installDefaults();
        this.continuousLayout = (Boolean)UIManager.get("SplitPane.continuousLayout");
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.oneTouchArrowColor = null;
        this.oneTouchHoverArrowColor = null;
        this.oneTouchPressedArrowColor = null;
    }

    @Override
    public boolean isContinuousLayout() {
        return super.isContinuousLayout() || this.continuousLayout != null && Boolean.TRUE.equals(this.continuousLayout);
    }

    @Override
    public BasicSplitPaneDivider createDefaultDivider() {
        return new FlatSplitPaneDivider(this);
    }

    protected class FlatSplitPaneDivider
    extends BasicSplitPaneDivider {
        protected final String style;
        protected final Color gripColor;
        protected final int gripDotCount;
        protected final int gripDotSize;
        protected final int gripGap;

        protected FlatSplitPaneDivider(BasicSplitPaneUI ui) {
            super(ui);
            this.style = UIManager.getString("SplitPaneDivider.style");
            this.gripColor = UIManager.getColor("SplitPaneDivider.gripColor");
            this.gripDotCount = FlatUIUtils.getUIInt("SplitPaneDivider.gripDotCount", 3);
            this.gripDotSize = FlatUIUtils.getUIInt("SplitPaneDivider.gripDotSize", 3);
            this.gripGap = FlatUIUtils.getUIInt("SplitPaneDivider.gripGap", 2);
            this.setLayout(new FlatDividerLayout());
        }

        @Override
        public void setDividerSize(int newSize) {
            super.setDividerSize(UIScale.scale(newSize));
        }

        @Override
        protected JButton createLeftOneTouchButton() {
            return new FlatOneTouchButton(true);
        }

        @Override
        protected JButton createRightOneTouchButton() {
            return new FlatOneTouchButton(false);
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            super.propertyChange(e);
            switch (e.getPropertyName()) {
                case "dividerLocation": {
                    this.revalidate();
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if ("plain".equals(this.style)) {
                return;
            }
            Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
            g.setColor(this.gripColor);
            this.paintGrip(g, 0, 0, this.getWidth(), this.getHeight());
            FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
        }

        protected void paintGrip(Graphics g, int x, int y, int width, int height) {
            FlatUIUtils.paintGrip(g, x, y, width, height, this.splitPane.getOrientation() == 0, this.gripDotCount, this.gripDotSize, this.gripGap, true);
        }

        protected boolean isLeftCollapsed() {
            int location = this.splitPane.getDividerLocation();
            Insets insets = this.splitPane.getInsets();
            return this.orientation == 0 ? location == insets.top : location == insets.left;
        }

        protected boolean isRightCollapsed() {
            int location = this.splitPane.getDividerLocation();
            Insets insets = this.splitPane.getInsets();
            return this.orientation == 0 ? location == this.splitPane.getHeight() - this.getHeight() - insets.bottom : location == this.splitPane.getWidth() - this.getWidth() - insets.right;
        }

        protected class FlatDividerLayout
        extends BasicSplitPaneDivider.DividerLayout {
            protected FlatDividerLayout() {
                super(FlatSplitPaneDivider.this);
            }

            @Override
            public void layoutContainer(Container c) {
                super.layoutContainer(c);
                if (FlatSplitPaneDivider.this.leftButton == null || FlatSplitPaneDivider.this.rightButton == null || !FlatSplitPaneDivider.this.splitPane.isOneTouchExpandable()) {
                    return;
                }
                int extraSize = UIScale.scale(4);
                if (FlatSplitPaneDivider.this.orientation == 0) {
                    FlatSplitPaneDivider.this.leftButton.setSize(FlatSplitPaneDivider.this.leftButton.getWidth() + extraSize, FlatSplitPaneDivider.this.leftButton.getHeight());
                    FlatSplitPaneDivider.this.rightButton.setBounds(FlatSplitPaneDivider.this.leftButton.getX() + FlatSplitPaneDivider.this.leftButton.getWidth(), FlatSplitPaneDivider.this.rightButton.getY(), FlatSplitPaneDivider.this.rightButton.getWidth() + extraSize, FlatSplitPaneDivider.this.rightButton.getHeight());
                } else {
                    FlatSplitPaneDivider.this.leftButton.setSize(FlatSplitPaneDivider.this.leftButton.getWidth(), FlatSplitPaneDivider.this.leftButton.getHeight() + extraSize);
                    FlatSplitPaneDivider.this.rightButton.setBounds(FlatSplitPaneDivider.this.rightButton.getX(), FlatSplitPaneDivider.this.leftButton.getY() + FlatSplitPaneDivider.this.leftButton.getHeight(), FlatSplitPaneDivider.this.rightButton.getWidth(), FlatSplitPaneDivider.this.rightButton.getHeight() + extraSize);
                }
                boolean leftCollapsed = FlatSplitPaneDivider.this.isLeftCollapsed();
                if (leftCollapsed) {
                    FlatSplitPaneDivider.this.rightButton.setLocation(FlatSplitPaneDivider.this.leftButton.getLocation());
                }
                FlatSplitPaneDivider.this.leftButton.setVisible(!leftCollapsed);
                FlatSplitPaneDivider.this.rightButton.setVisible(!FlatSplitPaneDivider.this.isRightCollapsed());
            }
        }

        protected class FlatOneTouchButton
        extends FlatArrowButton {
            protected final boolean left;

            protected FlatOneTouchButton(boolean left) {
                super(1, FlatSplitPaneUI.this.arrowType, FlatSplitPaneUI.this.oneTouchArrowColor, null, FlatSplitPaneUI.this.oneTouchHoverArrowColor, null, FlatSplitPaneUI.this.oneTouchPressedArrowColor, null);
                this.setCursor(Cursor.getPredefinedCursor(0));
                ToolTipManager.sharedInstance().registerComponent(this);
                this.left = left;
            }

            @Override
            public int getDirection() {
                return FlatSplitPaneDivider.this.orientation == 0 ? (this.left ? 1 : 5) : (this.left ? 7 : 3);
            }

            @Override
            public String getToolTipText(MouseEvent e) {
                String key = FlatSplitPaneDivider.this.orientation == 0 ? (this.left ? (FlatSplitPaneDivider.this.isRightCollapsed() ? "SplitPaneDivider.expandBottomToolTipText" : "SplitPaneDivider.collapseTopToolTipText") : (FlatSplitPaneDivider.this.isLeftCollapsed() ? "SplitPaneDivider.expandTopToolTipText" : "SplitPaneDivider.collapseBottomToolTipText")) : (this.left ? (FlatSplitPaneDivider.this.isRightCollapsed() ? "SplitPaneDivider.expandRightToolTipText" : "SplitPaneDivider.collapseLeftToolTipText") : (FlatSplitPaneDivider.this.isLeftCollapsed() ? "SplitPaneDivider.expandLeftToolTipText" : "SplitPaneDivider.collapseRightToolTipText"));
                Object value = FlatSplitPaneDivider.this.splitPane.getClientProperty(key);
                if (value instanceof String) {
                    return (String)value;
                }
                return UIManager.getString((Object)key, this.getLocale());
            }
        }
    }
}

