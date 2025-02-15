/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;

public class CompleteCertificateRefsTypeImpl
extends XmlComplexContentImpl
implements CompleteCertificateRefsType {
    private static final long serialVersionUID = 1L;
    private static final QName CERTREFS$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CertRefs");
    private static final QName ID$2 = new QName("", "Id");

    public CompleteCertificateRefsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertIDListType getCertRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertIDListType certIDListType = null;
            certIDListType = (CertIDListType)((Object)this.get_store().find_element_user(CERTREFS$0, 0));
            if (certIDListType == null) {
                return null;
            }
            return certIDListType;
        }
    }

    @Override
    public void setCertRefs(CertIDListType certIDListType) {
        this.generatedSetterHelperImpl(certIDListType, CERTREFS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertIDListType addNewCertRefs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertIDListType certIDListType = null;
            certIDListType = (CertIDListType)((Object)this.get_store().add_element_user(CERTREFS$0));
            return certIDListType;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$2));
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
            xmlID = (XmlID)((Object)this.get_store().find_attribute_user(ID$2));
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
            return this.get_store().find_attribute_user(ID$2) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ID$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ID$2));
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
            xmlID2 = (XmlID)((Object)this.get_store().find_attribute_user(ID$2));
            if (xmlID2 == null) {
                xmlID2 = (XmlID)((Object)this.get_store().add_attribute_user(ID$2));
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
            this.get_store().remove_attribute(ID$2);
        }
    }
}

