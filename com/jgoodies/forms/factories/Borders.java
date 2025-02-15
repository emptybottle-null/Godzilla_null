/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.factories;

import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.ConstantSize;
import javax.swing.border.EmptyBorder;

@Deprecated
public final class Borders {
    public static final EmptyBorder EMPTY = new EmptyBorder(0, 0, 0, 0);
    public static final Paddings.Padding DLU2 = Paddings.DLU2;
    public static final Paddings.Padding DLU4 = Paddings.DLU4;
    public static final Paddings.Padding DLU7 = Paddings.DLU7;
    public static final Paddings.Padding DLU9 = Paddings.DLU9;
    public static final Paddings.Padding DLU14 = Paddings.DLU14;
    public static final Paddings.Padding DLU21 = Paddings.DLU21;
    public static final Paddings.Padding BUTTON_BAR_PAD = Paddings.BUTTON_BAR_PAD;
    public static final Paddings.Padding DIALOG = Paddings.DIALOG;
    public static final Paddings.Padding TABBED_DIALOG = Paddings.TABBED_DIALOG;

    private Borders() {
    }

    @Deprecated
    public static Paddings.Padding createEmptyBorder(ConstantSize top, ConstantSize left, ConstantSize bottom, ConstantSize right) {
        return Paddings.createPadding(top, left, bottom, right);
    }

    @Deprecated
    public static Paddings.Padding createEmptyBorder(String encodedSizes) {
        return Paddings.createPadding(encodedSizes, new Object[0]);
    }
}

