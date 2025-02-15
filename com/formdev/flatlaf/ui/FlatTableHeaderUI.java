/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Objects;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class FlatTableHeaderUI
extends BasicTableHeaderUI {
    protected Color separatorColor;
    protected Color bottomSeparatorColor;
    protected int height;
    protected int sortIconPosition;

    public static ComponentUI createUI(JComponent c) {
        return new FlatTableHeaderUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.separatorColor = UIManager.getColor("TableHeader.separatorColor");
        this.bottomSeparatorColor = UIManager.getColor("TableHeader.bottomSeparatorColor");
        this.height = UIManager.getInt("TableHeader.height");
        switch (Objects.toString(UIManager.getString("TableHeader.sortIconPosition"), "right")) {
            default: {
                this.sortIconPosition = 4;
                break;
            }
            case "left": {
                this.sortIconPosition = 2;
                break;
            }
            case "top": {
                this.sortIconPosition = 1;
                break;
            }
            case "bottom": {
                this.sortIconPosition = 3;
            }
        }
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.separatorColor = null;
        this.bottomSeparatorColor = null;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        if (this.header.getColumnModel().getColumnCount() <= 0) {
            return;
        }
        TableCellRenderer defaultRenderer = this.header.getDefaultRenderer();
        boolean paintBorders = this.isSystemDefaultRenderer(defaultRenderer);
        if (!paintBorders) {
            Component rendererComponent = defaultRenderer.getTableCellRendererComponent(this.header.getTable(), "", false, false, -1, 0);
            paintBorders = this.isSystemDefaultRenderer(rendererComponent);
        }
        if (paintBorders) {
            this.paintColumnBorders(g, c);
        }
        FlatTableCellHeaderRenderer sortIconRenderer = null;
        if (this.sortIconPosition != 4) {
            sortIconRenderer = new FlatTableCellHeaderRenderer(this.header.getDefaultRenderer());
            this.header.setDefaultRenderer(sortIconRenderer);
        }
        super.paint(g, c);
        if (sortIconRenderer != null) {
            sortIconRenderer.reset();
            this.header.setDefaultRenderer(sortIconRenderer.delegate);
        }
        if (paintBorders) {
            this.paintDraggedColumnBorders(g, c);
        }
    }

    private boolean isSystemDefaultRenderer(Object headerRenderer) {
        String rendererClassName = headerRenderer.getClass().getName();
        return rendererClassName.equals("sun.swing.table.DefaultTableCellHeaderRenderer") || rendererClassName.equals("sun.swing.FilePane$AlignableTableHeaderRenderer");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void paintColumnBorders(Graphics g, JComponent c) {
        int columnCount;
        float lineWidth;
        int width = c.getWidth();
        int height = c.getHeight();
        float topLineIndent = lineWidth = UIScale.scale(1.0f);
        float bottomLineIndent = lineWidth * 3.0f;
        TableColumnModel columnModel = this.header.getColumnModel();
        int sepCount = columnCount = columnModel.getColumnCount();
        if (this.hideLastVerticalLine()) {
            --sepCount;
        }
        Graphics2D g2 = (Graphics2D)g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(this.bottomSeparatorColor);
            g2.fill(new Rectangle2D.Float(0.0f, (float)height - lineWidth, width, lineWidth));
            g2.setColor(this.separatorColor);
            float y = topLineIndent;
            float h = (float)height - bottomLineIndent;
            if (this.header.getComponentOrientation().isLeftToRight()) {
                int x = 0;
                for (int i = 0; i < sepCount; ++i) {
                    g2.fill(new Rectangle2D.Float((float)(x += columnModel.getColumn(i).getWidth()) - lineWidth, y, lineWidth, h));
                }
                if (!this.hideTrailingVerticalLine()) {
                    g2.fill(new Rectangle2D.Float((float)this.header.getWidth() - lineWidth, y, lineWidth, h));
                }
            } else {
                Rectangle cellRect = this.header.getHeaderRect(0);
                int x = cellRect.x + cellRect.width;
                for (int i = 0; i < sepCount; ++i) {
                    g2.fill(new Rectangle2D.Float((float)(x -= columnModel.getColumn(i).getWidth()) - (i < sepCount - 1 ? lineWidth : 0.0f), y, lineWidth, h));
                }
                if (!this.hideTrailingVerticalLine()) {
                    g2.fill(new Rectangle2D.Float(0.0f, y, lineWidth, h));
                }
            }
        } finally {
            g2.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void paintDraggedColumnBorders(Graphics g, JComponent c) {
        float lineWidth;
        TableColumn draggedColumn = this.header.getDraggedColumn();
        if (draggedColumn == null) {
            return;
        }
        TableColumnModel columnModel = this.header.getColumnModel();
        int columnCount = columnModel.getColumnCount();
        int draggedColumnIndex = -1;
        for (int i = 0; i < columnCount; ++i) {
            if (columnModel.getColumn(i) != draggedColumn) continue;
            draggedColumnIndex = i;
            break;
        }
        if (draggedColumnIndex < 0) {
            return;
        }
        float topLineIndent = lineWidth = UIScale.scale(1.0f);
        float bottomLineIndent = lineWidth * 3.0f;
        Rectangle r = this.header.getHeaderRect(draggedColumnIndex);
        r.x += this.header.getDraggedDistance();
        Graphics2D g2 = (Graphics2D)g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(this.bottomSeparatorColor);
            g2.fill(new Rectangle2D.Float(r.x, (float)(r.y + r.height) - lineWidth, r.width, lineWidth));
            g2.setColor(this.separatorColor);
            g2.fill(new Rectangle2D.Float(r.x, topLineIndent, lineWidth, (float)r.height - bottomLineIndent));
            g2.fill(new Rectangle2D.Float((float)(r.x + r.width) - lineWidth, (float)r.y + topLineIndent, lineWidth, (float)r.height - bottomLineIndent));
        } finally {
            g2.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension size = super.getPreferredSize(c);
        if (size.height > 0) {
            size.height = Math.max(size.height, UIScale.scale(this.height));
        }
        return size;
    }

    protected boolean hideLastVerticalLine() {
        Container viewportParent;
        Container viewport = this.header.getParent();
        Container container = viewportParent = viewport != null ? viewport.getParent() : null;
        if (!(viewportParent instanceof JScrollPane)) {
            return false;
        }
        Rectangle cellRect = this.header.getHeaderRect(this.header.getColumnModel().getColumnCount() - 1);
        JScrollPane scrollPane = (JScrollPane)viewportParent;
        return scrollPane.getComponentOrientation().isLeftToRight() ? cellRect.x + cellRect.width >= viewport.getWidth() : cellRect.x <= 0;
    }

    protected boolean hideTrailingVerticalLine() {
        Container viewportParent;
        Container viewport = this.header.getParent();
        Container container = viewportParent = viewport != null ? viewport.getParent() : null;
        if (!(viewportParent instanceof JScrollPane)) {
            return false;
        }
        JScrollPane scrollPane = (JScrollPane)viewportParent;
        return viewport == scrollPane.getColumnHeader() && scrollPane.getCorner("UPPER_TRAILING_CORNER") == null;
    }

    private class FlatTableCellHeaderRenderer
    implements TableCellRenderer,
    Border,
    UIResource {
        private final TableCellRenderer delegate;
        private JLabel l;
        private int oldHorizontalTextPosition = -1;
        private Border origBorder;
        private Icon sortIcon;

        FlatTableCellHeaderRenderer(TableCellRenderer delegate) {
            this.delegate = delegate;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = this.delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!(c instanceof JLabel)) {
                return c;
            }
            this.l = (JLabel)c;
            if (FlatTableHeaderUI.this.sortIconPosition == 2) {
                if (this.oldHorizontalTextPosition < 0) {
                    this.oldHorizontalTextPosition = this.l.getHorizontalTextPosition();
                }
                this.l.setHorizontalTextPosition(4);
            } else {
                this.sortIcon = this.l.getIcon();
                this.origBorder = this.l.getBorder();
                this.l.setIcon(null);
                this.l.setBorder(this);
            }
            return this.l;
        }

        void reset() {
            if (this.l != null && FlatTableHeaderUI.this.sortIconPosition == 2 && this.oldHorizontalTextPosition >= 0) {
                this.l.setHorizontalTextPosition(this.oldHorizontalTextPosition);
            }
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            if (this.origBorder != null) {
                this.origBorder.paintBorder(c, g, x, y, width, height);
            }
            if (this.sortIcon != null) {
                int xi = x + (width - this.sortIcon.getIconWidth()) / 2;
                int yi = FlatTableHeaderUI.this.sortIconPosition == 1 ? y + UIScale.scale(1) : y + height - this.sortIcon.getIconHeight() - 1 - (int)(1.0f * UIScale.getUserScaleFactor());
                this.sortIcon.paintIcon(c, g, xi, yi);
            }
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return this.origBorder != null ? this.origBorder.getBorderInsets(c) : new Insets(0, 0, 0, 0);
        }

        @Override
        public boolean isBorderOpaque() {
            return this.origBorder != null ? this.origBorder.isBorderOpaque() : false;
        }
    }
}

