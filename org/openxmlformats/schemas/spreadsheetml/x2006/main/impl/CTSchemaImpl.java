/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSchema;

public class CTSchemaImpl
extends XmlComplexContentImpl
implements CTSchema {
    private static final long serialVersionUID = 1L;
    private static final QName ID$0 = new QName("", "ID");
    private static final QName SCHEMAREF$2 = new QName("", "SchemaRef");
    private static final QName NAMESPACE$4 = new QName("", "Namespace");

    public CTSchemaImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getID() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetID() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(ID$0));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setID(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetID(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(ID$0));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(ID$0));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getSchemaRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SCHEMAREF$2));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetSchemaRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(SCHEMAREF$2));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSchemaRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SCHEMAREF$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSchemaRef(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SCHEMAREF$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SCHEMAREF$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSchemaRef(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(SCHEMAREF$2));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(SCHEMAREF$2));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSchemaRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SCHEMAREF$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAMESPACE$4));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlString xgetNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString = null;
            xmlString = (XmlString)((Object)this.get_store().find_attribute_user(NAMESPACE$4));
            return xmlString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(NAMESPACE$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setNamespace(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAMESPACE$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NAMESPACE$4));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetNamespace(XmlString xmlString) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlString xmlString2 = null;
            xmlString2 = (XmlString)((Object)this.get_store().find_attribute_user(NAMESPACE$4));
            if (xmlString2 == null) {
                xmlString2 = (XmlString)((Object)this.get_store().add_attribute_user(NAMESPACE$4));
            }
            xmlString2.set(xmlString);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNamespace() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(NAMESPACE$4);
        }
    }
}

