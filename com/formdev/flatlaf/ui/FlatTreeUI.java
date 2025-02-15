/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.CellRendererPane;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class FlatTreeUI
extends BasicTreeUI {
    protected Color selectionBackground;
    protected Color selectionForeground;
    protected Color selectionInactiveBackground;
    protected Color selectionInactiveForeground;
    protected Color selectionBorderColor;
    protected boolean wideSelection;
    protected boolean showCellFocusIndicator;

    public static ComponentUI createUI(JComponent c) {
        return new FlatTreeUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installBorder(this.tree, "Tree.border");
        this.selectionBackground = UIManager.getColor("Tree.selectionBackground");
        this.selectionForeground = UIManager.getColor("Tree.selectionForeground");
        this.selectionInactiveBackground = UIManager.getColor("Tree.selectionInactiveBackground");
        this.selectionInactiveForeground = UIManager.getColor("Tree.selectionInactiveForeground");
        this.selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
        this.wideSelection = UIManager.getBoolean("Tree.wideSelection");
        this.showCellFocusIndicator = UIManager.getBoolean("Tree.showCellFocusIndicator");
        int rowHeight = FlatUIUtils.getUIInt("Tree.rowHeight", 16);
        if (rowHeight > 0) {
            LookAndFeel.installProperty(this.tree, "rowHeight", UIScale.scale(rowHeight));
        }
        this.setLeftChildIndent(UIScale.scale(this.getLeftChildIndent()));
        this.setRightChildIndent(UIScale.scale(this.getRightChildIndent()));
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        LookAndFeel.uninstallBorder(this.tree);
        this.selectionBackground = null;
        this.selectionForeground = null;
        this.selectionInactiveBackground = null;
        this.selectionInactiveForeground = null;
        this.selectionBorderColor = null;
    }

    @Override
    protected MouseListener createMouseListener() {
        return new BasicTreeUI.MouseHandler(){

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(this.handleWideMouseEvent(e));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(this.handleWideMouseEvent(e));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(this.handleWideMouseEvent(e));
            }

            private MouseEvent handleWideMouseEvent(MouseEvent e) {
                if (!FlatTreeUI.this.isWideSelection() || !FlatTreeUI.this.tree.isEnabled() || !SwingUtilities.isLeftMouseButton(e) || e.isConsumed()) {
                    return e;
                }
                int x = e.getX();
                int y = e.getY();
                TreePath path = FlatTreeUI.this.getClosestPathForLocation(FlatTreeUI.this.tree, x, y);
                if (path == null || FlatTreeUI.this.isLocationInExpandControl(path, x, y)) {
                    return e;
                }
                Rectangle bounds = FlatTreeUI.this.getPathBounds(FlatTreeUI.this.tree, path);
                if (bounds == null || y < bounds.y || y >= bounds.y + bounds.height) {
                    return e;
                }
                int newX = Math.max(bounds.x, Math.min(x, bounds.x + bounds.width - 1));
                if (newX == x) {
                    return e;
                }
                return new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers() | e.getModifiersEx(), newX, e.getY(), e.getClickCount(), e.isPopupTrigger(), e.getButton());
            }
        };
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        return new BasicTreeUI.PropertyChangeHandler(){

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                super.propertyChange(e);
                if (e.getSource() == FlatTreeUI.this.tree) {
                    switch (e.getPropertyName()) {
                        case "JTree.wideSelection": 
                        case "JTree.paintSelection": {
                            FlatTreeUI.this.tree.repaint();
                            break;
                        }
                        case "dropLocation": {
                            if (!FlatTreeUI.this.isWideSelection()) break;
                            JTree.DropLocation oldValue = (JTree.DropLocation)e.getOldValue();
                            this.repaintWideDropLocation(oldValue);
                            this.repaintWideDropLocation(FlatTreeUI.this.tree.getDropLocation());
                        }
                    }
                }
            }

            private void repaintWideDropLocation(JTree.DropLocation loc) {
                if (loc == null || FlatTreeUI.this.isDropLine(loc)) {
                    return;
                }
                Rectangle r = FlatTreeUI.this.tree.getPathBounds(loc.getPath());
                if (r != null) {
                    FlatTreeUI.this.tree.repaint(0, r.y, FlatTreeUI.this.tree.getWidth(), r.height);
                }
            }
        };
    }

    @Override
    protected void paintRow(Graphics g, Rectangle clipBounds, Insets insets, Rectangle bounds, TreePath path, int row, boolean isExpanded, boolean hasBeenExpanded, boolean isLeaf) {
        DefaultTreeCellRenderer renderer;
        boolean cellHasFocus;
        boolean needsSelectionPainting;
        boolean isEditing = this.editingComponent != null && this.editingRow == row;
        boolean isSelected = this.tree.isRowSelected(row);
        boolean isDropRow = this.isDropRow(row);
        boolean bl = needsSelectionPainting = (isSelected || isDropRow) && this.isPaintSelection();
        if (isEditing && !needsSelectionPainting) {
            return;
        }
        boolean hasFocus = FlatUIUtils.isPermanentFocusOwner(this.tree);
        boolean bl2 = cellHasFocus = hasFocus && row == this.getLeadSelectionRow();
        if (!hasFocus && isSelected && this.tree.getParent() instanceof CellRendererPane) {
            hasFocus = FlatUIUtils.isPermanentFocusOwner(this.tree.getParent().getParent());
        }
        Component rendererComponent = this.currentCellRenderer.getTreeCellRendererComponent(this.tree, path.getLastPathComponent(), isSelected, isExpanded, isLeaf, row, cellHasFocus);
        Color oldBackgroundSelectionColor = null;
        if (isSelected && !hasFocus && !isDropRow) {
            if (rendererComponent instanceof DefaultTreeCellRenderer) {
                DefaultTreeCellRenderer renderer2 = (DefaultTreeCellRenderer)rendererComponent;
                if (renderer2.getBackgroundSelectionColor() == this.selectionBackground) {
                    oldBackgroundSelectionColor = renderer2.getBackgroundSelectionColor();
                    renderer2.setBackgroundSelectionColor(this.selectionInactiveBackground);
                }
            } else if (rendererComponent.getBackground() == this.selectionBackground) {
                rendererComponent.setBackground(this.selectionInactiveBackground);
            }
            if (rendererComponent.getForeground() == this.selectionForeground) {
                rendererComponent.setForeground(this.selectionInactiveForeground);
            }
        }
        Color oldBorderSelectionColor = null;
        if (isSelected && hasFocus && (!this.showCellFocusIndicator || this.tree.getMinSelectionRow() == this.tree.getMaxSelectionRow()) && rendererComponent instanceof DefaultTreeCellRenderer && (renderer = (DefaultTreeCellRenderer)rendererComponent).getBorderSelectionColor() == this.selectionBorderColor) {
            oldBorderSelectionColor = renderer.getBorderSelectionColor();
            renderer.setBorderSelectionColor(null);
        }
        if (needsSelectionPainting) {
            Color oldColor = g.getColor();
            g.setColor(isDropRow ? UIManager.getColor("Tree.dropCellBackground") : (rendererComponent instanceof DefaultTreeCellRenderer ? ((DefaultTreeCellRenderer)rendererComponent).getBackgroundSelectionColor() : (hasFocus ? this.selectionBackground : this.selectionInactiveBackground)));
            if (this.isWideSelection()) {
                g.fillRect(0, bounds.y, this.tree.getWidth(), bounds.height);
                if (this.shouldPaintExpandControl(path, row, isExpanded, hasBeenExpanded, isLeaf)) {
                    this.paintExpandControl(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
                }
            } else {
                int xOffset = 0;
                int imageOffset = 0;
                if (rendererComponent instanceof JLabel) {
                    JLabel label = (JLabel)rendererComponent;
                    Icon icon = label.getIcon();
                    imageOffset = icon != null && label.getText() != null ? icon.getIconWidth() + Math.max(label.getIconTextGap() - 1, 0) : 0;
                    xOffset = label.getComponentOrientation().isLeftToRight() ? imageOffset : 0;
                }
                g.fillRect(bounds.x + xOffset, bounds.y, bounds.width - imageOffset, bounds.height);
            }
            g.setColor(oldColor);
        }
        if (!isEditing) {
            this.rendererPane.paintComponent(g, rendererComponent, this.tree, bounds.x, bounds.y, bounds.width, bounds.height, true);
        }
        if (oldBackgroundSelectionColor != null) {
            ((DefaultTreeCellRenderer)rendererComponent).setBackgroundSelectionColor(oldBackgroundSelectionColor);
        }
        if (oldBorderSelectionColor != null) {
            ((DefaultTreeCellRenderer)rendererComponent).setBorderSelectionColor(oldBorderSelectionColor);
        }
    }

    private boolean isDropRow(int row) {
        JTree.DropLocation dropLocation = this.tree.getDropLocation();
        return dropLocation != null && dropLocation.getChildIndex() == -1 && this.tree.getRowForPath(dropLocation.getPath()) == row;
    }

    @Override
    protected Rectangle getDropLineRect(JTree.DropLocation loc) {
        Rectangle r = super.getDropLineRect(loc);
        return this.isWideSelection() ? new Rectangle(0, r.y, this.tree.getWidth(), r.height) : r;
    }

    protected boolean isWideSelection() {
        return FlatClientProperties.clientPropertyBoolean(this.tree, "JTree.wideSelection", this.wideSelection);
    }

    protected boolean isPaintSelection() {
        return FlatClientProperties.clientPropertyBoolean(this.tree, "JTree.paintSelection", true);
    }
}

