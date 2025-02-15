/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.IButtonGroup;
import com.intellij.uiDesigner.lw.IComponent;
import com.intellij.uiDesigner.lw.IContainer;

public interface IRootContainer
extends IContainer {
    public String getClassToBind();

    public String getButtonGroupName(IComponent var1);

    public String[] getButtonGroupComponentIds(String var1);

    public boolean isInspectionSuppressed(String var1, String var2);

    public IButtonGroup[] getButtonGroups();
}

