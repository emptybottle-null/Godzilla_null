/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.jmx;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InterruptedIOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.JMException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.jmx.AbstractDynamicMBean;
import org.apache.log4j.jmx.LayoutDynamicMBean;
import org.apache.log4j.jmx.MethodUnion;
import org.apache.log4j.spi.OptionHandler;

public class AppenderDynamicMBean
extends AbstractDynamicMBean {
    private MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private Vector dAttributes = new Vector();
    private String dClassName = this.getClass().getName();
    private Hashtable dynamicProps = new Hashtable(5);
    private MBeanOperationInfo[] dOperations = new MBeanOperationInfo[2];
    private String dDescription = "This MBean acts as a management facade for log4j appenders.";
    private static Logger cat = Logger.getLogger(AppenderDynamicMBean.class);
    private Appender appender;

    public AppenderDynamicMBean(Appender appender) throws IntrospectionException {
        this.appender = appender;
        this.buildDynamicMBeanInfo();
    }

    private void buildDynamicMBeanInfo() throws IntrospectionException {
        Constructor<?>[] constructors = this.getClass().getConstructors();
        this.dConstructors[0] = new MBeanConstructorInfo("AppenderDynamicMBean(): Constructs a AppenderDynamicMBean instance", constructors[0]);
        BeanInfo bi = Introspector.getBeanInfo(this.appender.getClass());
        PropertyDescriptor[] pd = bi.getPropertyDescriptors();
        int size = pd.length;
        for (int i = 0; i < size; ++i) {
            Class<?> returnClass;
            String name = pd[i].getName();
            Method readMethod = pd[i].getReadMethod();
            Method writeMethod = pd[i].getWriteMethod();
            if (readMethod == null || !this.isSupportedType(returnClass = readMethod.getReturnType())) continue;
            String returnClassName = returnClass.isAssignableFrom(class$org$apache$log4j$Priority == null ? AppenderDynamicMBean.class$("org.apache.log4j.Priority") : class$org$apache$log4j$Priority) ? "java.lang.String" : returnClass.getName();
            this.dAttributes.add(new MBeanAttributeInfo(name, returnClassName, "Dynamic", true, writeMethod != null, false));
            this.dynamicProps.put(name, new MethodUnion(readMethod, writeMethod));
        }
        MBeanParameterInfo[] params = new MBeanParameterInfo[]{};
        this.dOperations[0] = new MBeanOperationInfo("activateOptions", "activateOptions(): add an appender", params, "void", 1);
        params = new MBeanParameterInfo[]{new MBeanParameterInfo("layout class", "java.lang.String", "layout class")};
        this.dOperations[1] = new MBeanOperationInfo("setLayout", "setLayout(): add a layout", params, "void", 1);
    }

    private boolean isSupportedType(Class clazz) {
        if (clazz.isPrimitive()) {
            return true;
        }
        if (clazz == String.class) {
            return true;
        }
        return clazz.isAssignableFrom(Priority.class);
    }

    public MBeanInfo getMBeanInfo() {
        cat.debug("getMBeanInfo called.");
        MBeanAttributeInfo[] attribs = new MBeanAttributeInfo[this.dAttributes.size()];
        this.dAttributes.toArray(attribs);
        return new MBeanInfo(this.dClassName, this.dDescription, attribs, this.dConstructors, this.dOperations, new MBeanNotificationInfo[0]);
    }

    public Object invoke(String operationName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        if (operationName.equals("activateOptions") && this.appender instanceof OptionHandler) {
            OptionHandler oh = (OptionHandler)((Object)this.appender);
            oh.activateOptions();
            return "Options activated.";
        }
        if (operationName.equals("setLayout")) {
            Layout layout = (Layout)OptionConverter.instantiateByClassName((String)params[0], Layout.class, null);
            this.appender.setLayout(layout);
            this.registerLayoutMBean(layout);
        }
        return null;
    }

    void registerLayoutMBean(Layout layout) {
        if (layout == null) {
            return;
        }
        String name = AppenderDynamicMBean.getAppenderName(this.appender) + ",layout=" + layout.getClass().getName();
        cat.debug("Adding LayoutMBean:" + name);
        ObjectName objectName = null;
        try {
            LayoutDynamicMBean appenderMBean = new LayoutDynamicMBean(layout);
            objectName = new ObjectName("log4j:appender=" + name);
            if (!this.server.isRegistered(objectName)) {
                this.registerMBean(appenderMBean, objectName);
                this.dAttributes.add(new MBeanAttributeInfo("appender=" + name, "javax.management.ObjectName", "The " + name + " layout.", true, true, false));
            }
        } catch (JMException e) {
            cat.error("Could not add DynamicLayoutMBean for [" + name + "].", e);
        } catch (IntrospectionException e) {
            cat.error("Could not add DynamicLayoutMBean for [" + name + "].", e);
        } catch (RuntimeException e) {
            cat.error("Could not add DynamicLayoutMBean for [" + name + "].", e);
        }
    }

    protected Logger getLogger() {
        return cat;
    }

    public Object getAttribute(String attributeName) throws AttributeNotFoundException, MBeanException, ReflectionException {
        MethodUnion mu;
        if (attributeName == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke a getter of " + this.dClassName + " with null attribute name");
        }
        cat.debug("getAttribute called with [" + attributeName + "].");
        if (attributeName.startsWith("appender=" + this.appender.getName() + ",layout")) {
            try {
                return new ObjectName("log4j:" + attributeName);
            } catch (MalformedObjectNameException e) {
                cat.error("attributeName", e);
            } catch (RuntimeException e) {
                cat.error("attributeName", e);
            }
        }
        if ((mu = (MethodUnion)this.dynamicProps.get(attributeName)) != null && mu.readMethod != null) {
            try {
                return mu.readMethod.invoke(this.appender, null);
            } catch (IllegalAccessException e) {
                return null;
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof InterruptedException || e.getTargetException() instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                return null;
            } catch (RuntimeException e) {
                return null;
            }
        }
        throw new AttributeNotFoundException("Cannot find " + attributeName + " attribute in " + this.dClassName);
    }

    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        if (attribute == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute cannot be null"), "Cannot invoke a setter of " + this.dClassName + " with null attribute");
        }
        String name = attribute.getName();
        Object value = attribute.getValue();
        if (name == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), "Cannot invoke the setter of " + this.dClassName + " with null attribute name");
        }
        MethodUnion mu = (MethodUnion)this.dynamicProps.get(name);
        if (mu != null && mu.writeMethod != null) {
            Object[] o = new Object[1];
            Class<?>[] params = mu.writeMethod.getParameterTypes();
            if (params[0] == Priority.class) {
                value = OptionConverter.toLevel((String)value, (Level)this.getAttribute(name));
            }
            o[0] = value;
            try {
                mu.writeMethod.invoke(this.appender, o);
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof InterruptedException || e.getTargetException() instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                cat.error("FIXME", e);
            } catch (IllegalAccessException e) {
                cat.error("FIXME", e);
            } catch (RuntimeException e) {
                cat.error("FIXME", e);
            }
        } else if (!name.endsWith(".layout")) {
            throw new AttributeNotFoundException("Attribute " + name + " not found in " + this.getClass().getName());
        }
    }

    public ObjectName preRegister(MBeanServer server, ObjectName name) {
        cat.debug("preRegister called. Server=" + server + ", name=" + name);
        this.server = server;
        this.registerLayoutMBean(this.appender.getLayout());
        return name;
    }
}

