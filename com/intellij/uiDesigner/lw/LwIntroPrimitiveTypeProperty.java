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

public final class LwIntroPrimitiveTypeProperty
extends LwIntrospectedProperty {
    private final Class myValueClass;

    public LwIntroPrimitiveTypeProperty(String name, Class valueClass) {
        super(name, valueClass.getName());
        this.myValueClass = valueClass;
    }

    public Object read(Element element) throws Exception {
        return LwXmlReader.getRequiredPrimitiveTypeValue(element, "value", this.myValueClass);
    }
}

