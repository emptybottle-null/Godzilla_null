/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.digsig.impl;

import com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1;
import com.microsoft.schemas.office.x2006.digsig.SignatureInfoV1Document;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class SignatureInfoV1DocumentImpl
extends XmlComplexContentImpl
implements SignatureInfoV1Document {
    private static final long serialVersionUID = 1L;
    private static final QName SIGNATUREINFOV1$0 = new QName("http://schemas.microsoft.com/office/2006/digsig", "SignatureInfoV1");

    public SignatureInfoV1DocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignatureInfoV1 getSignatureInfoV1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignatureInfoV1 cTSignatureInfoV1 = null;
            cTSignatureInfoV1 = (CTSignatureInfoV1)((Object)this.get_store().find_element_user(SIGNATUREINFOV1$0, 0));
            if (cTSignatureInfoV1 == null) {
                return null;
            }
            return cTSignatureInfoV1;
        }
    }

    @Override
    public void setSignatureInfoV1(CTSignatureInfoV1 cTSignatureInfoV1) {
        this.generatedSetterHelperImpl(cTSignatureInfoV1, SIGNATUREINFOV1$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignatureInfoV1 addNewSignatureInfoV1() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignatureInfoV1 cTSignatureInfoV1 = null;
            cTSignatureInfoV1 = (CTSignatureInfoV1)((Object)this.get_store().add_element_user(SIGNATUREINFOV1$0));
            return cTSignatureInfoV1;
        }
    }
}

