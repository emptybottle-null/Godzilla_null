/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.extras.components.FlatComponentExtension;
import javax.swing.JTextPane;

public class FlatTextPane
extends JTextPane
implements FlatComponentExtension {
    public int getMinimumWidth() {
        return this.getClientPropertyInt((Object)"JComponent.minimumWidth", "Component.minimumWidth");
    }

    public void setMinimumWidth(int minimumWidth) {
        this.putClientProperty("JComponent.minimumWidth", minimumWidth >= 0 ? Integer.valueOf(minimumWidth) : null);
    }
}

