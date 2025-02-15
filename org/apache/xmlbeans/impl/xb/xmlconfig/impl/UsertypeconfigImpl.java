/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;

public class UsertypeconfigImpl
extends XmlComplexContentImpl
implements Usertypeconfig {
    private static final long serialVersionUID = 1L;
    private static final QName STATICHANDLER$0 = new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "staticHandler");
    private static final QName NAME$2 = new QName("", "name");
    private static final QName JAVANAME$4 = new QName("", "javaname");

    public UsertypeconfigImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getStaticHandler() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(STATICHANDLER$0, 0));
            if (target == null) {
                return null;
            }
            return target.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetStaticHandler() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(STATICHANDLER$0, 0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStaticHandler(String staticHandler) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_element_user(STATICHANDLER$0, 0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_element_user(STATICHANDLER$0));
            }
            target.setStringValue(staticHandler);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStaticHandler(XmlString staticHandler) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_element_user(STATICHANDLER$0, 0));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_element_user(STATICHANDLER$0));
            }
            target.set(staticHandler);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public QName getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$2));
            if (target == null) {
                return null;
            }
            return target.getQNameValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlQName xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(NAME$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(NAME$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(QName name) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$2));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$2));
            }
            target.setQNameValue(name);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(XmlQName name) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlQName target = null;
            target = (XmlQName)((Object)this.get_store().find_attribute_user(NAME$2));
            if (target == null) {
                target = (XmlQName)((Object)this.get_store().add_attribute_user(NAME$2));
            }
            target.set(name);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(NAME$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getJavaname() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(JAVANAME$4));
            if (target == null) {
                return null;
            }
            return target.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetJavaname() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_attribute_user(JAVANAME$4));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetJavaname() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(JAVANAME$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setJavaname(String javaname) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(JAVANAME$4));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(JAVANAME$4));
            }
            target.setStringValue(javaname);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetJavaname(XmlString javaname) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString target = null;
            target = (XmlString)((Object)this.get_store().find_attribute_user(JAVANAME$4));
            if (target == null) {
                target = (XmlString)((Object)this.get_store().add_attribute_user(JAVANAME$4));
            }
            target.set(javaname);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetJavaname() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(JAVANAME$4);
        }
    }
}

