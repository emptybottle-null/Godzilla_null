/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import com.intellij.uiDesigner.lw.LwXmlReader;
import com.intellij.uiDesigner.lw.StringDescriptor;
import org.jdom.Element;

public final class LwRbIntroStringProperty
extends LwIntrospectedProperty {
    static /* synthetic */ Class class$java$lang$String;

    public LwRbIntroStringProperty(String name) {
        super(name, (class$java$lang$String == null ? (class$java$lang$String = LwRbIntroStringProperty.class$("java.lang.String")) : class$java$lang$String).getName());
    }

    public Object read(Element element) throws Exception {
        StringDescriptor descriptor = LwXmlReader.getStringDescriptor(element, "value", "resource-bundle", "key");
        if (descriptor == null) {
            throw new IllegalArgumentException("String descriptor value required");
        }
        return descriptor;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

