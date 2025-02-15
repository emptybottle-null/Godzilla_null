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
import com.intellij.uiDesigner.shared.XYLayoutManager;
import java.awt.Rectangle;
import org.jdom.Element;

public class XYLayoutSerializer
extends LayoutSerializer {
    static XYLayoutSerializer INSTANCE = new XYLayoutSerializer();

    private XYLayoutSerializer() {
    }

    void readLayout(Element element, LwContainer container) {
        container.setLayout(new XYLayoutManager());
    }

    void readChildConstraints(Element constraintsElement, LwComponent component) {
        Element xyElement = LwXmlReader.getChild(constraintsElement, "xy");
        if (xyElement != null) {
            component.setBounds(new Rectangle(LwXmlReader.getRequiredInt(xyElement, "x"), LwXmlReader.getRequiredInt(xyElement, "y"), LwXmlReader.getRequiredInt(xyElement, "width"), LwXmlReader.getRequiredInt(xyElement, "height")));
        }
    }
}

