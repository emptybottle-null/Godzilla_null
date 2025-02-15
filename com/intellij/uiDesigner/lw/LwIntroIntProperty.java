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

public final class LwIntroIntProperty
extends LwIntrospectedProperty {
    static /* synthetic */ Class class$java$lang$Integer;

    public LwIntroIntProperty(String name) {
        super(name, (class$java$lang$Integer == null ? (class$java$lang$Integer = LwIntroIntProperty.class$("java.lang.Integer")) : class$java$lang$Integer).getName());
    }

    public Object read(Element element) throws Exception {
        return new Integer(LwXmlReader.getRequiredInt(element, "value"));
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

