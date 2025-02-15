/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.apache.xmlbeans.SystemProperties;
import org.xml.sax.EntityResolver;

public class ResolverUtil {
    private static EntityResolver _entityResolver = null;

    public static EntityResolver getGlobalEntityResolver() {
        return _entityResolver;
    }

    public static EntityResolver resolverForCatalog(String catalogFile) {
        if (catalogFile == null) {
            return null;
        }
        try {
            Class<?> cmClass = Class.forName("org.apache.xml.resolver.CatalogManager");
            Constructor<?> cstrCm = cmClass.getConstructor(new Class[0]);
            Object cmObj = cstrCm.newInstance(new Object[0]);
            Method cmMethod = cmClass.getMethod("setCatalogFiles", String.class);
            cmMethod.invoke(cmObj, catalogFile);
            Class<?> crClass = Class.forName("org.apache.xml.resolver.tools.CatalogResolver");
            Constructor<?> cstrCr = crClass.getConstructor(cmClass);
            Object crObj = cstrCr.newInstance(cmObj);
            return (EntityResolver)crObj;
        } catch (Exception e) {
            return null;
        }
    }

    static {
        try {
            String erClassName = SystemProperties.getProperty("xmlbean.entityResolver");
            if (erClassName != null) {
                Object o = Class.forName(erClassName).newInstance();
                _entityResolver = (EntityResolver)o;
            }
        } catch (Exception e) {
            _entityResolver = null;
        }
    }
}

