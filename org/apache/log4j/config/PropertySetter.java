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
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.config.PropertySetterException;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.OptionHandler;

public class PropertySetter {
    protected Object obj;
    protected PropertyDescriptor[] props;
    static /* synthetic */ Class class$org$apache$log4j$spi$OptionHandler;

    public PropertySetter(Object obj) {
        this.obj = obj;
    }

    protected void introspect() {
        try {
            BeanInfo bi = Introspector.getBeanInfo(this.obj.getClass());
            this.props = bi.getPropertyDescriptors();
        } catch (IntrospectionException ex) {
            LogLog.error("Failed to introspect " + this.obj + ": " + ex.getMessage());
            this.props = new PropertyDescriptor[0];
        }
    }

    public static void setProperties(Object obj, Properties properties, String prefix) {
        new PropertySetter(obj).setProperties(properties, prefix);
    }

    public void setProperties(Properties properties, String prefix) {
        int len = prefix.length();
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String)e.nextElement();
            if (!key.startsWith(prefix) || key.indexOf(46, len + 1) > 0) continue;
            String value = OptionConverter.findAndSubst(key, properties);
            if (("layout".equals(key = key.substring(len)) || "errorhandler".equals(key)) && this.obj instanceof Appender) continue;
            PropertyDescriptor prop = this.getPropertyDescriptor(Introspector.decapitalize(key));
            if (prop != null && (class$org$apache$log4j$spi$OptionHandler == null ? PropertySetter.class$("org.apache.log4j.spi.OptionHandler") : class$org$apache$log4j$spi$OptionHandler).isAssignableFrom(prop.getPropertyType()) && prop.getWriteMethod() != null) {
                OptionHandler opt = (OptionHandler)OptionConverter.instantiateByKey(properties, prefix + key, prop.getPropertyType(), null);
                PropertySetter setter = new PropertySetter(opt);
                setter.setProperties(properties, prefix + key + ".");
                try {
                    prop.getWriteMethod().invoke(this.obj, opt);
                } catch (IllegalAccessException ex) {
                    LogLog.warn("Failed to set property [" + key + "] to value \"" + value + "\". ", ex);
                } catch (InvocationTargetException ex) {
                    if (ex.getTargetException() instanceof InterruptedException || ex.getTargetException() instanceof InterruptedIOException) {
                        Thread.currentThread().interrupt();
                    }
                    LogLog.warn("Failed to set property [" + key + "] to value \"" + value + "\". ", ex);
                } catch (RuntimeException ex) {
                    LogLog.warn("Failed to set property [" + key + "] to value \"" + value + "\". ", ex);
                }
                continue;
            }
            this.setProperty(key, value);
        }
        this.activate();
    }

    public void setProperty(String name, String value) {
        if (value == null) {
            return;
        }
        PropertyDescriptor prop = this.getPropertyDescriptor(name = Introspector.decapitalize(name));
        if (prop == null) {
            LogLog.warn("No such property [" + name + "] in " + this.obj.getClass().getName() + ".");
        } else {
            try {
                this.setProperty(prop, name, value);
            } catch (PropertySetterException ex) {
                LogLog.warn("Failed to set property [" + name + "] to value \"" + value + "\". ", ex.rootCause);
            }
        }
    }

    public void setProperty(PropertyDescriptor prop, String name, String value) throws PropertySetterException {
        Object arg;
        Method setter = prop.getWriteMethod();
        if (setter == null) {
            throw new PropertySetterException("No setter for property [" + name + "].");
        }
        Class<?>[] paramTypes = setter.getParameterTypes();
        if (paramTypes.length != 1) {
            throw new PropertySetterException("#params for setter != 1");
        }
        try {
            arg = this.convertArg(value, paramTypes[0]);
        } catch (Throwable t) {
            throw new PropertySetterException("Conversion to type [" + paramTypes[0] + "] failed. Reason: " + t);
        }
        if (arg == null) {
            throw new PropertySetterException("Conversion to type [" + paramTypes[0] + "] failed.");
        }
        LogLog.debug("Setting property [" + name + "] to [" + arg + "].");
        try {
            setter.invoke(this.obj, arg);
        } catch (IllegalAccessException ex) {
            throw new PropertySetterException(ex);
        } catch (InvocationTargetException ex) {
            if (ex.getTargetException() instanceof InterruptedException || ex.getTargetException() instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            throw new PropertySetterException(ex);
        } catch (RuntimeException ex) {
            throw new PropertySetterException(ex);
        }
    }

    protected Object convertArg(String val, Class type) {
        if (val == null) {
            return null;
        }
        String v = val.trim();
        if (String.class.isAssignableFrom(type)) {
            return val;
        }
        if (Integer.TYPE.isAssignableFrom(type)) {
            return new Integer(v);
        }
        if (Long.TYPE.isAssignableFrom(type)) {
            return new Long(v);
        }
        if (Boolean.TYPE.isAssignableFrom(type)) {
            if ("true".equalsIgnoreCase(v)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(v)) {
                return Boolean.FALSE;
            }
        } else {
            if (Priority.class.isAssignableFrom(type)) {
                return OptionConverter.toLevel(v, Level.DEBUG);
            }
            if (ErrorHandler.class.isAssignableFrom(type)) {
                return OptionConverter.instantiateByClassName(v, ErrorHandler.class, null);
            }
        }
        return null;
    }

    protected PropertyDescriptor getPropertyDescriptor(String name) {
        if (this.props == null) {
            this.introspect();
        }
        for (int i = 0; i < this.props.length; ++i) {
            if (!name.equals(this.props[i].getName())) continue;
            return this.props[i];
        }
        return null;
    }

    public void activate() {
        if (this.obj instanceof OptionHandler) {
            ((OptionHandler)this.obj).activateOptions();
        }
    }
}

