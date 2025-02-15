/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStopList;

public class CTDashStopListImpl
extends XmlComplexContentImpl
implements CTDashStopList {
    private static final long serialVersionUID = 1L;
    private static final QName DS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "ds");

    public CTDashStopListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTDashStop> getDsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class DsList
            extends AbstractList<CTDashStop> {
                DsList() {
                }

                @Override
                public CTDashStop get(int n) {
                    return CTDashStopListImpl.this.getDsArray(n);
                }

                @Override
                public CTDashStop set(int n, CTDashStop cTDashStop) {
                    CTDashStop cTDashStop2 = CTDashStopListImpl.this.getDsArray(n);
                    CTDashStopListImpl.this.setDsArray(n, cTDashStop);
                    return cTDashStop2;
                }

                @Override
                public void add(int n, CTDashStop cTDashStop) {
                    CTDashStopListImpl.this.insertNewDs(n).set(cTDashStop);
                }

                @Override
                public CTDashStop remove(int n) {
                    CTDashStop cTDashStop = CTDashStopListImpl.this.getDsArray(n);
                    CTDashStopListImpl.this.removeDs(n);
                    return cTDashStop;
                }

                @Override
                public int size() {
                    return CTDashStopListImpl.this.sizeOfDsArray();
                }
            }
            return new DsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTDashStop[] getDsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(DS$0, arrayList);
            CTDashStop[] cTDashStopArray = new CTDashStop[arrayList.size()];
            arrayList.toArray(cTDashStopArray);
            return cTDashStopArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDashStop getDsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDashStop cTDashStop = null;
            cTDashStop = (CTDashStop)((Object)this.get_store().find_element_user(DS$0, n));
            if (cTDashStop == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTDashStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfDsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DS$0);
        }
    }

    @Override
    public void setDsArray(CTDashStop[] cTDashStopArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTDashStopArray, DS$0);
    }

    @Override
    public void setDsArray(int n, CTDashStop cTDashStop) {
        this.generatedSetterHelperImpl(cTDashStop, DS$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDashStop insertNewDs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDashStop cTDashStop = null;
            cTDashStop = (CTDashStop)((Object)this.get_store().insert_element_user(DS$0, n));
            return cTDashStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDashStop addNewDs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDashStop cTDashStop = null;
            cTDashStop = (CTDashStop)((Object)this.get_store().add_element_user(DS$0));
            return cTDashStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeDs(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DS$0, n);
        }
    }
}

