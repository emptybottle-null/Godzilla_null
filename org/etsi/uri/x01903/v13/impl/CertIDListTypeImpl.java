/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.CertIDType;

public class CertIDListTypeImpl
extends XmlComplexContentImpl
implements CertIDListType {
    private static final long serialVersionUID = 1L;
    private static final QName CERT$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "Cert");

    public CertIDListTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CertIDType> getCertList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CertList
            extends AbstractList<CertIDType> {
                CertList() {
                }

                @Override
                public CertIDType get(int n) {
                    return CertIDListTypeImpl.this.getCertArray(n);
                }

                @Override
                public CertIDType set(int n, CertIDType certIDType) {
                    CertIDType certIDType2 = CertIDListTypeImpl.this.getCertArray(n);
                    CertIDListTypeImpl.this.setCertArray(n, certIDType);
                    return certIDType2;
                }

                @Override
                public void add(int n, CertIDType certIDType) {
                    CertIDListTypeImpl.this.insertNewCert(n).set(certIDType);
                }

                @Override
                public CertIDType remove(int n) {
                    CertIDType certIDType = CertIDListTypeImpl.this.getCertArray(n);
                    CertIDListTypeImpl.this.removeCert(n);
                    return certIDType;
                }

                @Override
                public int size() {
                    return CertIDListTypeImpl.this.sizeOfCertArray();
                }
            }
            return new CertList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CertIDType[] getCertArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CERT$0, arrayList);
            CertIDType[] certIDTypeArray = new CertIDType[arrayList.size()];
            arrayList.toArray(certIDTypeArray);
            return certIDTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertIDType getCertArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertIDType certIDType = null;
            certIDType = (CertIDType)((Object)this.get_store().find_element_user(CERT$0, n));
            if (certIDType == null) {
                throw new IndexOutOfBoundsException();
            }
            return certIDType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCertArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CERT$0);
        }
    }

    @Override
    public void setCertArray(CertIDType[] certIDTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(certIDTypeArray, CERT$0);
    }

    @Override
    public void setCertArray(int n, CertIDType certIDType) {
        this.generatedSetterHelperImpl(certIDType, CERT$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertIDType insertNewCert(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertIDType certIDType = null;
            certIDType = (CertIDType)((Object)this.get_store().insert_element_user(CERT$0, n));
            return certIDType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CertIDType addNewCert() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CertIDType certIDType = null;
            certIDType = (CertIDType)((Object)this.get_store().add_element_user(CERT$0));
            return certIDType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCert(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CERT$0, n);
        }
    }
}

