/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.OtherCertStatusRefsType
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.OCSPRefsType;
import org.etsi.uri.x01903.v13.OtherCertStatusRefsType;

public class CompleteRevocationRefsTypeImpl
extends XmlComplexContentImpl
implements CompleteRevocationRefsType {
    private static final long serialVersionUID = 1L;
    private static final QName CRLREFS$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CRLRefs");
    private static final QName OCSPREFS$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "OCSPRefs");
    private static final QName OTHERREFS$4 = new QName("http://uri.etsi.org/01903/v1.3.2#", "OtherRefs");
    private static final QName ID$6 = new QName("", "Id");

    public CompleteRevocationRefsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CRLRefsType getCRLRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CRLRefsType cRLRefsType = null;
            cRLRefsType = (CRLRefsType)((Object)this.get_store().find_element_user(CRLREFS$0, 0));
            if (cRLRefsType == null) {
                return null;
            }
            return cRLRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCRLRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CRLREFS$0) != 0;
        }
    }

    @Override
    public void setCRLRefs(CRLRefsType cRLRefsType) {
        this.generatedSetterHelperImpl(cRLRefsType, CRLREFS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CRLRefsType addNewCRLRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CRLRefsType cRLRefsType = null;
            cRLRefsType = (CRLRefsType)((Object)this.get_store().add_element_user(CRLREFS$0));
            return cRLRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCRLRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CRLREFS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OCSPRefsType getOCSPRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OCSPRefsType oCSPRefsType = null;
            oCSPRefsType = (OCSPRefsType)((Object)this.get_store().find_element_user(OCSPREFS$2, 0));
            if (oCSPRefsType == null) {
                return null;
            }
            return oCSPRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOCSPRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OCSPREFS$2) != 0;
        }
    }

    @Override
    public void setOCSPRefs(OCSPRefsType oCSPRefsType) {
        this.generatedSetterHelperImpl(oCSPRefsType, OCSPREFS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OCSPRefsType addNewOCSPRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OCSPRefsType oCSPRefsType = null;
            oCSPRefsType = (OCSPRefsType)((Object)this.get_store().add_element_user(OCSPREFS$2));
            return oCSPRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOCSPRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OCSPREFS$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OtherCertStatusRefsType getOtherRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OtherCertStatusRefsType otherCertStatusRefsType = null;
            otherCertStatusRefsType = (OtherCertStatusRefsType)this.get_store().find_element_user(OTHERREFS$4, 0);
            if (otherCertStatusRefsType == null) {
                return null;
            }
            return otherCertStatusRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOtherRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OTHERREFS$4) != 0;
        }
    }

    @Override
    public void setOtherRefs(OtherCertStatusRefsType otherCertStatusRefsType) {
        this.generatedSetterHelperImpl((XmlObject)otherCertStatusRefsType, OTHERREFS$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OtherCertStatusRefsType addNewOtherRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OtherCertStatusRefsType otherCertStatusRefsType = null;
            otherCertStatusRefsType = (OtherCertStatusRefsType)this.get_store().add_element_user(OTHERREFS$4);
            return otherCertStatusRefsType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOtherRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OTHERREFS$4, 0);
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

