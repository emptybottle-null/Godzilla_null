/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PathDelegate {
    private static HashMap _constructors = new HashMap();

    private PathDelegate() {
    }

    private static synchronized void init(String implClassName) {
        if (implClassName == null) {
            implClassName = "org.apache.xmlbeans.impl.xpath.saxon.XBeansXPath";
        }
        Class<?> selectPathInterfaceImpl = null;
        boolean engineAvailable = true;
        try {
            selectPathInterfaceImpl = Class.forName(implClassName);
        } catch (ClassNotFoundException e) {
            engineAvailable = false;
        } catch (NoClassDefFoundError e) {
            engineAvailable = false;
        }
        if (engineAvailable) {
            try {
                Constructor<?> constructor = selectPathInterfaceImpl.getConstructor(String.class, String.class, Map.class, String.class);
                _constructors.put(implClassName, constructor);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static synchronized SelectPathInterface createInstance(String implClassName, String xpath, String contextVar, Map namespaceMap) {
        if (_constructors.get(implClassName) == null) {
            PathDelegate.init(implClassName);
        }
        if (_constructors.get(implClassName) == null) {
            return null;
        }
        Constructor constructor = (Constructor)_constructors.get(implClassName);
        try {
            Object defaultNS = namespaceMap.get("$xmlbeans!default_uri");
            if (defaultNS != null) {
                namespaceMap.remove("$xmlbeans!default_uri");
            }
            return (SelectPathInterface)constructor.newInstance(xpath, contextVar, namespaceMap, (String)defaultNS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static interface SelectPathInterface {
        public List selectPath(Object var1);
    }
}

