/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.config;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.LogLog;

public class PropertyGetter {
    protected static final Object[] NULL_ARG = new Object[0];
    protected Object obj;
    protected PropertyDescriptor[] props;

    public PropertyGetter(Object obj) throws IntrospectionException {
        BeanInfo bi = Introspector.getBeanInfo(obj.getClass());
        this.props = bi.getPropertyDescriptors();
        this.obj = obj;
    }

    public static void getProperties(Object obj, PropertyCallback callback, String prefix) {
        try {
            new PropertyGetter(obj).getProperties(callback, prefix);
        } catch (IntrospectionException ex) {
            LogLog.error("Failed to introspect object " + obj, ex);
        }
    }

    public void getProperties(PropertyCallback callback, String prefix) {
        for (int i = 0; i < this.props.length; ++i) {
            Method getter = this.props[i].getReadMethod();
            if (getter == null || !this.isHandledType(getter.getReturnType())) continue;
            String name = this.props[i].getName();
            try {
                Object result = getter.invoke(this.obj, NULL_ARG);
                if (result == null) continue;
                callback.foundProperty(this.obj, prefix, name, result);
                continue;
            } catch (IllegalAccessException ex) {
                LogLog.warn("Failed to get value of property " + name);
                continue;
            } catch (InvocationTargetException ex) {
                if (ex.getTargetException() instanceof InterruptedException || ex.getTargetException() instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.warn("Failed to get value of property " + name);
                continue;
            } catch (RuntimeException ex) {
                LogLog.warn("Failed to get value of property " + name);
            }
        }
    }

    protected boolean isHandledType(Class type) {
        return String.class.isAssignableFrom(type) || Integer.TYPE.isAssignableFrom(type) || Long.TYPE.isAssignableFrom(type) || Boolean.TYPE.isAssignableFrom(type) || Priority.class.isAssignableFrom(type);
    }

    public static interface PropertyCallback {
        public void foundProperty(Object var1, String var2, String var3, Object var4);
    }
}

