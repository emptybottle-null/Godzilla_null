/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatTextFieldUI;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

public class FlatFormattedTextFieldUI
extends FlatTextFieldUI {
    public static ComponentUI createUI(JComponent c) {
        return new FlatFormattedTextFieldUI();
    }

    @Override
    protected String getPropertyPrefix() {
        return "FormattedTextField";
    }
}

