/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption.impl;

import com.microsoft.schemas.office.x2006.encryption.CTEncryption;
import com.microsoft.schemas.office.x2006.encryption.EncryptionDocument;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class EncryptionDocumentImpl
extends XmlComplexContentImpl
implements EncryptionDocument {
    private static final long serialVersionUID = 1L;
    private static final QName ENCRYPTION$0 = new QName("http://schemas.microsoft.com/office/2006/encryption", "encryption");

    public EncryptionDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEncryption getEncryption() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEncryption cTEncryption = null;
            cTEncryption = (CTEncryption)((Object)this.get_store().find_element_user(ENCRYPTION$0, 0));
            if (cTEncryption == null) {
                return null;
            }
            return cTEncryption;
        }
    }

    @Override
    public void setEncryption(CTEncryption cTEncryption) {
        this.generatedSetterHelperImpl(cTEncryption, ENCRYPTION$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEncryption addNewEncryption() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEncryption cTEncryption = null;
            cTEncryption = (CTEncryption)((Object)this.get_store().add_element_user(ENCRYPTION$0));
            return cTEncryption;
        }
    }
}

