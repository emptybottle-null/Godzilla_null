/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.OCSPIdentifierType;
import org.etsi.uri.x01903.v13.OCSPRefType;

public class OCSPRefTypeImpl
extends XmlComplexContentImpl
implements OCSPRefType {
    private static final long serialVersionUID = 1L;
    private static final QName OCSPIDENTIFIER$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "OCSPIdentifier");
    private static final QName DIGESTALGANDVALUE$2 = new QName("http://uri.etsi.org/01903/v1.3.2#", "DigestAlgAndValue");

    public OCSPRefTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OCSPIdentifierType getOCSPIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OCSPIdentifierType oCSPIdentifierType = null;
            oCSPIdentifierType = (OCSPIdentifierType)((Object)this.get_store().find_element_user(OCSPIDENTIFIER$0, 0));
            if (oCSPIdentifierType == null) {
                return null;
            }
            return oCSPIdentifierType;
        }
    }

    @Override
    public void setOCSPIdentifier(OCSPIdentifierType oCSPIdentifierType) {
        this.generatedSetterHelperImpl(oCSPIdentifierType, OCSPIDENTIFIER$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OCSPIdentifierType addNewOCSPIdentifier() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OCSPIdentifierType oCSPIdentifierType = null;
            oCSPIdentifierType = (OCSPIdentifierType)((Object)this.get_store().add_element_user(OCSPIDENTIFIER$0));
            return oCSPIdentifierType;
        }
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
            digestAlgAndValueType = (DigestAlgAndValueType)((Object)this.get_store().find_element_user(DIGESTALGANDVALUE$2, 0));
            if (digestAlgAndValueType == null) {
                return null;
            }
            return digestAlgAndValueType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDigestAlgAndValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DIGESTALGANDVALUE$2) != 0;
        }
    }

    @Override
    public void setDigestAlgAndValue(DigestAlgAndValueType digestAlgAndValueType) {
        this.generatedSetterHelperImpl(digestAlgAndValueType, DIGESTALGANDVALUE$2, 0, (short)1);
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
            digestAlgAndValueType = (DigestAlgAndValueType)((Object)this.get_store().add_element_user(DIGESTALGANDVALUE$2));
            return digestAlgAndValueType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDigestAlgAndValue() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DIGESTALGANDVALUE$2, 0);
        }
    }
}

