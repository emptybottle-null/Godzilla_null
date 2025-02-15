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
import java.awt.CardLayout;
import org.jdom.Element;

public class CardLayoutSerializer
extends LayoutSerializer {
    public static final CardLayoutSerializer INSTANCE = new CardLayoutSerializer();

    private CardLayoutSerializer() {
    }

    void readLayout(Element element, LwContainer container) {
        int hGap = LwXmlReader.getOptionalInt(element, "hgap", 0);
        int vGap = LwXmlReader.getOptionalInt(element, "vgap", 0);
        container.setLayout(new CardLayout(hGap, vGap));
    }

    void readChildConstraints(Element constraintsElement, LwComponent component) {
        Element cardChild = LwXmlReader.getRequiredChild(constraintsElement, "card");
        String name = LwXmlReader.getRequiredString(cardChild, "name");
        component.setCustomLayoutConstraints(name);
    }
}

