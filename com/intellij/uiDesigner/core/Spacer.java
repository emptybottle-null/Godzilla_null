/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.core;

import java.awt.Dimension;
import javax.swing.JComponent;

public class Spacer
extends JComponent {
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(0, 0);
    }

    @Override
    public final Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
}

