/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.layout;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jgoodies.forms.util.LayoutStyle;

public final class FormSpecs {
    public static final ColumnSpec MIN_COLSPEC = new ColumnSpec(Sizes.MINIMUM);
    public static final ColumnSpec PREF_COLSPEC = new ColumnSpec(Sizes.PREFERRED);
    public static final ColumnSpec DEFAULT_COLSPEC = new ColumnSpec(Sizes.DEFAULT);
    public static final ColumnSpec GLUE_COLSPEC = new ColumnSpec(ColumnSpec.DEFAULT, Sizes.ZERO, 1.0);
    public static final ColumnSpec LABEL_COMPONENT_GAP_COLSPEC = ColumnSpec.createGap(LayoutStyle.getCurrent().getLabelComponentPadX());
    public static final ColumnSpec RELATED_GAP_COLSPEC = ColumnSpec.createGap(LayoutStyle.getCurrent().getRelatedComponentsPadX());
    public static final ColumnSpec UNRELATED_GAP_COLSPEC = ColumnSpec.createGap(LayoutStyle.getCurrent().getUnrelatedComponentsPadX());
    public static final ColumnSpec BUTTON_COLSPEC = new ColumnSpec(Sizes.bounded(Sizes.PREFERRED, LayoutStyle.getCurrent().getDefaultButtonWidth(), null));
    public static final ColumnSpec GROWING_BUTTON_COLSPEC = new ColumnSpec(ColumnSpec.DEFAULT, BUTTON_COLSPEC.getSize(), 1.0);
    public static final RowSpec MIN_ROWSPEC = new RowSpec(Sizes.MINIMUM);
    public static final RowSpec PREF_ROWSPEC = new RowSpec(Sizes.PREFERRED);
    public static final RowSpec DEFAULT_ROWSPEC = new RowSpec(Sizes.DEFAULT);
    public static final RowSpec GLUE_ROWSPEC = new RowSpec(RowSpec.DEFAULT, Sizes.ZERO, 1.0);
    public static final RowSpec LABEL_COMPONENT_GAP_ROWSPEC = RowSpec.createGap(LayoutStyle.getCurrent().getLabelComponentPadY());
    public static final RowSpec RELATED_GAP_ROWSPEC = RowSpec.createGap(LayoutStyle.getCurrent().getRelatedComponentsPadY());
    public static final RowSpec UNRELATED_GAP_ROWSPEC = RowSpec.createGap(LayoutStyle.getCurrent().getUnrelatedComponentsPadY());
    public static final RowSpec NARROW_LINE_GAP_ROWSPEC = RowSpec.createGap(LayoutStyle.getCurrent().getNarrowLinePad());
    public static final RowSpec LINE_GAP_ROWSPEC = RowSpec.createGap(LayoutStyle.getCurrent().getLinePad());
    public static final RowSpec PARAGRAPH_GAP_ROWSPEC = RowSpec.createGap(LayoutStyle.getCurrent().getParagraphPad());
    public static final RowSpec BUTTON_ROWSPEC = new RowSpec(Sizes.bounded(Sizes.PREFERRED, LayoutStyle.getCurrent().getDefaultButtonHeight(), null));

    private FormSpecs() {
    }
}

