/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.xmlbeans.XmlObject;

public class ObjectFactory<T, X extends XmlObject> {
    Map<String, Constructor<? extends T>> _types = new HashMap<String, Constructor<? extends T>>();

    public void put(String typeName, Class<? extends T> cls, Class<?> ... varargs) throws NoSuchMethodException, SecurityException {
        this._types.put(typeName, cls.getDeclaredConstructor(varargs));
    }

    public T load(String name, Object ... varargs) {
        Constructor<T> constructor = this._types.get(name);
        if (constructor == null) {
            XmlObject xmlObject = (XmlObject)varargs[0];
            String typeName = xmlObject.schemaType().getName().getLocalPart();
            throw new POIXMLException("Invalid '" + typeName + "' name '" + name + "'");
        }
        try {
            return constructor.newInstance(varargs);
        } catch (InvocationTargetException e) {
            throw new POIXMLException(e.getCause());
        } catch (Exception e) {
            throw new POIXMLException(e);
        }
    }
}

