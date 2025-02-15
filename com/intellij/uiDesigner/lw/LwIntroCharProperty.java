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

public final class LwIntroCharProperty
extends LwIntrospectedProperty {
    static /* synthetic */ Class class$java$lang$Character;

    public LwIntroCharProperty(String name) {
        super(name, (class$java$lang$Character == null ? (class$java$lang$Character = LwIntroCharProperty.class$("java.lang.Character")) : class$java$lang$Character).getName());
    }

    public Object read(Element element) throws Exception {
        return Character.valueOf(LwXmlReader.getRequiredString(element, "value").charAt(0));
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

