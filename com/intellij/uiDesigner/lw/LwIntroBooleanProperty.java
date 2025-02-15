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

public final class LwIntroBooleanProperty
extends LwIntrospectedProperty {
    static /* synthetic */ Class class$java$lang$Boolean;

    public LwIntroBooleanProperty(String name) {
        super(name, (class$java$lang$Boolean == null ? (class$java$lang$Boolean = LwIntroBooleanProperty.class$("java.lang.Boolean")) : class$java$lang$Boolean).getName());
    }

    public Object read(Element element) throws Exception {
        return Boolean.valueOf(LwXmlReader.getRequiredString(element, "value"));
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

