/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.extras.components.FlatComponentExtension;
import javax.swing.JScrollPane;

public class FlatScrollPane
extends JScrollPane
implements FlatComponentExtension {
    public boolean isShowButtons() {
        return this.getClientPropertyBoolean((Object)"JScrollBar.showButtons", "ScrollBar.showButtons");
    }

    public void setShowButtons(boolean showButtons) {
        this.putClientProperty("JScrollBar.showButtons", showButtons);
    }

    public boolean isSmoothScrolling() {
        return this.getClientPropertyBoolean((Object)"JScrollPane.smoothScrolling", "ScrollPane.smoothScrolling");
    }

    public void setSmoothScrolling(boolean smoothScrolling) {
        this.putClientProperty("JScrollPane.smoothScrolling", smoothScrolling);
    }

    public Object getOutline() {
        return this.getClientProperty("JComponent.outline");
    }

    public void setOutline(Object outline) {
        this.putClientProperty("JComponent.outline", outline);
    }
}

