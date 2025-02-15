/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.debug;

import com.jgoodies.forms.debug.FormDebugUtils;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class FormDebugPanel
extends JPanel {
    public static boolean paintRowsDefault = true;
    private static final Color DEFAULT_GRID_COLOR = Color.red;
    private boolean paintInBackground;
    private boolean paintDiagonals;
    private boolean paintRows = paintRowsDefault;
    private Color gridColor = DEFAULT_GRID_COLOR;

    public FormDebugPanel() {
        this(null);
    }

    public FormDebugPanel(FormLayout layout) {
        this(layout, false, false);
    }

    public FormDebugPanel(boolean paintInBackground, boolean paintDiagonals) {
        this(null, paintInBackground, paintDiagonals);
    }

    public FormDebugPanel(FormLayout layout, boolean paintInBackground, boolean paintDiagonals) {
        super(layout);
        this.setPaintInBackground(paintInBackground);
        this.setPaintDiagonals(paintDiagonals);
        this.setGridColor(DEFAULT_GRID_COLOR);
    }

    public void setPaintInBackground(boolean b) {
        this.paintInBackground = b;
    }

    public void setPaintDiagonals(boolean b) {
        this.paintDiagonals = b;
    }

    public void setPaintRows(boolean b) {
        this.paintRows = b;
    }

    public void setGridColor(Color color) {
        this.gridColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.paintInBackground) {
            this.paintGrid(g);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!this.paintInBackground) {
            this.paintGrid(g);
        }
    }

    private void paintGrid(Graphics g) {
        int length;
        int i;
        int stop;
        int start;
        boolean firstOrLast;
        if (!(this.getLayout() instanceof FormLayout)) {
            return;
        }
        FormLayout.LayoutInfo layoutInfo = FormDebugUtils.getLayoutInfo(this);
        int left = layoutInfo.getX();
        int top = layoutInfo.getY();
        int width = layoutInfo.getWidth();
        int height = layoutInfo.getHeight();
        g.setColor(this.gridColor);
        int last = layoutInfo.columnOrigins.length - 1;
        for (int col = 0; col <= last; ++col) {
            firstOrLast = col == 0 || col == last;
            int x = layoutInfo.columnOrigins[col];
            start = firstOrLast ? 0 : top;
            stop = firstOrLast ? this.getHeight() : top + height;
            for (i = start; i < stop; i += 5) {
                length = Math.min(3, stop - i);
                g.fillRect(x, i, 1, length);
            }
        }
        last = layoutInfo.rowOrigins.length - 1;
        for (int row = 0; row <= last; ++row) {
            firstOrLast = row == 0 || row == last;
            int y = layoutInfo.rowOrigins[row];
            start = firstOrLast ? 0 : left;
            int n = stop = firstOrLast ? this.getWidth() : left + width;
            if (!firstOrLast && !this.paintRows) continue;
            for (i = start; i < stop; i += 5) {
                length = Math.min(3, stop - i);
                g.fillRect(i, y, length, 1);
            }
        }
        if (this.paintDiagonals) {
            g.drawLine(left, top, left + width, top + height);
            g.drawLine(left, top + height, left + width, top);
        }
    }
}

