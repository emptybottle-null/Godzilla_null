/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.w3.x2000.x09.xmldsig.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.SignatureDocument;
import org.w3.x2000.x09.xmldsig.SignatureType;

public class SignatureDocumentImpl
extends XmlComplexContentImpl
implements SignatureDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SIGNATURE$0 = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");

    public SignatureDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignatureType getSignature() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignatureType signatureType = null;
            signatureType = (SignatureType)((Object)this.get_store().find_element_user(SIGNATURE$0, 0));
            if (signatureType == null) {
                return null;
            }
            return signatureType;
        }
    }

    @Override
    public void setSignature(SignatureType signatureType) {
        this.generatedSetterHelperImpl(signatureType, SIGNATURE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SignatureType addNewSignature() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SignatureType signatureType = null;
            signatureType = (SignatureType)((Object)this.get_store().add_element_user(SIGNATURE$0));
            return signatureType;
        }
    }
}

