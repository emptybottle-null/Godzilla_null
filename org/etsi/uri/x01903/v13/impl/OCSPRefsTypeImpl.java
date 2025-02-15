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
import org.etsi.uri.x01903.v13.OCSPRefType;
import org.etsi.uri.x01903.v13.OCSPRefsType;

public class OCSPRefsTypeImpl
extends XmlComplexContentImpl
implements OCSPRefsType {
    private static final long serialVersionUID = 1L;
    private static final QName OCSPREF$0 = new QName("http://uri.etsi.org/01903/v1.3.2#", "OCSPRef");

    public OCSPRefsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<OCSPRefType> getOCSPRefList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class OCSPRefList
            extends AbstractList<OCSPRefType> {
                OCSPRefList() {
                }

                @Override
                public OCSPRefType get(int n) {
                    return OCSPRefsTypeImpl.this.getOCSPRefArray(n);
                }

                @Override
                public OCSPRefType set(int n, OCSPRefType oCSPRefType) {
                    OCSPRefType oCSPRefType2 = OCSPRefsTypeImpl.this.getOCSPRefArray(n);
                    OCSPRefsTypeImpl.this.setOCSPRefArray(n, oCSPRefType);
                    return oCSPRefType2;
                }

                @Override
                public void add(int n, OCSPRefType oCSPRefType) {
                    OCSPRefsTypeImpl.this.insertNewOCSPRef(n).set(oCSPRefType);
                }

                @Override
                public OCSPRefType remove(int n) {
                    OCSPRefType oCSPRefType = OCSPRefsTypeImpl.this.getOCSPRefArray(n);
                    OCSPRefsTypeImpl.this.removeOCSPRef(n);
                    return oCSPRefType;
                }

                @Override
                public int size() {
                    return OCSPRefsTypeImpl.this.sizeOfOCSPRefArray();
                }
            }
            return new OCSPRefList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public OCSPRefType[] getOCSPRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(OCSPREF$0, arrayList);
            OCSPRefType[] oCSPRefTypeArray = new OCSPRefType[arrayList.size()];
            arrayList.toArray(oCSPRefTypeArray);
            return oCSPRefTypeArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OCSPRefType getOCSPRefArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OCSPRefType oCSPRefType = null;
            oCSPRefType = (OCSPRefType)((Object)this.get_store().find_element_user(OCSPREF$0, n));
            if (oCSPRefType == null) {
                throw new IndexOutOfBoundsException();
            }
            return oCSPRefType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfOCSPRefArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OCSPREF$0);
        }
    }

    @Override
    public void setOCSPRefArray(OCSPRefType[] oCSPRefTypeArray) {
        this.check_orphaned();
        this.arraySetterHelper(oCSPRefTypeArray, OCSPREF$0);
    }

    @Override
    public void setOCSPRefArray(int n, OCSPRefType oCSPRefType) {
        this.generatedSetterHelperImpl(oCSPRefType, OCSPREF$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OCSPRefType insertNewOCSPRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OCSPRefType oCSPRefType = null;
            oCSPRefType = (OCSPRefType)((Object)this.get_store().insert_element_user(OCSPREF$0, n));
            return oCSPRefType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public OCSPRefType addNewOCSPRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            OCSPRefType oCSPRefType = null;
            oCSPRefType = (OCSPRefType)((Object)this.get_store().add_element_user(OCSPREF$0));
            return oCSPRefType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeOCSPRef(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OCSPREF$0, n);
        }
    }
}

