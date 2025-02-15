/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.Graphics2DProxy;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableUI;

public class FlatTableUI
extends BasicTableUI {
    protected boolean showHorizontalLines;
    protected boolean showVerticalLines;
    protected Dimension intercellSpacing;
    protected Color selectionBackground;
    protected Color selectionForeground;
    protected Color selectionInactiveBackground;
    protected Color selectionInactiveForeground;
    private boolean oldShowHorizontalLines;
    private boolean oldShowVerticalLines;
    private Dimension oldIntercellSpacing;

    public static ComponentUI createUI(JComponent c) {
        return new FlatTableUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.showHorizontalLines = UIManager.getBoolean("Table.showHorizontalLines");
        this.showVerticalLines = UIManager.getBoolean("Table.showVerticalLines");
        this.intercellSpacing = UIManager.getDimension("Table.intercellSpacing");
        this.selectionBackground = UIManager.getColor("Table.selectionBackground");
        this.selectionForeground = UIManager.getColor("Table.selectionForeground");
        this.selectionInactiveBackground = UIManager.getColor("Table.selectionInactiveBackground");
        this.selectionInactiveForeground = UIManager.getColor("Table.selectionInactiveForeground");
        this.toggleSelectionColors();
        int rowHeight = FlatUIUtils.getUIInt("Table.rowHeight", 16);
        if (rowHeight > 0) {
            LookAndFeel.installProperty(this.table, "rowHeight", UIScale.scale(rowHeight));
        }
        if (!this.showHorizontalLines) {
            this.oldShowHorizontalLines = this.table.getShowHorizontalLines();
            this.table.setShowHorizontalLines(false);
        }
        if (!this.showVerticalLines) {
            this.oldShowVerticalLines = this.table.getShowVerticalLines();
            this.table.setShowVerticalLines(false);
        }
        if (this.intercellSpacing != null) {
            this.oldIntercellSpacing = this.table.getIntercellSpacing();
            this.table.setIntercellSpacing(this.intercellSpacing);
        }
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.selectionBackground = null;
        this.selectionForeground = null;
        this.selectionInactiveBackground = null;
        this.selectionInactiveForeground = null;
        if (!this.showHorizontalLines && this.oldShowHorizontalLines && !this.table.getShowHorizontalLines()) {
            this.table.setShowHorizontalLines(true);
        }
        if (!this.showVerticalLines && this.oldShowVerticalLines && !this.table.getShowVerticalLines()) {
            this.table.setShowVerticalLines(true);
        }
        if (this.intercellSpacing != null && this.table.getIntercellSpacing().equals(this.intercellSpacing)) {
            this.table.setIntercellSpacing(this.oldIntercellSpacing);
        }
    }

    @Override
    protected FocusListener createFocusListener() {
        return new BasicTableUI.FocusHandler(){

            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                FlatTableUI.this.toggleSelectionColors();
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                EventQueue.invokeLater(() -> FlatTableUI.this.toggleSelectionColors());
            }
        };
    }

    private void toggleSelectionColors() {
        if (this.table == null) {
            return;
        }
        if (FlatUIUtils.isPermanentFocusOwner(this.table)) {
            if (this.table.getSelectionBackground() == this.selectionInactiveBackground) {
                this.table.setSelectionBackground(this.selectionBackground);
            }
            if (this.table.getSelectionForeground() == this.selectionInactiveForeground) {
                this.table.setSelectionForeground(this.selectionForeground);
            }
        } else {
            if (this.table.getSelectionBackground() == this.selectionBackground) {
                this.table.setSelectionBackground(this.selectionInactiveBackground);
            }
            if (this.table.getSelectionForeground() == this.selectionForeground) {
                this.table.setSelectionForeground(this.selectionInactiveForeground);
            }
        }
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        final boolean horizontalLines = this.table.getShowHorizontalLines();
        final boolean verticalLines = this.table.getShowVerticalLines();
        if (horizontalLines || verticalLines) {
            final boolean hideLastVerticalLine = this.hideLastVerticalLine();
            final int tableWidth = this.table.getWidth();
            double systemScaleFactor = UIScale.getSystemScaleFactor((Graphics2D)g);
            final double lineThickness = 1.0 / systemScaleFactor * (double)((int)systemScaleFactor);
            g = new Graphics2DProxy((Graphics2D)g){

                @Override
                public void drawLine(int x1, int y1, int x2, int y2) {
                    if (hideLastVerticalLine && verticalLines && x1 == x2 && y1 == 0 && x1 == tableWidth - 1 && this.wasInvokedFromPaintGrid()) {
                        return;
                    }
                    super.drawLine(x1, y1, x2, y2);
                }

                @Override
                public void fillRect(int x, int y, int width, int height) {
                    if (hideLastVerticalLine && verticalLines && width == 1 && y == 0 && x == tableWidth - 1 && this.wasInvokedFromPaintGrid()) {
                        return;
                    }
                    if (lineThickness != 1.0) {
                        if (horizontalLines && height == 1 && this.wasInvokedFromPaintGrid()) {
                            super.fill(new Rectangle2D.Double(x, y, width, lineThickness));
                            return;
                        }
                        if (verticalLines && width == 1 && y == 0 && this.wasInvokedFromPaintGrid()) {
                            super.fill(new Rectangle2D.Double(x, y, lineThickness, height));
                            return;
                        }
                    }
                    super.fillRect(x, y, width, height);
                }

                private boolean wasInvokedFromPaintGrid() {
                    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                    for (int i = 0; i < 10 || i < stackTrace.length; ++i) {
                        if (!"javax.swing.plaf.basic.BasicTableUI".equals(stackTrace[i].getClassName()) || !"paintGrid".equals(stackTrace[i].getMethodName())) continue;
                        return true;
                    }
                    return false;
                }
            };
        }
        super.paint(g, c);
    }

    protected boolean hideLastVerticalLine() {
        Container viewportParent;
        Container viewport = SwingUtilities.getUnwrappedParent(this.table);
        Container container = viewportParent = viewport != null ? viewport.getParent() : null;
        if (!(viewportParent instanceof JScrollPane)) {
            return false;
        }
        if (this.table.getX() + this.table.getWidth() < viewport.getWidth()) {
            return false;
        }
        JScrollPane scrollPane = (JScrollPane)viewportParent;
        JViewport rowHeader = scrollPane.getRowHeader();
        return scrollPane.getComponentOrientation().isLeftToRight() ? viewport != rowHeader : viewport == rowHeader || rowHeader == null;
    }
}

