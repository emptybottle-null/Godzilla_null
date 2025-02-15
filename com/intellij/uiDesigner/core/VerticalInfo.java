/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.DimensionInfo;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.LayoutState;

final class VerticalInfo
extends DimensionInfo {
    VerticalInfo(LayoutState layoutState, int gap) {
        super(layoutState, gap);
    }

    @Override
    protected int getOriginalCell(GridConstraints constraints) {
        return constraints.getRow();
    }

    @Override
    protected int getOriginalSpan(GridConstraints constraints) {
        return constraints.getRowSpan();
    }

    @Override
    int getSizePolicy(int componentIndex) {
        return this.myLayoutState.getConstraints(componentIndex).getVSizePolicy();
    }

    @Override
    int getChildLayoutCellCount(GridLayoutManager childLayout) {
        return childLayout.getRowCount();
    }

    @Override
    public int getMinimumWidth(int componentIndex) {
        return this.getMinimumSize((int)componentIndex).height;
    }

    @Override
    public DimensionInfo getDimensionInfo(GridLayoutManager grid) {
        return grid.myVerticalInfo;
    }

    @Override
    public int getCellCount() {
        return this.myLayoutState.getRowCount();
    }

    @Override
    public int getPreferredWidth(int componentIndex) {
        return this.getPreferredSize((int)componentIndex).height;
    }
}

