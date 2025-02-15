/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatUIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

public class FlatPanelUI
extends BasicPanelUI {
    public static ComponentUI createUI(JComponent c) {
        return FlatUIUtils.createSharedUI(FlatPanelUI.class, FlatPanelUI::new);
    }
}

