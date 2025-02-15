/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.debug;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JLabel;

public final class FormDebugUtils {
    private FormDebugUtils() {
    }

    public static void dumpAll(Container container) {
        if (!(container.getLayout() instanceof FormLayout)) {
            System.out.println("The container's layout is not a FormLayout.");
            return;
        }
        FormLayout layout = (FormLayout)container.getLayout();
        FormDebugUtils.dumpColumnSpecs(layout);
        FormDebugUtils.dumpRowSpecs(layout);
        System.out.println();
        FormDebugUtils.dumpColumnGroups(layout);
        FormDebugUtils.dumpRowGroups(layout);
        System.out.println();
        FormDebugUtils.dumpConstraints(container);
        FormDebugUtils.dumpGridBounds(container);
    }

    public static void dumpColumnSpecs(FormLayout layout) {
        System.out.print("COLUMN SPECS:");
        for (int col = 1; col <= layout.getColumnCount(); ++col) {
            ColumnSpec colSpec = layout.getColumnSpec(col);
            System.out.print(colSpec.toShortString());
            if (col >= layout.getColumnCount()) continue;
            System.out.print(", ");
        }
        System.out.println();
    }

    public static void dumpRowSpecs(FormLayout layout) {
        System.out.print("ROW SPECS:   ");
        for (int row = 1; row <= layout.getRowCount(); ++row) {
            RowSpec rowSpec = layout.getRowSpec(row);
            System.out.print(rowSpec.toShortString());
            if (row >= layout.getRowCount()) continue;
            System.out.print(", ");
        }
        System.out.println();
    }

    public static void dumpColumnGroups(FormLayout layout) {
        FormDebugUtils.dumpGroups("COLUMN GROUPS: ", layout.getColumnGroups());
    }

    public static void dumpRowGroups(FormLayout layout) {
        FormDebugUtils.dumpGroups("ROW GROUPS:    ", layout.getRowGroups());
    }

    public static void dumpGridBounds(Container container) {
        System.out.println("GRID BOUNDS");
        FormDebugUtils.dumpGridBounds(FormDebugUtils.getLayoutInfo(container));
    }

    public static void dumpGridBounds(FormLayout.LayoutInfo layoutInfo) {
        System.out.print("COLUMN ORIGINS: ");
        for (int columnOrigin : layoutInfo.columnOrigins) {
            System.out.print(columnOrigin + " ");
        }
        System.out.println();
        System.out.print("ROW ORIGINS:    ");
        for (int rowOrigin : layoutInfo.rowOrigins) {
            System.out.print(rowOrigin + " ");
        }
        System.out.println();
    }

    public static void dumpConstraints(Container container) {
        System.out.println("COMPONENT CONSTRAINTS");
        if (!(container.getLayout() instanceof FormLayout)) {
            System.out.println("The container's layout is not a FormLayout.");
            return;
        }
        FormLayout layout = (FormLayout)container.getLayout();
        int childCount = container.getComponentCount();
        for (int i = 0; i < childCount; ++i) {
            Component child = container.getComponent(i);
            CellConstraints cc = layout.getConstraints(child);
            String ccString = cc == null ? "no constraints" : cc.toShortString(layout);
            System.out.print(ccString);
            System.out.print("; ");
            String childType = child.getClass().getName();
            System.out.print(childType);
            if (child instanceof JLabel) {
                JLabel label = (JLabel)child;
                System.out.print("      \"" + label.getText() + "\"");
            }
            if (child.getName() != null) {
                System.out.print("; name=");
                System.out.print(child.getName());
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void dumpGroups(String title, int[][] allGroups) {
        System.out.print(title + " {");
        for (int group = 0; group < allGroups.length; ++group) {
            int[] groupIndices = allGroups[group];
            System.out.print(" {");
            for (int i = 0; i < groupIndices.length; ++i) {
                System.out.print(groupIndices[i]);
                if (i >= groupIndices.length - 1) continue;
                System.out.print(", ");
            }
            System.out.print("} ");
            if (group >= allGroups.length - 1) continue;
            System.out.print(", ");
        }
        System.out.println("}");
    }

    public static FormLayout.LayoutInfo getLayoutInfo(Container container) {
        Preconditions.checkNotNull(container, "The container must not be null.");
        Preconditions.checkArgument(container.getLayout() instanceof FormLayout, "The container must use an instance of FormLayout.");
        FormLayout layout = (FormLayout)container.getLayout();
        return layout.getLayoutInfo(container);
    }
}

