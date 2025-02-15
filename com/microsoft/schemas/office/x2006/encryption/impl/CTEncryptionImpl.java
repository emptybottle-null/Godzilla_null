/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption.impl;

import com.microsoft.schemas.office.x2006.encryption.CTDataIntegrity;
import com.microsoft.schemas.office.x2006.encryption.CTEncryption;
import com.microsoft.schemas.office.x2006.encryption.CTKeyData;
import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptors;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTEncryptionImpl
extends XmlComplexContentImpl
implements CTEncryption {
    private static final long serialVersionUID = 1L;
    private static final QName KEYDATA$0 = new QName("http://schemas.microsoft.com/office/2006/encryption", "keyData");
    private static final QName DATAINTEGRITY$2 = new QName("http://schemas.microsoft.com/office/2006/encryption", "dataIntegrity");
    private static final QName KEYENCRYPTORS$4 = new QName("http://schemas.microsoft.com/office/2006/encryption", "keyEncryptors");

    public CTEncryptionImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKeyData getKeyData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKeyData cTKeyData = null;
            cTKeyData = (CTKeyData)((Object)this.get_store().find_element_user(KEYDATA$0, 0));
            if (cTKeyData == null) {
                return null;
            }
            return cTKeyData;
        }
    }

    @Override
    public void setKeyData(CTKeyData cTKeyData) {
        this.generatedSetterHelperImpl(cTKeyData, KEYDATA$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKeyData addNewKeyData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKeyData cTKeyData = null;
            cTKeyData = (CTKeyData)((Object)this.get_store().add_element_user(KEYDATA$0));
            return cTKeyData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataIntegrity getDataIntegrity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataIntegrity cTDataIntegrity = null;
            cTDataIntegrity = (CTDataIntegrity)((Object)this.get_store().find_element_user(DATAINTEGRITY$2, 0));
            if (cTDataIntegrity == null) {
                return null;
            }
            return cTDataIntegrity;
        }
    }

    @Override
    public void setDataIntegrity(CTDataIntegrity cTDataIntegrity) {
        this.generatedSetterHelperImpl(cTDataIntegrity, DATAINTEGRITY$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataIntegrity addNewDataIntegrity() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataIntegrity cTDataIntegrity = null;
            cTDataIntegrity = (CTDataIntegrity)((Object)this.get_store().add_element_user(DATAINTEGRITY$2));
            return cTDataIntegrity;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKeyEncryptors getKeyEncryptors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKeyEncryptors cTKeyEncryptors = null;
            cTKeyEncryptors = (CTKeyEncryptors)((Object)this.get_store().find_element_user(KEYENCRYPTORS$4, 0));
            if (cTKeyEncryptors == null) {
                return null;
            }
            return cTKeyEncryptors;
        }
    }

    @Override
    public void setKeyEncryptors(CTKeyEncryptors cTKeyEncryptors) {
        this.generatedSetterHelperImpl(cTKeyEncryptors, KEYENCRYPTORS$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKeyEncryptors addNewKeyEncryptors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKeyEncryptors cTKeyEncryptors = null;
            cTKeyEncryptors = (CTKeyEncryptors)((Object)this.get_store().add_element_user(KEYENCRYPTORS$4));
            return cTKeyEncryptors;
        }
    }
}

