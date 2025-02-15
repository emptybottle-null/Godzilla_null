/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.xpackage.x2006.digitalSignature.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.SignatureTimeDocument;

public class SignatureTimeDocumentImpl
extends XmlComplexContentImpl
implements SignatureTimeDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SIGNATURETIME$0 = new QName("http://schemas.openxmlformats.org/package/2006/digital-signature", "SignatureTime");

    public SignatureTimeDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignatureTime getSignatureTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignatureTime cTSignatureTime = null;
            cTSignatureTime = (CTSignatureTime)((Object)this.get_store().find_element_user(SIGNATURETIME$0, 0));
            if (cTSignatureTime == null) {
                return null;
            }
            return cTSignatureTime;
        }
    }

    @Override
    public void setSignatureTime(CTSignatureTime cTSignatureTime) {
        this.generatedSetterHelperImpl(cTSignatureTime, SIGNATURETIME$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSignatureTime addNewSignatureTime() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSignatureTime cTSignatureTime = null;
            cTSignatureTime = (CTSignatureTime)((Object)this.get_store().add_element_user(SIGNATURETIME$0));
            return cTSignatureTime;
        }
    }
}

