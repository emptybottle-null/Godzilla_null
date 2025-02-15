/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRecord
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheRecords;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRecord;

public class CTPivotCacheRecordsImpl
extends XmlComplexContentImpl
implements CTPivotCacheRecords {
    private static final long serialVersionUID = 1L;
    private static final QName R$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "r");
    private static final QName EXTLST$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");
    private static final QName COUNT$4 = new QName("", "count");

    public CTPivotCacheRecordsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRecord> getRList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RList
            extends AbstractList<CTRecord> {
                RList() {
                }

                @Override
                public CTRecord get(int n) {
                    return CTPivotCacheRecordsImpl.this.getRArray(n);
                }

                @Override
                public CTRecord set(int n, CTRecord cTRecord) {
                    CTRecord cTRecord2 = CTPivotCacheRecordsImpl.this.getRArray(n);
                    CTPivotCacheRecordsImpl.this.setRArray(n, cTRecord);
                    return cTRecord2;
                }

                @Override
                public void add(int n, CTRecord cTRecord) {
                    CTPivotCacheRecordsImpl.this.insertNewR(n).set((XmlObject)cTRecord);
                }

                @Override
                public CTRecord remove(int n) {
                    CTRecord cTRecord = CTPivotCacheRecordsImpl.this.getRArray(n);
                    CTPivotCacheRecordsImpl.this.removeR(n);
                    return cTRecord;
                }

                @Override
                public int size() {
                    return CTPivotCacheRecordsImpl.this.sizeOfRArray();
                }
            }
            return new RList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRecord[] getRArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(R$0, arrayList);
            CTRecord[] cTRecordArray = new CTRecord[arrayList.size()];
            arrayList.toArray(cTRecordArray);
            return cTRecordArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRecord getRArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRecord cTRecord = null;
            cTRecord = (CTRecord)this.get_store().find_element_user(R$0, n);
            if (cTRecord == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRecord;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(R$0);
        }
    }

    @Override
    public void setRArray(CTRecord[] cTRecordArray) {
        this.check_orphaned();
        this.arraySetterHelper((XmlObject[])cTRecordArray, R$0);
    }

    @Override
    public void setRArray(int n, CTRecord cTRecord) {
        this.generatedSetterHelperImpl((XmlObject)cTRecord, R$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRecord insertNewR(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRecord cTRecord = null;
            cTRecord = (CTRecord)this.get_store().insert_element_user(R$0, n);
            return cTRecord;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRecord addNewR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRecord cTRecord = null;
            cTRecord = (CTRecord)this.get_store().add_element_user(R$0);
            return cTRecord;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeR(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(R$0, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$2, 0);
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$2) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$2);
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$4));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlUnsignedInt xgetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$4));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(COUNT$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCount(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COUNT$4));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCount(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$4));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(COUNT$4));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCount() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(COUNT$4);
        }
    }
}

