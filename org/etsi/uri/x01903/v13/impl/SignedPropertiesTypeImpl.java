/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType;
import org.etsi.uri.x01903.v13.SignedPropertiesType;
import org.etsi.uri.x01903.v13.SignedSignaturePropertiesType;

public class SignedPropertiesTypeImpl
extends XmlComplexContentImpl
implements SignedPropertiesType {
    private static final long serialVersionUID = 1L;
    private static final QName SIGNEDSIGNATUREPROPERTIES$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignedSignatureProperties");
    private static final QName SIGNEDDATAOBJECTPROPERTIES$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignedDataObjectProperties");
    private static final QName ID$4 = new QName("", "Id");

    public SignedPropertiesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignedSignaturePropertiesType getSignedSignatureProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignedSignaturePropertiesType signedSignaturePropertiesType = null;
            signedSignaturePropertiesType = (SignedSignaturePropertiesType)((Object)this.get_store().find_element_user(SIGNEDSIGNATUREPROPERTIES$0, 0));
            if (signedSignaturePropertiesType == null) {
                return null;
            }
            return signedSignaturePropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSignedSignatureProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNEDSIGNATUREPROPERTIES$0) != 0;
        }
    }

    @Override
    public void setSignedSignatureProperties(SignedSignaturePropertiesType signedSignaturePropertiesType) {
        this.generatedSetterHelperImpl(signedSignaturePropertiesType, SIGNEDSIGNATUREPROPERTIES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignedSignaturePropertiesType addNewSignedSignatureProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignedSignaturePropertiesType signedSignaturePropertiesType = null;
            signedSignaturePropertiesType = (SignedSignaturePropertiesType)((Object)this.get_store().add_element_user(SIGNEDSIGNATUREPROPERTIES$0));
            return signedSignaturePropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSignedSignatureProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNEDSIGNATUREPROPERTIES$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignedDataObjectPropertiesType getSignedDataObjectProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignedDataObjectPropertiesType signedDataObjectPropertiesType = null;
            signedDataObjectPropertiesType = (SignedDataObjectPropertiesType)this.get_store().find_element_user(SIGNEDDATAOBJECTPROPERTIES$2, 0);
            if (signedDataObjectPropertiesType == null) {
                return null;
            }
            return signedDataObjectPropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSignedDataObjectProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNEDDATAOBJECTPROPERTIES$2) != 0;
        }
    }

    @Override
    public void setSignedDataObjectProperties(SignedDataObjectPropertiesType signedDataObjectPropertiesType) {
        this.generatedSetterHelperImpl((XmlObject)signedDataObjectPropertiesType, SIGNEDDATAOBJECTPROPERTIES$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignedDataObjectPropertiesType addNewSignedDataObjectProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignedDataObjectPropertiesType signedDataObjectPropertiesType = null;
            signedDataObjectPropertiesType = (SignedDataObjectPropertiesType)this.get_store().add_element_user(SIGNEDDATAOBJECTPROPERTIES$2);
            return signedDataObjectPropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSignedDataObjectProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNEDDATAOBJECTPROPERTIES$2, 0);
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

