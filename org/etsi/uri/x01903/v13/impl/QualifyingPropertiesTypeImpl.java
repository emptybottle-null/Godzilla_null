/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.SignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;

public class QualifyingPropertiesTypeImpl
extends XmlComplexContentImpl
implements QualifyingPropertiesType {
    private static final long serialVersionUID = 1L;
    private static final QName SIGNEDPROPERTIES$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignedProperties");
    private static final QName UNSIGNEDPROPERTIES$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedProperties");
    private static final QName TARGET$4 = new QName("", "Target");
    private static final QName ID$6 = new QName("", "Id");

    public QualifyingPropertiesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignedPropertiesType getSignedProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignedPropertiesType signedPropertiesType = null;
            signedPropertiesType = (SignedPropertiesType)((Object)this.get_store().find_element_user(SIGNEDPROPERTIES$0, 0));
            if (signedPropertiesType == null) {
                return null;
            }
            return signedPropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSignedProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNEDPROPERTIES$0) != 0;
        }
    }

    @Override
    public void setSignedProperties(SignedPropertiesType signedPropertiesType) {
        this.generatedSetterHelperImpl(signedPropertiesType, SIGNEDPROPERTIES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignedPropertiesType addNewSignedProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignedPropertiesType signedPropertiesType = null;
            signedPropertiesType = (SignedPropertiesType)((Object)this.get_store().add_element_user(SIGNEDPROPERTIES$0));
            return signedPropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSignedProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNEDPROPERTIES$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public UnsignedPropertiesType getUnsignedProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            UnsignedPropertiesType unsignedPropertiesType = null;
            unsignedPropertiesType = (UnsignedPropertiesType)((Object)this.get_store().find_element_user(UNSIGNEDPROPERTIES$2, 0));
            if (unsignedPropertiesType == null) {
                return null;
            }
            return unsignedPropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUnsignedProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(UNSIGNEDPROPERTIES$2) != 0;
        }
    }

    @Override
    public void setUnsignedProperties(UnsignedPropertiesType unsignedPropertiesType) {
        this.generatedSetterHelperImpl(unsignedPropertiesType, UNSIGNEDPROPERTIES$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public UnsignedPropertiesType addNewUnsignedProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            UnsignedPropertiesType unsignedPropertiesType = null;
            unsignedPropertiesType = (UnsignedPropertiesType)((Object)this.get_store().add_element_user(UNSIGNEDPROPERTIES$2));
            return unsignedPropertiesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUnsignedProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(UNSIGNEDPROPERTIES$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TARGET$4));
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
    public XmlAnyURI xgetTarget() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI = null;
            xmlAnyURI = (XmlAnyURI)((Object)this.get_store().find_attribute_user(TARGET$4));
            return xmlAnyURI;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTarget(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TARGET$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TARGET$4));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetTarget(XmlAnyURI xmlAnyURI) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI2 = null;
            xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().find_attribute_user(TARGET$4));
            if (xmlAnyURI2 == null) {
                xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().add_attribute_user(TARGET$4));
            }
            xmlAnyURI2.set(xmlAnyURI);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$6));
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
            xmlID = (XmlID)((Object)this.get_store().find_attribute_user(ID$6));
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
            return this.get_store().find_attribute_user(ID$6) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$6));
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
            xmlID2 = (XmlID)((Object)this.get_store().find_attribute_user(ID$6));
            if (xmlID2 == null) {
                xmlID2 = (XmlID)((Object)this.get_store().add_attribute_user(ID$6));
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
            this.get_store().remove_attribute(ID$6);
        }
    }
}

