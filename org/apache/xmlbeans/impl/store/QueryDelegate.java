/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmlbeans.XmlOptions;

public final class QueryDelegate {
    private static final Map<String, Constructor<? extends QueryInterface>> _constructors = new HashMap<String, Constructor<? extends QueryInterface>>();

    private QueryDelegate() {
    }

    private static synchronized void init(String implClassName) {
        if (implClassName == null) {
            implClassName = "org.apache.xmlbeans.impl.xquery.saxon.XBeansXQuery";
        }
        Class<?> queryInterfaceImpl = null;
        boolean engineAvailable = true;
        try {
            queryInterfaceImpl = Class.forName(implClassName);
        } catch (ClassNotFoundException e) {
            engineAvailable = false;
        } catch (NoClassDefFoundError e) {
            engineAvailable = false;
        }
        if (engineAvailable) {
            try {
                Constructor<?> constructor = queryInterfaceImpl.getConstructor(String.class, String.class, Integer.class, XmlOptions.class);
                _constructors.put(implClassName, constructor);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static synchronized QueryInterface createInstance(String implClassName, String query, String contextVar, int boundary, XmlOptions xmlOptions) {
        if (_constructors.get(implClassName) == null) {
            QueryDelegate.init(implClassName);
        }
        if (_constructors.get(implClassName) == null) {
            return null;
        }
        Constructor<? extends QueryInterface> constructor = _constructors.get(implClassName);
        try {
            return constructor.newInstance(query, contextVar, boundary, xmlOptions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static interface QueryInterface {
        public List execQuery(Object var1, Map var2);
    }
}

