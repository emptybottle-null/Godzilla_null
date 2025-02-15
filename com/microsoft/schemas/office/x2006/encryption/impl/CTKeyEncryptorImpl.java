/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption.impl;

import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor;
import com.microsoft.schemas.office.x2006.keyEncryptor.certificate.CTCertificateKeyEncryptor;
import com.microsoft.schemas.office.x2006.keyEncryptor.password.CTPasswordKeyEncryptor;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTKeyEncryptorImpl
extends XmlComplexContentImpl
implements CTKeyEncryptor {
    private static final long serialVersionUID = 1L;
    private static final QName ENCRYPTEDPASSWORDKEY$0 = new QName("http://schemas.microsoft.com/office/2006/keyEncryptor/password", "encryptedKey");
    private static final QName ENCRYPTEDCERTIFICATEKEY$2 = new QName("http://schemas.microsoft.com/office/2006/keyEncryptor/certificate", "encryptedKey");
    private static final QName URI$4 = new QName("", "uri");

    public CTKeyEncryptorImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPasswordKeyEncryptor getEncryptedPasswordKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPasswordKeyEncryptor cTPasswordKeyEncryptor = null;
            cTPasswordKeyEncryptor = (CTPasswordKeyEncryptor)((Object)this.get_store().find_element_user(ENCRYPTEDPASSWORDKEY$0, 0));
            if (cTPasswordKeyEncryptor == null) {
                return null;
            }
            return cTPasswordKeyEncryptor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEncryptedPasswordKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENCRYPTEDPASSWORDKEY$0) != 0;
        }
    }

    @Override
    public void setEncryptedPasswordKey(CTPasswordKeyEncryptor cTPasswordKeyEncryptor) {
        this.generatedSetterHelperImpl(cTPasswordKeyEncryptor, ENCRYPTEDPASSWORDKEY$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPasswordKeyEncryptor addNewEncryptedPasswordKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPasswordKeyEncryptor cTPasswordKeyEncryptor = null;
            cTPasswordKeyEncryptor = (CTPasswordKeyEncryptor)((Object)this.get_store().add_element_user(ENCRYPTEDPASSWORDKEY$0));
            return cTPasswordKeyEncryptor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEncryptedPasswordKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENCRYPTEDPASSWORDKEY$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCertificateKeyEncryptor getEncryptedCertificateKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCertificateKeyEncryptor cTCertificateKeyEncryptor = null;
            cTCertificateKeyEncryptor = (CTCertificateKeyEncryptor)((Object)this.get_store().find_element_user(ENCRYPTEDCERTIFICATEKEY$2, 0));
            if (cTCertificateKeyEncryptor == null) {
                return null;
            }
            return cTCertificateKeyEncryptor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEncryptedCertificateKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ENCRYPTEDCERTIFICATEKEY$2) != 0;
        }
    }

    @Override
    public void setEncryptedCertificateKey(CTCertificateKeyEncryptor cTCertificateKeyEncryptor) {
        this.generatedSetterHelperImpl(cTCertificateKeyEncryptor, ENCRYPTEDCERTIFICATEKEY$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCertificateKeyEncryptor addNewEncryptedCertificateKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCertificateKeyEncryptor cTCertificateKeyEncryptor = null;
            cTCertificateKeyEncryptor = (CTCertificateKeyEncryptor)((Object)this.get_store().add_element_user(ENCRYPTEDCERTIFICATEKEY$2));
            return cTCertificateKeyEncryptor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEncryptedCertificateKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ENCRYPTEDCERTIFICATEKEY$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKeyEncryptor.Uri.Enum getUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$4));
            if (simpleValue == null) {
                return null;
            }
            return (CTKeyEncryptor.Uri.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKeyEncryptor.Uri xgetUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKeyEncryptor.Uri uri = null;
            uri = (CTKeyEncryptor.Uri)((Object)this.get_store().find_attribute_user(URI$4));
            return uri;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUri() {
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
    public void setUri(CTKeyEncryptor.Uri.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(URI$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(URI$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetUri(CTKeyEncryptor.Uri uri) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKeyEncryptor.Uri uri2 = null;
            uri2 = (CTKeyEncryptor.Uri)((Object)this.get_store().find_attribute_user(URI$4));
            if (uri2 == null) {
                uri2 = (CTKeyEncryptor.Uri)((Object)this.get_store().add_attribute_user(URI$4));
            }
            uri2.set(uri);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUri() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(URI$4);
        }
    }

    public static class UriImpl
    extends JavaStringEnumerationHolderEx
    implements CTKeyEncryptor.Uri {
        private static final long serialVersionUID = 1L;

        public UriImpl(SchemaType schemaType) {
            super(schemaType, false);
        }

        protected UriImpl(SchemaType schemaType, boolean bl) {
            super(schemaType, bl);
        }
    }
}

