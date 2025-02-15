/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.internal;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.forms.internal.AbstractBuilder;
import com.jgoodies.forms.internal.FocusTraversalUtilsAccessor;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

public abstract class AbstractButtonPanelBuilder<B extends AbstractButtonPanelBuilder<B>>
extends AbstractBuilder<B> {
    private boolean leftToRight;
    protected boolean focusGrouped = false;

    protected AbstractButtonPanelBuilder(FormLayout layout, JPanel container) {
        super(layout, container);
        this.opaque(false);
        ComponentOrientation orientation = container.getComponentOrientation();
        this.leftToRight = orientation.isLeftToRight() || !orientation.isHorizontal();
    }

    @Override
    public final JPanel build() {
        if (!this.focusGrouped) {
            ArrayList<AbstractButton> buttons = new ArrayList<AbstractButton>();
            for (Component component : this.getPanel().getComponents()) {
                if (!(component instanceof AbstractButton)) continue;
                buttons.add((AbstractButton)component);
            }
            FocusTraversalUtilsAccessor.tryToBuildAFocusGroup(buttons.toArray(new AbstractButton[0]));
            this.focusGrouped = true;
        }
        return this.getPanel();
    }

    @Deprecated
    public final void setBackground(Color background) {
        this.getPanel().setBackground(background);
        this.opaque(true);
    }

    @Deprecated
    public final void setBorder(Border border) {
        this.getPanel().setBorder(border);
    }

    @Deprecated
    public final void setOpaque(boolean b) {
        this.getPanel().setOpaque(b);
    }

    public final boolean isLeftToRight() {
        return this.leftToRight;
    }

    public final void setLeftToRight(boolean b) {
        this.leftToRight = b;
    }

    protected final void nextColumn() {
        this.nextColumn(1);
    }

    private void nextColumn(int columns) {
        this.currentCellConstraints.gridX += columns * this.getColumnIncrementSign();
    }

    protected final int getColumn() {
        return this.currentCellConstraints.gridX;
    }

    protected final int getRow() {
        return this.currentCellConstraints.gridY;
    }

    protected final void nextRow() {
        this.nextRow(1);
    }

    private void nextRow(int rows) {
        this.currentCellConstraints.gridY += rows;
    }

    protected final void appendColumn(ColumnSpec columnSpec) {
        this.getLayout().appendColumn(columnSpec);
    }

    protected final void appendGlueColumn() {
        this.appendColumn(FormSpecs.GLUE_COLSPEC);
    }

    protected final void appendRelatedComponentsGapColumn() {
        this.appendColumn(FormSpecs.RELATED_GAP_COLSPEC);
    }

    protected final void appendUnrelatedComponentsGapColumn() {
        this.appendColumn(FormSpecs.UNRELATED_GAP_COLSPEC);
    }

    protected final void appendRow(RowSpec rowSpec) {
        this.getLayout().appendRow(rowSpec);
    }

    protected final void appendGlueRow() {
        this.appendRow(FormSpecs.GLUE_ROWSPEC);
    }

    protected final void appendRelatedComponentsGapRow() {
        this.appendRow(FormSpecs.RELATED_GAP_ROWSPEC);
    }

    protected final void appendUnrelatedComponentsGapRow() {
        this.appendRow(FormSpecs.UNRELATED_GAP_ROWSPEC);
    }

    protected final Component add(Component component) {
        this.getPanel().add(component, this.currentCellConstraints);
        this.focusGrouped = false;
        return component;
    }

    protected abstract AbstractButtonPanelBuilder addButton(JComponent var1);

    protected AbstractButtonPanelBuilder addButton(JComponent ... buttons) {
        Preconditions.checkNotNull(buttons, "The button array must not be null.");
        Preconditions.checkArgument(buttons.length > 0, "The button array must not be empty.");
        boolean needsGap = false;
        for (JComponent button : buttons) {
            if (button == null) {
                this.addUnrelatedGap();
                needsGap = false;
                continue;
            }
            if (needsGap) {
                this.addRelatedGap();
            }
            this.addButton(button);
            needsGap = true;
        }
        return this;
    }

    protected AbstractButtonPanelBuilder addButton(Action ... actions) {
        Preconditions.checkNotNull(actions, "The Action array must not be null.");
        int length = actions.length;
        Preconditions.checkArgument(length > 0, "The Action array must not be empty.");
        JComponent[] buttons = new JButton[length];
        for (int i = 0; i < length; ++i) {
            Action action = actions[i];
            buttons[i] = action == null ? null : this.createButton(action);
        }
        return this.addButton(buttons);
    }

    protected abstract AbstractButtonPanelBuilder addRelatedGap();

    protected abstract AbstractButtonPanelBuilder addUnrelatedGap();

    protected JButton createButton(Action action) {
        return this.getComponentFactory().createButton(action);
    }

    private int getColumnIncrementSign() {
        return this.isLeftToRight() ? 1 : -1;
    }
}

