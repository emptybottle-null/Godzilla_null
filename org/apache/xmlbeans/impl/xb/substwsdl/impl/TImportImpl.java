/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.substwsdl.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.substwsdl.TImport;

public class TImportImpl
extends XmlComplexContentImpl
implements TImport {
    private static final long serialVersionUID = 1L;
    private static final QName NAMESPACE$0 = new QName("", "namespace");
    private static final QName LOCATION$2 = new QName("", "location");

    public TImportImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
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
    public XmlAnyURI xgetNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI target = null;
            target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNamespace(String namespace) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(NAMESPACE$0));
            }
            target.setStringValue(namespace);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNamespace(XmlAnyURI namespace) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI target = null;
            target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(NAMESPACE$0));
            if (target == null) {
                target = (XmlAnyURI)((Object)this.get_store().add_attribute_user(NAMESPACE$0));
            }
            target.set(namespace);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getLocation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(LOCATION$2));
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
    public XmlAnyURI xgetLocation() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI target = null;
            target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(LOCATION$2));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLocation(String location) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(LOCATION$2));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(LOCATION$2));
            }
            target.setStringValue(location);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLocation(XmlAnyURI location) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI target = null;
            target = (XmlAnyURI)((Object)this.get_store().find_attribute_user(LOCATION$2));
            if (target == null) {
                target = (XmlAnyURI)((Object)this.get_store().add_attribute_user(LOCATION$2));
            }
            target.set(location);
        }
    }
}

