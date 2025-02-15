/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.OtherCertStatusValuesType
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.OCSPValuesType;
import org.etsi.uri.x01903.v13.OtherCertStatusValuesType;
import org.etsi.uri.x01903.v13.RevocationValuesType;

public class RevocationValuesTypeImpl
extends XmlComplexContentImpl
implements RevocationValuesType {
    private static final long serialVersionUID = 1L;
    private static final QName CRLVALUES$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CRLValues");
    private static final QName OCSPVALUES$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "OCSPValues");
    private static final QName OTHERVALUES$4 = new QName("http://uri.etsi.org/01903/v1.3.2#", "OtherValues");
    private static final QName ID$6 = new QName("", "Id");

    public RevocationValuesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CRLValuesType getCRLValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CRLValuesType cRLValuesType = null;
            cRLValuesType = (CRLValuesType)((Object)this.get_store().find_element_user(CRLVALUES$0, 0));
            if (cRLValuesType == null) {
                return null;
            }
            return cRLValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCRLValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CRLVALUES$0) != 0;
        }
    }

    @Override
    public void setCRLValues(CRLValuesType cRLValuesType) {
        this.generatedSetterHelperImpl(cRLValuesType, CRLVALUES$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CRLValuesType addNewCRLValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CRLValuesType cRLValuesType = null;
            cRLValuesType = (CRLValuesType)((Object)this.get_store().add_element_user(CRLVALUES$0));
            return cRLValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCRLValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CRLVALUES$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OCSPValuesType getOCSPValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OCSPValuesType oCSPValuesType = null;
            oCSPValuesType = (OCSPValuesType)((Object)this.get_store().find_element_user(OCSPVALUES$2, 0));
            if (oCSPValuesType == null) {
                return null;
            }
            return oCSPValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOCSPValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OCSPVALUES$2) != 0;
        }
    }

    @Override
    public void setOCSPValues(OCSPValuesType oCSPValuesType) {
        this.generatedSetterHelperImpl(oCSPValuesType, OCSPVALUES$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OCSPValuesType addNewOCSPValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OCSPValuesType oCSPValuesType = null;
            oCSPValuesType = (OCSPValuesType)((Object)this.get_store().add_element_user(OCSPVALUES$2));
            return oCSPValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOCSPValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OCSPVALUES$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OtherCertStatusValuesType getOtherValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OtherCertStatusValuesType otherCertStatusValuesType = null;
            otherCertStatusValuesType = (OtherCertStatusValuesType)this.get_store().find_element_user(OTHERVALUES$4, 0);
            if (otherCertStatusValuesType == null) {
                return null;
            }
            return otherCertStatusValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOtherValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OTHERVALUES$4) != 0;
        }
    }

    @Override
    public void setOtherValues(OtherCertStatusValuesType otherCertStatusValuesType) {
        this.generatedSetterHelperImpl((XmlObject)otherCertStatusValuesType, OTHERVALUES$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OtherCertStatusValuesType addNewOtherValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OtherCertStatusValuesType otherCertStatusValuesType = null;
            otherCertStatusValuesType = (OtherCertStatusValuesType)this.get_store().add_element_user(OTHERVALUES$4);
            return otherCertStatusValuesType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOtherValues() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OTHERVALUES$4, 0);
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

