/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.core.AbstractLayout;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.lw.IComponent;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.LwHSpacer;
import com.intellij.uiDesigner.lw.LwVSpacer;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JComponent;

public class GridBagConverter {
    private Insets myInsets;
    private int myHGap;
    private int myVGap;
    private boolean mySameSizeHorz;
    private boolean mySameSizeVert;
    private ArrayList myComponents = new ArrayList();
    private ArrayList myConstraints = new ArrayList();
    private int myLastRow = -1;
    private int myLastCol = -1;

    public GridBagConverter() {
        this.myInsets = new Insets(0, 0, 0, 0);
    }

    public GridBagConverter(Insets insets, int hgap, int vgap, boolean sameSizeHorz, boolean sameSizeVert) {
        this.myInsets = insets;
        this.myHGap = hgap;
        this.myVGap = vgap;
        this.mySameSizeHorz = sameSizeHorz;
        this.mySameSizeVert = sameSizeVert;
    }

    public void addComponent(JComponent component, GridConstraints constraints) {
        this.myComponents.add(component);
        this.myConstraints.add(constraints);
    }

    public static void prepareConstraints(LwContainer container, Map idToConstraintsMap) {
        GridLayoutManager gridLayout = (GridLayoutManager)container.getLayout();
        GridBagConverter converter = new GridBagConverter(gridLayout.getMargin(), GridBagConverter.getGap(container, true), GridBagConverter.getGap(container, false), gridLayout.isSameSizeHorizontally(), gridLayout.isSameSizeVertically());
        for (int i = 0; i < container.getComponentCount(); ++i) {
            LwComponent component = (LwComponent)container.getComponent(i);
            if (component instanceof LwHSpacer || component instanceof LwVSpacer) {
                GridConstraints constraints = component.getConstraints().store();
                constraints.setHSizePolicy(constraints.getHSizePolicy() & 0xFFFFFFFB);
                constraints.setVSizePolicy(constraints.getVSizePolicy() & 0xFFFFFFFB);
                converter.addComponent(null, constraints);
                continue;
            }
            converter.addComponent(null, component.getConstraints());
        }
        Result[] results = converter.convert();
        int componentIndex = 0;
        for (int i = 0; i < results.length; ++i) {
            if (results[i].isFillerPanel) continue;
            LwComponent component = (LwComponent)container.getComponent(componentIndex++);
            idToConstraintsMap.put(component.getId(), results[i]);
        }
    }

    private static int getGap(LwContainer container, boolean horizontal) {
        while (container != null) {
            LayoutManager layout = container.getLayout();
            if (layout instanceof AbstractLayout) {
                int gap;
                AbstractLayout aLayout = (AbstractLayout)layout;
                int n = gap = horizontal ? aLayout.getHGap() : aLayout.getVGap();
                if (gap >= 0) {
                    return gap;
                }
            }
            container = container.getParent();
        }
        return horizontal ? 10 : 5;
    }

    public Result[] convert() {
        ArrayList<Result> results = new ArrayList<Result>();
        for (int i = 0; i < this.myComponents.size(); ++i) {
            results.add(this.convert((JComponent)this.myComponents.get(i), (GridConstraints)this.myConstraints.get(i)));
        }
        Result[] resultArray = results.toArray(new Result[results.size()]);
        if (this.myHGap > 0 || this.myVGap > 0) {
            this.applyGaps(resultArray);
        }
        if (this.mySameSizeHorz) {
            GridBagConverter.makeSameSizes(resultArray, true);
        }
        if (this.mySameSizeVert) {
            GridBagConverter.makeSameSizes(resultArray, false);
        }
        return resultArray;
    }

    private void applyGaps(Result[] resultArray) {
        int leftGap = this.myHGap / 2;
        int rightGap = this.myHGap - this.myHGap / 2;
        int topGap = this.myVGap / 2;
        int bottomGap = this.myVGap - this.myVGap / 2;
        for (int i = 0; i < resultArray.length; ++i) {
            Result result = resultArray[i];
            if (result.constraints.gridx > 0) {
                result.constraints.insets.left += leftGap;
            }
            if (result.constraints.gridx + result.constraints.gridwidth - 1 < this.myLastCol) {
                result.constraints.insets.right += rightGap;
            }
            if (result.constraints.gridy > 0) {
                result.constraints.insets.top += topGap;
            }
            if (result.constraints.gridy + result.constraints.gridheight - 1 >= this.myLastRow) continue;
            result.constraints.insets.bottom += bottomGap;
        }
    }

    private static void makeSameSizes(Result[] resultArray, boolean horizontal) {
        Result result;
        int i;
        int minimum = -1;
        int preferred = -1;
        for (i = 0; i < resultArray.length; ++i) {
            Dimension prefSize;
            result = resultArray[i];
            Dimension minSize = result.minimumSize != null || result.component == null ? result.minimumSize : result.component.getMinimumSize();
            Dimension dimension = prefSize = result.preferredSize != null || result.component == null ? result.preferredSize : result.component.getPreferredSize();
            if (minSize != null) {
                minimum = Math.max(minimum, horizontal ? minSize.width : minSize.height);
            }
            if (prefSize == null) continue;
            preferred = Math.max(preferred, horizontal ? prefSize.width : prefSize.height);
        }
        if (minimum >= 0 || preferred >= 0) {
            for (i = 0; i < resultArray.length; ++i) {
                result = resultArray[i];
                if ((result.minimumSize != null || result.component != null) && minimum >= 0) {
                    if (result.minimumSize == null) {
                        result.minimumSize = result.component.getMinimumSize();
                    }
                    if (horizontal) {
                        result.minimumSize.width = minimum;
                    } else {
                        result.minimumSize.height = minimum;
                    }
                }
                if (result.preferredSize == null && result.component == null || preferred < 0) continue;
                if (result.preferredSize == null) {
                    result.preferredSize = result.component.getPreferredSize();
                }
                if (horizontal) {
                    result.preferredSize.width = preferred;
                    continue;
                }
                result.preferredSize.height = preferred;
            }
        }
    }

