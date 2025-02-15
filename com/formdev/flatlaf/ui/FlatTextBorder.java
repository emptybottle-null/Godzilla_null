/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import javax.swing.UIManager;

public class FlatTextBorder
extends FlatBorder {
    protected final int arc = UIManager.getInt("TextComponent.arc");

    @Override
    protected int getArc(Component c) {
        if (this.isCellEditor(c)) {
            return 0;
        }
        Boolean roundRect = FlatUIUtils.isRoundRect(c);
        return roundRect != null ? (roundRect.booleanValue() ? Short.MAX_VALUE : 0) : this.arc;
    }
}

