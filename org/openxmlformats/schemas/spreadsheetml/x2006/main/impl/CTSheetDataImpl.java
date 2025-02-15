/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetData;

public class CTSheetDataImpl
extends XmlComplexContentImpl
implements CTSheetData {
    private static final long serialVersionUID = 1L;
    private static final QName ROW$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "row");

    public CTSheetDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTRow> getRowList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class RowList
            extends AbstractList<CTRow> {
                RowList() {
                }

                @Override
                public CTRow get(int n) {
                    return CTSheetDataImpl.this.getRowArray(n);
                }

                @Override
                public CTRow set(int n, CTRow cTRow) {
                    CTRow cTRow2 = CTSheetDataImpl.this.getRowArray(n);
                    CTSheetDataImpl.this.setRowArray(n, cTRow);
                    return cTRow2;
                }

                @Override
                public void add(int n, CTRow cTRow) {
                    CTSheetDataImpl.this.insertNewRow(n).set(cTRow);
                }

                @Override
                public CTRow remove(int n) {
                    CTRow cTRow = CTSheetDataImpl.this.getRowArray(n);
                    CTSheetDataImpl.this.removeRow(n);
                    return cTRow;
                }

                @Override
                public int size() {
                    return CTSheetDataImpl.this.sizeOfRowArray();
                }
            }
            return new RowList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTRow[] getRowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(ROW$0, arrayList);
            CTRow[] cTRowArray = new CTRow[arrayList.size()];
            arrayList.toArray(cTRowArray);
            return cTRowArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRow getRowArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRow cTRow = null;
            cTRow = (CTRow)((Object)this.get_store().find_element_user(ROW$0, n));
            if (cTRow == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTRow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfRowArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ROW$0);
        }
    }

    @Override
    public void setRowArray(CTRow[] cTRowArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTRowArray, ROW$0);
    }

    @Override
    public void setRowArray(int n, CTRow cTRow) {
        this.generatedSetterHelperImpl(cTRow, ROW$0, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRow insertNewRow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRow cTRow = null;
            cTRow = (CTRow)((Object)this.get_store().insert_element_user(ROW$0, n));
            return cTRow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRow addNewRow() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRow cTRow = null;
            cTRow = (CTRow)((Object)this.get_store().add_element_user(ROW$0));
            return cTRow;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeRow(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ROW$0, n);
        }
    }
}

