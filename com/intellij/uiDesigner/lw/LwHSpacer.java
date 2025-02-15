/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LwAtomicComponent;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import org.jdom.Element;

public final class LwHSpacer
extends LwAtomicComponent {
    public LwHSpacer() throws Exception {
        super("com.intellij.uiDesigner.core.Spacer");
    }

    public void read(Element element, PropertiesProvider provider) throws Exception {
        this.readBase(element);
        this.readConstraints(element);
    }
}

