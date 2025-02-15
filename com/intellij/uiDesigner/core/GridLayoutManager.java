/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.uiDesigner.core.DimensionInfo;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.HorizontalInfo;
import com.intellij.uiDesigner.core.LayoutState;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.uiDesigner.core.Util;
import com.intellij.uiDesigner.core.VerticalInfo;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.JComponent;

public final class GridLayoutManager
extends AbstractLayout {
    private int myMinCellSize = 20;
    private final int[] myRowStretches;
    private final int[] myColumnStretches;
    private final int[] myYs;
    private final int[] myHeights;
    private final int[] myXs;
    private final int[] myWidths;
    private LayoutState myLayoutState;
    DimensionInfo myHorizontalInfo;
    DimensionInfo myVerticalInfo;
    private boolean mySameSizeHorizontally;
    private boolean mySameSizeVertically;
    public static final Object DESIGN_TIME_INSETS = new Object();
    private static final int SKIP_ROW = 1;
    private static final int SKIP_COL = 2;

    public GridLayoutManager(int rowCount, int columnCount) {
        int i;
        if (columnCount < 1) {
            throw new IllegalArgumentException("wrong columnCount: " + columnCount);
        }
        if (rowCount < 1) {
            throw new IllegalArgumentException("wrong rowCount: " + rowCount);
        }
        this.myRowStretches = new int[rowCount];
        for (i = 0; i < rowCount; ++i) {
            this.myRowStretches[i] = 1;
        }
        this.myColumnStretches = new int[columnCount];
        for (i = 0; i < columnCount; ++i) {
            this.myColumnStretches[i] = 1;
        }
        this.myXs = new int[columnCount];
        this.myWidths = new int[columnCount];
        this.myYs = new int[rowCount];
        this.myHeights = new int[rowCount];
    }

    public GridLayoutManager(int rowCount, int columnCount, Insets margin, int hGap, int vGap) {
        this(rowCount, columnCount);
        this.setMargin(margin);
        this.setHGap(hGap);
        this.setVGap(vGap);
        this.myMinCellSize = 0;
    }

    public GridLayoutManager(int rowCount, int columnCount, Insets margin, int hGap, int vGap, boolean sameSizeHorizontally, boolean sameSizeVertically) {
        this(rowCount, columnCount, margin, hGap, vGap);
        this.mySameSizeHorizontally = sameSizeHorizontally;
        this.mySameSizeVertically = sameSizeVertically;
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        GridConstraints c = (GridConstraints)constraints;
        int row = c.getRow();
        int rowSpan = c.getRowSpan();
        int rowCount = this.getRowCount();
        if (row < 0 || row >= rowCount) {
            throw new IllegalArgumentException("wrong row: " + row);
        }
        if (row + rowSpan - 1 >= rowCount) {
            throw new IllegalArgumentException("wrong row span: " + rowSpan + "; row=" + row + " rowCount=" + rowCount);
        }
        int column = c.getColumn();
        int colSpan = c.getColSpan();
        int columnCount = this.getColumnCount();
        if (column < 0 || column >= columnCount) {
            throw new IllegalArgumentException("wrong column: " + column);
        }
        if (column + colSpan - 1 >= columnCount) {
            throw new IllegalArgumentException("wrong col span: " + colSpan + "; column=" + column + " columnCount=" + columnCount);
        }
        super.addLayoutComponent(comp, constraints);
    }

    public int getRowCount() {
        return this.myRowStretches.length;
    }

    public int getColumnCount() {
        return this.myColumnStretches.length;
    }

    public int getRowStretch(int rowIndex) {
        return this.myRowStretches[rowIndex];
    }

    public void setRowStretch(int rowIndex, int stretch) {
        if (stretch < 1) {
            throw new IllegalArgumentException("wrong stretch: " + stretch);
        }
        this.myRowStretches[rowIndex] = stretch;
    }

    public int getColumnStretch(int columnIndex) {
        return this.myColumnStretches[columnIndex];
    }

    public void setColumnStretch(int columnIndex, int stretch) {
        if (stretch < 1) {
            throw new IllegalArgumentException("wrong stretch: " + stretch);
        }
        this.myColumnStretches[columnIndex] = stretch;
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public Dimension minimumLayoutSize(Container container) {
        this.validateInfos(container);
        DimensionInfo horizontalInfo = this.myHorizontalInfo;
        DimensionInfo verticalInfo = this.myVerticalInfo;
        Dimension result = this.getTotalGap(container, horizontalInfo, verticalInfo);
        int[] widths = this.getMinSizes(horizontalInfo);
        if (this.mySameSizeHorizontally) {
            GridLayoutManager.makeSameSizes(widths);
        }
        result.width += GridLayoutManager.sum(widths);
        int[] heights = this.getMinSizes(verticalInfo);
        if (this.mySameSizeVertically) {
            GridLayoutManager.makeSameSizes(heights);
        }
        result.height += GridLayoutManager.sum(heights);
        return result;
    }

    private static void makeSameSizes(int[] widths) {
        int max = widths[0];
        for (int width : widths) {
            max = Math.max(width, max);
        }
        Arrays.fill(widths, max);
    }

    private static int[] getSameSizes(DimensionInfo info, int totalWidth) {
        int[] widths = new int[info.getCellCount()];
        int average = totalWidth / widths.length;
        int rest = totalWidth % widths.length;
        for (int i = 0; i < widths.length; ++i) {
            widths[i] = average;
            if (rest <= 0) continue;
            int n = i;
            widths[n] = widths[n] + 1;
            --rest;
        }
        return widths;
    }

    @Override
    public Dimension preferredLayoutSize(Container container) {
        this.validateInfos(container);
        DimensionInfo horizontalInfo = this.myHorizontalInfo;
        DimensionInfo verticalInfo = this.myVerticalInfo;
        Dimension result = this.getTotalGap(container, horizontalInfo, verticalInfo);
        int[] widths = this.getPrefSizes(horizontalInfo);
        if (this.mySameSizeHorizontally) {
            GridLayoutManager.makeSameSizes(widths);
        }
        result.width += GridLayoutManager.sum(widths);
        int[] heights = this.getPrefSizes(verticalInfo);
        if (this.mySameSizeVertically) {
            GridLayoutManager.makeSameSizes(heights);
        }
        result.height += GridLayoutManager.sum(heights);
        return result;
    }

    private static int sum(int[] ints) {
        int result = 0;
        for (int i = ints.length - 1; i >= 0; --i) {
            result += ints[i];
        }
        return result;
    }

    private Dimension getTotalGap(Container container, DimensionInfo hInfo, DimensionInfo vInfo) {
        Insets insets = GridLayoutManager.getInsets(container);
        return new Dimension(insets.left + insets.right + GridLayoutManager.countGap(hInfo, 0, hInfo.getCellCount()) + this.myMargin.left + this.myMargin.right, insets.top + insets.bottom + GridLayoutManager.countGap(vInfo, 0, vInfo.getCellCount()) + this.myMargin.top + this.myMargin.bottom);
    }

    private static int getDesignTimeInsets(Container container) {
        while (container != null) {
            Integer designTimeInsets;
            if (container instanceof JComponent && (designTimeInsets = (Integer)((JComponent)container).getClientProperty(DESIGN_TIME_INSETS)) != null) {
                return designTimeInsets;
            }
            container = container.getParent();
        }
        return 0;
    }

    private static Insets getInsets(Container container) {
        Insets insets = container.getInsets();
        int insetsValue = GridLayoutManager.getDesignTimeInsets(container);
        if (insetsValue != 0) {
            return new Insets(insets.top + insetsValue, insets.left + insetsValue, insets.bottom + insetsValue, insets.right + insetsValue);
        }
        return insets;
    }

    private static int countGap(DimensionInfo info, int startCell, int cellCount) {
        int counter = 0;
        for (int cellIndex = startCell + cellCount - 2; cellIndex >= startCell; --cellIndex) {
            if (!GridLayoutManager.shouldAddGapAfterCell(info, cellIndex)) continue;
            ++counter;
        }
        return counter * info.getGap();
    }

    private static boolean shouldAddGapAfterCell(DimensionInfo info, int cellIndex) {
        int i;
        if (cellIndex < 0 || cellIndex >= info.getCellCount()) {
            throw new IllegalArgumentException("wrong cellIndex: " + cellIndex + "; cellCount=" + info.getCellCount());
        }
        boolean endsInThis = false;
        boolean startsInNext = false;
        int indexOfNextNotEmpty = -1;
        for (i = cellIndex + 1; i < info.getCellCount(); ++i) {
            if (GridLayoutManager.isCellEmpty(info, i)) continue;
            indexOfNextNotEmpty = i;
            break;
        }
        for (i = 0; i < info.getComponentCount(); ++i) {
            Component component = info.getComponent(i);
            if (component instanceof Spacer) continue;
            if (info.componentBelongsCell(i, cellIndex) && DimensionInfo.findAlignedChild(component, info.getConstraints(i)) != null) {
                return true;
            }
            if (info.getCell(i) == indexOfNextNotEmpty) {
                startsInNext = true;
            }
            if (info.getCell(i) + info.getSpan(i) - 1 != cellIndex) continue;
            endsInThis = true;
        }
        return startsInNext && endsInThis;
    }

    private static boolean isCellEmpty(DimensionInfo info, int cellIndex) {
        if (cellIndex < 0 || cellIndex >= info.getCellCount()) {
            throw new IllegalArgumentException("wrong cellIndex: " + cellIndex + "; cellCount=" + info.getCellCount());
        }
        for (int i = 0; i < info.getComponentCount(); ++i) {
            Component component = info.getComponent(i);
            if (info.getCell(i) != cellIndex || component instanceof Spacer) continue;
            return false;
        }
        return true;
    }

    @Override
    public void layoutContainer(Container container) {
        int i;
        this.validateInfos(container);
        LayoutState layoutState = this.myLayoutState;
        DimensionInfo horizontalInfo = this.myHorizontalInfo;
        DimensionInfo verticalInfo = this.myVerticalInfo;
        Insets insets = GridLayoutManager.getInsets(container);
        int skipLayout = this.checkSetSizesFromParent(container, insets);
        Dimension gap = this.getTotalGap(container, horizontalInfo, verticalInfo);
        Dimension size = container.getSize();
        size.width -= gap.width;
        size.height -= gap.height;
        Dimension prefSize = this.preferredLayoutSize(container);
        prefSize.width -= gap.width;
        prefSize.height -= gap.height;
        Dimension minSize = this.minimumLayoutSize(container);
        minSize.width -= gap.width;
        minSize.height -= gap.height;
        if ((skipLayout & 1) == 0) {
            int[] heights;
            if (this.mySameSizeVertically) {
                heights = GridLayoutManager.getSameSizes(verticalInfo, Math.max(size.height, minSize.height));
            } else if (size.height < prefSize.height) {
                heights = this.getMinSizes(verticalInfo);
                this.new_doIt(heights, 0, verticalInfo.getCellCount(), size.height, verticalInfo, true);
            } else {
                heights = this.getPrefSizes(verticalInfo);
                this.new_doIt(heights, 0, verticalInfo.getCellCount(), size.height, verticalInfo, false);
            }
            int y = insets.top + this.myMargin.top;
            for (i = 0; i < heights.length; ++i) {
                this.myYs[i] = y;
                this.myHeights[i] = heights[i];
                y += heights[i];
                if (!GridLayoutManager.shouldAddGapAfterCell(verticalInfo, i)) continue;
                y += verticalInfo.getGap();
            }
        }
        if ((skipLayout & 2) == 0) {
            int[] widths;
            if (this.mySameSizeHorizontally) {
                widths = GridLayoutManager.getSameSizes(horizontalInfo, Math.max(size.width, minSize.width));
            } else if (size.width < prefSize.width) {
                widths = this.getMinSizes(horizontalInfo);
                this.new_doIt(widths, 0, horizontalInfo.getCellCount(), size.width, horizontalInfo, true);
            } else {
                widths = this.getPrefSizes(horizontalInfo);
                this.new_doIt(widths, 0, horizontalInfo.getCellCount(), size.width, horizontalInfo, false);
            }
            int x = insets.left + this.myMargin.left;
            for (i = 0; i < widths.length; ++i) {
                this.myXs[i] = x;
                this.myWidths[i] = widths[i];
                x += widths[i];
                if (!GridLayoutManager.shouldAddGapAfterCell(horizontalInfo, i)) continue;
                x += horizontalInfo.getGap();
            }
        }
        for (int i2 = 0; i2 < layoutState.getComponentCount(); ++i2) {
            GridConstraints c = layoutState.getConstraints(i2);
            Component component = layoutState.getComponent(i2);
            int column = horizontalInfo.getCell(i2);
            int colSpan = horizontalInfo.getSpan(i2);
            int row = verticalInfo.getCell(i2);
            int rowSpan = verticalInfo.getSpan(i2);
            int cellWidth = this.myXs[column + colSpan - 1] + this.myWidths[column + colSpan - 1] - this.myXs[column];
            int cellHeight = this.myYs[row + rowSpan - 1] + this.myHeights[row + rowSpan - 1] - this.myYs[row];
            Dimension componentSize = new Dimension(cellWidth, cellHeight);
            if ((c.getFill() & 1) == 0) {
                componentSize.width = Math.min(componentSize.width, horizontalInfo.getPreferredWidth(i2));
            }
            if ((c.getFill() & 2) == 0) {
                componentSize.height = Math.min(componentSize.height, verticalInfo.getPreferredWidth(i2));
            }
            Util.adjustSize(component, c, componentSize);
            int dx = 0;
            int dy = 0;
            if ((c.getAnchor() & 4) != 0) {
                dx = cellWidth - componentSize.width;
            } else if ((c.getAnchor() & 8) == 0) {
                dx = (cellWidth - componentSize.width) / 2;
            }
            if ((c.getAnchor() & 2) != 0) {
                dy = cellHeight - componentSize.height;
            } else if ((c.getAnchor() & 1) == 0) {
                dy = (cellHeight - componentSize.height) / 2;
            }
            int indent = 10 * c.getIndent();
            componentSize.width -= indent;
            component.setBounds(this.myXs[column] + (dx += indent), this.myYs[row] + dy, componentSize.width, componentSize.height);
        }
    }

    private int checkSetSizesFromParent(Container container, Insets insets) {
        int skipLayout = 0;
        GridLayoutManager parentGridLayout = null;
        GridConstraints parentGridConstraints = null;
        Container parent = container.getParent();
        if (parent != null) {
            if (parent.getLayout() instanceof GridLayoutManager) {
                parentGridLayout = (GridLayoutManager)parent.getLayout();
                parentGridConstraints = parentGridLayout.getConstraintsForComponent(container);
            } else {
                Container parent2 = parent.getParent();
                if (parent2 != null && parent2.getLayout() instanceof GridLayoutManager) {
                    parentGridLayout = (GridLayoutManager)parent2.getLayout();
                    parentGridConstraints = parentGridLayout.getConstraintsForComponent(parent);
                }
            }
        }
        if (parentGridLayout != null && parentGridConstraints.isUseParentLayout()) {
            int i;
            if (this.myRowStretches.length == parentGridConstraints.getRowSpan()) {
                int row = parentGridConstraints.getRow();
                this.myYs[0] = insets.top + this.myMargin.top;
                this.myHeights[0] = parentGridLayout.myHeights[row] - this.myYs[0];
                for (i = 1; i < this.myRowStretches.length; ++i) {
                    this.myYs[i] = parentGridLayout.myYs[i + row] - parentGridLayout.myYs[row];
                    this.myHeights[i] = parentGridLayout.myHeights[i + row];
                }
                int n = this.myRowStretches.length - 1;
                this.myHeights[n] = this.myHeights[n] - (insets.bottom + this.myMargin.bottom);
                skipLayout |= 1;
            }
            if (this.myColumnStretches.length == parentGridConstraints.getColSpan()) {
                int col = parentGridConstraints.getColumn();
                this.myXs[0] = insets.left + this.myMargin.left;
                this.myWidths[0] = parentGridLayout.myWidths[col] - this.myXs[0];
                for (i = 1; i < this.myColumnStretches.length; ++i) {
                    this.myXs[i] = parentGridLayout.myXs[i + col] - parentGridLayout.myXs[col];
                    this.myWidths[i] = parentGridLayout.myWidths[i + col];
                }
                int n = this.myColumnStretches.length - 1;
                this.myWidths[n] = this.myWidths[n] - (insets.right + this.myMargin.right);
                skipLayout |= 2;
            }
        }
        return skipLayout;
    }

    @Override
    public void invalidateLayout(Container container) {
        this.myLayoutState = null;
        this.myHorizontalInfo = null;
        this.myVerticalInfo = null;
    }

    void validateInfos(Container container) {
        if (this.myLayoutState == null) {
            this.myLayoutState = new LayoutState(this, GridLayoutManager.getDesignTimeInsets(container) == 0);
            this.myHorizontalInfo = new HorizontalInfo(this.myLayoutState, GridLayoutManager.getHGapImpl(container));
            this.myVerticalInfo = new VerticalInfo(this.myLayoutState, GridLayoutManager.getVGapImpl(container));
        }
    }

    public int[] getXs() {
        return this.myXs;
    }

    public int[] getWidths() {
        return this.myWidths;
    }

    public int[] getYs() {
        return this.myYs;
    }

    public int[] getHeights() {
        return this.myHeights;
    }

    public int[] getCoords(boolean isRow) {
        return isRow ? this.myYs : this.myXs;
    }

    public int[] getSizes(boolean isRow) {
        return isRow ? this.myHeights : this.myWidths;
    }

    private int[] getMinSizes(DimensionInfo info) {
        return this.getMinOrPrefSizes(info, true);
    }

    private int[] getPrefSizes(DimensionInfo info) {
        return this.getMinOrPrefSizes(info, false);
    }

    private int[] getMinOrPrefSizes(DimensionInfo info, boolean min) {
        int[] widths = new int[info.getCellCount()];
        Arrays.fill(widths, this.myMinCellSize);
        for (int i = info.getComponentCount() - 1; i >= 0; --i) {
            if (info.getSpan(i) != 1) continue;
            int size = min ? GridLayoutManager.getMin2(info, i) : Math.max(info.getMinimumWidth(i), info.getPreferredWidth(i));
            int cell = info.getCell(i);
            int gap = GridLayoutManager.countGap(info, cell, info.getSpan(i));
            size = Math.max(size - gap, 0);
            widths[cell] = Math.max(widths[cell], size);
        }
        GridLayoutManager.updateSizesFromChildren(info, min, widths);
        boolean[] toProcess = new boolean[info.getCellCount()];
        for (int i = info.getComponentCount() - 1; i >= 0; --i) {
            int size = min ? GridLayoutManager.getMin2(info, i) : Math.max(info.getMinimumWidth(i), info.getPreferredWidth(i));
            int span = info.getSpan(i);
            int cell = info.getCell(i);
            int gap = GridLayoutManager.countGap(info, cell, span);
            size = Math.max(size - gap, 0);
            Arrays.fill(toProcess, false);
            int curSize = 0;
            for (int j = 0; j < span; ++j) {
                curSize += widths[j + cell];
                toProcess[j + cell] = true;
            }
            if (curSize >= size) continue;
            boolean[] higherPriorityCells = new boolean[toProcess.length];
            this.getCellsWithHigherPriorities(info, toProcess, higherPriorityCells, false, widths);
            GridLayoutManager.distribute(higherPriorityCells, info, size - curSize, widths);
        }
        return widths;
    }

    private static void updateSizesFromChildren(DimensionInfo info, boolean min, int[] widths) {
        for (int i = info.getComponentCount() - 1; i >= 0; --i) {
            Container childContainer;
            Component child = info.getComponent(i);
            GridConstraints c = info.getConstraints(i);
            if (!c.isUseParentLayout() || !(child instanceof Container)) continue;
            Container container = (Container)child;
            if (container.getLayout() instanceof GridLayoutManager) {
                GridLayoutManager.updateSizesFromChild(info, min, widths, container, i);
                continue;
            }
            if (container.getComponentCount() != 1 || !(container.getComponent(0) instanceof Container) || !((childContainer = (Container)container.getComponent(0)).getLayout() instanceof GridLayoutManager)) continue;
            GridLayoutManager.updateSizesFromChild(info, min, widths, childContainer, i);
        }
    }

    private static void updateSizesFromChild(DimensionInfo info, boolean min, int[] widths, Container container, int childIndex) {
        GridLayoutManager childLayout = (GridLayoutManager)container.getLayout();
        if (info.getSpan(childIndex) == info.getChildLayoutCellCount(childLayout)) {
            childLayout.validateInfos(container);
            DimensionInfo childInfo = info instanceof HorizontalInfo ? childLayout.myHorizontalInfo : childLayout.myVerticalInfo;
            int[] sizes = childLayout.getMinOrPrefSizes(childInfo, min);
            int cell = info.getCell(childIndex);
            for (int j = 0; j < sizes.length; ++j) {
                widths[cell + j] = Math.max(widths[cell + j], sizes[j]);
            }
        }
    }

    private static int getMin2(DimensionInfo info, int componentIndex) {
        int s = (info.getSizePolicy(componentIndex) & 1) != 0 ? info.getMinimumWidth(componentIndex) : Math.max(info.getMinimumWidth(componentIndex), info.getPreferredWidth(componentIndex));
        return s;
    }

    private void new_doIt(int[] widths, int cell, int span, int minWidth, DimensionInfo info, boolean checkPrefs) {
        int toDistribute = minWidth;
        for (int i = cell; i < cell + span; ++i) {
            toDistribute -= widths[i];
        }
        if (toDistribute <= 0) {
            return;
        }
        boolean[] allowedCells = new boolean[info.getCellCount()];
        for (int i = cell; i < cell + span; ++i) {
            allowedCells[i] = true;
        }
        boolean[] higherPriorityCells = new boolean[info.getCellCount()];
        this.getCellsWithHigherPriorities(info, allowedCells, higherPriorityCells, checkPrefs, widths);
        GridLayoutManager.distribute(higherPriorityCells, info, toDistribute, widths);
    }

    private static void distribute(boolean[] higherPriorityCells, DimensionInfo info, int toDistribute, int[] widths) {
        int i;
        int stretches = 0;
        for (i = 0; i < info.getCellCount(); ++i) {
            if (!higherPriorityCells[i]) continue;
            stretches += info.getStretch(i);
        }
        int toDistributeFrozen = toDistribute;
        for (int i2 = 0; i2 < info.getCellCount(); ++i2) {
            if (!higherPriorityCells[i2]) continue;
            int addon = toDistributeFrozen * info.getStretch(i2) / stretches;
            int n = i2;
            widths[n] = widths[n] + addon;
            toDistribute -= addon;
        }
        if (toDistribute != 0) {
            for (i = 0; i < info.getCellCount(); ++i) {
                if (!higherPriorityCells[i]) continue;
                int n = i;
                widths[n] = widths[n] + 1;
                if (--toDistribute == 0) break;
            }
        }
        if (toDistribute != 0) {
            throw new IllegalStateException("toDistribute = " + toDistribute);
        }
    }

    private void getCellsWithHigherPriorities(DimensionInfo info, boolean[] allowedCells, boolean[] higherPriorityCells, boolean checkPrefs, int[] widths) {
        int cell;
        Arrays.fill(higherPriorityCells, false);
        int foundCells = 0;
        if (checkPrefs) {
            int[] prefs = this.getMinOrPrefSizes(info, false);
            for (int cell2 = 0; cell2 < allowedCells.length; ++cell2) {
                if (!allowedCells[cell2] || GridLayoutManager.isCellEmpty(info, cell2) || prefs[cell2] <= widths[cell2]) continue;
                higherPriorityCells[cell2] = true;
                ++foundCells;
            }
            if (foundCells > 0) {
                return;
            }
        }
        for (cell = 0; cell < allowedCells.length; ++cell) {
            if (!allowedCells[cell] || (info.getCellSizePolicy(cell) & 4) == 0) continue;
            higherPriorityCells[cell] = true;
            ++foundCells;
        }
        if (foundCells > 0) {
            return;
        }
        for (cell = 0; cell < allowedCells.length; ++cell) {
            if (!allowedCells[cell] || (info.getCellSizePolicy(cell) & 2) == 0) continue;
            higherPriorityCells[cell] = true;
            ++foundCells;
        }
        if (foundCells > 0) {
            return;
        }
        for (cell = 0; cell < allowedCells.length; ++cell) {
            if (!allowedCells[cell] || GridLayoutManager.isCellEmpty(info, cell)) continue;
            higherPriorityCells[cell] = true;
            ++foundCells;
        }
        if (foundCells > 0) {
            return;
        }
        for (cell = 0; cell < allowedCells.length; ++cell) {
            if (!allowedCells[cell]) continue;
            higherPriorityCells[cell] = true;
        }
    }

    public boolean isSameSizeHorizontally() {
        return this.mySameSizeHorizontally;
    }

    public boolean isSameSizeVertically() {
        return this.mySameSizeVertically;
    }

    public void setSameSizeHorizontally(boolean sameSizeHorizontally) {
        this.mySameSizeHorizontally = sameSizeHorizontally;
    }

    public void setSameSizeVertically(boolean sameSizeVertically) {
        this.mySameSizeVertically = sameSizeVertically;
    }

    public int[] getHorizontalGridLines() {
        return GridLayoutManager.getGridLines(this.myYs, this.myHeights);
    }

    public int[] getVerticalGridLines() {
        return GridLayoutManager.getGridLines(this.myXs, this.myWidths);
    }

    private static int[] getGridLines(int[] pos, int[] heights) {
        int[] result = new int[pos.length + 1];
        result[0] = pos[0];
        for (int i = 0; i < pos.length - 1; ++i) {
            result[i + 1] = (pos[i] + heights[i] + pos[i + 1]) / 2;
        }
        result[pos.length] = pos[pos.length - 1] + heights[pos.length - 1];
        return result;
    }

    public int getCellCount(boolean isRow) {
        return isRow ? this.getRowCount() : this.getColumnCount();
    }

    public int getCellSizePolicy(boolean isRow, int cellIndex) {
        DimensionInfo info;
        DimensionInfo dimensionInfo = info = isRow ? this.myVerticalInfo : this.myHorizontalInfo;
        if (info == null) {
            return 0;
        }
        return info.getCellSizePolicy(cellIndex);
    }
}

