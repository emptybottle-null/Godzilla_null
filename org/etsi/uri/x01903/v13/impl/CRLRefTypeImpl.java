/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLIdentifierType;
import org.etsi.uri.x01903.v13.CRLRefType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;

public class CRLRefTypeImpl
extends XmlComplexContentImpl
implements CRLRefType {
    private static final long serialVersionUID = 1L;
    private static final QName DIGESTALGANDVALUE$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "DigestAlgAndValue");
    private static final QName CRLIDENTIFIER$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CRLIdentifier");

    public CRLRefTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DigestAlgAndValueType getDigestAlgAndValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestAlgAndValueType digestAlgAndValueType = null;
            digestAlgAndValueType = (DigestAlgAndValueType)((Object)this.get_store().find_element_user(DIGESTALGANDVALUE$0, 0));
            if (digestAlgAndValueType == null) {
                return null;
            }
            return digestAlgAndValueType;
        }
    }

    @Override
    public void setDigestAlgAndValue(DigestAlgAndValueType digestAlgAndValueType) {
        this.generatedSetterHelperImpl(digestAlgAndValueType, DIGESTALGANDVALUE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public DigestAlgAndValueType addNewDigestAlgAndValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            DigestAlgAndValueType digestAlgAndValueType = null;
            digestAlgAndValueType = (DigestAlgAndValueType)((Object)this.get_store().add_element_user(DIGESTALGANDVALUE$0));
            return digestAlgAndValueType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CRLIdentifierType getCRLIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CRLIdentifierType cRLIdentifierType = null;
            cRLIdentifierType = (CRLIdentifierType)((Object)this.get_store().find_element_user(CRLIDENTIFIER$2, 0));
            if (cRLIdentifierType == null) {
                return null;
            }
            return cRLIdentifierType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCRLIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CRLIDENTIFIER$2) != 0;
        }
    }

    @Override
    public void setCRLIdentifier(CRLIdentifierType cRLIdentifierType) {
        this.generatedSetterHelperImpl(cRLIdentifierType, CRLIDENTIFIER$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CRLIdentifierType addNewCRLIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CRLIdentifierType cRLIdentifierType = null;
            cRLIdentifierType = (CRLIdentifierType)((Object)this.get_store().add_element_user(CRLIDENTIFIER$2));
            return cRLIdentifierType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCRLIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CRLIDENTIFIER$2, 0);
        }
    }
}

