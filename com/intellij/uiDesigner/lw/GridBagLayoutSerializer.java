/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.compiler.GridBagConverter;
import com.intellij.uiDesigner.lw.GridLayoutSerializer;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.intellij.uiDesigner.lw.LwXmlReader;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import org.jdom.Element;

public class GridBagLayoutSerializer
extends GridLayoutSerializer {
    public static GridBagLayoutSerializer INSTANCE = new GridBagLayoutSerializer();

    private GridBagLayoutSerializer() {
    }

    void readLayout(Element element, LwContainer container) {
        container.setLayout(new GridBagLayout());
    }

    void readChildConstraints(Element constraintsElement, LwComponent component) {
        super.readChildConstraints(constraintsElement, component);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConverter.constraintsToGridBag(component.getConstraints(), gbc);
        Element gridBagElement = LwXmlReader.getChild(constraintsElement, "gridbag");
        if (gridBagElement != null) {
            if (gridBagElement.getAttributeValue("top") != null) {
                gbc.insets = LwXmlReader.readInsets(gridBagElement);
            }
            gbc.weightx = LwXmlReader.getOptionalDouble(gridBagElement, "weightx", 0.0);
            gbc.weighty = LwXmlReader.getOptionalDouble(gridBagElement, "weighty", 0.0);
            gbc.ipadx = LwXmlReader.getOptionalInt(gridBagElement, "ipadx", 0);
            gbc.ipady = LwXmlReader.getOptionalInt(gridBagElement, "ipady", 0);
        }
        component.setCustomLayoutConstraints(gbc);
    }
}

