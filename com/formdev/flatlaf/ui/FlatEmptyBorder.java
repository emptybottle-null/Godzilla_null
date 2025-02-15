/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.plaf.BorderUIResource;

public class FlatEmptyBorder
extends BorderUIResource.EmptyBorderUIResource {
    public FlatEmptyBorder() {
        super(0, 0, 0, 0);
    }

    public FlatEmptyBorder(int top, int left, int bottom, int right) {
        super(top, left, bottom, right);
    }

    public FlatEmptyBorder(Insets insets) {
        super(insets);
    }

    @Override
    public Insets getBorderInsets() {
        return new Insets(UIScale.scale(this.top), UIScale.scale(this.left), UIScale.scale(this.bottom), UIScale.scale(this.right));
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        boolean leftToRight = this.left == this.right || c.getComponentOrientation().isLeftToRight();
        insets.left = UIScale.scale(leftToRight ? this.left : this.right);
        insets.top = UIScale.scale(this.top);
        insets.right = UIScale.scale(leftToRight ? this.right : this.left);
        insets.bottom = UIScale.scale(this.bottom);
        return insets;
    }

    public Insets getUnscaledBorderInsets() {
        return super.getBorderInsets();
    }
}

