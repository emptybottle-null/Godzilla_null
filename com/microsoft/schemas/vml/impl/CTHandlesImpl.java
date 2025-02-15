/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.vml.impl;

import com.microsoft.schemas.vml.CTH;
import com.microsoft.schemas.vml.CTHandles;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTHandlesImpl
extends XmlComplexContentImpl
implements CTHandles {
    private static final long serialVersionUID = 1L;
    private static final QName H$0 = new QName("urn:schemas-microsoft-com:vml", "h");

    public CTHandlesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTH> getHList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class HList
            extends AbstractList<CTH> {
                HList() {
                }

                @Override
                public CTH get(int n) {
                    return CTHandlesImpl.this.getHArray(n);
                }

                @Override
                public CTH set(int n, CTH cTH) {
                    CTH cTH2 = CTHandlesImpl.this.getHArray(n);
                    CTHandlesImpl.this.setHArray(n, cTH);
                    return cTH2;
                }

                @Override
                public void add(int n, CTH cTH) {
                    CTHandlesImpl.this.insertNewH(n).set(cTH);
                }

                @Override
                public CTH remove(int n) {
                    CTH cTH = CTHandlesImpl.this.getHArray(n);
                    CTHandlesImpl.this.removeH(n);
                    return cTH;
                }

                @Override
                public int size() {
                    return CTHandlesImpl.this.sizeOfHArray();
                }
            }
            return new HList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTH[] getHArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(H$0, arrayList);
            CTH[] cTHArray = new CTH[arrayList.size()];
            arrayList.toArray(cTHArray);
            return cTHArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTH getHArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTH cTH = null;
            cTH = (CTH)((Object)this.get_store().find_element_user(H$0, n));
            if (cTH == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTH;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfHArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(H$0);
        }
    }

    @Override
    public void setHArray(CTH[] cTHArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTHArray, H$0);
    }

    @Override
    public void setHArray(int n, CTH cTH) {
        this.generatedSetterHelperImpl(cTH, H$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTH insertNewH(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTH cTH = null;
            cTH = (CTH)((Object)this.get_store().insert_element_user(H$0, n));
            return cTH;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTH addNewH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTH cTH = null;
            cTH = (CTH)((Object)this.get_store().add_element_user(H$0));
            return cTH;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeH(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(H$0, n);
        }
    }
}

