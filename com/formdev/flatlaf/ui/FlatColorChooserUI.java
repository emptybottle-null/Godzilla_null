/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicColorChooserUI;

public class FlatColorChooserUI
extends BasicColorChooserUI {
    public static ComponentUI createUI(JComponent c) {
        return new FlatColorChooserUI();
    }
}

