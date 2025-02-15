/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.IComponent;
import com.intellij.uiDesigner.lw.StringDescriptor;
import com.intellij.uiDesigner.shared.BorderType;

public interface IContainer
extends IComponent {
    public int getComponentCount();

    public IComponent getComponent(int var1);

    public int indexOfComponent(IComponent var1);

    public boolean isXY();

    public StringDescriptor getBorderTitle();

    public BorderType getBorderType();
}

