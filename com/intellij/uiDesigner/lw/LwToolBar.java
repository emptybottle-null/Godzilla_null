/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import java.awt.LayoutManager;
import org.jdom.Element;

public class LwToolBar
extends LwContainer {
    public LwToolBar(String className) {
        super(className);
    }

    protected LayoutManager createInitialLayout() {
        return null;
    }

    public void read(Element element, PropertiesProvider provider) throws Exception {
        this.readNoLayout(element, provider);
    }

    protected void readConstraintsForChild(Element element, LwComponent component) {
    }
}

