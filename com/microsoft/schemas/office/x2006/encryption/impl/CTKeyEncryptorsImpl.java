/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption.impl;

import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor;
import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptors;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTKeyEncryptorsImpl
extends XmlComplexContentImpl
implements CTKeyEncryptors {
    private static final long serialVersionUID = 1L;
    private static final QName KEYENCRYPTOR$0 = new QName("http://schemas.microsoft.com/office/2006/encryption", "keyEncryptor");

    public CTKeyEncryptorsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTKeyEncryptor> getKeyEncryptorList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class KeyEncryptorList
            extends AbstractList<CTKeyEncryptor> {
                KeyEncryptorList() {
                }

                @Override
                public CTKeyEncryptor get(int n) {
                    return CTKeyEncryptorsImpl.this.getKeyEncryptorArray(n);
                }

                @Override
                public CTKeyEncryptor set(int n, CTKeyEncryptor cTKeyEncryptor) {
                    CTKeyEncryptor cTKeyEncryptor2 = CTKeyEncryptorsImpl.this.getKeyEncryptorArray(n);
                    CTKeyEncryptorsImpl.this.setKeyEncryptorArray(n, cTKeyEncryptor);
                    return cTKeyEncryptor2;
                }

                @Override
                public void add(int n, CTKeyEncryptor cTKeyEncryptor) {
                    CTKeyEncryptorsImpl.this.insertNewKeyEncryptor(n).set(cTKeyEncryptor);
                }

                @Override
                public CTKeyEncryptor remove(int n) {
                    CTKeyEncryptor cTKeyEncryptor = CTKeyEncryptorsImpl.this.getKeyEncryptorArray(n);
                    CTKeyEncryptorsImpl.this.removeKeyEncryptor(n);
                    return cTKeyEncryptor;
                }

                @Override
                public int size() {
                    return CTKeyEncryptorsImpl.this.sizeOfKeyEncryptorArray();
                }
            }
            return new KeyEncryptorList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTKeyEncryptor[] getKeyEncryptorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(KEYENCRYPTOR$0, arrayList);
            CTKeyEncryptor[] cTKeyEncryptorArray = new CTKeyEncryptor[arrayList.size()];
            arrayList.toArray(cTKeyEncryptorArray);
            return cTKeyEncryptorArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKeyEncryptor getKeyEncryptorArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKeyEncryptor cTKeyEncryptor = null;
            cTKeyEncryptor = (CTKeyEncryptor)((Object)this.get_store().find_element_user(KEYENCRYPTOR$0, n));
            if (cTKeyEncryptor == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTKeyEncryptor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfKeyEncryptorArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(KEYENCRYPTOR$0);
        }
    }

    @Override
    public void setKeyEncryptorArray(CTKeyEncryptor[] cTKeyEncryptorArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTKeyEncryptorArray, KEYENCRYPTOR$0);
    }

    @Override
    public void setKeyEncryptorArray(int n, CTKeyEncryptor cTKeyEncryptor) {
        this.generatedSetterHelperImpl(cTKeyEncryptor, KEYENCRYPTOR$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKeyEncryptor insertNewKeyEncryptor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKeyEncryptor cTKeyEncryptor = null;
            cTKeyEncryptor = (CTKeyEncryptor)((Object)this.get_store().insert_element_user(KEYENCRYPTOR$0, n));
            return cTKeyEncryptor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTKeyEncryptor addNewKeyEncryptor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTKeyEncryptor cTKeyEncryptor = null;
            cTKeyEncryptor = (CTKeyEncryptor)((Object)this.get_store().add_element_user(KEYENCRYPTOR$0));
            return cTKeyEncryptor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeKeyEncryptor(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(KEYENCRYPTOR$0, n);
        }
    }
}

