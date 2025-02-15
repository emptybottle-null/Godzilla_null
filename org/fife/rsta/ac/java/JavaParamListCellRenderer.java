/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JList;
import org.fife.rsta.ac.java.JavaCellRenderer;
import org.fife.rsta.ac.java.JavaSourceCompletion;

public class JavaParamListCellRenderer
extends JavaCellRenderer {
    public JavaParamListCellRenderer() {
        this.setSimpleText(true);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d.width += 32;
        return d;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean hasFocus) {
        super.getListCellRendererComponent((JList<?>)list, value, index, selected, hasFocus);
        JavaSourceCompletion ajsc = (JavaSourceCompletion)value;
        this.setIcon(ajsc.getIcon());
        return this;
    }
}

