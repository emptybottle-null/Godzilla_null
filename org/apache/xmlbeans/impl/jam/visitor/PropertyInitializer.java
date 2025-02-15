/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.visitor;

import java.util.HashMap;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JMethod;
import org.apache.xmlbeans.impl.jam.JProperty;
import org.apache.xmlbeans.impl.jam.internal.elements.PropertyImpl;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public class PropertyInitializer
extends MVisitor {
    @Override
    public void visit(MClass clazz) {
        this.addProperties(clazz, true);
        this.addProperties(clazz, false);
    }

    private void addProperties(MClass clazz, boolean declared) {
        JMethod[] methods = declared ? clazz.getDeclaredMethods() : clazz.getMethods();
        HashMap<String, JProperty> name2prop = new HashMap<String, JProperty>();
        for (int i = 0; i < methods.length; ++i) {
            JProperty prop;
            String name = methods[i].getSimpleName();
            if (name.startsWith("get") && name.length() > 3 || name.startsWith("is") && name.length() > 2) {
                JClass typ = methods[i].getReturnType();
                if (typ == null || methods[i].getParameters().length > 0) continue;
                prop = (JProperty)name2prop.get(name = name.startsWith("get") ? name.substring(3) : name.substring(2));
                if (prop == null) {
                    prop = declared ? clazz.addNewDeclaredProperty(name, methods[i], null) : clazz.addNewProperty(name, methods[i], null);
                    name2prop.put(name, prop);
                } else if (((Object)typ).equals(prop.getType())) {
                    ((PropertyImpl)prop).setGetter(methods[i]);
                }
            }
            if (!name.startsWith("set") || name.length() <= 3 || methods[i].getParameters().length != 1) continue;
            JClass type = methods[i].getParameters()[0].getType();
            prop = (JProperty)name2prop.get(name = name.substring(3));
            if (prop == null) {
                prop = declared ? clazz.addNewDeclaredProperty(name, null, methods[i]) : clazz.addNewProperty(name, null, methods[i]);
                name2prop.put(name, prop);
                continue;
            }
            if (!((Object)type).equals(prop.getType())) continue;
            ((PropertyImpl)prop).setSetter(methods[i]);
        }
    }
}