    private Result convert(JComponent component, GridConstraints constraints) {
        Result result = new Result(component);
        int endRow = constraints.getRow() + constraints.getRowSpan() - 1;
        this.myLastRow = Math.max(this.myLastRow, endRow);
        int endCol = constraints.getColumn() + constraints.getColSpan() - 1;
        this.myLastCol = Math.max(this.myLastCol, endCol);
        int indent = 10 * constraints.getIndent();
        GridBagConverter.constraintsToGridBag(constraints, result.constraints);
        result.constraints.weightx = this.getWeight(constraints, true);
        result.constraints.weighty = this.getWeight(constraints, false);
        result.constraints.insets = new Insets(this.myInsets.top, this.myInsets.left + indent, this.myInsets.bottom, this.myInsets.right);
        Dimension minSize = constraints.myMinimumSize;
        if (component != null && minSize.width <= 0 && minSize.height <= 0) {
            minSize = component.getMinimumSize();
        }
        if ((constraints.getHSizePolicy() & 1) == 0) {
            int n = minSize.width = constraints.myPreferredSize.width > 0 || component == null ? constraints.myPreferredSize.width : component.getPreferredSize().width;
        }
        if ((constraints.getVSizePolicy() & 1) == 0) {
            int n = minSize.height = constraints.myPreferredSize.height > 0 || component == null ? constraints.myPreferredSize.height : component.getPreferredSize().height;
        }
        if (minSize.width != -1 || minSize.height != -1) {
            result.minimumSize = minSize;
        }
        if (constraints.myPreferredSize.width > 0 && constraints.myPreferredSize.height > 0) {
            result.preferredSize = constraints.myPreferredSize;
        }
        if (constraints.myMaximumSize.width > 0 && constraints.myMaximumSize.height > 0) {
            result.maximumSize = constraints.myMaximumSize;
        }
        return result;
    }

    public static GridBagConstraints getGridBagConstraints(IComponent component) {
        GridBagConstraints gbc = component.getCustomLayoutConstraints() instanceof GridBagConstraints ? (GridBagConstraints)component.getCustomLayoutConstraints() : new GridBagConstraints();
        GridBagConverter.constraintsToGridBag(component.getConstraints(), gbc);
        return gbc;
    }

    public static void constraintsToGridBag(GridConstraints constraints, GridBagConstraints result) {
        result.gridx = constraints.getColumn();
        result.gridy = constraints.getRow();
        result.gridwidth = constraints.getColSpan();
        result.gridheight = constraints.getRowSpan();
        switch (constraints.getFill()) {
            case 1: {
                result.fill = 2;
                break;
            }
            case 2: {
                result.fill = 3;
                break;
            }
            case 3: {
                result.fill = 1;
                break;
            }
            default: {
                result.fill = 0;
            }
        }
        switch (constraints.getAnchor()) {
            case 9: {
                result.anchor = 18;
                break;
            }
            case 1: {
                result.anchor = 11;
                break;
            }
            case 5: {
                result.anchor = 12;
                break;
            }
            case 4: {
                result.anchor = 13;
                break;
            }
            case 6: {
                result.anchor = 14;
                break;
            }
            case 2: {
                result.anchor = 15;
                break;
            }
            case 10: {
                result.anchor = 16;
                break;
            }
            case 8: {
                result.anchor = 17;
            }
        }
    }

    private double getWeight(GridConstraints constraints, boolean horizontal) {
        int policy;
        int n = policy = horizontal ? constraints.getHSizePolicy() : constraints.getVSizePolicy();
        if ((policy & 4) != 0) {
            return 1.0;
        }
        boolean canGrow = (policy & 2) != 0;
        Iterator iterator = this.myConstraints.iterator();
        while (iterator.hasNext()) {
            int otherPolicy;
            GridConstraints otherConstraints = (GridConstraints)iterator.next();
            if (this.constraintsIntersect(horizontal, constraints, otherConstraints)) continue;
            int n2 = otherPolicy = horizontal ? otherConstraints.getHSizePolicy() : otherConstraints.getVSizePolicy();
            if ((otherPolicy & 4) != 0) {
                return 0.0;
            }
            if (canGrow || (otherPolicy & 2) == 0) continue;
            return 0.0;
        }
        return 1.0;
    }

    private boolean constraintsIntersect(boolean horizontal, GridConstraints constraints, GridConstraints otherConstraints) {
        int start = constraints.getCell(!horizontal);
        int end = start + constraints.getSpan(!horizontal) - 1;
        int otherStart = otherConstraints.getCell(!horizontal);
        int otherEnd = otherStart + otherConstraints.getSpan(!horizontal) - 1;
        return start <= otherEnd && otherStart <= end;
    }

    public static class Result {
        public JComponent component;
        public boolean isFillerPanel;
        public GridBagConstraints constraints;
        public Dimension preferredSize;
        public Dimension minimumSize;
        public Dimension maximumSize;

        public Result(JComponent component) {
            this.component = component;
            this.constraints = new GridBagConstraints();
        }
    }
}

