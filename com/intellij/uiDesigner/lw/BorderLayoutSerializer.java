/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LayoutSerializer;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.LwXmlReader;
import java.awt.BorderLayout;
import org.jdom.Element;

public class BorderLayoutSerializer
extends LayoutSerializer {
    public static final BorderLayoutSerializer INSTANCE = new BorderLayoutSerializer();

    private BorderLayoutSerializer() {
    }

    void readLayout(Element element, LwContainer container) {
        int hGap = LwXmlReader.getOptionalInt(element, "hgap", 0);
        int vGap = LwXmlReader.getOptionalInt(element, "vgap", 0);
        container.setLayout(new BorderLayout(hGap, vGap));
    }

    void readChildConstraints(Element constraintsElement, LwComponent component) {
        component.setCustomLayoutConstraints(LwXmlReader.getRequiredString(constraintsElement, "border-constraint"));
    }
}

