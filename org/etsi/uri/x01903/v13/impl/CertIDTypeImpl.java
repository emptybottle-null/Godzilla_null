/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertIDType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.w3.x2000.x09.xmldsig.X509IssuerSerialType;

public class CertIDTypeImpl
extends XmlComplexContentImpl
implements CertIDType {
    private static final long serialVersionUID = 1L;
    private static final QName CERTDIGEST$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CertDigest");
    private static final QName ISSUERSERIAL$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "IssuerSerial");
    private static final QName URI$4 = new QName("", "URI");

    public CertIDTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DigestAlgAndValueType getCertDigest() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestAlgAndValueType digestAlgAndValueType = null;
            digestAlgAndValueType = (DigestAlgAndValueType)((Object)this.get_store().find_element_user(CERTDIGEST$0, 0));
            if (digestAlgAndValueType == null) {
                return null;
            }
            return digestAlgAndValueType;
        }
    }

    @Override
    public void setCertDigest(DigestAlgAndValueType digestAlgAndValueType) {
        this.generatedSetterHelperImpl(digestAlgAndValueType, CERTDIGEST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DigestAlgAndValueType addNewCertDigest() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestAlgAndValueType digestAlgAndValueType = null;
            digestAlgAndValueType = (DigestAlgAndValueType)((Object)this.get_store().add_element_user(CERTDIGEST$0));
            return digestAlgAndValueType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public X509IssuerSerialType getIssuerSerial() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            X509IssuerSerialType x509IssuerSerialType = null;
            x509IssuerSerialType = (X509IssuerSerialType)((Object)this.get_store().find_element_user(ISSUERSERIAL$2, 0));
            if (x509IssuerSerialType == null) {
                return null;
            }
            return x509IssuerSerialType;
        }
    }

    @Override
    public void setIssuerSerial(X509IssuerSerialType x509IssuerSerialType) {
        this.generatedSetterHelperImpl(x509IssuerSerialType, ISSUERSERIAL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public X509IssuerSerialType addNewIssuerSerial() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            X509IssuerSerialType x509IssuerSerialType = null;
            x509IssuerSerialType = (X509IssuerSerialType)((Object)this.get_store().add_element_user(ISSUERSERIAL$2));
            return x509IssuerSerialType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getURI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$4));
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
    public XmlAnyURI xgetURI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI = null;
            xmlAnyURI = (XmlAnyURI)((Object)this.get_store().find_attribute_user(URI$4));
            return xmlAnyURI;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetURI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(URI$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setURI(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(URI$4));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetURI(XmlAnyURI xmlAnyURI) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlAnyURI xmlAnyURI2 = null;
            xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().find_attribute_user(URI$4));
            if (xmlAnyURI2 == null) {
                xmlAnyURI2 = (XmlAnyURI)((Object)this.get_store().add_attribute_user(URI$4));
            }
            xmlAnyURI2.set(xmlAnyURI);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetURI() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(URI$4);
        }
    }
}

