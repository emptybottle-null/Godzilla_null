/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;

public class UnsignedPropertiesTypeImpl
extends XmlComplexContentImpl
implements UnsignedPropertiesType {
    private static final long serialVersionUID = 1L;
    private static final QName UNSIGNEDSIGNATUREPROPERTIES$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedSignatureProperties");
    private static final QName UNSIGNEDDATAOBJECTPROPERTIES$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedDataObjectProperties");
    private static final QName ID$4 = new QName("", "Id");

    public UnsignedPropertiesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public UnsignedSignaturePropertiesType getUnsignedSignatureProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            UnsignedSignaturePropertiesType unsignedSignaturePropertiesType = null;
            unsignedSignaturePropertiesType = (UnsignedSignaturePropertiesType)((Object)this.get_store().find_element_user(UNSIGNEDSIGNATUREPROPERTIES$0, 0));
            if (unsignedSignaturePropertiesType == null) {
                return null;
            }
            return unsignedSignaturePropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUnsignedSignatureProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UNSIGNEDSIGNATUREPROPERTIES$0) != 0;
        }
    }

    @Override
    public void setUnsignedSignatureProperties(UnsignedSignaturePropertiesType unsignedSignaturePropertiesType) {
        this.generatedSetterHelperImpl(unsignedSignaturePropertiesType, UNSIGNEDSIGNATUREPROPERTIES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public UnsignedSignaturePropertiesType addNewUnsignedSignatureProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            UnsignedSignaturePropertiesType unsignedSignaturePropertiesType = null;
            unsignedSignaturePropertiesType = (UnsignedSignaturePropertiesType)((Object)this.get_store().add_element_user(UNSIGNEDSIGNATUREPROPERTIES$0));
            return unsignedSignaturePropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUnsignedSignatureProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UNSIGNEDSIGNATUREPROPERTIES$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public UnsignedDataObjectPropertiesType getUnsignedDataObjectProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            UnsignedDataObjectPropertiesType unsignedDataObjectPropertiesType = null;
            unsignedDataObjectPropertiesType = (UnsignedDataObjectPropertiesType)this.get_store().find_element_user(UNSIGNEDDATAOBJECTPROPERTIES$2, 0);
            if (unsignedDataObjectPropertiesType == null) {
                return null;
            }
            return unsignedDataObjectPropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUnsignedDataObjectProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UNSIGNEDDATAOBJECTPROPERTIES$2) != 0;
        }
    }

    @Override
    public void setUnsignedDataObjectProperties(UnsignedDataObjectPropertiesType unsignedDataObjectPropertiesType) {
        this.generatedSetterHelperImpl((XmlObject)unsignedDataObjectPropertiesType, UNSIGNEDDATAOBJECTPROPERTIES$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public UnsignedDataObjectPropertiesType addNewUnsignedDataObjectProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            UnsignedDataObjectPropertiesType unsignedDataObjectPropertiesType = null;
            unsignedDataObjectPropertiesType = (UnsignedDataObjectPropertiesType)this.get_store().add_element_user(UNSIGNEDDATAOBJECTPROPERTIES$2);
            return unsignedDataObjectPropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUnsignedDataObjectProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UNSIGNEDDATAOBJECTPROPERTIES$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$4));
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
    public XmlID xgetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlID xmlID = null;
            xmlID = (XmlID)((Object)this.get_store().find_attribute_user(ID$4));
            return xmlID;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ID$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setId(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$4));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetId(XmlID xmlID) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlID xmlID2 = null;
            xmlID2 = (XmlID)((Object)this.get_store().find_attribute_user(ID$4));
            if (xmlID2 == null) {
                xmlID2 = (XmlID)((Object)this.get_store().add_attribute_user(ID$4));
            }
            xmlID2.set(xmlID);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ID$4);
        }
    }
}

