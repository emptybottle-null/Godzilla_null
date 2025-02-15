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
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdListEntry;

public class CTSlideMasterIdListImpl
extends XmlComplexContentImpl
implements CTSlideMasterIdList {
    private static final long serialVersionUID = 1L;
    private static final QName SLDMASTERID$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldMasterId");

    public CTSlideMasterIdListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTSlideMasterIdListEntry> getSldMasterIdList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class SldMasterIdList
            extends AbstractList<CTSlideMasterIdListEntry> {
                SldMasterIdList() {
                }

                @Override
                public CTSlideMasterIdListEntry get(int n) {
                    return CTSlideMasterIdListImpl.this.getSldMasterIdArray(n);
                }

                @Override
                public CTSlideMasterIdListEntry set(int n, CTSlideMasterIdListEntry cTSlideMasterIdListEntry) {
                    CTSlideMasterIdListEntry cTSlideMasterIdListEntry2 = CTSlideMasterIdListImpl.this.getSldMasterIdArray(n);
                    CTSlideMasterIdListImpl.this.setSldMasterIdArray(n, cTSlideMasterIdListEntry);
                    return cTSlideMasterIdListEntry2;
                }

                @Override
                public void add(int n, CTSlideMasterIdListEntry cTSlideMasterIdListEntry) {
                    CTSlideMasterIdListImpl.this.insertNewSldMasterId(n).set(cTSlideMasterIdListEntry);
                }

                @Override
                public CTSlideMasterIdListEntry remove(int n) {
                    CTSlideMasterIdListEntry cTSlideMasterIdListEntry = CTSlideMasterIdListImpl.this.getSldMasterIdArray(n);
                    CTSlideMasterIdListImpl.this.removeSldMasterId(n);
                    return cTSlideMasterIdListEntry;
                }

                @Override
                public int size() {
                    return CTSlideMasterIdListImpl.this.sizeOfSldMasterIdArray();
                }
            }
            return new SldMasterIdList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTSlideMasterIdListEntry[] getSldMasterIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(SLDMASTERID$0, arrayList);
            CTSlideMasterIdListEntry[] cTSlideMasterIdListEntryArray = new CTSlideMasterIdListEntry[arrayList.size()];
            arrayList.toArray(cTSlideMasterIdListEntryArray);
            return cTSlideMasterIdListEntryArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideMasterIdListEntry getSldMasterIdArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideMasterIdListEntry cTSlideMasterIdListEntry = null;
            cTSlideMasterIdListEntry = (CTSlideMasterIdListEntry)((Object)this.get_store().find_element_user(SLDMASTERID$0, n));
            if (cTSlideMasterIdListEntry == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTSlideMasterIdListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfSldMasterIdArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SLDMASTERID$0);
        }
    }

    @Override
    public void setSldMasterIdArray(CTSlideMasterIdListEntry[] cTSlideMasterIdListEntryArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTSlideMasterIdListEntryArray, SLDMASTERID$0);
    }

    @Override
    public void setSldMasterIdArray(int n, CTSlideMasterIdListEntry cTSlideMasterIdListEntry) {
        this.generatedSetterHelperImpl(cTSlideMasterIdListEntry, SLDMASTERID$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideMasterIdListEntry insertNewSldMasterId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideMasterIdListEntry cTSlideMasterIdListEntry = null;
            cTSlideMasterIdListEntry = (CTSlideMasterIdListEntry)((Object)this.get_store().insert_element_user(SLDMASTERID$0, n));
            return cTSlideMasterIdListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideMasterIdListEntry addNewSldMasterId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideMasterIdListEntry cTSlideMasterIdListEntry = null;
            cTSlideMasterIdListEntry = (CTSlideMasterIdListEntry)((Object)this.get_store().add_element_user(SLDMASTERID$0));
            return cTSlideMasterIdListEntry;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeSldMasterId(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SLDMASTERID$0, n);
        }
    }
}

