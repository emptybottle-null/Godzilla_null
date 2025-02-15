/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.SignaturePolicyIdType
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.SignaturePolicyIdType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType;

public class SignaturePolicyIdentifierTypeImpl
extends XmlComplexContentImpl
implements SignaturePolicyIdentifierType {
    private static final long serialVersionUID = 1L;
    private static final QName SIGNATUREPOLICYID$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignaturePolicyId");
    private static final QName SIGNATUREPOLICYIMPLIED$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignaturePolicyImplied");

    public SignaturePolicyIdentifierTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignaturePolicyIdType getSignaturePolicyId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignaturePolicyIdType signaturePolicyIdType = null;
            signaturePolicyIdType = (SignaturePolicyIdType)this.get_store().find_element_user(SIGNATUREPOLICYID$0, 0);
            if (signaturePolicyIdType == null) {
                return null;
            }
            return signaturePolicyIdType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSignaturePolicyId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNATUREPOLICYID$0) != 0;
        }
    }

    @Override
    public void setSignaturePolicyId(SignaturePolicyIdType signaturePolicyIdType) {
        this.generatedSetterHelperImpl((XmlObject)signaturePolicyIdType, SIGNATUREPOLICYID$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignaturePolicyIdType addNewSignaturePolicyId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignaturePolicyIdType signaturePolicyIdType = null;
            signaturePolicyIdType = (SignaturePolicyIdType)this.get_store().add_element_user(SIGNATUREPOLICYID$0);
            return signaturePolicyIdType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSignaturePolicyId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNATUREPOLICYID$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject getSignaturePolicyImplied() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlObject xmlObject = null;
            xmlObject = (XmlObject)((Object)this.get_store().find_element_user(SIGNATUREPOLICYIMPLIED$2, 0));
            if (xmlObject == null) {
                return null;
            }
            return xmlObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSignaturePolicyImplied() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIGNATUREPOLICYIMPLIED$2) != 0;
        }
    }

    @Override
    public void setSignaturePolicyImplied(XmlObject xmlObject) {
        this.generatedSetterHelperImpl(xmlObject, SIGNATUREPOLICYIMPLIED$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject addNewSignaturePolicyImplied() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlObject xmlObject = null;
            xmlObject = (XmlObject)((Object)this.get_store().add_element_user(SIGNATUREPOLICYIMPLIED$2));
            return xmlObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSignaturePolicyImplied() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIGNATUREPOLICYIMPLIED$2, 0);
        }
    }
}

