/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class FlatProgressBarUI
extends BasicProgressBarUI {
    protected int arc;
    protected Dimension horizontalSize;
    protected Dimension verticalSize;
    private PropertyChangeListener propertyChangeListener;

    public static ComponentUI createUI(JComponent c) {
        return new FlatProgressBarUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.progressBar, "opaque", false);
        this.arc = UIManager.getInt("ProgressBar.arc");
        this.horizontalSize = UIManager.getDimension("ProgressBar.horizontalSize");
        this.verticalSize = UIManager.getDimension("ProgressBar.verticalSize");
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.propertyChangeListener = e -> {
            switch (e.getPropertyName()) {
                case "JProgressBar.largeHeight": 
                case "JProgressBar.square": {
                    this.progressBar.revalidate();
                    this.progressBar.repaint();
                }
            }
        };
        this.progressBar.addPropertyChangeListener(this.propertyChangeListener);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.progressBar.removePropertyChangeListener(this.propertyChangeListener);
        this.propertyChangeListener = null;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension size = super.getPreferredSize(c);
        if (this.progressBar.isStringPainted() || FlatClientProperties.clientPropertyBoolean(c, "JProgressBar.largeHeight", false)) {
            Insets insets = this.progressBar.getInsets();
            FontMetrics fm = this.progressBar.getFontMetrics(this.progressBar.getFont());
            if (this.progressBar.getOrientation() == 0) {
                size.height = Math.max(fm.getHeight() + insets.top + insets.bottom, this.getPreferredInnerHorizontal().height);
            } else {
                size.width = Math.max(fm.getHeight() + insets.left + insets.right, this.getPreferredInnerVertical().width);
            }
        }
        return size;
    }

    @Override
    protected Dimension getPreferredInnerHorizontal() {
        return UIScale.scale(this.horizontalSize);
    }

    @Override
    protected Dimension getPreferredInnerVertical() {
        return UIScale.scale(this.verticalSize);
    }

    @Override
    public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            FlatUIUtils.paintParentBackground(g, c);
        }
        this.paint(g, c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        boolean horizontal;
        Insets insets = this.progressBar.getInsets();
        int x = insets.left;
        int y = insets.top;
        int width = this.progressBar.getWidth() - (insets.right + insets.left);
        int height = this.progressBar.getHeight() - (insets.top + insets.bottom);
        if (width <= 0 || height <= 0) {
            return;
        }
        boolean bl = horizontal = this.progressBar.getOrientation() == 0;
        int arc = FlatClientProperties.clientPropertyBoolean(c, "JProgressBar.square", false) ? 0 : Math.min(UIScale.scale(this.arc), horizontal ? height : width);
        Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
        RoundRectangle2D.Float trackShape = new RoundRectangle2D.Float(x, y, width, height, arc, arc);
        g.setColor(this.progressBar.getBackground());
        ((Graphics2D)g).fill(trackShape);
        int amountFull = 0;
        if (this.progressBar.isIndeterminate()) {
            this.boxRect = this.getBox(this.boxRect);
            if (this.boxRect != null) {
                g.setColor(this.progressBar.getForeground());
                ((Graphics2D)g).fill(new RoundRectangle2D.Float(this.boxRect.x, this.boxRect.y, this.boxRect.width, this.boxRect.height, arc, arc));
            }
        } else {
            amountFull = this.getAmountFull(insets, width, height);
            RoundRectangle2D.Float progressShape = horizontal ? new RoundRectangle2D.Float(c.getComponentOrientation().isLeftToRight() ? (float)x : (float)(x + (width - amountFull)), y, amountFull, height, arc, arc) : new RoundRectangle2D.Float(x, y + (height - amountFull), width, amountFull, arc, arc);
            g.setColor(this.progressBar.getForeground());
            if (amountFull < (horizontal ? height : width)) {
                Area area = new Area(trackShape);
                area.intersect(new Area(progressShape));
                ((Graphics2D)g).fill(area);
            } else {
                ((Graphics2D)g).fill(progressShape);
            }
        }
        FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
        if (this.progressBar.isStringPainted()) {
            this.paintString(g, x, y, width, height, amountFull, insets);
        }
    }

    @Override
    protected void paintString(Graphics g, int x, int y, int width, int height, int amountFull, Insets b) {
        super.paintString(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g), x, y, width, height, amountFull, b);
    }

    @Override
    protected void setAnimationIndex(int newValue) {
        super.setAnimationIndex(newValue);
        double systemScaleFactor = UIScale.getSystemScaleFactor(this.progressBar.getGraphicsConfiguration());
        if ((double)((int)systemScaleFactor) != systemScaleFactor) {
            this.progressBar.repaint();
        }
    }
}

