/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry;

public class CTSlideIdListImpl
extends XmlComplexContentImpl
implements CTSlideIdList {
    private static final long serialVersionUID = 1L;
    private static final QName SLDID$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldId");

    public CTSlideIdListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSlideIdListEntry> getSldIdList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SldIdList
            extends AbstractList<CTSlideIdListEntry> {
                SldIdList() {
                }

                @Override
                public CTSlideIdListEntry get(int n) {
                    return CTSlideIdListImpl.this.getSldIdArray(n);
                }

                @Override
                public CTSlideIdListEntry set(int n, CTSlideIdListEntry cTSlideIdListEntry) {
                    CTSlideIdListEntry cTSlideIdListEntry2 = CTSlideIdListImpl.this.getSldIdArray(n);
                    CTSlideIdListImpl.this.setSldIdArray(n, cTSlideIdListEntry);
                    return cTSlideIdListEntry2;
                }

                @Override
                public void add(int n, CTSlideIdListEntry cTSlideIdListEntry) {
                    CTSlideIdListImpl.this.insertNewSldId(n).set(cTSlideIdListEntry);
                }

                @Override
                public CTSlideIdListEntry remove(int n) {
                    CTSlideIdListEntry cTSlideIdListEntry = CTSlideIdListImpl.this.getSldIdArray(n);
                    CTSlideIdListImpl.this.removeSldId(n);
                    return cTSlideIdListEntry;
                }

                @Override
                public int size() {
                    return CTSlideIdListImpl.this.sizeOfSldIdArray();
                }
            }
            return new SldIdList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSlideIdListEntry[] getSldIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SLDID$0, arrayList);
            CTSlideIdListEntry[] cTSlideIdListEntryArray = new CTSlideIdListEntry[arrayList.size()];
            arrayList.toArray(cTSlideIdListEntryArray);
            return cTSlideIdListEntryArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideIdListEntry getSldIdArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideIdListEntry cTSlideIdListEntry = null;
            cTSlideIdListEntry = (CTSlideIdListEntry)((Object)this.get_store().find_element_user(SLDID$0, n));
            if (cTSlideIdListEntry == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSlideIdListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSldIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SLDID$0);
        }
    }

    @Override
    public void setSldIdArray(CTSlideIdListEntry[] cTSlideIdListEntryArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSlideIdListEntryArray, SLDID$0);
    }

    @Override
    public void setSldIdArray(int n, CTSlideIdListEntry cTSlideIdListEntry) {
        this.generatedSetterHelperImpl(cTSlideIdListEntry, SLDID$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideIdListEntry insertNewSldId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideIdListEntry cTSlideIdListEntry = null;
            cTSlideIdListEntry = (CTSlideIdListEntry)((Object)this.get_store().insert_element_user(SLDID$0, n));
            return cTSlideIdListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideIdListEntry addNewSldId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideIdListEntry cTSlideIdListEntry = null;
            cTSlideIdListEntry = (CTSlideIdListEntry)((Object)this.get_store().add_element_user(SLDID$0));
            return cTSlideIdListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSldId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SLDID$0, n);
        }
    }
}

