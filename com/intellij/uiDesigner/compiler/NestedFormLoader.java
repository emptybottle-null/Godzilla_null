/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.lw.LwRootContainer;

public interface NestedFormLoader {
    public LwRootContainer loadForm(String var1) throws Exception;

    public String getClassToBindName(LwRootContainer var1);
}

