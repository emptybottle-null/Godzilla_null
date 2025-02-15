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
import java.awt.FlowLayout;
import org.jdom.Element;

public class FlowLayoutSerializer
extends LayoutSerializer {
    public static final FlowLayoutSerializer INSTANCE = new FlowLayoutSerializer();

    private FlowLayoutSerializer() {
    }

    void readLayout(Element element, LwContainer container) {
        int hGap = LwXmlReader.getOptionalInt(element, "hgap", 5);
        int vGap = LwXmlReader.getOptionalInt(element, "vgap", 5);
        int flowAlign = LwXmlReader.getOptionalInt(element, "flow-align", 1);
        container.setLayout(new FlowLayout(flowAlign, hGap, vGap));
    }

    void readChildConstraints(Element constraintsElement, LwComponent component) {
    }
}

