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

public final class ButtonStackBuilder
extends AbstractButtonPanelBuilder<ButtonStackBuilder> {
    private static final ColumnSpec[] COL_SPECS = new ColumnSpec[]{FormSpecs.BUTTON_COLSPEC};
    private static final RowSpec[] ROW_SPECS = new RowSpec[0];

    public ButtonStackBuilder() {
        this(new JPanel(null));
    }

    public ButtonStackBuilder(JPanel panel) {
        super(new FormLayout(COL_SPECS, ROW_SPECS), panel);
    }

    public static ButtonStackBuilder create() {
        return new ButtonStackBuilder();
    }

    @Override
    public ButtonStackBuilder addButton(JComponent button) {
        Preconditions.checkNotNull(button, "The button must not be null.");
        this.getLayout().appendRow(FormSpecs.PREF_ROWSPEC);
        this.add(button);
        this.nextRow();
        return this;
    }

    @Override
    public ButtonStackBuilder addButton(JComponent ... buttons) {
        super.addButton(buttons);
        return this;
    }

    @Override
    public ButtonStackBuilder addButton(Action ... actions) {
        super.addButton(actions);
        return this;
    }

    public ButtonStackBuilder addFixed(JComponent component) {
        this.getLayout().appendRow(FormSpecs.PREF_ROWSPEC);
        this.add(component);
        this.nextRow();
        return this;
    }

    public ButtonStackBuilder addGlue() {
        this.appendGlueRow();
        this.nextRow();
        return this;
    }

    @Override
    public ButtonStackBuilder addRelatedGap() {
        this.appendRelatedComponentsGapRow();
        this.nextRow();
        return this;
    }

    @Override
    public ButtonStackBuilder addUnrelatedGap() {
        this.appendUnrelatedComponentsGapRow();
        this.nextRow();
        return this;
    }

    public ButtonStackBuilder addStrut(ConstantSize size) {
        this.getLayout().appendRow(new RowSpec(RowSpec.TOP, size, 0.0));
        this.nextRow();
        return this;
    }
}

