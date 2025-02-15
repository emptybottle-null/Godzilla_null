/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import org.jdom.Element;

public abstract class LayoutSerializer {
    abstract void readLayout(Element var1, LwContainer var2);

    abstract void readChildConstraints(Element var1, LwComponent var2);
}

