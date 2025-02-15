/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import com.intellij.uiDesigner.lw.LwXmlReader;
import org.jdom.Element;

public class LwIntroFontProperty
extends LwIntrospectedProperty {
    public LwIntroFontProperty(String name) {
        super(name, "java.awt.Font");
    }

    public Object read(Element element) throws Exception {
        return LwXmlReader.getFontDescriptor(element);
    }
}

