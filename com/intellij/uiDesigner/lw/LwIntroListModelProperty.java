/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import com.intellij.uiDesigner.lw.LwXmlReader;
import java.util.List;
import org.jdom.Element;

public class LwIntroListModelProperty
extends LwIntrospectedProperty {
    public LwIntroListModelProperty(String name, String propertyClassName) {
        super(name, propertyClassName);
    }

    public Object read(Element element) throws Exception {
        List list = element.getChildren("item", element.getNamespace());
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            Element itemElement = (Element)list.get(i);
            result[i] = LwXmlReader.getRequiredString(itemElement, "value");
        }
        return result;
    }
}

