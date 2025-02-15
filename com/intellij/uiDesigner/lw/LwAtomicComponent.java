/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import org.jdom.Element;

public class LwAtomicComponent
extends LwComponent {
    public LwAtomicComponent(String className) {
        super(className);
    }

    public void read(Element element, PropertiesProvider provider) throws Exception {
        this.readBase(element);
        this.readConstraints(element);
        this.readProperties(element, provider);
    }
}

