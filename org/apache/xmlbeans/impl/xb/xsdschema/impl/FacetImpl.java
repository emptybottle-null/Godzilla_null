/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotatedImpl;

public class FacetImpl
extends AnnotatedImpl
implements Facet {
    private static final long serialVersionUID = 1L;
    private static final QName VALUE$0 = new QName("", "value");
    private static final QName FIXED$2 = new QName("", "fixed");

    public FacetImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlAnySimpleType getValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnySimpleType target = null;
            target = (XmlAnySimpleType)((Object)this.get_store().find_attribute_user(VALUE$0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setValue(XmlAnySimpleType value) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnySimpleType target = null;
            target = (XmlAnySimpleType)((Object)this.get_store().find_attribute_user(VALUE$0));
            if (target == null) {
                target = (XmlAnySimpleType)((Object)this.get_store().add_attribute_user(VALUE$0));
            }
            target.set(value);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlAnySimpleType addNewValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnySimpleType target = null;
            target = (XmlAnySimpleType)((Object)this.get_store().add_attribute_user(VALUE$0));
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getFixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FIXED$2));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_default_attribute_value(FIXED$2));
            }
            if (target == null) {
                return false;
            }
            return target.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetFixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(FIXED$2));
            if (target == null) {
                target = (XmlBoolean)this.get_default_attribute_value(FIXED$2);
            }
            return target;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(FIXED$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFixed(boolean fixed) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue target = null;
            target = (SimpleValue)((Object)this.get_store().find_attribute_user(FIXED$2));
            if (target == null) {
                target = (SimpleValue)((Object)this.get_store().add_attribute_user(FIXED$2));
            }
            target.setBooleanValue(fixed);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFixed(XmlBoolean fixed) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean target = null;
            target = (XmlBoolean)((Object)this.get_store().find_attribute_user(FIXED$2));
            if (target == null) {
                target = (XmlBoolean)((Object)this.get_store().add_attribute_user(FIXED$2));
            }
            target.set(fixed);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFixed() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(FIXED$2);
        }
    }
}

