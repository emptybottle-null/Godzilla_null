/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCells;

public class CTMergeCellsImpl
extends XmlComplexContentImpl
implements CTMergeCells {
    private static final long serialVersionUID = 1L;
    private static final QName MERGECELL$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "mergeCell");
    private static final QName COUNT$2 = new QName("", "count");

    public CTMergeCellsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTMergeCell> getMergeCellList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class MergeCellList
            extends AbstractList<CTMergeCell> {
                MergeCellList() {
                }

                @Override
                public CTMergeCell get(int n) {
                    return CTMergeCellsImpl.this.getMergeCellArray(n);
                }

                @Override
                public CTMergeCell set(int n, CTMergeCell cTMergeCell) {
                    CTMergeCell cTMergeCell2 = CTMergeCellsImpl.this.getMergeCellArray(n);
                    CTMergeCellsImpl.this.setMergeCellArray(n, cTMergeCell);
                    return cTMergeCell2;
                }

                @Override
                public void add(int n, CTMergeCell cTMergeCell) {
                    CTMergeCellsImpl.this.insertNewMergeCell(n).set(cTMergeCell);
                }

                @Override
                public CTMergeCell remove(int n) {
                    CTMergeCell cTMergeCell = CTMergeCellsImpl.this.getMergeCellArray(n);
                    CTMergeCellsImpl.this.removeMergeCell(n);
                    return cTMergeCell;
                }

                @Override
                public int size() {
                    return CTMergeCellsImpl.this.sizeOfMergeCellArray();
                }
            }
            return new MergeCellList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTMergeCell[] getMergeCellArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(MERGECELL$0, arrayList);
            CTMergeCell[] cTMergeCellArray = new CTMergeCell[arrayList.size()];
            arrayList.toArray(cTMergeCellArray);
            return cTMergeCellArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMergeCell getMergeCellArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMergeCell cTMergeCell = null;
            cTMergeCell = (CTMergeCell)((Object)this.get_store().find_element_user(MERGECELL$0, n));
            if (cTMergeCell == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTMergeCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfMergeCellArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MERGECELL$0);
        }
    }

    @Override
    public void setMergeCellArray(CTMergeCell[] cTMergeCellArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTMergeCellArray, MERGECELL$0);
    }

    @Override
    public void setMergeCellArray(int n, CTMergeCell cTMergeCell) {
        this.generatedSetterHelperImpl(cTMergeCell, MERGECELL$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMergeCell insertNewMergeCell(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMergeCell cTMergeCell = null;
            cTMergeCell = (CTMergeCell)((Object)this.get_store().insert_element_user(MERGECELL$0, n));
            return cTMergeCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMergeCell addNewMergeCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMergeCell cTMergeCell = null;
            cTMergeCell = (CTMergeCell)((Object)this.get_store().add_element_user(MERGECELL$0));
            return cTMergeCell;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeMergeCell(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MERGECELL$0, n);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$2));
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
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$2));
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
            return this.get_store().find_attribute_user(COUNT$2) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(COUNT$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(COUNT$2));
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
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(COUNT$2));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(COUNT$2));
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
            this.get_store().remove_attribute(COUNT$2);
        }
    }
}

