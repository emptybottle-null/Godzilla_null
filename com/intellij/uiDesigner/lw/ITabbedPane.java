/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.IComponent;
import com.intellij.uiDesigner.lw.IContainer;
import com.intellij.uiDesigner.lw.StringDescriptor;

public interface ITabbedPane
extends IContainer {
    public static final String TAB_TITLE_PROPERTY = "Tab Title";
    public static final String TAB_TOOLTIP_PROPERTY = "Tab Tooltip";

    public StringDescriptor getTabProperty(IComponent var1, String var2);
}

