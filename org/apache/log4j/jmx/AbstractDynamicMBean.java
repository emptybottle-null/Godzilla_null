/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.jmx;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.DynamicMBean;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.JMException;
import javax.management.MBeanRegistration;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.RuntimeOperationsException;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

public abstract class AbstractDynamicMBean
implements DynamicMBean,
MBeanRegistration {
    String dClassName;
    MBeanServer server;
    private final Vector mbeanList = new Vector();

    protected static String getAppenderName(Appender appender) {
        String name = appender.getName();
        if (name == null || name.trim().length() == 0) {
            name = appender.toString();
        }
        return name;
    }

    public AttributeList getAttributes(String[] attributeNames) {
        if (attributeNames == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("attributeNames[] cannot be null"), "Cannot invoke a getter of " + this.dClassName);
        }
        AttributeList resultList = new AttributeList();
        if (attributeNames.length == 0) {
            return resultList;
        }
        for (int i = 0; i < attributeNames.length; ++i) {
            try {
                Object value = this.getAttribute(attributeNames[i]);
                resultList.add(new Attribute(attributeNames[i], value));
                continue;
            } catch (JMException e) {
                e.printStackTrace();
                continue;
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public AttributeList setAttributes(AttributeList attributes) {
        if (attributes == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("AttributeList attributes cannot be null"), "Cannot invoke a setter of " + this.dClassName);
        }
        AttributeList resultList = new AttributeList();
        if (attributes.isEmpty()) {
            return resultList;
        }
        Iterator i = attributes.iterator();
        while (i.hasNext()) {
            Attribute attr = (Attribute)i.next();
            try {
                this.setAttribute(attr);
                String name = attr.getName();
                Object value = this.getAttribute(name);
                resultList.add(new Attribute(name, value));
            } catch (JMException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    protected abstract Logger getLogger();

    public void postDeregister() {
        this.getLogger().debug("postDeregister is called.");
    }

    public void postRegister(Boolean registrationDone) {
    }

    public ObjectName preRegister(MBeanServer server, ObjectName name) {
        this.getLogger().debug("preRegister called. Server=" + server + ", name=" + name);
        this.server = server;
        return name;
    }

    protected void registerMBean(Object mbean, ObjectName objectName) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        this.server.registerMBean(mbean, objectName);
        this.mbeanList.add(objectName);
    }

    public void preDeregister() {
        this.getLogger().debug("preDeregister called.");
        Enumeration iterator = this.mbeanList.elements();
        while (iterator.hasMoreElements()) {
            ObjectName name = (ObjectName)iterator.nextElement();
            try {
                this.server.unregisterMBean(name);
            } catch (InstanceNotFoundException e) {
                this.getLogger().warn("Missing MBean " + name.getCanonicalName());
            } catch (MBeanRegistrationException e) {
                this.getLogger().warn("Failed unregistering " + name.getCanonicalName());
            }
        }
    }
}

