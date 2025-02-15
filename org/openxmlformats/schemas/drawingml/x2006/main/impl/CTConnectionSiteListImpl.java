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
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList;

public class CTConnectionSiteListImpl
extends XmlComplexContentImpl
implements CTConnectionSiteList {
    private static final long serialVersionUID = 1L;
    private static final QName CXN$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "cxn");

    public CTConnectionSiteListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTConnectionSite> getCxnList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class CxnList
            extends AbstractList<CTConnectionSite> {
                CxnList() {
                }

                @Override
                public CTConnectionSite get(int n) {
                    return CTConnectionSiteListImpl.this.getCxnArray(n);
                }

                @Override
                public CTConnectionSite set(int n, CTConnectionSite cTConnectionSite) {
                    CTConnectionSite cTConnectionSite2 = CTConnectionSiteListImpl.this.getCxnArray(n);
                    CTConnectionSiteListImpl.this.setCxnArray(n, cTConnectionSite);
                    return cTConnectionSite2;
                }

                @Override
                public void add(int n, CTConnectionSite cTConnectionSite) {
                    CTConnectionSiteListImpl.this.insertNewCxn(n).set(cTConnectionSite);
                }

                @Override
                public CTConnectionSite remove(int n) {
                    CTConnectionSite cTConnectionSite = CTConnectionSiteListImpl.this.getCxnArray(n);
                    CTConnectionSiteListImpl.this.removeCxn(n);
                    return cTConnectionSite;
                }

                @Override
                public int size() {
                    return CTConnectionSiteListImpl.this.sizeOfCxnArray();
                }
            }
            return new CxnList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTConnectionSite[] getCxnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CXN$0, arrayList);
            CTConnectionSite[] cTConnectionSiteArray = new CTConnectionSite[arrayList.size()];
            arrayList.toArray(cTConnectionSiteArray);
            return cTConnectionSiteArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnectionSite getCxnArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnectionSite cTConnectionSite = null;
            cTConnectionSite = (CTConnectionSite)((Object)this.get_store().find_element_user(CXN$0, n));
            if (cTConnectionSite == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTConnectionSite;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfCxnArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CXN$0);
        }
    }

    @Override
    public void setCxnArray(CTConnectionSite[] cTConnectionSiteArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTConnectionSiteArray, CXN$0);
    }

    @Override
    public void setCxnArray(int n, CTConnectionSite cTConnectionSite) {
        this.generatedSetterHelperImpl(cTConnectionSite, CXN$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnectionSite insertNewCxn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnectionSite cTConnectionSite = null;
            cTConnectionSite = (CTConnectionSite)((Object)this.get_store().insert_element_user(CXN$0, n));
            return cTConnectionSite;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConnectionSite addNewCxn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConnectionSite cTConnectionSite = null;
            cTConnectionSite = (CTConnectionSite)((Object)this.get_store().add_element_user(CXN$0));
            return cTConnectionSite;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCxn(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CXN$0, n);
        }
    }
}

