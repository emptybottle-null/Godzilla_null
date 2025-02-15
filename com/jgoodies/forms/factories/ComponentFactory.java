/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.factories;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public interface ComponentFactory {
    public JButton createButton(Action var1);

    public JLabel createLabel(String var1);

    public JLabel createReadOnlyLabel(String var1);

    public JLabel createTitle(String var1);

    public JLabel createHeaderLabel(String var1);

    public JComponent createSeparator(String var1, int var2);
}

