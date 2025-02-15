/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.IconDescriptor;
import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import com.intellij.uiDesigner.lw.LwXmlReader;
import org.jdom.Element;

public class LwIntroIconProperty
extends LwIntrospectedProperty {
    public LwIntroIconProperty(String name) {
        super(name, "javax.swing.Icon");
    }

    public Object read(Element element) throws Exception {
        String value = LwXmlReader.getRequiredString(element, "value");
        return new IconDescriptor(value);
    }
}

