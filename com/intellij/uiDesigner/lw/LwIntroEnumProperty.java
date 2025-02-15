/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import java.lang.reflect.Method;
import org.jdom.Element;

public class LwIntroEnumProperty
extends LwIntrospectedProperty {
    private final Class myEnumClass;
    static /* synthetic */ Class class$java$lang$String;

    public LwIntroEnumProperty(String name, Class enumClass) {
        super(name, enumClass.getName());
        this.myEnumClass = enumClass;
    }

    public Object read(Element element) throws Exception {
        String value = element.getAttributeValue("value");
        Method method = this.myEnumClass.getMethod("valueOf", class$java$lang$String == null ? (class$java$lang$String = LwIntroEnumProperty.class$("java.lang.String")) : class$java$lang$String);
        return method.invoke(null, value);
    }

    public String getCodeGenPropertyClassName() {
        return "java.lang.Enum";
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

