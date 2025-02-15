/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.DimensionInfo;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.LayoutState;

final class HorizontalInfo
extends DimensionInfo {
    HorizontalInfo(LayoutState layoutState, int gap) {
        super(layoutState, gap);
    }

    @Override
    protected int getOriginalCell(GridConstraints constraints) {
        return constraints.getColumn();
    }

    @Override
    protected int getOriginalSpan(GridConstraints constraints) {
        return constraints.getColSpan();
    }

    @Override
    int getSizePolicy(int componentIndex) {
        return this.myLayoutState.getConstraints(componentIndex).getHSizePolicy();
    }

    @Override
    int getChildLayoutCellCount(GridLayoutManager childLayout) {
        return childLayout.getColumnCount();
    }

    @Override
    public int getMinimumWidth(int componentIndex) {
        return this.getMinimumSize((int)componentIndex).width;
    }

    @Override
    public DimensionInfo getDimensionInfo(GridLayoutManager grid) {
        return grid.myHorizontalInfo;
    }

    @Override
    public int getCellCount() {
        return this.myLayoutState.getColumnCount();
    }

    @Override
    public int getPreferredWidth(int componentIndex) {
        return this.getPreferredSize((int)componentIndex).width;
    }
}

