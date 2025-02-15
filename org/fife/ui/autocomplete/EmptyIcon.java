/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.Icon;

public class EmptyIcon
implements Icon,
Serializable {
    private int size;

    public EmptyIcon(int size) {
        this.size = size;
    }

    @Override
    public int getIconHeight() {
        return this.size;
    }

    @Override
    public int getIconWidth() {
        return this.size;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
    }
}

