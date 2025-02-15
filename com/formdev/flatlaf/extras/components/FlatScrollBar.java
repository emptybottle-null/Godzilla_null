/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.extras.components.FlatComponentExtension;
import javax.swing.JScrollBar;

public class FlatScrollBar
extends JScrollBar
implements FlatComponentExtension {
    public boolean isShowButtons() {
        return this.getClientPropertyBoolean((Object)"JScrollBar.showButtons", "ScrollBar.showButtons");
    }

    public void setShowButtons(boolean showButtons) {
        this.putClientProperty("JScrollBar.showButtons", showButtons);
    }
}

