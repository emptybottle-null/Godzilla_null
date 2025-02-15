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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;

public class CTTextTabStopListImpl
extends XmlComplexContentImpl
implements CTTextTabStopList {
    private static final long serialVersionUID = 1L;
    private static final QName TAB$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tab");

    public CTTextTabStopListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTextTabStop> getTabList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TabList
            extends AbstractList<CTTextTabStop> {
                TabList() {
                }

                @Override
                public CTTextTabStop get(int n) {
                    return CTTextTabStopListImpl.this.getTabArray(n);
                }

                @Override
                public CTTextTabStop set(int n, CTTextTabStop cTTextTabStop) {
                    CTTextTabStop cTTextTabStop2 = CTTextTabStopListImpl.this.getTabArray(n);
                    CTTextTabStopListImpl.this.setTabArray(n, cTTextTabStop);
                    return cTTextTabStop2;
                }

                @Override
                public void add(int n, CTTextTabStop cTTextTabStop) {
                    CTTextTabStopListImpl.this.insertNewTab(n).set(cTTextTabStop);
                }

                @Override
                public CTTextTabStop remove(int n) {
                    CTTextTabStop cTTextTabStop = CTTextTabStopListImpl.this.getTabArray(n);
                    CTTextTabStopListImpl.this.removeTab(n);
                    return cTTextTabStop;
                }

                @Override
                public int size() {
                    return CTTextTabStopListImpl.this.sizeOfTabArray();
                }
            }
            return new TabList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTTextTabStop[] getTabArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TAB$0, arrayList);
            CTTextTabStop[] cTTextTabStopArray = new CTTextTabStop[arrayList.size()];
            arrayList.toArray(cTTextTabStopArray);
            return cTTextTabStopArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextTabStop getTabArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextTabStop cTTextTabStop = null;
            cTTextTabStop = (CTTextTabStop)((Object)this.get_store().find_element_user(TAB$0, n));
            if (cTTextTabStop == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTextTabStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfTabArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TAB$0);
        }
    }

    @Override
    public void setTabArray(CTTextTabStop[] cTTextTabStopArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTextTabStopArray, TAB$0);
    }

    @Override
    public void setTabArray(int n, CTTextTabStop cTTextTabStop) {
        this.generatedSetterHelperImpl(cTTextTabStop, TAB$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextTabStop insertNewTab(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextTabStop cTTextTabStop = null;
            cTTextTabStop = (CTTextTabStop)((Object)this.get_store().insert_element_user(TAB$0, n));
            return cTTextTabStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextTabStop addNewTab() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextTabStop cTTextTabStop = null;
            cTTextTabStop = (CTTextTabStop)((Object)this.get_store().add_element_user(TAB$0));
            return cTTextTabStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeTab(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TAB$0, n);
        }
    }
}

