/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.builder;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.forms.internal.AbstractButtonPanelBuilder;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;

public final class ButtonBarBuilder
extends AbstractButtonPanelBuilder<ButtonBarBuilder> {
    private static final ColumnSpec[] COL_SPECS = new ColumnSpec[0];
    private static final RowSpec[] ROW_SPECS = new RowSpec[]{RowSpec.decode("center:pref")};

    public ButtonBarBuilder() {
        this(new JPanel(null));
    }

    public ButtonBarBuilder(JPanel panel) {
        super(new FormLayout(COL_SPECS, ROW_SPECS), panel);
    }

    public static ButtonBarBuilder create() {
        return new ButtonBarBuilder();
    }

    @Override
    public ButtonBarBuilder addButton(JComponent button) {
        Preconditions.checkNotNull(button, "The button to add must not be null.");
        this.getLayout().appendColumn(FormSpecs.BUTTON_COLSPEC);
        this.add(button);
        this.nextColumn();
        return this;
    }

    @Override
    public ButtonBarBuilder addButton(JComponent ... buttons) {
        super.addButton(buttons);
        return this;
    }

    @Override
    public ButtonBarBuilder addButton(Action ... actions) {
        super.addButton(actions);
        return this;
    }

    public ButtonBarBuilder addFixed(JComponent component) {
        this.getLayout().appendColumn(FormSpecs.PREF_COLSPEC);
        this.add(component);
        this.nextColumn();
        return this;
    }

    public ButtonBarBuilder addGrowing(JComponent component) {
        this.getLayout().appendColumn(FormSpecs.GROWING_BUTTON_COLSPEC);
        this.add(component);
        this.nextColumn();
        return this;
    }

    public ButtonBarBuilder addGlue() {
        this.appendGlueColumn();
        this.nextColumn();
        return this;
    }

    @Override
    public ButtonBarBuilder addRelatedGap() {
        this.appendRelatedComponentsGapColumn();
        this.nextColumn();
        return this;
    }

    @Override
    public ButtonBarBuilder addUnrelatedGap() {
        this.appendUnrelatedComponentsGapColumn();
        this.nextColumn();
        return this;
    }

    public ButtonBarBuilder addStrut(ConstantSize width) {
        this.getLayout().appendColumn(ColumnSpec.createGap(width));
        this.nextColumn();
        return this;
    }
}

