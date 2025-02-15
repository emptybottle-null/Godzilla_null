/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

public class ScaledEmptyBorder
extends EmptyBorder {
    public ScaledEmptyBorder(int top, int left, int bottom, int right) {
        super(top, left, bottom, right);
    }

    public ScaledEmptyBorder(Insets insets) {
        super(insets);
    }

    @Override
    public Insets getBorderInsets() {
        return new Insets(UIScale.scale(this.top), UIScale.scale(this.left), UIScale.scale(this.bottom), UIScale.scale(this.right));
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = UIScale.scale(this.left);
        insets.top = UIScale.scale(this.top);
        insets.right = UIScale.scale(this.right);
        insets.bottom = UIScale.scale(this.bottom);
        return insets;
    }
}

