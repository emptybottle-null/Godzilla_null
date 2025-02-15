/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

public final class LayoutState {
    private final Component[] myComponents;
    private final GridConstraints[] myConstraints;
    private final int myColumnCount;
    private final int myRowCount;
    final Dimension[] myPreferredSizes;
    final Dimension[] myMinimumSizes;

    public LayoutState(GridLayoutManager layout, boolean ignoreInvisibleComponents) {
        ArrayList<Component> componentsList = new ArrayList<Component>(layout.getComponentCount());
        ArrayList<GridConstraints> constraintsList = new ArrayList<GridConstraints>(layout.getComponentCount());
        for (int i = 0; i < layout.getComponentCount(); ++i) {
            Component component = layout.getComponent(i);
            if (ignoreInvisibleComponents && !component.isVisible()) continue;
            componentsList.add(component);
            GridConstraints constraints = layout.getConstraints(i);
            constraintsList.add(constraints);
        }
        this.myComponents = componentsList.toArray(new Component[0]);
        this.myConstraints = constraintsList.toArray(GridConstraints.EMPTY_ARRAY);
        this.myMinimumSizes = new Dimension[this.myComponents.length];
        this.myPreferredSizes = new Dimension[this.myComponents.length];
        this.myColumnCount = layout.getColumnCount();
        this.myRowCount = layout.getRowCount();
    }

    public int getComponentCount() {
        return this.myComponents.length;
    }

    public Component getComponent(int index) {
        return this.myComponents[index];
    }

    public GridConstraints getConstraints(int index) {
        return this.myConstraints[index];
    }

    public int getColumnCount() {
        return this.myColumnCount;
    }

    public int getRowCount() {
        return this.myRowCount;
    }
}

