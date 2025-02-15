/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.LayoutState;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.uiDesigner.core.Util;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class DimensionInfo {
    private final int[] myCell;
    private final int[] mySpan;
    protected final LayoutState myLayoutState;
    private final int[] myStretches;
    private final int[] mySpansAfterElimination;
    private final int[] myCellSizePolicies;
    private final int myGap;

    public DimensionInfo(LayoutState layoutState, int gap) {
        if (layoutState == null) {
            throw new IllegalArgumentException("layoutState cannot be null");
        }
        if (gap < 0) {
            throw new IllegalArgumentException("invalid gap: " + gap);
        }
        this.myLayoutState = layoutState;
        this.myGap = gap;
        this.myCell = new int[layoutState.getComponentCount()];
        this.mySpan = new int[layoutState.getComponentCount()];
        for (int i = 0; i < layoutState.getComponentCount(); ++i) {
            GridConstraints c = layoutState.getConstraints(i);
            this.myCell[i] = this.getOriginalCell(c);
            this.mySpan[i] = this.getOriginalSpan(c);
        }
        this.myStretches = new int[this.getCellCount()];
        Arrays.fill(this.myStretches, 1);
        ArrayList<Integer> eliminated = new ArrayList<Integer>();
        this.mySpansAfterElimination = (int[])this.mySpan.clone();
        Util.eliminate((int[])this.myCell.clone(), this.mySpansAfterElimination, eliminated);
        this.myCellSizePolicies = new int[this.getCellCount()];
        for (int i = 0; i < this.myCellSizePolicies.length; ++i) {
            this.myCellSizePolicies[i] = this.getCellSizePolicyImpl(i, eliminated);
        }
    }

    public final int getComponentCount() {
        return this.myLayoutState.getComponentCount();
    }

    public final Component getComponent(int componentIndex) {
        return this.myLayoutState.getComponent(componentIndex);
    }

    public final GridConstraints getConstraints(int componentIndex) {
        return this.myLayoutState.getConstraints(componentIndex);
    }

    public abstract int getCellCount();

    public abstract int getPreferredWidth(int var1);

    public abstract int getMinimumWidth(int var1);

    public abstract DimensionInfo getDimensionInfo(GridLayoutManager var1);

    public final int getCell(int componentIndex) {
        return this.myCell[componentIndex];
    }

    public final int getSpan(int componentIndex) {
        return this.mySpan[componentIndex];
    }

    public final int getStretch(int cellIndex) {
        return this.myStretches[cellIndex];
    }

    protected abstract int getOriginalCell(GridConstraints var1);

    protected abstract int getOriginalSpan(GridConstraints var1);

    abstract int getSizePolicy(int var1);

    abstract int getChildLayoutCellCount(GridLayoutManager var1);

    public final int getGap() {
        return this.myGap;
    }

    public boolean componentBelongsCell(int componentIndex, int cellIndex) {
        int componentStartCell = this.getCell(componentIndex);
        int span = this.getSpan(componentIndex);
        return componentStartCell <= cellIndex && cellIndex < componentStartCell + span;
    }

    public final int getCellSizePolicy(int cellIndex) {
        return this.myCellSizePolicies[cellIndex];
    }

    private int getCellSizePolicyImpl(int cellIndex, ArrayList<Integer> eliminatedCells) {
        int policyFromChild = this.getCellSizePolicyFromInheriting(cellIndex);
        if (policyFromChild != -1) {
            return policyFromChild;
        }
        for (int i = eliminatedCells.size() - 1; i >= 0; --i) {
            if (cellIndex != eliminatedCells.get(i)) continue;
            return 1;
        }
        return this.calcCellSizePolicy(cellIndex);
    }

    private int calcCellSizePolicy(int cellIndex) {
        boolean canShrink = true;
        boolean canGrow = false;
        boolean wantGrow = false;
        boolean weakCanGrow = true;
        boolean weakWantGrow = true;
        int countOfBelongingComponents = 0;
        for (int i = 0; i < this.getComponentCount(); ++i) {
            boolean thisWantGrow;
            if (!this.componentBelongsCell(i, cellIndex)) continue;
            ++countOfBelongingComponents;
            int p = this.getSizePolicy(i);
            boolean thisCanShrink = (p & 1) != 0;
            boolean thisCanGrow = (p & 2) != 0;
            boolean bl = thisWantGrow = (p & 4) != 0;
            if (this.getCell(i) == cellIndex && this.mySpansAfterElimination[i] == 1) {
                canShrink &= thisCanShrink;
                canGrow |= thisCanGrow;
                wantGrow |= thisWantGrow;
            }
            if (!thisCanGrow) {
                weakCanGrow = false;
            }
            if (thisWantGrow) continue;
            weakWantGrow = false;
        }
        return (canShrink ? 1 : 0) | (canGrow || countOfBelongingComponents > 0 && weakCanGrow ? 2 : 0) | (wantGrow || countOfBelongingComponents > 0 && weakWantGrow ? 4 : 0);
    }

    private int getCellSizePolicyFromInheriting(int cellIndex) {
        int nonInheritingComponentsInCell = 0;
        int policyFromInheriting = -1;
        for (int i = this.getComponentCount() - 1; i >= 0; --i) {
            GridConstraints c;
            if (!this.componentBelongsCell(i, cellIndex)) continue;
            Component child = this.getComponent(i);
            Container container = DimensionInfo.findAlignedChild(child, c = this.getConstraints(i));
            if (container != null) {
                GridLayoutManager grid = (GridLayoutManager)container.getLayout();
                grid.validateInfos(container);
                DimensionInfo info = this.getDimensionInfo(grid);
                int policy = info.calcCellSizePolicy(cellIndex - this.getOriginalCell(c));
                if (policyFromInheriting == -1) {
                    policyFromInheriting = policy;
                    continue;
                }
                policyFromInheriting |= policy;
                continue;
            }
            if (this.getOriginalCell(c) != cellIndex || this.getOriginalSpan(c) != 1 || child instanceof Spacer) continue;
            ++nonInheritingComponentsInCell;
        }
        if (nonInheritingComponentsInCell > 0) {
            return -1;
        }
        return policyFromInheriting;
    }

    public static Container findAlignedChild(Component child, GridConstraints c) {
        if (c.isUseParentLayout() && child instanceof Container) {
            Container childContainer;
            Container container = (Container)child;
            if (container.getLayout() instanceof GridLayoutManager) {
                return container;
            }
            if (container.getComponentCount() == 1 && container.getComponent(0) instanceof Container && (childContainer = (Container)container.getComponent(0)).getLayout() instanceof GridLayoutManager) {
                return childContainer;
            }
        }
        return null;
    }

    protected final Dimension getPreferredSize(int componentIndex) {
        Dimension size = this.myLayoutState.myPreferredSizes[componentIndex];
        if (size == null) {
            this.myLayoutState.myPreferredSizes[componentIndex] = size = Util.getPreferredSize(this.myLayoutState.getComponent(componentIndex), this.myLayoutState.getConstraints(componentIndex), true);
        }
        return size;
    }

    protected final Dimension getMinimumSize(int componentIndex) {
        Dimension size = this.myLayoutState.myMinimumSizes[componentIndex];
        if (size == null) {
            this.myLayoutState.myMinimumSizes[componentIndex] = size = Util.getMinimumSize(this.myLayoutState.getComponent(componentIndex), this.myLayoutState.getConstraints(componentIndex), true);
        }
        return size;
    }
}

