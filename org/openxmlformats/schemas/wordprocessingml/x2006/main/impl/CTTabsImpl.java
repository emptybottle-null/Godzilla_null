/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;

public class CTTabsImpl
extends XmlComplexContentImpl
implements CTTabs {
    private static final long serialVersionUID = 1L;
    private static final QName TAB$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tab");

    public CTTabsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTTabStop> getTabList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class TabList
            extends AbstractList<CTTabStop> {
                TabList() {
                }

                @Override
                public CTTabStop get(int n) {
                    return CTTabsImpl.this.getTabArray(n);
                }

                @Override
                public CTTabStop set(int n, CTTabStop cTTabStop) {
                    CTTabStop cTTabStop2 = CTTabsImpl.this.getTabArray(n);
                    CTTabsImpl.this.setTabArray(n, cTTabStop);
                    return cTTabStop2;
                }

                @Override
                public void add(int n, CTTabStop cTTabStop) {
                    CTTabsImpl.this.insertNewTab(n).set(cTTabStop);
                }

                @Override
                public CTTabStop remove(int n) {
                    CTTabStop cTTabStop = CTTabsImpl.this.getTabArray(n);
                    CTTabsImpl.this.removeTab(n);
                    return cTTabStop;
                }

                @Override
                public int size() {
                    return CTTabsImpl.this.sizeOfTabArray();
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
    public CTTabStop[] getTabArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(TAB$0, arrayList);
            CTTabStop[] cTTabStopArray = new CTTabStop[arrayList.size()];
            arrayList.toArray(cTTabStopArray);
            return cTTabStopArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTabStop getTabArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTabStop cTTabStop = null;
            cTTabStop = (CTTabStop)((Object)this.get_store().find_element_user(TAB$0, n));
            if (cTTabStop == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTTabStop;
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
    public void setTabArray(CTTabStop[] cTTabStopArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTTabStopArray, TAB$0);
    }

    @Override
    public void setTabArray(int n, CTTabStop cTTabStop) {
        this.generatedSetterHelperImpl(cTTabStop, TAB$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTabStop insertNewTab(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTabStop cTTabStop = null;
            cTTabStop = (CTTabStop)((Object)this.get_store().insert_element_user(TAB$0, n));
            return cTTabStop;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTabStop addNewTab() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTabStop cTTabStop = null;
            cTTabStop = (CTTabStop)((Object)this.get_store().add_element_user(TAB$0));
            return cTTabStop;
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

