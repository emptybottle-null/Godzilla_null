/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.core;

import com.intellij.uiDesigner.core.GridConstraints;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

public final class Util {
    private static final Dimension MAX_SIZE = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    public static final int DEFAULT_INDENT = 10;

    public static Dimension getMinimumSize(Component component, GridConstraints constraints, boolean addIndent) {
        try {
            Dimension size = Util.getSize(constraints.myMinimumSize, component.getMinimumSize());
            if (addIndent) {
                size.width += 10 * constraints.getIndent();
            }
            return size;
        } catch (NullPointerException npe) {
            return new Dimension(0, 0);
        }
    }

    public static Dimension getMaximumSize(GridConstraints constraints, boolean addIndent) {
        try {
            Dimension size = Util.getSize(constraints.myMaximumSize, MAX_SIZE);
            if (addIndent && size.width < Util.MAX_SIZE.width) {
                size.width += 10 * constraints.getIndent();
            }
            return size;
        } catch (NullPointerException e) {
            return new Dimension(0, 0);
        }
    }

    public static Dimension getPreferredSize(Component component, GridConstraints constraints, boolean addIndent) {
        try {
            Dimension size = Util.getSize(constraints.myPreferredSize, component.getPreferredSize());
            if (addIndent) {
                size.width += 10 * constraints.getIndent();
            }
            return size;
        } catch (NullPointerException e) {
            return new Dimension(0, 0);
        }
    }

    private static Dimension getSize(Dimension overridenSize, Dimension ownSize) {
        int overridenWidth = overridenSize.width >= 0 ? overridenSize.width : ownSize.width;
        int overridenHeight = overridenSize.height >= 0 ? overridenSize.height : ownSize.height;
        return new Dimension(overridenWidth, overridenHeight);
    }

    public static void adjustSize(Component component, GridConstraints constraints, Dimension size) {
        Dimension minimumSize = Util.getMinimumSize(component, constraints, false);
        Dimension maximumSize = Util.getMaximumSize(constraints, false);
        size.width = Math.max(size.width, minimumSize.width);
        size.height = Math.max(size.height, minimumSize.height);
        size.width = Math.min(size.width, maximumSize.width);
        size.height = Math.min(size.height, maximumSize.height);
    }

    public static int eliminate(int[] cellIndices, int[] spans, ArrayList eliminated) {
        int size = cellIndices.length;
        if (size != spans.length) {
            throw new IllegalArgumentException("size mismatch: " + size + ", " + spans.length);
        }
        if (eliminated != null && eliminated.size() != 0) {
            throw new IllegalArgumentException("eliminated must be empty");
        }
        int cellCount = 0;
        for (int i = 0; i < size; ++i) {
            cellCount = Math.max(cellCount, cellIndices[i] + spans[i]);
        }
        for (int cell = cellCount - 1; cell >= 0; --cell) {
            int i;
            boolean starts = false;
            boolean ends = false;
            for (i = 0; i < size; ++i) {
                if (cellIndices[i] == cell) {
                    starts = true;
                }
                if (cellIndices[i] + spans[i] - 1 != cell) continue;
                ends = true;
            }
            if (starts && ends) continue;
            if (eliminated != null) {
                eliminated.add(new Integer(cell));
            }
            for (i = 0; i < size; ++i) {
                boolean decreaseIndex;
                boolean decreaseSpan = cellIndices[i] <= cell && cell < cellIndices[i] + spans[i];
                boolean bl = decreaseIndex = cellIndices[i] > cell;
                if (decreaseSpan) {
                    int n = i;
                    spans[n] = spans[n] - 1;
                }
                if (!decreaseIndex) continue;
                int n = i;
                cellIndices[n] = cellIndices[n] - 1;
            }
            --cellCount;
        }
        return cellCount;
    }
}

