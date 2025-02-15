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
import org.etsi.uri.x01903.v13.CRLRefType;
import org.etsi.uri.x01903.v13.CRLRefsType;

public class CRLRefsTypeImpl
extends XmlComplexContentImpl
implements CRLRefsType {
    private static final long serialVersionUID = 1L;
    private static final QName CRLREF$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "CRLRef");

    public CRLRefsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CRLRefType> getCRLRefList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CRLRefList
            extends AbstractList<CRLRefType> {
                CRLRefList() {
                }

                @Override
                public CRLRefType get(int n) {
                    return CRLRefsTypeImpl.this.getCRLRefArray(n);
                }

                @Override
                public CRLRefType set(int n, CRLRefType cRLRefType) {
                    CRLRefType cRLRefType2 = CRLRefsTypeImpl.this.getCRLRefArray(n);
                    CRLRefsTypeImpl.this.setCRLRefArray(n, cRLRefType);
                    return cRLRefType2;
                }

                @Override
                public void add(int n, CRLRefType cRLRefType) {
                    CRLRefsTypeImpl.this.insertNewCRLRef(n).set(cRLRefType);
                }

                @Override
                public CRLRefType remove(int n) {
                    CRLRefType cRLRefType = CRLRefsTypeImpl.this.getCRLRefArray(n);
                    CRLRefsTypeImpl.this.removeCRLRef(n);
                    return cRLRefType;
                }

                @Override
                public int size() {
                    return CRLRefsTypeImpl.this.sizeOfCRLRefArray();
                }
            }
            return new CRLRefList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CRLRefType[] getCRLRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CRLREF$0, arrayList);
            CRLRefType[] cRLRefTypeArray = new CRLRefType[arrayList.size()];
            arrayList.toArray(cRLRefTypeArray);
            return cRLRefTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CRLRefType getCRLRefArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CRLRefType cRLRefType = null;
            cRLRefType = (CRLRefType)((Object)this.get_store().find_element_user(CRLREF$0, n));
            if (cRLRefType == null) {
                throw new IndexOutOfBoundsException();
            }
            return cRLRefType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCRLRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CRLREF$0);
        }
    }

    @Override
    public void setCRLRefArray(CRLRefType[] cRLRefTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(cRLRefTypeArray, CRLREF$0);
    }

    @Override
    public void setCRLRefArray(int n, CRLRefType cRLRefType) {
        this.generatedSetterHelperImpl(cRLRefType, CRLREF$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CRLRefType insertNewCRLRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CRLRefType cRLRefType = null;
            cRLRefType = (CRLRefType)((Object)this.get_store().insert_element_user(CRLREF$0, n));
            return cRLRefType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CRLRefType addNewCRLRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CRLRefType cRLRefType = null;
            cRLRefType = (CRLRefType)((Object)this.get_store().add_element_user(CRLREF$0));
            return cRLRefType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCRLRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CRLREF$0, n);
        }
    }
}

